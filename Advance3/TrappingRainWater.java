package Advance3;

import sun.nio.cs.ext.MacHebrew;

/**
 * Created by 502575560 on 7/20/17.
 */
public class TrappingRainWater {
    //还是不会,容易和largestrectangleinhistogram搞混
    //http://blog.csdn.net/linhuanmars/article/details/20888505
    //http://blog.csdn.net/okiwilldoit/article/details/23266495
    public int trapRainWater(int[] heights) {
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
}
