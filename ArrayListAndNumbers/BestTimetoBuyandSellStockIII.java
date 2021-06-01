package ArrayListAndNumbers;


import java.util.Arrays;

/**
 * Created by 502575560 on 10/8/17.
 */
public class BestTimetoBuyandSellStockIII {
    public static void main(String[] a){
        BestTimetoBuyandSellStockIII bt=new BestTimetoBuyandSellStockIII();
        int[] n={2,1,2,0,1};
       System.out.print( bt.maxProfit4(n));
        int[] coin={2,7,8};
        System.out.println(bt.coinChange2(coin,9));

    }

    //自己写的就是把数组分成2段,分别求第一段和第二段的最大值,加一起比较最大值,再比较整个数组值进行一次交易得出的值就行了,结果超时了
    public int maxProfit(int[] prices) {
        if(prices.length==0){
            return 0;
        }
        int rs=0;
        for(int i=0;i<prices.length;i++){
            int temp=helper(prices,0,i)+helper(prices,i+1,prices.length-1);
            rs=Math.max(rs,temp);
        }
        rs=Math.max(helper(prices,0,prices.length-1),rs);
        return rs;
    }
    int helper(int[] prices,int b,int e){
        if(e-b<=0){
            return 0;
        }
        int rs=0;
        int buy=prices[b];
        for(int i=b+1;i<=e;i++){
            if(prices[i]>buy){
                rs=Math.max(prices[i]-buy,rs);
            }else{
                buy=Math.min(prices[i],buy);
            }
        }
        return rs;
    }
//https://www.2cto.com/kf/201311/256139.html  用dp才行,关键点是两个dp如何合并这里下标要仔细点想
    public int maxProfit2(int[] prices) {
        if(prices.length<=1){
            return 0;
        }
        int[] dp1=new int[prices.length];
        int[] dp2=new int[prices.length];
        int rs=0;
        int buy=prices[0];
        for(int i=1;i<prices.length;i++){
            dp1[i]=Math.max(prices[i]-buy,dp1[i-1]);
            buy=Math.min(buy,prices[i]);
        }
        int sell=prices[prices.length-1];
        for(int i=prices.length-2;i>=0;i--){
            dp2[i]=Math.max(dp2[i+1],sell-prices[i]);
            sell=Math.max(prices[i],sell);

        }
        for(int i=0;i<prices.length;i++){//其实dp1[i]和dp2[i]再i这一点上是可以重叠的,就是dp1时在i点卖了,dp2在i点买,没问题
            rs=Math.max(rs,dp1[i]+dp2[i]);
        }
        return rs;
    }

    //10/20/2018,自己想的双序列dp结果是行不通的。还是写不出正确的写法
    public int maxProfit3(int[] prices) {
        if(prices.length<=1){
            return 0;
        }
        int rs=0;
        int[] dp1=new int[prices.length];
        int[] dp2=new int[prices.length];
        int buy=prices[0];
        int sell=prices[prices.length-1];
        for(int i=1;i<prices.length;i++){
            buy=Math.min(prices[i],buy);
            dp1[i]=Math.max(dp1[i-1],prices[i]-buy);
        }
        for(int i=prices.length-2;i>=0;i--){
            sell=Math.max(prices[i],sell);
            dp2[i]=Math.max(dp2[i+1],sell-prices[i]);
        }
        for(int i=0;i<prices.length;i++){
            rs=Math.max(dp1[i]+dp2[i],rs);
        }

        return rs;
    }

    //04/17/2020,思路大概记得，写得不好，就是dp2[i]的意义是从i开始到末尾能做到交易的最大profit，因此dp2[dp2.length-1]不应该有值，倒数第二位开始才有值
    public int maxProfit4(int[] prices) {
        if(prices.length<=1){
            return 0;
        }
        int[] dp1=new int[prices.length];
        int[] dp2=new int[prices.length];
        int buy=prices[0];
        int local=0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]-buy>0){
                local=Math.max(local,prices[i]-buy);
            }
            buy=Math.min(prices[i],buy);
            dp1[i]=Math.max(dp1[i],local);
        }
        int sell=prices[prices.length-1];
        local=0;
        for(int i=prices.length-2;i>=0;i--){
            if(prices[i]<sell){
                local=Math.max(local,sell-prices[i]);
            }
            sell=Math.max(prices[i],sell);
            dp2[i]=Math.max(dp2[i],local);
        }
        int rs=0;
        for(int i=0;i<dp1.length;i++){
            rs=Math.max(dp1[i]+dp2[i],rs);
        }
        return rs;

    }

    //1/5/2021,基本上是懂的，就是dp1多写了个if（prices[i]>buy)这个条件，dp1意义是0到i之间可以取得的最大profit，即比如当前prices【i】是没有profit，但是只要0到i-1是有profit的，那么dp1【i】也是有profit
    public int maxProfit5(int[] prices) {
        if(prices.length==0||prices.length==1){
            return 0;
        }
        int rs=0;
        int[] dp1=new int[prices.length];
        int[] dp2=new int[prices.length];
        int buy=prices[0];
        for(int i=1;i<prices.length;i++){
            dp1[i]=Math.max(dp1[i-1],prices[i]-buy);

            buy=Math.min(buy,prices[i]);
        }
        int sell=prices[prices.length-1];
        for(int i=prices.length-2;i>=0;i--){
            dp2[i]=Math.max(sell-prices[i],dp2[i+1]);

            sell=Math.max(sell,prices[i]);
        }
        for(int i=1;i<prices.length;i++){
            rs=Math.max(dp1[i]+dp2[i],rs);
        }
        return rs;
    }


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
