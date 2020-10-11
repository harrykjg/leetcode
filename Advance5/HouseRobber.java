package Advance5;

/**
 * Created by yufengzhu on 4/21/18.
 */
public class HouseRobber {
    //4／21／2018九章第二轮，其实一开始也想不起来，后来瞄了一下答案自己再想的，居然一次过
    public int rob(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int[] dp=new int[nums.length+1];//其实不用+1也可以

        dp[0]=0;//我想的是0作为dummy，dp【1】作为偷的第一个
        dp[1]=nums[0];
        int rs=Math.max(dp[0],dp[1]);
        for(int i=2;i<dp.length;i++){//i=2开始其实是nums【1】开始看nums【1】偷不偷
            dp[i]=Math.max(nums[i-1]+dp[i-2],dp[i-1]);
        }
        return Math.max(rs,dp[dp.length-1]);

    }
    //8/16/2018 一次过
    public int rob2(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if (nums.length==1){
            return nums[0];
        }
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++){
            dp[i]=Math.max(dp[i-1],nums[i]+dp[i-2]);
        }
        return dp[dp.length-1];
    }
//9/9/2018,试着优化空间
    public int rob3(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if (nums.length==1){
            return nums[0];
        }
        int prepre=nums[0];
        int pre=Math.max(nums[0],nums[1]);
        int rs=pre;
        for(int i=2;i<nums.length;i++){
            rs=Math.max(nums[i]+prepre,pre);
            prepre=pre;
            pre=rs;
        }
        return rs;
    }
//05/21/2020,一次过，但是没有空间优化
    public int rob5(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++){
            dp[i]=Math.max(nums[i]+dp[i-2],dp[i-1]);
        }
        return dp[dp.length-1];
    }
}
