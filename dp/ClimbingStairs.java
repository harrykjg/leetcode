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
    //05/25/2020,试着写滚动数组的版本，就是所有的index都%3就对了
    public int climbStairs2(int n) {
        int[] dp=new int[3];
        dp[0]=1;
        if(n<=2){
            return n;
        }
        dp[1]=2;
        for(int i=2;i<n;i++){
            dp[i%3]=dp[(i-1)%3]+dp[(i-2)%3];
        }
        return dp[(n-1)%3];
    }
}
