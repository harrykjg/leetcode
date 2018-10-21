package sweepLine;

import java.util.*;

/**
 * Created by yufengzhu on 7/21/18.
 */
//区间类 interval
public class EmployeeFreeTime {
    //看了一下确定是sweepline问题之后然后知道要排序后自己想的
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> rs=new ArrayList<>();
        PriorityQueue<Interval> pq=new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start==o2.start){
                    return o1.end-o2.end;
                }
                return o1.start-o2.start;
            }
        });
        for(List<Interval> ls:schedule){
            for(Interval it:ls){
                pq.offer(it);
            }
        }
        Interval pre=pq.poll();
        while (!pq.isEmpty()){
            Interval cur=pq.poll();
            if(cur.start>pre.end){
                rs.add(new Interval(pre.end,cur.start));
            }
            if(pre.end<=cur.end){//这里开始漏了，要当前的cur的end比pre的end在数轴上靠右才更新pre
                pre=cur;
            }
        }
        return rs;
    }
    class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    //9/22/2018想的不顺，有点困难，妈的还没以前想的好。注意，这线扫描貌似还和meeting room2那种不同的，这个是直接丢到queue里然后从头开始poll就完了，那个是要边放进pq边搞的
    public List<Interval> employeeFreeTime2(List<List<Interval>> schedule) {
        List<Interval> rs=new ArrayList<>();
        PriorityQueue<Interval> pq=new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start==o2.start){
                    return o1.end-o2.end;
                }
                return o1.start-o2.start;
            }
        });
        for(List<Interval> ls:schedule){
            for(Interval it:ls){
                pq.offer(it);
            }
        }
        Interval pre=pq.poll();
        while (!pq.isEmpty()){
            Interval cur=pq.poll();
            if(cur.start>pre.end){
                rs.add(new Interval(pre.end,cur.start));
            }
            if(pre.end<=cur.end){
                pre.end=cur.end;
            }
        }
        return rs;

    }
}
