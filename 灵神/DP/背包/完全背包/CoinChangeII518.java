package 灵神.DP.背包.完全背包;

public class CoinChangeII518 {
    public static void main(String[] args) {

    }
    /*
      0 1 2 3 4 5
   0  1 0 0 0 0 0
   1  1 1 1 1 1 1
   2  1 1 2 2 3 3
   5  1

     dp[i][j]= if(j-nums[i-1]>=0){ dp[i][j-nums[i-1])+dp[i-1][j]} else dp[i-1][j]
    这个比coinchange 1还简单些
     */

    public int change(int amount, int[] coins) {
        int[][] dp=new int[coins.length+1][amount+1];
        for (int i=0;i<dp.length;i++){
            dp[i][0]=1;
        }
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if(j-coins[i-1]>=0){
                    dp[i][j]=dp[i][j-coins[i-1]]+dp[i-1][j];
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
