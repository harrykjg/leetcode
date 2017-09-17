package dp;

/**
 * Created by 502575560 on 9/10/17.
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        int[] dp=new int[n];
        dp[0]=1;
        if(n<=1){
            return 1;
        }
        dp[1]=2;
        for(int i=2;i<n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[dp.length-1];

    }
}
