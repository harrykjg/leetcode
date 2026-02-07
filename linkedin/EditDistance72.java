package linkedin;

import java.util.List;

public class EditDistance72 {
    static void main() {

    }
    //2/6/2026 差点忘了，要画图列一个矩阵，初始化，然后就知道了
    public int minDistance(String word1, String word2) {

        int[][] dp=new int[word1.length()+1][word2.length()+1];
        for (int i=0;i<dp.length;i++){
            dp[i][0]=i;
        }
        for (int i=0;i<dp[0].length;i++){
            dp[0][i]=i;
        }
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
