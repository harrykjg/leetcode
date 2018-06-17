package Advance6;

/**
 * Created by yufengzhu on 5/8/18.
 */
public class DistinctSubsequences {
    //5/18/2018,知道画图，但是还是不好搞啊,以为只要初始化dp[0][0]就可以，其实要整个第一行初始化为1才行
    public int numDistinct(String s, String t) {
        int[][] dp=new int[t.length()+1][s.length()+1];
        for(int i=0;i<dp[0].length;i++){
            dp[0][i]=1;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(s.charAt(j-1)==t.charAt(i-1)){
                    dp[i][j]=dp[i][j-1]+dp[i-1][j-1];
                }else{
                    dp[i][j]=dp[i][j-1];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
