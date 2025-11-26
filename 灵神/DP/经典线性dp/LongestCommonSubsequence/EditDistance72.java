package 灵神.DP.经典线性dp.LongestCommonSubsequence;

public class EditDistance72 {
    public static void main(String[] args) {
        System.out.println(minDistance("horse","ros"));
    }

    public static int minDistance(String word1, String word2) {
        int[][] dp=new int[word2.length()+1][word1.length()+1];
        for (int i=0;i<dp[0].length;i++){
            dp[0][i]=i;
        }
        for(int j=0;j<dp.length;j++){
            dp[j][0]=j;//意义就是加从空加几个字符可以成为word2
        }
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if(word1.charAt(j-1)!=word2.charAt(i-1)){
                    dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                }else{
                    dp[i][j]=dp[i-1][j-1];//这一步开始想错了，直接继承上一个i-1，j-1，开始还考虑如果有重复的字符会不会重复利用。
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

}
