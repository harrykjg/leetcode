package 灵神.贪心.区间;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MaximumNumberofEventsThatCanBeAttended1353 {
    static void main() {
        int[][] a={{1,10},{2,2},{2,2}};
        System.out.println(maxEvents(a));
    }
    //1/2/2026,自己想的超时。想着是和courseschedule3差不多，按结束时间优先排。然后loop over event，得到begin和end，做一个while循环
    //看哪个数没有被占用就使用，rs++。
    //其实和courseschedule3不一样，就算那里把课程简化成duration=1也不同，但是那里是没有start day的！所以可以先按lastday，再按duration反悔
    //但是这里貌似反悔出来也没什么好处？
    public static int maxEvents(int[][] events) {
        Arrays.sort(events,(a,b)->a[0]-b[0]);
        int rs=0;
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);//end day先结束的排前面
        int index=0;
        int end=(int)Math.pow(10,5);
        for(int i=1;i<=end;i++){
           while (index<events.length&&events[index][0]<=i){//把可以参加的会议入q
               pq.offer(events[index++]);
           }
           while (!pq.isEmpty()&&pq.peek()[1]<i){//把过时的排除，这个while放到前面也行
               pq.poll();
           }
           if(!pq.isEmpty()){
               pq.poll();
               rs++;
           }

        }
        return rs;
    }
}
