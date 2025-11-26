package 灵神.DP.状态机.股票买卖;

public class MaximumEnergyBoostFromTwoDrinks3259 {
    public static void main(String[] args) {

    }
    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        long[][] dp=new long[energyDrinkA.length][2];
        dp[0][0]=energyDrinkA[0];
        dp[0][1]=energyDrinkB[0];
        dp[1][0]=dp[0][0]+energyDrinkA[1];
        dp[1][1]=dp[0][1]+energyDrinkB[1];

        for (int i=2;i<energyDrinkA.length;i++){
            dp[i][0]=Math.max(dp[i-2][1],dp[i-1][0])+energyDrinkA[i];
            dp[i][0]=Math.max(dp[i-2][0],dp[i-1][1])+energyDrinkB[i];
        }
        return Math.max(dp[dp.length-1][0],dp[dp.length-1][1]);
    }
}
