package dp;

/**
 * Created by 502575560 on 9/10/17.
 */
public class UniquePaths {
    //九章第二轮
    public int uniquePaths(int m, int n) {
        int[] dp=new int[n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0&&j==0){
                    dp[0]=1;
                }else if(i==0){
                    dp[j]=dp[j-1];
                }else if(j==0){
                    dp[j]=dp[j];//这里开始写错了写成dp[i-1]就错了
                }else{
                    dp[j]=dp[j]+dp[j-1];
                }
            }
        }
        return dp[dp.length-1];
    }
}
