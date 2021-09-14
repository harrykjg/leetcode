package sweepLine;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by 502575560 on 7/29/17.
 */
//leetcode 没有
public class NumberofAirplanesintheSky {
    //http://blog.csdn.net/feliciafay/article/details/49061801
    //http://www.cnblogs.com/beiyeqingteng/p/5690066.html  //排序的算法神奇,不好想到
    //http://www.jiuzhang.com/solutions/number-of-airplanes-in-the-sky/ //排序的
    public int countOfAirplanes(List<Interval> airplanes) {
        HashMap <Integer,Integer> map=new HashMap<>();
        int rs=0;
        for(Interval it:airplanes){
            int i=it.start;
            for(;i<it.end;i++){
                if(!map.containsKey(i)){
                    map.put(i,1);
                    rs=Math.max(1,rs);
                }else {
                    rs=Math.max(rs,map.get(i)+1);
                    map.put(i,map.get(i)+1);
                }
            }
        }
        return rs;
    }

    //6/18/2021,就是开始Line的起飞或降落的flag设成是boolean了，那样写comparator的话容易出问题，改成int的flag就对了
    public int countOfAirplanes2(List<Interval> airplanes) {
        PriorityQueue<Line> pq=new PriorityQueue<>(new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                if (o1.val==o2.val){
                    return o1.isStart-o2.isStart;//降落飞机先出，开始写成o1。isStart的话return -1，就错了
                }
                return o1.val-o2.val;
            }
        });
        for (Interval in:airplanes){
            Line l1=new Line(in.start,1);
            Line l2=new Line(in.end,0);
            pq.offer(l1);
            pq.offer(l2);
        }
        int cur=0;
        int rs=0;
        while (!pq.isEmpty()){
            Line l=pq.poll();
            if (l.isStart==1){
                cur++;
                rs=Math.max(rs,cur);
            }else {
                cur--;
            }
        }
        return rs;

    }
    class Line{
        int val;
        int isStart;
        public Line(int val,int isStart){
            this.val=val;
            this.isStart = isStart;
        }
    }
    class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
