package 灵神.DP.经典线性dp.LongestIncreasingSubsequence;

import java.util.Arrays;

public class LongestIncreasingSubsequence300 {
    public static void main(String[] args) {

    }
    //想错了以为是dp最后一位是答案
    public int lengthOfLIS(int[] nums) {
        int[] dp=new int[nums.length];
        Arrays.fill(dp,1);
        int rs=0;
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
                rs=Math.max(rs,dp[i]);
            }
        }
        return rs;

    }

}
