//http://blog.csdn.net/linhuanmars/article/details/23162793 屌
//http://jixiangsanbao.wordpress.com/2014/05/17/best-time-to-buy-and-sell-stock/ 
public class BestTimetoBuyandSellStock {
	//和maximum subarray对比学习
public int maxProfit(int[] prices) {
        
        if(prices.length==0){
            return 0;
        }
       int loc=0;
       int glo=0;
       for(int i=0;i<prices.length-1;i++){
    	   

//15年11月做，动态规划还是不太好理解，吉祥的那个对于这题好理解一些。
//比如2，3，5，1，10，4,12这个例子，他这样写实际上就是从左到右每个相邻的两个数都比较，比如先2买3卖，则loc是1，glo是1，
//然后3买5卖，则差价是2，但是他还把之前的2买3卖的差价1加上了，所以loc是1+2=3，glo也是3，然后5买1卖，此时差价是-4，就算加上之前的3
//还是负数，则把loc变为0，然后1买10卖，差价是9，由于loc之前变成0了，所以现在loc就是9，glo也是9，后面也一样，不知道为什么会这么想
//有点绕
    	   loc=Math.max(loc+prices[i+1]-prices[i], 0);
    	   glo=Math.max(glo, loc);
       }
        return glo;
    }

public int maxProfit2(int[] prices) { 
    
    if(prices.length==0){
        return 0;
    }
   int min=prices[0];
   int profit=0;
   for(int i=1;i<prices.length;i++){
	   if(prices[i]-min>profit){
		   profit=prices[i]-min;
		   
	   }
	   if(prices[i]<min){
		   min=prices[i];
	   }
	
   }
    return profit;
}
}
