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
}
