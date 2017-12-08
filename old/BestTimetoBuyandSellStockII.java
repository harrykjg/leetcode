public class BestTimetoBuyandSellStockII {
	
	public static void main(String[] args) {
		BestTimetoBuyandSellStockII bt=new BestTimetoBuyandSellStockII();
		int[] p={1,3,6,0,2};
		System.out.println(bt.maxProfit(p));
	}

	public int maxProfit(int[] prices) {
//看意思的话就是比如136020这个例子，可以1买3卖，然后再3买6卖，其实实际上和1买6卖是一样的
		int profit=0;
		for(int i=0;i<prices.length-1;i++){
			if(prices[i]<prices[i+1]){
				profit+=prices[i+1]-prices[i];
			}
		}
		return profit;
		
		
	}
	

}
