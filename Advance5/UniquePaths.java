package Advance5;

/**
 * Created by yufengzhu on 4/24/18.
 */
public class UniquePaths {
    //一次过
    public int uniquePaths(int m, int n) {
        if(m==0||n==0){
            return 0;
        }
        int[] dp=new int[m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0){
                    if(i==0){
                        dp[j]=1;
                    }else{
                        dp[j]=dp[j-1];
                    }
                }
                else if(j==0){
                    dp[j]=dp[j];
                }else{
                    dp[j]=dp[j-1]+dp[j];
                }

            }
        }
        return dp[dp.length-1];
    }
}
