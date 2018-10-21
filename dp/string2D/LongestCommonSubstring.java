package dp.string2D;

/**
 * Created by yufengzhu on 9/26/18.
 */
public class LongestCommonSubstring {
    public static void main(String[] args){
        LongestCommonSubstring lc=new LongestCommonSubstring();
        System.out.print(lc.longestCommonSubstring("abcd","ac"));
    }
    //lintcode才有的，改了几次才对，主要是注意substring这个需要连续的条件，否则dp会写错
    //https://www.jiuzhang.com/solutions/longest-common-substring/  看他的分析

    public int longestCommonSubstring(String A, String B) {
        // write your code here
        if(A.length()==0&&B.length()==0){
            return 0;
        }
        if(A.length()==0&&B.length()!=0){
            return 0;
        }
        if(A.length()!=0&&B.length()==0){
            return 0;
        }
        int rs=0;
        int[][] dp=new int[A.length()+1][B.length()+1];
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(A.charAt(i-1)!=B.charAt(j-1)){
                    dp[i][j]=0;//开始写成取他左，上，左上的值，那是错的，那样就是subsequence了
                }else{
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                rs=Math.max(rs,dp[i][j]);
            }
        }
        return rs;
    }
}
