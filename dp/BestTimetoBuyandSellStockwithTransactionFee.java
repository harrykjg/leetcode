package dp;

/**
 * Created by yufengzhu on 7/28/18.
 */
public class BestTimetoBuyandSellStockwithTransactionFee {
    public static void main(String[] ar){
        BestTimetoBuyandSellStockwithTransactionFee bb=new BestTimetoBuyandSellStockwithTransactionFee();
        int[] a={1,3,2,8,4,9};
        System.out.print(bb.maxProfit2(a,2));

    }
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/most-consistent-ways-of-dealing-with-the-series-of-stock-problems 大神总结这一系列
//开始感觉greedy可以，后来发现好想不行，其实就是可以。dp也写不出来，,感觉可以用dfs,改了好几次超时，写的也不好
    int rs=0;
    public int maxProfit(int[] prices, int fee) {
        if(prices.length<=1){
            return 0;
        }
        dfs(prices[0],1,0,prices,fee);//思路就是每一个都可以分买当前的prices[i]和不买，
        dfs(-1,1,0,prices,fee);

        return rs;
    }
    void dfs(int buy,int index,int profit,int[] prices,int fee){
        if(index>=prices.length){
            rs=Math.max(profit,rs);
            return;
        }
        if(buy>=0){//之前已经买了的情况
            int loc=prices[index]-buy-fee;
            if(loc>0){//做当前交易的话，那么之前的buy就不能再用了
                dfs(-1,index+1,profit+loc,prices,fee);
            }
            dfs(Math.min(buy,prices[index]),index+1,profit,prices,fee);//不做交易的话，看当前price[index]是不是比buy还小，小的话可以入手，否则也不可能会去入手

        }
        //没买的情况，又可以分当前买price[index]或者不买
        dfs(-1,index+1,profit,prices,fee);
        dfs(prices[index],index+1,profit,prices,fee);
    }

    //这个是容易理解一点的dp，n方复杂度超时,是可以当天卖了再买的
    //https://blog.csdn.net/a1203177935/article/details/79005122
    public int maxProfit2(int[] prices, int fee) {
        if(prices.length<2){
            return 0;
        }
        int[] dp=new int[prices.length];
        for(int i=1;i<prices.length;i++){
            for(int j=0;j<i;j++){
                dp[i]=Math.max(Math.max(dp[i],dp[j]),dp[j]+prices[i]-prices[j]-fee);//这里开始还漏了比较dp[j]，意义就是第j天到i之间就不买不卖了
            }
        }
        return dp[prices.length-1];
    }
//https://blog.csdn.net/zarlove/article/details/78323469  他的贪心法，试试1，3，7，5，10，3，100fee是3和1，3，2，8fee是2。很不好想
    public int maxProfit3(int[] prices, int fee) {
        int profit=0;
        int curProfit=0;
        int minP=prices[0];
        int maxP=prices[0];
        int i;
        for(i=1;i<prices.length;i++){
            minP=Math.min(minP,prices[i]);
            maxP=Math.max(maxP,prices[i]);
            curProfit=Math.max(curProfit,prices[i]-minP-fee);
            if((maxP-prices[i])>=fee){//意思是比如1，4，2，8，到了4的时候，怎么直到应不应该卖了呢？无法不知道，所以先算出来一个curProfit，以后再说，到了8了，min还是1，还是不知道
                profit+=curProfit;    //应不应该卖，所以还是算算curprofit，后来发现数组完了，那么就结果加上curprofit。再举个例子1，3，7，5，10，3，100fee是3，现在到了7，无法直到应不应该卖，
                curProfit=0;         //所以先算curprofit，到了5，算了curprofit没变化，关键在这一步，直观上说，现在直到max是7，然后当前是5，跌了，但是不知道现在7卖了之后会不会后面再有一个更大数x，
                minP=prices[i];  //导致1买7卖加上5买x卖的值会大于1买7卖，而(maxP-prices[i])>=fee正是用来检测这个的， 继续到了3，现在max是10，发现curprofit比较大，那也不能说明什么，
                maxP=prices[i];//直观上看直到就是比较7-1-fee加上10-5-fee和10-1-fee谁大，即比较2个交易好还是一个交易好，知：2个交易好的条件是max之后的那个数足够小的话，才有可能出现后面一个更大的数x，使得。。
            }                //算了，很不好解释
        }
        return profit+curProfit;
    }
}
