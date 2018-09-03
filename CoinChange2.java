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
}
