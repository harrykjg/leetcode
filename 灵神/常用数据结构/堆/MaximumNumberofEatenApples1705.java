package 灵神.常用数据结构.堆;

import java.util.PriorityQueue;

public class MaximumNumberofEatenApples1705 {
    static void main() {
        int[] a={3,0,0,0,0,2};
        int[] d={3,0,0,0,0,2};
        System.out.println(eatenApples(a,d));
    }
    //就是把水果和腐烂日期做成一个pair，放进堆，每次吃最早腐烂的.写起来改了很多次，主要是日期和修改剩余果实那里漏了不少
    //https://leetcode.cn/problems/maximum-number-of-eaten-apples/solutions/1172766/chi-ping-guo-de-zui-da-shu-mu-by-leetcod-93ka/
    public static int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        int rs=0;
        for (int i=0;i<apples.length;i++){
            while (!pq.isEmpty() && pq.peek()[1] < i+1) {
                pq.poll();
            }
            if(days[i]>=1&&apples[i]>0){
                pq.offer(new int[]{apples[i],i+days[i]});
            }
            if (!pq.isEmpty()) {
                int[] todayeat=pq.peek();
                if(todayeat[1]>=i+1){
                    rs++;
                    todayeat[0]--;
                }
                if(todayeat[0]<=0){
                    pq.poll();
                }
            }

        }
        int day=apples.length;
        while (!pq.isEmpty()){
            day++;
            while (!pq.isEmpty()&&pq.peek()[1]<day){
                //前面几天已经烂的扔掉
                pq.poll();
            }
            if(pq.isEmpty()){
                break;
            }
            int[] today=pq.poll();
            if(today[0]>=1){
                rs++;
            }
            if(today[1]-1>0&&today[0]-1>0){
                pq.offer(new int[]{today[0]-1,today[1]});
            }
        }

        return rs;
    }
}
