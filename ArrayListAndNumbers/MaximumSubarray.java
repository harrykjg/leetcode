package ArrayListAndNumbers;

import sun.nio.cs.ext.MacHebrew;

/**
 * Created by 502575560 on 7/10/17.
 */
public class MaximumSubarray {
    public static void main(String[] args){
        int[] n=new int[]{-2,1,-3,4,-1,2,1,-5,4};
        maxSubArray2(n);
    }
    public int maxSubArray(int[] nums) {
        //又是瞎想的也对了,貌似这个是greedy算法,和他们写的都不怎么一样
        //http://codecloud.net/20692.html  前缀和和贪心法写起来差别很小,我这个是
        if(nums.length==0){
            return 0;
        }
        int pre=nums[0];
        int rs=pre;
        for(int i=1;i<nums.length;i++){
            pre=Math.max(pre,0);
            rs= Math.max(pre+nums[i],rs);
            pre+=nums[i];
        }
        return rs;
    }
    //前缀和法,比较神奇
    public static int maxSubArray2(int[] A) {
        if (A == null || A.length == 0){
            return 0;
        }

        int max = Integer.MIN_VALUE, sum = 0, minSum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];//不断累加A[i],同时记录此刻sum的最小值,然后sum-minSum就是连续数组的最大值,真神奇,可能要在数轴上画图理解
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
        }

        return max;
    }
}
