package Advance3;

import sun.nio.cs.ext.MacHebrew;

import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by 502575560 on 7/20/17.
 */
public class TrappingRainWater {
    //还是不会,容易和largestrectangleinhistogram搞混
    //http://blog.csdn.net/linhuanmars/article/details/20888505
    //http://blog.csdn.net/okiwilldoit/article/details/23266495
    public int trapRainWater(int[] heights) {//udemy的夹逼法还比这个更好一点
        // write your code here
        int rs=0;
        if(heights.length<3){
            return 0;
        }
        int[] high=new int[heights.length];
        int max=0;
        for(int i=0;i<heights.length;i++){
            high[i]=max;
            max=Math.max(max,heights[i]);
        }
        max=0;
        for(int i=heights.length-1;i>=0;i--){//只需一个数组记录i点左边最高的点,右边最高的是on the fly得出来的
            if(max>high[i]&&high[i]>heights[i]){
                rs+=high[i]-heights[i];
            }
            if(max<=high[i]&&max>heights[i]){
                rs+=max-heights[i];
            }
            max= Math.max(heights[i],max);
        }

        return rs;

    }
//九章第二轮，4／5／2018,想了半天突然想到好想是左右扫一遍的,写的是两个数组，还加了个heap，基本一次过，但是上面的就用了一个数组而已更好，而且其实不用heap也行
    //21年update，当想找到i位置左边和右边最大的数的时候，还是想着的是暴力的从i向左/右扫找到最大。这个想法应该要改掉，既然是要找所有的i的左边最大数，则应该
    //从0位置开始边走边记录遇到的最大的数就可以了！
    public int trapRainWater2(int[] heights) {
        if(heights.length==0||heights.length<3){
            return 0;
        }
        int[] left=new int[heights.length];
        int[] right=new int[heights.length];
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<left.length;i++){
            if(i==0){
                left[i]=0;
                pq.offer(heights[i]);
                continue;
            }
            left[i]=pq.peek();
            pq.offer(heights[i]);
        }
        pq.clear();
        for(int i=right.length-1;i>=0;i--){
            if(i==right.length-1){
                right[i]=0;
                pq.offer(heights[i]);
                continue;
            }
            right[i]=pq.peek();
            pq.offer(heights[i]);
        }
        int rs=0;
        for(int i=1;i<left.length-1;i++){
            int temp=Math.min(left[i],right[i]);
            if(temp>heights[i]){
                rs+=temp-heights[i];
            }
        }
        return rs;
    }
}
