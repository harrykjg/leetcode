package ArrayListAndNumbers;

/**
 * Created by 502575560 on 10/8/17.
 */
public class BestTimetoBuyandSellStockIII {
    public static void main(String[] a){
        BestTimetoBuyandSellStockIII bt=new BestTimetoBuyandSellStockIII();
        int[] n={2,1,2,0,1};
        bt.maxProfit(n);

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

}
