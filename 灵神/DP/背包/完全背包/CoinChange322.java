package 灵神.DP.背包.完全背包;

import javax.print.DocFlavor;
import java.util.Arrays;

public class CoinChange322 {
    public static void main(String[] args) {
        int[] n={2};
        System.out.println(coinChange(n,3));
    }
     /*
     画图找规律
          0 1 2 3 4 5 6 7 8 9
       0  0 maxxxxxxxxxxxxxxx
       1  0 1 2 3 4 5 6 7 8 9
       2  0 1 1 2 2 3 3 4 4 5
       5  0 1 2 3 2 1 2 2 3 3

       看gpt解释改的，以上画的图并不能完全反应所有情况

 每一个点拆成三种可能的情况：

j - coin < 0
→ 金额太小，不能用这一枚，只能继承：

dp[i][j] = dp[i - 1][j];


✔ 这个你在 else 里处理了。

j - coin >= 0，并且 dp[i][j - coin] != MAX
→ 可以通过“用当前硬币一次以上”来凑，用你这一句：

dp[i][j] = Math.min(dp[i][j - coin] + 1, dp[i - 1][j]);


✔ 这个分支也 OK。

最重要也是你漏掉的情况：

j - coin >= 0（金额足够大，从理论上可以试图用当前硬币）

但是 dp[i][j - coin] == MAX_VALUE （说明：不能通过用当前硬币来凑 j）

但是有可能 dp[i-1][j] 本身是一个合法的值（之前已有别的硬币组合能凑出 j）

      */

    //
    public static int coinChange(int[] coins, int amount) {
        int[][] dp=new int[coins.length+1][amount+1];
        for (int[] a:dp){
            Arrays.fill(a,Integer.MAX_VALUE);
        }
        for(int i=0;i<dp.length;i++){
            dp[i][0]=0;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(j-coins[i-1]>=0){
                    if(dp[i][j-coins[i-1]]!=Integer.MAX_VALUE){
                        dp[i][j]=Math.min(dp[i][j-coins[i-1]]+1,dp[i-1][j]);
                    }else{
                        dp[i][j]=dp[i-1][j];//这里漏了，不好想。既 dp[i][j - coin] == MAX_VALUE （说明：不能通过用当前硬币来凑 j）但是有可能 dp[i-1][j] 本身是一个合法的值（之前已有别的硬币组合能凑出 j）
                    }
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        if(dp[dp.length-1][amount]==Integer.MAX_VALUE){
            return -1;
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
