package 灵神.DP.爬梯子;

import java.util.Arrays;

public class ClimbingStairsII3693 {
    public static void main(String[] args) {


    }
    public int climbStairs(int n, int[] costs) {
        int[] dp=new int[costs.length];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=1*1+costs[0];//0也得跨一步
        if(dp.length==1){
            return dp[0];
        }
        dp[1]=Math.min(costs[1]+2*2,dp[0]+costs[1]+1);//由于0也得跨一步，因此直接跨到dp【1】就是两步
        if(dp.length==2){
            return dp[dp.length-1];
        }
        dp[2]=Math.min(costs[2]+3*3,Math.min(dp[1]+costs[2]+1*1,dp[0]+costs[2]+2*2));
        for(int i=3;i<costs.length;i++){
            for(int j=i-3;j<i;j++){
                dp[i]=Math.max(dp[i],dp[j]+costs[i]+(i-j)*(i-j));
            }
        }

        return dp[dp.length-1];
    }
}
