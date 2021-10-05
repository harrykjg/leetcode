package interval;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumNumberofEventsThatCanBeAttended {
    public static void main(String[] args){
        MaximumNumberofEventsThatCanBeAttended mn=new MaximumNumberofEventsThatCanBeAttended();
        System.out.println(mn.maxEvents(new int[][]{{1,2},{2,2},{3,3},{3,4},{3,4}}));
    }

    //10/1/2021 不会。
    /*
    [[1,1],[1,4],[2,2],[3,4],[4,4]] 先按start day排序，start day相同的按end小的排在前面，看第一个元素，参加，则把1，2删掉，此时第二位的1，4改成2，4，pq变成
    22 24 34 44
    34 34 44
    44 44

    1,2},{2,2},{3,3},{3,4},{3,4}
    22 33 34 34
    33 34 34
    34 44

     */
    public int maxEvents(int[][] events) {//自己想的如上图演示的，会超时，复杂度可能是n*nlogn
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });
        for (int[] e:events){
            pq.offer(e);
        }
        int rs=0;
        while (!pq.isEmpty()){//poll出来的第一个肯定是能参加的，
            int[] cur= pq.poll();
            rs++;
            while (!pq.isEmpty()&&pq.peek()[1]<cur[0]+1){//参加的这一天是cur【0】，那么pq头上要是end是小于这一天+1，则不可能参加
                pq.poll();
            }
            while (!pq.isEmpty()&&pq.peek()[0]<cur[0]+1){//然后对于后面pq里凡是start是小于当前天数+1的，都更新到当前天数+1，
                int[] next=pq.poll();
                next[0]=cur[0]+1;
                if (next[0]<=next[1]){//要是更新后的start还是小于等于end的才放回pq里，否则直接不行了
                    pq.offer(next);
                }
            }
        }
        return rs;
    }

    //别人的
    //https://www.youtube.com/watch?v=9bJvSySPcZM
    //https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/discuss/510263/JavaC%2B%2BPython-Priority-Queue
    public int maxEvents2(int[][] events) {
        Arrays.sort(events,(a,b)->a[0]-b[0]);
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        int i=0;
        int rs=0;
        for (int d=1;d<=100000;d++){//就是扫描每一天，发现有event在这一天开始的代表都可能可以参加了，加入pq
            while (i<events.length&&events[i][0]<=d){
                pq.offer(events[i++]);
            }
            //同时，这里可能存在前一天加进来的一堆相同start day的event，但是只能参加一个，现在到了今天，这些以前加进来的event可能已经结束了，因此要删除
            while (!pq.isEmpty()&&pq.peek()[1]<d){//这两个while顺序互换也对
                pq.poll();
            }
            if (!pq.isEmpty()){//这里poll出来的就是要参加的那个
                pq.poll();
                rs++;
            }
        }
        return rs;
    }
}
