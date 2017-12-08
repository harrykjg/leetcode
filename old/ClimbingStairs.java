public class ClimbingStairs {

//2015年11月，注意他是n阶台阶，意思是你开始站在地板上，所以那个dp数组要n+1个长度，其中dp[0]是站在地板上的时候
//dp[i]代表到底i阶台阶的步数
	public int climbStairs(int n) {
		if(n==0){
			return 0;
		}
		int[] dp=new int[n+1];
		dp[0]=1;
		for(int i=1;i<=n;i++){
			if(i-2<0){
				dp[i]=dp[i-1];
			}
			else{
				dp[i]=dp[i-1]+dp[i-2];
			}
		}
		return dp[n];
	}
}
