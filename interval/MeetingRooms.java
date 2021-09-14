package interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yufengzhu on 6/29/18.
 */
//interval
public class MeetingRooms {
    //Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
    // determine if a person could attend all meetings. For example, Given [[0, 30],[5, 10],[15, 20]], return false.
    //要钱的
    public boolean canAttendMeetings(interval.Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<interval.Interval>() {
            @Override
            public int compare(interval.Interval o1, interval.Interval o2) {
                return o1.start-o2.start;
            }
        });
        for(int i=1;i<intervals.length;i++){
            if(intervals[i].start<intervals[i-1].end){
                return false;
            }
        }
        return true;
    }
    //04/12/2020,
    public boolean canAttendMeetings2(interval.Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start==o2.start){
                    return o1.end-o2.end;
                }
                return o1.start-o2.start;
            }
        });
        for(int i=1;i<intervals.length;i++){
            if(intervals[i].start<intervals[i-1].end){
                return false;
            }
        }
        return true;

    }
//6/24/2021 基本一次过
    public boolean canAttendMeetings2(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });
        for (int i=1;i<intervals.length;i++){
            if (intervals[i][0]<intervals[i-1][1]){
                return false;
            }
        }
        return true;
    }
}
