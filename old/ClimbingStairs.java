public class ClimbingStairs {

//2015��11�£�ע������n��̨�ף���˼���㿪ʼվ�ڵذ��ϣ������Ǹ�dp����Ҫn+1�����ȣ�����dp[0]��վ�ڵذ��ϵ�ʱ��
//dp[i]������i��̨�׵Ĳ���
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
