package interval;

import java.util.*;

//http://jixiangsanbao.wordpress.com/2014/07/17/insert-interval/
public class InsertInterval {
	
	public static void main(String[] args) {
		InsertInterval ii=new InsertInterval();
//		Interval i1=new Interval(1,5);
//		Interval i2=new Interval(2,7);
////		Interval i3=new Interval(7,15);
//		ArrayList<Interval> in=new ArrayList<Interval>();
//		in.add(i1);
//		ii.insert(in, i2);
		int[][] interval={{1,3},{6,9}};
		int[] newint={2,5};
		ii.insert2(interval,newint);
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
		if(i>=intervals.size()){//×¢ÒâÕâÀïÈÝÒ×Â©µô£¬Èç¹ûÕâ¸önewIntervalµÄstart±ÈÊý×éÀïµÄÈ«²¿¶¼´ó
                                //ÔòÉÏÃæµÄforÑ­»·¾Í²»»á²åÈëÁË£¬ÒªÕâÀï¼ÓÉÏ			
			intervals.add(newInterval);
		}
		//ÏÂÃæºÍmerger intervalsÍêÈ«Ò»Ñù
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
//6/24/2021这次想的是用priorityQ 也行但是其实不需要，就是感觉代码量比较大，而且他题目把Interval这个class取消了用二维数组导致后面要先用arraylist装再变成二维数组
	//以前的写法也行
	public int[][] insert2(int[][] intervals, int[] newInterval) {
		PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0]==o2[0]){
					return o1[1]-o2[1];
				}
				return o1[0]-o2[0];
			}
		}) ;
		for (int[] a:intervals){
			pq.offer(a);
		}
		List<int[]> al=new ArrayList<>();
		boolean inserted=false;
		while (!pq.isEmpty()){//思路是遍历input，然后每个interval和new interval相交的，则把new interval扩大，被new interval包含的则略过poll出来，
			int[] cur=pq.peek(); //比new 小的直接poll出来加入结果集。然后找到了第一个start比new的end大的，则说明完事了，pq剩下的额加入结果集
			if (cur[1]<newInterval[0]){//cur在newinterval之前
				al.add(pq.poll());
				continue;
			}
			if (newInterval[0]>=cur[0]&&newInterval[0]<=cur[1]){//
				pq.poll();
				newInterval[0]=Math.min(cur[0],newInterval[0]);
				newInterval[1]=Math.max(cur[1],newInterval[1]);
				continue;
			}
			if (cur[0]>newInterval[1]){//cur在newinteval之后
				pq.offer(newInterval);
				inserted=true;
				break;
			}
			//newinterval包含/半包含cur
			pq.poll();
			newInterval[0]=Math.min(cur[0],newInterval[0]);
			newInterval[1]=Math.max(cur[1],newInterval[1]);
		}
		if (!inserted){
			pq.offer(newInterval);
		}
		while (!pq.isEmpty()){
			al.add(pq.poll());
		}
		int[][] rs=new int[al.size()][2];

		for (int i=0;i<al.size();i++){
			rs[i]=al.get(i);
		}
		return rs;
	}

	//9/8/2021直接就写的很烂，题目说了是按start排序好的，不需要pq。看回以前的方法，就是先遍历intervals，找到合适的位置（只考虑start）插进去，然后再从
	//插进去的这点后面开始，把余下的原来的interval和前面的merge
	public int[][] insert3(int[][] intervals, int[] newInterval) {
		if (intervals.length==0){
			return new int[][]{{newInterval[0],newInterval[1]}};
		}
		int b=newInterval[0];
		int e=newInterval[1];
		List<int[]> ls=new LinkedList<>(Arrays.asList(intervals));//先把intervals变成list
		int i=0;
		for (;i<intervals.length;i++){//他这个恶心的是以前的input是list，可以直接在原list上插入新的interval，再弄个新的list去merge，现在只能再搞一个新的list，往里加
			if (intervals[i][0]>=b){//原来的和新的
				ls.add(i,newInterval);
				break;
			}
		}
		if (i==intervals.length){
			ls.add(newInterval);
		}
		List<int[]> al=new ArrayList<>();
		al.add(ls.get(0));
		i=1;
		while (i<ls.size()){
			int[] pre=al.get(al.size()-1);
			if (ls.get(i)[0]>=pre[0]&&ls.get(i)[0]<=pre[1]){
				pre[1]=Math.max(ls.get(i)[1],pre[1]);
			}else {
				al.add(ls.get(i));
			}
			i++;
		}


		int[][] rs=new int[al.size()][2];

		for (i=0;i<al.size();i++){
			rs[i]=al.get(i);
		}
		return rs;
	}

	//9/9/2021 这个是参考别人的代码 这个好
	public int[][] insert4(int[][] intervals, int[] ni) {
		int i=0;
		List<int[]> ls=new ArrayList<>();
		while (i<intervals.length){
			if (ni[0]>intervals[i][1]){//与ni没有交集，在前面的
				ls.add(intervals[i]);
				i++;
			}else {
				break;
			}
		}
		while (i<intervals.length){//到了这里的话说明是ni[0]<=intervals[i][1], 然后举例子看，当ni【1】小于intervals【i】【0】的时候说明没交集，反过来
			if (intervals[i][0]<=ni[1]){       //就是intervals[i][0]<=ni[1]，则有交集。则不断扩大new interval
				ni[0]=Math.min(intervals[i][0],ni[0]);
				ni[1]=Math.max(intervals[i][1],ni[1]);
				i++;
			}else {
				break;
			}
		}
		ls.add(ni);
		while (i<intervals.length){//把剩下的加上
			ls.add(intervals[i]);
		}
		int[][] rs=new int[ls.size()][2];

		for (i=0;i<ls.size();i++){
			rs[i]=ls.get(i);
		}
		return rs;
	}

}
class Interval {
	int start;
	int end;

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}
