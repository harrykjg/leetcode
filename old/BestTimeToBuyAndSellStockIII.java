//http://www.2cto.com/kf/201311/256139.html
//http://blog.csdn.net/jellyyin/article/details/8671277
//http://blog.csdn.net/linhuanmars/article/details/23236995
public class BestTimeToBuyAndSellStockIII {
	public static void main(String[] args) {
		BestTimeToBuyAndSellStockIII bt=new BestTimeToBuyAndSellStockIII();
		int[] n={6,1,3,2,4,7};
			System.out.println(bt.maxProfit4(n));	
		}
	
	
	
	 public int maxProfit(int[] prices) {//nƽ���㷨���ᳬʱ
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
	 public int maxProfit2(int[] prices){//���Ȼ�ͽж�̬�滮��
		 
		 if(prices.length==0||prices.length==1){
				return 0;
			}
		 int[] left=new int[prices.length];//����[0,i]��������ֵ
		 int[] right=new int[prices.length];//����[i,length-1]��������ֵ
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
	 //�ڶ��λ��ǲ��ᣬҪ�����˵�˼·
	 public int maxProfit3(int[] prices){
		 if(prices.length==0||prices.length==1){
				return 0;
			}
		 int[] left=new int[prices.length];//�������0��i֮������profit��ֵ
		 int[] right=new int[prices.length];
		 int total=0;
		 int min=prices[0];
		 int max=prices[prices.length-1];
		 
		 for(int i=1;i<left.length;i++){
			 left[i]=prices[i]-min>left[i-1]?prices[i]-min:left[i-1];
			 min=Math.min(min, prices[i]);
		 }
		 for(int i=left.length-2;i>0;i--){//�����Ҿ�������Сֵȥ�������ҵ�����������ֵȥ������
	//��������������������дӦ���Ǵ�ģ�right[i]Ӧ������right[i+1]���Ƚ����ֵ��������i-1��
			 //�����ó���dp����Ӧ���Ǵ�ĵ��Ǿ�Ȼaccept�ˣ�������leetcode����������
			 //accept��
			 right[i]=max-prices[i]>right[i-1]?max-prices[i]:right[i-1];
			 
			 max=Math.max(max, prices[i]);
		 }
		 for(int i=0;i<left.length;i++){
			 total=Math.max(left[i]+right[i], total);
		 }
		 return total;
		 
	 }
	 //�����֣�˼·�Ǽǵõģ����ɨһ���ұ�ɨһ�顣����dp�����������ˡ�
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
			//����ע�⣬Ҫ�������dp2��������i��A.lenth-1֮������profit��û������Ļ�dp2��д��
			 //���ĳ�����dp1�ǵ�һ��ֵ��0��dp2�����һ��ֵ��0��
			 dp2[i]=max-prices[i]>dp2[i+1]?max-prices[i]:dp2[i+1];
			 if(max<prices[i]){
				 max=prices[i];
			 }
		 }
		 for(int i=0;i<dp1.length;i++){
			// ��һ�룬���˵ֻ����һ�����׵Ļ������������������ǰ��εĻ�����ô���
			 //forѭ��Ҳ�ܵó���ȷ��ֵ����Ϊ�����Ļ����϶�˵�����ε����н��׿϶�����׬Ǯ����dp2
			 //����0��ͬ�������������ٺ��εĻ���˵��ǰ��β�׬Ǯ������dp1Ҳ����0
			 rs=Math.max(dp1[i]+dp2[i], rs);
		 }
		 return rs;
	 }

}
