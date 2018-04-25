package Advance4;

import ArrayListAndNumbers.InterleavingPositiveandNegativeNumbers;

/**
 * Created by yufengzhu on 4/18/18.
 */
public class MaximumAverageSubarrayI {
    //自己想的就是sliding window，但貌似可以用二分on值做，不好想，因为是double
    //https://www.jiuzhang.com/solutions/maximum-average-subarray/ 二分法
    public double findMaxAverage(int[] nums, int k) {
        // Write your code here
        if(nums==null||nums.length==0||k==0){
            return 0;
        }
        double rs=Integer.MIN_VALUE;
        double cur=0;
        int count=0;
        for(int i=0;i<nums.length;i++){
            cur+=nums[i];
            count++;
            if(count==k){
                rs=Math.max(rs,cur);
                cur-=nums[i-k+1];
                count--;
            }
        }
        return rs/k;
    }
}
