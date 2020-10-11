package dp.backpack;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args){
        CoinChange ch=new CoinChange();
        int[] coin={186,419,83,408};
    System.out.println(ch.coinChange(coin,1));

    }
    //04/18/2020,画图填数字，发现无解的情况好像不太好写,搞得很恶心，而且大家都用一维dp写的
    public int coinChange(int[] coins, int amount) {
        if(coins.length==0){
            return 0;
        }
        Arrays.sort(coins);
        int[][] dp=new int[coins.length+1][amount+1];
        for (int i=0;i<dp[0].length;i++){
            dp[0][i]=Integer.MAX_VALUE;
        }
        for(int i=1;i<coins.length+1;i++){
            for(int j=1;j<amount+1;j++){
                if(j<coins[i-1]){
                    dp[i][j]=dp[i-1][j];
                }else{
                    if(dp[i][j-coins[i-1]]==Integer.MAX_VALUE){//由于dp[i][j-coins[i-1]]可能是max，即无解，所以不能dp[i][j-coins[i-1]]+1，只能是max，
                        dp[i][j]=Math.min(Integer.MAX_VALUE,dp[i-1][j]);//但是很容易忘了和dp[i-1][j]比较取小的
                    }else{
                        dp[i][j]=Math.min(dp[i][j-coins[i-1]]+1,dp[i-1][j]);
                    }
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1]==Integer.MAX_VALUE?-1:dp[dp.length-1][dp[0].length-1];
    }
    //  //画图二维dp可知dp[i][j]并不依赖于dp[i-1][j-1]，因此可以优化空间
    public int coinChange2(int[] coins, int amount) {
        if (coins.length == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int[] dp=new int[amount+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=0;i<coins.length;i++){
            for(int j=1;j<amount+1;j++){
                if(j>=coins[i]&&dp[j-coins[i]]!=Integer.MAX_VALUE){
                    dp[j]=Math.min(dp[j],dp[j-coins[i]]+1);
                }
            }
        }
        return dp[dp.length-1]==Integer.MAX_VALUE?-1:dp[dp.length-1];
    }
}

