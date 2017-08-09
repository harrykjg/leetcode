package dp;

import java.util.Arrays;

/**
 * Created by 502575560 on 7/30/17.
 */
public class HouseRobber {
    //忘了,不会做
    public int rob(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        if(nums.length==2){
            return Math.max(nums[0],nums[1]);
        }
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);//这个初始化不太好想,开始我想的是如果nums[1]大则dp[1]赋值而dp[0]就不赋值了,这样不对的
        for(int i=2;i<nums.length;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[dp.length-1];
    }
}
