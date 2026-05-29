package SomeInterviews.snap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomgII253 {

    //3/4/2026,一次过，用sort加pq，
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        int rs=0;
        for (int i=0;i<intervals.length;i++){
            while (!pq.isEmpty()&&pq.peek()[1]<=intervals[i][0]){
                pq.poll();
            }
            pq.offer(intervals[i]);
            rs=Math.max(rs,pq.size());//我这个是要rs算最大值的，其实还有写法是不需要的，直接返回pq.size，那里其实是上面的while改成if就行了。
        }
        return rs;
    }
}
