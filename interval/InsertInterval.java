package interval;

import java.util.ArrayList;
//http://jixiangsanbao.wordpress.com/2014/07/17/insert-interval/
public class InsertInterval {
	
	public static void main(String[] args) {
		InsertInterval ii=new InsertInterval();
		Interval i1=new Interval(1,5);
		Interval i2=new Interval(2,7);
//		Interval i3=new Interval(7,15);
		ArrayList<Interval> in=new ArrayList<Interval>();
		in.add(i1);
		ii.insert(in, i2);
	}

	public ArrayList<Interval> insert(ArrayList<Interval> intervals,Interval newInterval) {
		ArrayList<Interval> al = new ArrayList<Interval>();
		if(intervals.size()==0){
			al.add(newInterval);
			return al;
		}
		int i=0;
		for( i=0;i<intervals.size();i++){
			if(newInterval.start<=intervals.get(i).start){
				intervals.add(i,newInterval);
				break;
			}
		}
		if(i>=intervals.size()){//ע����������©����������newInterval��start���������ȫ������
                                //�������forѭ���Ͳ�������ˣ�Ҫ�������			
			intervals.add(newInterval);
		}
		//�����merger intervals��ȫһ��
		al.add(intervals.get(0));
		for( i=1;i<intervals.size();i++){
			if(intervals.get(i).start<=al.get(al.size()-1).end){
				al.get(al.size()-1).end=Math.max(intervals.get(i).end, al.get(al.size()-1).end);
			}else{
				al.add(intervals.get(i));
			}
		}
		return al;

	}

}
