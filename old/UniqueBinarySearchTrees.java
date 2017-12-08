//http://jixiangsanbao.wordpress.com/2014/05/16/unique-binary-search-trees/
public class UniqueBinarySearchTrees {

	public int numTrees(int n) {
		if(n==0) return 0;
		if(n==1) return 1;
		int [] dp=new int[n+1];
		dp[0]=1;
		dp[1]=1;
		for(int i=2;i<=n;i++){
			dp[i]=create(dp,i);
			
		}
		return dp[n];

	}
	public int create(int[] dp, int n){
		int rs=0;
		for(int i=1;i<=n;i++){//比如n=4，就是以1为根，左边0个右边3个，加上以2为根，左边1个右边2个
			//加上3为根，左边2个右边一个，加上以4为根，左边3个右边0个
			rs+=dp[i-1]*dp[n-i];
		}
		return rs;
	}
	
	//第三次
	public int numTrees2(int n) {
		if(n==0) return 0;
		if(n==1) return 1;
		int [] dp=new int[n+1];
		dp[0]=1;//注意这是1
		dp[1]=1;
		gen(dp,n);
		return dp[n];
	
	}
	private int gen(int[] dp,int n){
		if(n==1){
			return 1; 
		}
		
		dp[n-1]=gen(dp,n-1);
		int i=1;
		while(i<=n){
			dp[n]+=dp[i-1]*dp[n-i];//注意这是乘法
			i++;
		}
		
		return dp[n];
	}
	
}
