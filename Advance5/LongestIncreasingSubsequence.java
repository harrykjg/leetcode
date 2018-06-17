package Advance5;

/**
 * Created by yufengzhu on 4/28/18.
 */
public class LongestIncreasingSubsequence {
    //4/28/2018,还是不会，dp理解不准确，以为要二维数组。记录i到j这个区间的上升子序长度，从长度为2的窗口开始算
    public int lengthOfLIS(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int[] dp=new int[nums.length];
        dp[0]=1;
        int rs=1;
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){//这里j正着来和倒着来都行
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[j]+1,dp[i]);
                }else{
                    dp[i]=Math.max(1,dp[i]);//这里容易直接写成dp【i】=1就错了
                }
                rs=Math.max(dp[i],rs);
            }
        }
        return rs;
    }
}
