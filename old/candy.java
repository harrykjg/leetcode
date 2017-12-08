
//http://blog.csdn.net/lanxu_yy/article/details/17752273 看他给的例子
//http://blog.csdn.net/kenden23/article/details/14003169 看他给的例子
//http://jixiangsanbao.wordpress.com/2014/07/26/candy/  看他知道是左右各扫一遍
public class candy {

	public static void main(String[] args) {
		
		int[] x={4,2,3,4,1};
		candy xx=new candy();
		System.out.println(xx.candy2(x));
		
	}

	public int candy(int[] ratings) {

	
		int[] can = new int[ratings.length];
		int sum=0;

		if (ratings.length == 1) {
			return 1;
		}
		if (ratings.length == 0) {
			return 0;
		}

		for (int i = 0; i < can.length; i++) {
			can[i] = 1;
		}

		for (int i = 0; i < can.length-1; i++) {//从前一个往后一个看，加后者
			if (ratings[i] < ratings[i + 1] && can[i] >= can[i+1]) {
				can[i+1]=can[i]+1;
			}	
		}
		//从后往前看，加前者
		
		for(int k=can.length-1;k>0;k--){
			if(ratings[k]<ratings[k-1]&&can[k-1]<=can[k]){
				can[k-1]=can[k]+1;
			}
		}
		
		for(int i=0;i<can.length;i++){
			sum+=can[i];
		}
		return sum;

	}
	//第二次写，看了他们的思路和几个例子之后才想得到.拿1,2,3,4,5,5,10,3,2,8,9这个例子想
	public int candy2(int[] ratings) {
		if(ratings.length==0){
			return 0;
		}
		int[] dp=new int[ratings.length];
		dp[0]=1;
		for(int i=0;i<dp.length-1;i++){
			if(ratings[i]<ratings[i+1]){
				dp[i+1]=dp[i]+1;
			}else{
				dp[i+1]=1;
			}
		}
		for(int i=dp.length-2;i>=0;i--){
			if(ratings[i]>ratings[i+1]&&dp[i]<=dp[i+1]){//这里开始时少了dp[i]<=dp[i+1]这个条件，
				                           //用4,2,3,4,1试就知道了，因为如果dp的第i个数本来就比第
				dp[i]=dp[i+1]+1;           //i+1个数值大的话，就不用管了，否则才要加1
			}
		}
		int rs=0;
		for(int i=0;i<dp.length;i++){
			rs+=dp[i];
		}
		return rs;
	}
//第三轮，算是记得思路.还要记得相邻rating一样的话，糖数可以不同的
	public int candy3(int[] ratings) {
		if(ratings.length==0){
			return 0;
		}
		int[] dp=new int[ratings.length];
		dp[0]=1;
		int count=0;
		
		for(int i=1;i<ratings.length;i++){
			if(ratings[i]>ratings[i-1]){
				dp[i]=dp[i-1]+1;
			}else{
				dp[i]=1;
			}
		}
		for(int i=ratings.length-2;i>=0;i--){//第二个循环要记！
			if(ratings[i]>ratings[i+1]&&dp[i]<=dp[i+1]){//dp[i]<=dp[i+1]容易漏
				dp[i]=dp[i+1]+1;
			}//也不用else了
		}
		for(int i=0;i<dp.length;i++){
			count+=dp[i];
		}
		return count;
	}
}
