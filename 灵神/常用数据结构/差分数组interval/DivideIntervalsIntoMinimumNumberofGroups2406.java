package 灵神.常用数据结构.差分数组interval;

import java.util.Arrays;
import java.util.PriorityQueue;

public class DivideIntervalsIntoMinimumNumberofGroups2406 {
    static void main() {

    }
    //看提示：最少要分多少个区间，就是找到最多重叠的那堆有几个重叠，注意不是重叠发生过几次,写不出.
    //https://leetcode.cn/problems/divide-intervals-into-minimum-number-of-groups/solutions/3772408/chai-fen-shu-zu-pythonti-jie-by-yfsilver-sgaw/
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);//貌似不是skyline问题就不用管终点？
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        int rs=0;
        for(int[] a:intervals){
            while (!pq.isEmpty()&&pq.peek()<a[0]){//注意灵神的答案是这里写的是if，然后答案是pq。size，而我这里是用while，就是把
                pq.poll();                    //所有结束的课都poll出来，这样的话就是要记录这个过程中pq的最大的size作为答案。
            }                    //而if的写法实际上就是有意的的只弹出最早结束的那个，别的有的已经结束的会还在堆里，这样pq。size就恰好是
            pq.offer(a[1]);    //曾经出现过的最大的那个数。
            rs=Math.max(rs,pq.size());
        }

        return rs;

    }
}
