package ArrayListAndNumbers;

/**
 * Created by 502575560 on 10/8/17.
 */
public class BestTimetoBuyandSellStockII {
    //一次过
    public int maxProfit(int[] prices) {
        int rs=0;
        if(prices.length==0){
            return 0;
        }
        int buy=prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i]>buy){
                rs+=prices[i]-buy;
                buy=prices[i];
            }else{
                buy=prices[i];
            }
        }
        return rs;
    }
    //10/20/2018
    public int maxProfit2(int[] prices) {
        int rs=0;
        if(prices.length==0){
            return 0;
        }
        int buy=prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i]<buy){
                buy=prices[i];
            }else{
                rs+=prices[i]-buy;
                buy=prices[i];
            }
        }
        return rs;
    }
//04/17/2020
    public int maxProfit3(int[] prices) {
        int rs=0;
        int buy=prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i]>buy){
                rs+=prices[i]-buy;
                buy=prices[i];
            }else{
                buy=Math.min(buy,prices[i]);//这个比较其实是不必要的，因为当前的prices【i】如果是大于buy的话那么也不会来到这个else里
            }
        }
        return rs;
    }
//6/2/2021,其实和trapingRainWater有点像，就是要看i位置上左边最小的数字，on the fly从左到右就行
    public int maxProfit4(int[] prices) {
        int rs=0;
        int buy=prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i]>buy){
                rs+=prices[i]-buy;
                buy=prices[i];
            }else{
                buy=Math.min(buy,prices[i]);
            }
        }
        return rs;
    }
}
