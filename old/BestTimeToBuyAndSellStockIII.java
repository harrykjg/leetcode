//http://www.2cto.com/kf/201311/256139.html
//http://blog.csdn.net/jellyyin/article/details/8671277
//http://blog.csdn.net/linhuanmars/article/details/23236995
public class BestTimeToBuyAndSellStockIII {
	public static void main(String[] args) {
		BestTimeToBuyAndSellStockIII bt=new BestTimeToBuyAndSellStockIII();
		int[] n={6,1,3,2,4,7};
			System.out.println(bt.maxProfit4(n));	
		}
	
	
	
	 public int maxProfit(int[] prices) {//n平方算法，会超时
	       if(prices.length==0||prices.length==1){
				return 0;
			}
			int buy=prices[0];
			int first=0;
			int buy2=0;
			int total=0;
			
			for(int i=0;i<prices.length;i++){
				if(buy>prices[i]){
					buy=prices[i];
				}
				if(first<prices[i]-buy){
					first=prices[i]-buy;
				}
				if (i + 1 < prices.length) {
					buy2 = prices[i + 1];
					total = first + total;
					for (int j = i + 1; j < prices.length; j++) {
						if (prices[j] < buy2) {
							buy2 = prices[j];
						}
						if (first + prices[j] - buy2 > total) {
							total = first + prices[j] - buy2;
						}
					}
				}
			}
			return total;

		}
	 public int maxProfit2(int[] prices){//这居然就叫动态规划了
		 
		 if(prices.length==0||prices.length==1){
				return 0;
			}
		 int[] left=new int[prices.length];//保存[0,i]区间的最大值
		 int[] right=new int[prices.length];//保存[i,length-1]区间的最大值
		 int total=0;
		 int min=prices[0];
		 int max=prices[prices.length-1];
		 

		 for(int i=1;i<prices.length;i++){
			 if(min>prices[i]){
				 min=prices[i];
			 }
			 if(left[i-1]>prices[i]-min){
				 left[i]=left[i-1];
			 }else{
				 left[i]=prices[i]-min;
			 }
		 }
		 for(int i=prices.length-2;i>=0;i--){
			 if(max<prices[i]){
				 max=prices[i];
			 }
			 if(right[i+1]<max-prices[i]){
				 right[i]=max-prices[i];
			 }else{
				 right[i]=right[i+1];
			 }
		 }
		 for(int i=0;i<prices.length;i++){
			 if(total<left[i]+right[i]){
				 total=left[i]+right[i];
			 }
		 }
		 return total;		 
	 }
	 //第二次还是不会，要看别人的思路
	 public int maxProfit3(int[] prices){
		 if(prices.length==0||prices.length==1){
				return 0;
			}
		 int[] left=new int[prices.length];//保存的是0到i之间的最大profit的值
		 int[] right=new int[prices.length];
		 int total=0;
		 int min=prices[0];
		 int max=prices[prices.length-1];
		 
		 for(int i=1;i<left.length;i++){
			 left[i]=prices[i]-min>left[i-1]?prices[i]-min:left[i-1];
			 min=Math.min(min, prices[i]);
		 }
		 for(int i=left.length-2;i>0;i--){//从左到右就是找最小值去减，从右到左就是找最大值去减别人
	//第三轮做发现这里这样写应该是错的，right[i]应该是找right[i+1]来比较最大值，而不是i-1，
			 //这样得出的dp数组应该是错的但是居然accept了，可能是leetcode的例子问题
			 //accept了
			 right[i]=max-prices[i]>right[i-1]?max-prices[i]:right[i-1];
			 
			 max=Math.max(max, prices[i]);
		 }
		 for(int i=0;i<left.length;i++){
			 total=Math.max(left[i]+right[i], total);
		 }
		 return total;
		 
	 }
	 //第三轮，思路是记得的，左边扫一遍右边扫一遍。就是dp数组意义搞错了。
	 public int maxProfit4(int[] prices){
		 if(prices.length==0){
			 return 0;
		 }
		 int rs=0;
		 int[] dp1=new int[prices.length];
		 int[] dp2=new int[prices.length];
		 
		 int min=prices[0];
		 for(int i=1;i<prices.length;i++){
			 
			 dp1[i]=prices[i]-min>dp1[i-1]?prices[i]-min:dp1[i-1];
			 if(min>prices[i]){
				 min=prices[i];
			 }
		 }
		 
		 int max=prices[prices.length-1];
		 for(int i=prices.length-2;i>=0;i--){
			//这里注意，要想清楚，dp2的意义是i到A.lenth-1之间的最大profit，没想清楚的话dp2会写错
			 //最后的出来的dp1是第一个值是0，dp2的最后一个值是0。
			 dp2[i]=max-prices[i]>dp2[i+1]?max-prices[i]:dp2[i+1];
			 if(max<prices[i]){
				 max=prices[i];
			 }
		 }
		 for(int i=0;i<dp1.length;i++){
			// 想一想，如果说只进行一个交易的话，假如这个交易是在前半段的话，那么这个
			 //for循坏也能得出正确的值，因为那样的话，肯定说明后半段的所有交易肯定都不赚钱，即dp2
			 //都是0。同理，如果这个交易再后半段的话，说明前半段不赚钱，所以dp1也都是0
			 rs=Math.max(dp1[i]+dp2[i], rs);
		 }
		 return rs;
	 }

}
