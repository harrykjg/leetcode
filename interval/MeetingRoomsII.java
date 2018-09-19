package interval;

import java.util.*;

/**
 * Created by yufengzhu on 6/29/18.
 */
//区间类
public class MeetingRoomsII {
    public static void main(String[] args){
        MeetingRoomsII mr=new MeetingRoomsII();
        Interval[] in={new Interval(1,8),new Interval(2,4),new Interval(5,6),new Interval(10,12)};
        mr.minMeetingRooms3(in);
    }
    //Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the minimum number of conference rooms required.
    //我以为用stack能做结果是不行的，stack只能把按start先后顺序排了放进去，但是要从stack中peak出元素和当前interval start的时候，peak出来的元素要先
    //peak出end为先的inverval，stack无法满足此要求，因此要用priorityqueue
    //https://blog.csdn.net/bsbcarter/article/details/50005563
    //https://leetcode.com/problems/meeting-rooms-ii/discuss/67883/Super-Easy-Java-Solution-Beats-98.8
    public int minMeetingRooms(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start==o2.start){
                    return o1.end-o2.end;
                }
                return o1.start-o2.start;
            }
        });
        PriorityQueue<Interval> pq=new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end-o2.end;
            }
        });

        int rs=0;
        for(int i=0;i<intervals.length;i++){
            if(pq.isEmpty()){
                pq.offer(intervals[i]);
                rs=Math.max(rs,pq.size());
            }else{
                while (!pq.isEmpty()&&pq.peek().end<=intervals[i].start){
                    pq.poll();
                }
                pq.offer(intervals[i]);
                rs=Math.max(rs,pq.size());
            }

        }
        return rs;
    }
    //这个是numberofairplaneinthesky直接copy过来的改了对应变量名而已，直接accept了。说明不需要priorityqueue也行，就是要新建个class
    public int minMeetingRooms2(Interval[] intervals) {
        int rs = 0;
        if (intervals.length == 0) {
            return 0;
        }
        List<point> ls = new ArrayList<>();
        for (Interval v : intervals) {
            ls.add(new point(v.start, 1));
            ls.add(new point(v.end, 0));
        }
        Collections.sort(ls, new Comparator<point>() {
            @Override
            public int compare(point o1, point o2) {
                if (o1.time == o2.time) {
                    return o1.flag - o2.flag;
                }
                return o1.time - o2.time;
            }
        });

        int cur = 0;
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i).flag == 1) {
                cur++;
            } else {
                cur--;
            }
            rs = Math.max(cur, rs);
        }
        return rs;
    }
    class point{
        int time;
        int flag;
        public point(int time,int flag){
            this.time=time;
            this.flag=flag;
        }
    }
//9/12/2018,以为肯定会结果还差点做不出来,而写错了几次
    public int minMeetingRooms3(Interval[] intervals) {
        int rs=0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start==o2.start){
                    return o1.end-o2.end;
                }
                return o1.start-o2.start;
            }
        });
        PriorityQueue<Interval> pq=new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end-o2.end;//我只关心谁先end，如果end一样，谁先出来都一样,但是前面interval排序的时候是要看谁先start的
            }
        });
        for(int i=0;i<intervals.length;i++){
            if(pq.isEmpty()){
                pq.offer(intervals[i]);
                rs=Math.max(rs,pq.size());
            }else{
                while (!pq.isEmpty()&&pq.peek().end<=intervals[i].start){
                    pq.poll();
                }
                pq.offer(intervals[i]);
                rs=Math.max(rs,pq.size());
            }
        }
        return rs;


    }

}

