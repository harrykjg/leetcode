package ArrayListAndNumbers;

/**
 * Created by 502575560 on 7/10/17.
 */
public class BestTimetoBuyandSellStock {
    public int maxProfit(int[] prices) {
        // 卧槽瞎写的也对了
        //http://blog.csdn.net/linhuanmars/article/details/23162793
        int rs=0;
        if(prices.length<=1){
            return 0;
        }
        int buy=prices[0];
        for(int i=0;i<prices.length-1;i++){
            if(prices[i]<prices[i+1]){
                rs=Math.max(prices[i+1]-buy,rs);
            }else{
                buy=Math.min(buy,prices[i+1]);
            }
        }
        return rs;
    }
}
