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

    //九章第二轮,10/7/2017,还是不太确定的却一次过
    public int maxProfit2(int[] prices) {
        int rs=0;
        if(prices.length==0){
            return 0;
        }
        int buy=prices[0];
        for (int i=1;i<prices.length;i++){
            if(prices[i]>buy){
                rs=Math.max(rs,prices[i]-buy);
            }else{
                buy=Math.min(buy,prices[i]);
            }
        }
        return rs;


    }
//04/17/2020,不会做了。
    public int maxProfit3(int[] prices) {
        if(prices.length<=1){
            return 0;
        }
        int rs=0;
        int min=prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i]>min){
                rs=Math.max(prices[i]-min,rs);
            }else{
                min=Math.min(prices[i],min);
            }
        }
        return rs;
    }
}
