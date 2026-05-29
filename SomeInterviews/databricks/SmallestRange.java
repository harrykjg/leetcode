package SomeInterviews.databricks;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SmallestRange {
    static void main() {
        SmallestRange sr=new SmallestRange();
        int[][] r={{5,100},
                {6},
                {7,8}};
        int[] rs=sr.findRange(r);
        for(int i:rs){
            System.out.println(i);
        }
    }
    //https://leetcode.com/discuss/post/3162377/databricks-interview-question-by-anonymo-t7ts/
    //Given a 2d array, choose one element from each row such that final array has lowest difference between maximum and minimum elements of final array. Returrn sorted final array.
    //
    //I have tried using DFS backtracking, but was not able to solve the question. Any idea on what approach I should take ?
    //
    //Ex: Input : [[61,90,60], [59,61],[58,62,92]]
    //OutPut: [61,61,62]
    //和632很像，不同的是那里记录最小和最大值，这里是记录每一个数组选的数字，无非就是遇到gap最小的时候记录下每个数组的index
    public int[] findRange(int[][] range){
        int[] rs=new int[range.length];
        int[] index=new int[range.length];
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[0]-b[0]);
        int max=Integer.MIN_VALUE;//开始写成0的话就不能处理负数的情况
        for (int i=0;i<range.length;i++){
            Arrays.sort(range[i]);
            //第一个是元素本身，第二个是index，第三个是这个数组的在二维数组里的index
            pq.offer(new int[]{range[i][0],0,i});
            max=Math.max(max,range[i][0]);
        }
        int gap=Integer.MAX_VALUE;

        while (pq.size()==range.length){//这个条件也挺巧妙
            int[] cur=pq.poll();
            int idx=cur[1];
            if(max-cur[0]<gap){
                gap=max-cur[0];
                for (int i=0;i<index.length;i++){//记录当前状态下，即每个range里的index指向谁的状态
                    rs[i]=range[i][index[i]];
                }
            }
            if(idx+1<range[cur[2]].length){
                index[cur[2]]++;
                pq.offer(new int[]{range[cur[2]][idx+1],idx+1,cur[2]});
                max=Math.max(max,range[cur[2]][idx+1]);
            }
        }
        Arrays.sort(rs);
        return rs;
    }
}
