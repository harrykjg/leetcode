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
		InteComparator com=new InteComparator();//注意这里是没有泛型的
		Collections.sort(intervals,com);
		al.add(intervals.get(0));
		//主要就是写个comparator，然后思路就是，如果当前的start比结果集里的最后一个的end小，
		//则是重叠，那么就把当前融入结果集里的最后那个，该interval的end就是他们两者中大的那个
		//不重叠的话就加进al里
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

class InteComparator implements Comparator<Interval>{//注意这里第一个InteComparator那没有<Interval>
	                                             //如果有的话，他会屏蔽里面的i1的start和end参数
	                                           //但是code ganker那样的写法又不会，为啥？
	public int compare(Interval i1,Interval i2){    //第二个Comparator这如果不加泛型的话，他
		if(i1.start==i2.start){                    //就叫你重写一个comepare方法，里面是参数
			return i1.end-i2.end;               //是两个object
		}else{
			return i1.start-i2.start;
		}	
	}
}
