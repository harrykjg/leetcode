package 灵神.贪心.区间;

import java.util.Arrays;

public class NonoverlappingIntervals435 {
    static void main() {

    }
    //1/2/2026 以为不难其实不好想。
    /*

    |-------t1---------|
    |-t2-||-t3-|  |------t4-----|
                           |---t5----|
    看上面这个例子，答案是2.其实想法就是按起点排序，如果起点相同的话，终点谁先谁后貌似无所谓。假如t1排最前，则pre是t1，看t2，发现是相交的
    则先rs++，代表要删一个，并且要更新pre，把pre的右侧改成pre和t2的右侧的较小的那一段，这样pre其实就变成t2这一段了，这样再看t3，由于pre的
    右边已经改成t2了，因此t3和pre是不重叠了，rs不用++，相当于知道如果t1特别长的话相交了几个小段，只要删掉t1一个就行了。

    另外一个例子,答案是2
    |------t1------|
    |----------t2-----------|
             |--------t3---------|
    同理，无论t1先还是t2先，反正t1t2处理之后pre的右端取他们较小的那个，现在到了t3，发现t3还是和pre相交的，即还要rs++
     */


    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        int[] pre=intervals[0];
        int rs=0;
        for (int i=1;i<intervals.length;i++){
            if(intervals[i][0]>=pre[1]){//没有相交
                pre[1]=intervals[i][1];
            }else{
                rs++;
                pre[1]=Math.min(pre[1],intervals[i][1]);
            }
        }
        return rs;
    }
}
