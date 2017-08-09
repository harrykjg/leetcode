package dp.string2D;

/**
 * Created by 502575560 on 7/7/17.
 */
public class EditDistance {
    //http://blog.163.com/gjx_12358@126/blog/static/895363452014232191498/
    //lintcode写的,但是还是不太顺,还是dp的理解问题,还有index要注意
    public int minDistance(String word1, String word2) {
        int[][] dp=new int[word2.length()+1][word1.length()+1];
        for(int i=0;i<dp[0].length;i++){//开始初始化的时候写成了整个二维数组都初始化了
            dp[0][i]=i;
        }
        for(int i=0;i<dp.length;i++){
            dp[i][0]=i;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(word1.charAt(j-1)==word2.charAt(i-1)){
                    dp[i][j]=dp[i-1][j-1];//这里开始写成math.min(dp[i][j],dp[i-1][j-1])
                }else{
                    dp[i][j]=Math.min(dp[i-1][j]+1,Math.min(dp[i][j-1]+1,dp[i-1][j-1]+1));
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }



























//还是差一点,没彻底想清楚dp加减替换的操作,比如dp[i-1][j]的含义我就卡住了,看九章ppt的advance dp下的18页的图,可以理解kar怎么转换成ma
    public int minDistance2(String word1, String word2) {
        int[][] dp=new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<dp.length;i++){
            dp[i][0]=i;
        }
        for (int i=0;i<dp[0].length;i++){
            dp[0][i]=i;
        }
        for (int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                    continue;
                }
                dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
