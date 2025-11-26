package 灵神.DP.爬梯子;

import java.util.Arrays;

public class MinCostClimbingStairs746 {
    public static void main(String[] args) {

    }
    public int minCostClimbingStairs(int[] cost) {
        int[] dp=new int[cost.length+1];//这里有点绕，题目是要到数组的最后一位的下一位。因此dp【i】的意义开始很confusing要想清楚，
        // 其实可以这样想dp【i】的意义就是到达i点的最小距离，i点的cost不用取。那么这个i点就取决于
        //i-2位置要取，或者i-1位置要取。等整个array扫完之后，dp要再算一位才是答案。 因此dp可以后面加一位，那么返回的答案就可以是dp【dp。length-1】
        //如果是另一种想法，就是dp【i】的意义是必须包括i这一点cost要取
        //https://leetcode.cn/problems/min-cost-climbing-stairs/solutions/177077/yi-bu-yi-bu-tui-dao-dong-tai-gui-hua-de-duo-chong-/
        Arrays.fill(dp,Integer.MAX_VALUE);//而是i点必取的情况下最小的cost。
        //题目说了cost数组》=2
        dp[0]=0;
        dp[1]=0;
        for (int i=2;i<dp.length;i++){
            dp[i]=Math.min(dp[i-2]+cost[i-2],dp[i-1]+cost[i-1]);
        }
        return dp[dp.length-1];

    }
}
