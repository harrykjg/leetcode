package 灵神.DP.背包.零一背包;

import java.util.Arrays;
import java.util.List;

public class LengthoftheLongestSubsequenceThatSumstoTarget2915 {
    public static void main(String[] args) {

    }
    //这个dp意义也不好想，但是貌似只要确定是01背包问题就可以。列个二维数组，row是nums，col是target，然后是从target至1便利去搞的
    /*
        0 1 2 3 4 5 6 7 8 9
      0 0-1-1-1-1-1-1-1-1-1
      1 0 1-1-1-1-1-1-1-1-1
      2 0 1 1 2-1-1-1-1-1-1
      3 0 1 1 2 2 2 3-1-1-1
      4 0 1 1 1 2 2 3 3 3 3
      5 0

     */
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int[][] dp=new int[nums.size()+1][target+1];
        for(int i=0;i<dp.length;i++){
            Arrays.fill(dp[i],-1);
        }
        for(int i=0;i<dp.length;i++){
            dp[i][0]=0;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(j-nums.get(i-1)>=0){
                    if(dp[i-1][j-nums.get(i-1)]!=-1){
                        dp[i][j]=Math.max(dp[i-1][j-nums.get(i-1)]+1,dp[i-1][j]);//这里想的是取vs不取这个nums【i-1】这个数，应该是对的，但是别人答案不是这么写的
                    }else{
                        dp[i][j]=dp[i-1][j];//这里漏了，假如nums 是7,3，target是7则到3的时候发现dp【i-1】【j-3】=-1，所以dp【i】【j】应该继承7这里的1.
                    }
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
