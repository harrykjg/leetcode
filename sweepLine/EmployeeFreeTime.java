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

    //7/7/2021,没想到只需要pq的方法，只想到先sort schedule，把interval一个个放进pq，然后放之前把之前pq里符合条件的排除去。以前的方法还是有点巧妙的
    public List<Interval> employeeFreeTime3(List<List<Interval>> schedule) {
        PriorityQueue<Interval> pq=new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start-o2.start;//画图，只关心start就行了
            }
        });

        for (List<Interval> ls:schedule){
            for (Interval in:ls){
                pq.offer(in);
            }
        }
        List<Interval> rs=new ArrayList<>();
        Interval pre=pq.poll();
        while (!pq.isEmpty()){
            Interval cur=pq.poll();
            if (cur.start>pre.end){
                rs.add(new Interval(pre.end,cur.start));
            }
            if (pre.end<cur.end){
                pre=cur;
            }
        }
        return rs;
    }

    //3/15/2026 基本一次过，没用pq，就是所有interval排序，然后merge，然后遍历interval找slot就行了。再看以前的思路，其实不用merge，
    //可以直接判断得出答案
    public List<Interval> employeeFreeTime4(List<List<Interval>> schedule) {
        List<Interval> all=new ArrayList<>();
        for (List<Interval> l:schedule){
            for (int i=0;i<l.size();i++){
                all.add(l.get(i));
            }
        }
        Collections.sort(all,(a,b)->{
            if(a.start==b.start){
                return a.end-b.end;
            }
            return a.start-b.start;
        });
        Interval pre=all.get(0);
//        List<Interval> merge=new ArrayList<>();
//        for (int i=1;i<all.size();i++){
//            if(all.get(i).start>pre.end){
//                merge.add(pre);
//                pre=all.get(i);
//            }else if(all.get(i).start<=pre.end){
//                pre.end=Math.max(all.get(i).end,pre.end);
//            }
//        }
//        merge.add(pre);
        List<Interval> rs=new ArrayList<>();
        pre=all.get(0);
        for (int i=1;i<all.size();i++){
            if(all.get(i).start<=pre.end){
                pre.end=Math.max(pre.end,all.get(i).end);//省去merge的写法，只更新end就行了，和不merge的有些不一样
            }else{
                int start=pre.end;
                int end=all.get(i).start;
                rs.add(new Interval(start,end));
                pre.end=Math.max(pre.end,all.get(i).end);
            }

        }
        return rs;
    }
}
