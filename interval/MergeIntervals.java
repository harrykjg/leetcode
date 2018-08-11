package interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//http://blog.csdn.net/linhuanmars/article/details/21857617
//http://jixiangsanbao.wordpress.com/2014/07/14/merge-intervals/
//
public class MergeIntervals {
	public static void main(String[] args) {
		MergeIntervals mi=new MergeIntervals();
		Interval i1=new Interval(1,10);
		Interval i2=new Interval(2,7);
		Interval i3=new Interval(7,15);
		List<Interval> ls=new ArrayList<Interval>();
		ls.add(i2);
		ls.add(i1);
		ls.add(i3);
		mi.merge(ls);
	}
	
	public List<Interval> merge(List<Interval> intervals) {
		ArrayList<Interval> al=new ArrayList<Interval>();
		if(intervals.size()==0){
			return al;
		}
		InteComparator com=new InteComparator();//ע��������û�з��͵�
		Collections.sort(intervals,com);
		al.add(intervals.get(0));
		//��Ҫ����д��comparator��Ȼ��˼·���ǣ������ǰ��start�Ƚ����������һ����endС��
		//�����ص�����ô�Ͱѵ�ǰ���������������Ǹ�����interval��end�������������д���Ǹ�
		//���ص��Ļ��ͼӽ�al��
		for(int i=1;i<intervals.size();i++){
			if(intervals.get(i).start<=al.get(al.size()-1).end){
				al.get(al.size()-1).end=Math.max(intervals.get(i).end, al.get(al.size()-1).end);
			}else{
				al.add(intervals.get(i));
			}
		}
		return al;

	}
}

class InteComparator implements Comparator<Interval>{//ע�������һ��InteComparator��û��<Interval>
	                                             //����еĻ����������������i1��start��end����
	                                           //����code ganker������д���ֲ��ᣬΪɶ��
	public int compare(Interval i1,Interval i2){    //�ڶ���Comparator��������ӷ��͵Ļ�����
		if(i1.start==i2.start){                    //�ͽ�����дһ��comepare�����������ǲ���
			return i1.end-i2.end;               //������object
		}else{
			return i1.start-i2.start;
		}	
	}
}
