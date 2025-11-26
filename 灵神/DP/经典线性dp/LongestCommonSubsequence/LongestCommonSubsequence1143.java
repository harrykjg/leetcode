package 灵神.DP.经典线性dp.LongestCommonSubsequence;

public class LongestCommonSubsequence1143 {
    public static void main(String[] args) {

    }

    /*
    还是画图看
       0 a b c d e c
     0 0
     a   1 1 1 1 1 1
     c   1 1 2 2 2 2
     e

     dp[i][j]= if charAt(i)==charAt(j) {dp[i-1][j-1]+1} else{dp[i-1][j]}

          0 b l
        0 0
        y   0 0
        b   1 1---->注意这里是1，说明得看左边的继承过来的
        y

     */

    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp=new int[text1.length()+1][text2.length()+1];
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
