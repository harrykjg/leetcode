package dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class PerfectSquares {
    //7/10/2021自己想的直接accept了
    public int numSquares(int n) {
        int[] dp=new int[n+1];
        for (int i=1;i<dp.length;i++){
            dp[i]=i;
            for (int j=i/2;j>=0;j--){//就是dp[i] 在0到i中间试着插进一个数的平方，j尽量从大的开始搞
                if (j*j<=i){
                    dp[i]=Math.min(dp[i-j*j]+1,dp[i]);
                }
            }
        }
        return dp[dp.length-1];
    }
}
