package Advance6;

/**
 * Created by yufengzhu on 5/6/18.
 */
public class LongestCommonSubsequence {
    //5/6/2018,一次过，但是没有写很好的例子直接猜着写的
    public int longestCommonSubsequence(String A, String B) {
        int[][] dp=new int[B.length()+1][A.length()+1];
        for(int i=1;i<B.length()+1;i++){
            for(int j=1;j<A.length()+1;j++){
                if(B.charAt(i-1)==A.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],Math.max(dp[i][j-1],dp[i-1][j-1]));//以前写的也不用比较dp[i-1][j-1]的
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
//05/21/2020,写得还行吧
    public int longestCommonSubsequence2(String A, String B) {
        int[][] dp=new int[A.length()+1][B.length()+1];
        for(int i=1;i<A.length()+1;i++){
            for(int j=1;j<B.length()+1;j++){
                if(A.charAt(i-1)==B.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j-1],Math.max(dp[i-1][j],dp[i][j-1]));
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
