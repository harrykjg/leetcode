package SomeInterviews.purestorage;

public class FootballTouchdown {

    //据说是给定一个分数和touchdown，kick还是咋的可得1，3，6分，有几种得分方式。画图找规律
    public static int coinChange(int[] coins, int amount) {
        int[][] dp=new int[coins.length+1][amount+1];

        for (int i=0;i<dp.length;i++){
            dp[i][0]=1;
        }
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<=amount;j++){
                if(coins[i-1]>j){
                    dp[i][j]=dp[i-1][j];
                }else {
                    dp[i][j]=dp[i][j-coins[i-1]]+dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
//一维写法
    public static int coinChange2(int[] coins, int amount) {
        int[] dp=new int[amount+1];
        dp[0]=1;

        for (int i=0;i<coins.length;i++){
            for (int j=1;j<=amount;j++){//这里可以优化成写j=coins[i]开始
                if(coins[i]<=j){
                    dp[j]=dp[j-coins[i]]+dp[j];
                }
            }
        }
        return dp[dp.length-1];
    }
}
