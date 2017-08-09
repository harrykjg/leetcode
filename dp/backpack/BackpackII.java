package dp.backpack;

import java.util.Arrays;

/**
 * Created by 502575560 on 8/7/17.
 */
public class BackpackII {
    //第一眼感觉有点像walmart叠罗汉那道题那样,其实不一样
    //画图找规律居然一次过
    //http://www.jiuzhang.com/solutions/backpack-ii/
    //https://segmentfault.com/a/1190000006325321
    public int backPackII(int m, int[] A, int V[]) {

        int[][]dp=new int[A.length+1][m+1];
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(j>=A[i-1]){
                    dp[i][j]=Math.max(dp[i-1][j-A[i-1]]+V[i-1],dp[i-1][j]);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
