public class BestTimetoBuyandSellStockII {
	
	public static void main(String[] args) {
		BestTimetoBuyandSellStockII bt=new BestTimetoBuyandSellStockII();
		int[] p={1,3,6,0,2};
		System.out.println(bt.maxProfit(p));
	}

	public int maxProfit(int[] prices) {
//����˼�Ļ����Ǳ���136020������ӣ�����1��3����Ȼ����3��6������ʵʵ���Ϻ�1��6����һ����
		int profit=0;
		for(int i=0;i<prices.length-1;i++){
			if(prices[i]<prices[i+1]){
				profit+=prices[i+1]-prices[i];
			}
		}
		return profit;
		
		
	}
	

}
