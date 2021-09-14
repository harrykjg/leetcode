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

    //九章第二轮,9/24/2017,思路不好想,还是靠画图,还是不太熟,并且还可以优化成1维空间
    public int backPackII2(int m, int[] A, int V[]) {
        int rs=Integer.MIN_VALUE;
        int[][] dp=new int[A.length+1][m+1];
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(A[i-1]>j){
                    dp[i][j]=dp[i-1][j];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j-A[i-1]]+V[i-1],dp[i-1][j]);//注意dp[i-1][j]是看上一行的同列,而不是看同行前一个列
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
//6/6/2021,发现A不需要sort，导致其实和backpack一样，就是把max(dp[i-1][j-A[i-1]]+A[i-1],dp[i-1][j])改成max(dp[i-1][j-A[i-1]]+V[i-1],dp[i-1][j]);
    public int backPackII3(int m, int[] A, int V[]) {
        int[][] dp=new int[A.length+1][m+1];
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(j>=A[i-1]){
                    dp[i][j]=Math.max(dp[i-1][j-A[i-1]]+V[i-1],dp[i-1][j]);
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
