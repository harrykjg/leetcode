package dp.memorize;

/**
 * Created by 502575560 on 7/31/17.
 */
public class LongestIncreasingContinuoussubsequenceII {
    //https://algorithm.yuanbin.me/zh-hans/dynamic_programming/longest_increasing_continuous_subsequence_ii.html 题目在这
    //https://codesolutiony.wordpress.com/2015/05/25/lintcode-longest-increasing-continuous-subsequence-ii/ 代码看他的
    public int longestIncreasingContinuousSubsequenceII(int[][] a) {
        int[][] dp=new int[a.length][a[0].length];
        int rs=0;
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                if(dp[i][j]==0){
                    dp[i][j]=dfs(dp,a,i,j);
                    rs=Math.max(rs,dp[i][j]);
                }
            }
        }
        return rs;
    }
    int dfs(int[][] dp,int[][] a,int i,int j ){
        if(dp[i][j]!=0){
            return dp[i][j];
        }
        int up=0,right=0,left=0,down=0;
        if(i>0&&dp[i-1][j]<dp[i][j]){
            up=dfs(dp,a,i-1,j);
        }
        if(j+1<a[0].length&&dp[i][j+1]<dp[i][j]){
            right=dfs(dp,a,i,j+1);
        }
        if(i+1<a.length&&dp[i+1][j]<dp[i][j]){
            down=dfs(dp,a,i+1,j);
        }
        if(j>0&&dp[i][j-1]<dp[i][j]){
            left=dfs(dp,a,i,j-1);
        }
        dp[i][j]=Math.max(Math.max(up,right),Math.max(left,down))+1;//这个+1有点绕,如果说这个点小于上下左右某个点,则不会进去if里面,
        return dp[i][j];                                    //则该上下左右就是0,那么dp[i][j]本来就该是0+1,如果进去了if则也应该是+1
    }
}
