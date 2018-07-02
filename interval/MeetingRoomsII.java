package interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yufengzhu on 6/29/18.
 */
//区间类
public class MeetingRoomsII {
    public static void main(String[] args){
        MeetingRoomsII mr=new MeetingRoomsII();
        Interval[] in={new Interval(1,8),new Interval(2,4),new Interval(5,6),new Interval(10,12)};
        mr.minMeetingRooms(in);
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

}
class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
