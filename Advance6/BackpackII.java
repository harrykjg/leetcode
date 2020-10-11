package Advance6;

/**
 * Created by yufengzhu on 5/19/18.
 */
public class BackpackII {
    //lintcode的
    //写的不太好， dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-A[i-1]]+V[i-1]);这里开始写成和dp[i][j]比了,确实应该是dp[i-1][j]，因为dp意义应该
    //是不加入当前A[i-1]的值即dp[i-1][j]和加入A[i-1]的值之间比较取大的
//    public int backPackII(int m, int[] A, int[] V) {
        // write your code here
//        int[][] dp=new int[A.length+1][m+1]; 这代码错的
//        int rs=0;
//        for(int i=1;i<dp.length;i++){
//            for(int j=0;j<dp[0].length;i++){
//                if(A[i-1]>j){
//                    dp[i][j]=dp[i-1][j];
//                }else{
//                    dp[i][j]=Math.max(dp[i-1][j],dp[i-1][j-A[i-1]]+V[i-1]);
//
//                }
//                rs=Math.max(rs,dp[i][j]);
//            }
//        }
//        return rs;
//    }
    //05/21/2020,画图写得还行
    public int backPackII2(int m, int[] A, int[] V) {
        int[][] dp=new int[A.length+1][m+1];
        for(int i=1;i<A.length+1;i++){
            for(int j=1;j<m+1;j++){
                if(A[i-1]>j){
                    dp[i][j]=dp[i-1][j];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j-A[i-1]]+V[i-1],dp[i-1][j]);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
