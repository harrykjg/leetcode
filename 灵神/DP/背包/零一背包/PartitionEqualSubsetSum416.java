package 灵神.DP.背包.零一背包;

import java.util.Arrays;

public class PartitionEqualSubsetSum416 {
    public static void main(String[] args) {
        int[] nums={14,9,8,4,3,2};
        System.out.println(canPartition(nums));
    }
    //自己写不出，画图,可以优化到一维dp，这里还是写二维的
    public static boolean canPartition(int[] nums) {
        int sum=0;
        Arrays.sort(nums);
        for(int i:nums){
            sum+=i;
        }
        if(sum%2!=0){
            return false;
        }
        boolean[][] dp=new boolean[sum/2+1][nums.length+1];
        Arrays.fill(dp[0],true);
        for(int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if(i>=nums[j-1]){
                    dp[i][j]=dp[i-nums[j-1]][j-1]||dp[i][j-1];//dp[i][j-1]就是不选这个数字，前面的数已经能组合i了
                }else{
                    dp[i][j]=dp[i][j-1];//还是这里容易漏，i大于nums【j-1】的时候如果前面有别的数字可以组合成i也是可以的
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
