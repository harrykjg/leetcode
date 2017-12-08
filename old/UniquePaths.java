//http://blog.csdn.net/linhuanmars/article/details/22126357
public class UniquePaths {
	//这样应该是对的，但是leetcode会超时，要用动态规划
	
	
	public static void main(String[] args) {
		UniquePaths up=new UniquePaths();
		System.out.println(up.uniquePaths3(10, 1));
	}
	
	int count=0;
	public int uniquePaths(int m, int n) {
		if(m==0||n==0){
			return 0;
		}
		
		int[][] A=new int[m][n];
		int i=0;
		int j=0;
		
		go(i,j,m,n);
		return count;

	}
	public void go(int i,int j,int m,int n){
		if(i>=m||j>=n){
			return;
		}
		if(i==m-1&&j==n-1){
			count++;
			return;
		}
		if(j+1<n){
		go(i,j+1,m,n);
		}
		if(i+1<m){
		go(i+1,j,m,n);
		}
	}
	//这是用二维数组存的，可以优化为一维，即先存第一行，在从左往右更新其，使其变成第二行，and so on
	//见草稿解释
	public int uniquePaths2(int m, int n) {
		int[][] dp=new int[m][n];
		dp[0][0]=1;
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if(i==0&&j==0){
					dp[i][j]=1;
					continue;
				}
				if(i-1<0){
					dp[i][j]=dp[i][j-1];
					continue;
				}
				if(j-1<0){
					dp[i][j]=dp[i-1][j];
					continue;
				}
				dp[i][j]=dp[i-1][j]+dp[i][j-1];
			}
		}
		return dp[m-1][n-1];
	}
//1/10，一维的
	 public int uniquePaths3(int m, int n) {
	        
	        int[] dp=new int[n];//注意这里就要把dp的长度设为列数，即n
	        
	        for(int i=0;i<m;i++){
	            for(int j=0;j<n;j++){
	             if(i==0&&j==0){
	                 dp[j]=1;
	                 continue;
	             }   
	             if(i==0){
	                 dp[j]=dp[j-1];
	                 continue;
	             }
	             if(j==0){//这里注意。。dp[j]=dp[j];看着很怪，但就这样
	                 dp[j]=dp[j];
	                 continue;
	             }
	             dp[j]+=dp[j-1];
	             
	             
	            }
	        }
	        return dp[dp.length-1];
	    }
}
