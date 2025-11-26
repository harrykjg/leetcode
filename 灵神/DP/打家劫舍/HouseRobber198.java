package 灵神.DP.打家劫舍;

public class HouseRobber198 {
    public static void main(String[] args) {

    }
    //dp[i]的意义还想错了，不代表i点必须取就是代表i点能抢的最大值。如果代表必须取则 dp[i]=dp[i-2]+nums【i-1】，则2,1,1,2这种就过不了了
    public int rob(int[] nums) {
        int[] dp=new int[nums.length+1];
        dp[1]=nums[0];
        for(int i=2;i<dp.length;i++){
            dp[i]=Math.max(dp[i-2]+nums[i-1],dp[i-1]);
        }
        return dp[dp.length-1];
    }

}
