package 灵神.DP.状态机.股票买卖;

public class BestTimetoBuyandSellStockIII123 {
    public static void main(String[] args) {
        int[] p={3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(p));
    }
    //写不出
    public static int maxProfit(int[] prices) {
        int[] dp1=new int[prices.length];
        int[] dp2=new int[prices.length];
        int rs=0;
        int buy=prices[0];
        for(int i=1;i<prices.length;i++){//还以为是单纯的记录dp1从前到后每个i能获得的profit，其实不对，dp[i]应该要记录0到i中最大的profit，这样算前后段的时候才对。
            buy=Math.min(buy,prices[i]);
            dp1[i]=Math.max(dp1[i-1],prices[i]-buy);
        }
        //dp2的意义就和dp1不一样了，是从后面找max，再把当前i作为买的价格
        int max=prices[prices.length-1];
        for(int i=dp2.length-2;i>=0;i--){
            max=Math.max(max,prices[i]);
            dp2[i]=Math.max(dp2[i+1],max-prices[i]);
        }
        for (int i=0;i<dp1.length;i++){
            rs=Math.max(rs,dp1[i]+dp2[i]);//题目说了可以同一天买卖，既第一次卖了当天又买是可以的
        }
        return rs;
    }
}
