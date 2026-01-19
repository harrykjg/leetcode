package 灵神.常用数据结构.堆;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsIII2402 {
    static void main() {
        int[][] m={{18,19},{3,12},{17,19},{2,13},{7,10}};
        System.out.println(mostBooked(4,m));
    }
    //12/3/2025
    //这里还有点恶心，假如4个房间都被占了，现在来了第五个meeting，他的开始时间的时候所有的meeting room已经又空了（有些先空有些后空），
    //那么这第五个meeting会去room 0.
    //他写了可以用两个pq或者一个pq
    //https://leetcode.com/problems/meeting-rooms-iii/solutions/2527284/one-heap-two-heaps-by-votrubac-uxte/
    public static int mostBooked(int n, int[][] meetings) {
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->{
           if(a[1]==b[1]){
               return a[0]-b[0];
           }else{
               return a[1]-b[1];
           }
        });
        for(int i=0;i<n;i++){
            pq.offer(new int[]{i,0,0});
        }
        int rs=n;
        int max=0;
        Arrays.sort(meetings,(a,b)->a[0]-b[0]);
        for(int i=0;i<meetings.length;i++){
            int[] cur=meetings[i];
            while (!pq.isEmpty()&&pq.peek()[1]<cur[0]){//这里还得倒腾一次，把已经结束的room先出来，把end设成当前没开始的meeting的start，
                int[] r=pq.poll();       //再放回去，这样倒腾完才能保证下面的poll出来的是最小编号的room
                r[1]=cur[0];
                pq.offer(r);
            }
            int[] room=pq.poll();

            room[1]+=cur[1]-cur[0];
            room[2]++;
            if(room[2]==max&&room[0]<rs){
                rs=room[0];
            }
            if(room[2]>max){
                rs=room[0];
                max=room[2];
            }

            pq.offer(room);
        }
        return rs;
    }
}
