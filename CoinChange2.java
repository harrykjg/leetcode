/**
 * Created by yufengzhu on 9/2/18.
 */
public class CoinChange2 {
    //类似背包问题，自己画图推的
    public int change(int amount, int[] coins) {
        int[][] dp=new int[coins.length+1][amount+1];
        for(int i=0;i<dp.length;i++){
            dp[i][0]=1;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<=amount;j++){
                if(j>=coins[i-1]){
                    dp[i][j]=dp[i-1][j]+dp[i][j-coins[i-1]];
                }else{
                    dp[i][j]=dp[i-1][j];
                }

            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
//04/18/2020,还是画图，就是初始化第一列为1这个没想到,这里写优化成一维的
    public int change2(int amount, int[] coins) {

        int[] dp=new int[amount+1];
        dp[0]=1;
        for(int i=0;i<coins.length;i++){
            for (int j=1;j<amount+1;j++){
                if(j>=coins[i]){
                    dp[j]=dp[j-coins[i]]+dp[j];
                }
            }
        }
        return dp[dp.length-1];

    }
//9/21/2021 画图也没想对，关键是初始化没想好
    public int change3(int amount, int[] coins) {
        int[][] dp=new int[coins.length+1][amount+1];
        for (int i=0;i<dp.length;i++){
            dp[i][0]=1;
        }
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp.length;j++){
                if (coins[i-1]<=j){
                    dp[i][j]=dp[i][j-coins[i-1]]+dp[i-1][j];
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];


    }
}
