package SomeInterviews.snap;

import java.util.Arrays;

public class LongestIncreasingPathinaMatrix329 {
    // 3/8/2026想的是dp，那么一行一行来，那么遍历到[i][j]的时候看左边，或者上面的值，现在问题来了，是看谁大于谁？如果当前值大于左边或上面的，可以更新
    //当前dp[i][j]=左边/右边+1，但是如果当前值小于左边或上面的话呢？你得更新左边或上面的值？这是可以的吗？应该是不行的，应该是只能更新当前[i][j]。
    //对于题目第一个例子从左上到右下不行，但是从右下到左上可以,所以我想着就是左上到右下还有右下到左上都来一遍，结果是不行的，反例[[7,8,9],[9,7,6],[7,2,3]]
    //即这种弓形左右拐的不行。那就是还得dfs加memo
    static void main() {
        int[][] m={{9,9,4},{6,6,8},{2,1,1}};
        LongestIncreasingPathinaMatrix329 li=new LongestIncreasingPathinaMatrix329();
        System.out.println(li.longestIncreasingPath(m));
    }
    public int longestIncreasingPath(int[][] matrix) {
        int rs=0;
        int[][] dp=new int[matrix.length][matrix[0].length];
        for (int[] a:dp){
            Arrays.fill(a,-1);
        }
        for (int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                int cur=dfs(i,j,matrix,dp);
                rs=Math.max(rs,cur);
            }
        }
        return rs;

    }
    int dfs(int row,int col,int[][] matrix,int[][] dp){
        if(dp[row][col]!=-1){
            return dp[row][col];
        }
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        dp[row][col]=Math.max(dp[row][col],1);
        int next=0;
        for (int i=0;i<4;i++){
            int r=row+dx[i];
            int c=col+dy[i];

            if(r>=0&&r<matrix.length&&c>=0&&c<matrix[0].length&&matrix[r][c]>matrix[row][col]){
                int n=dfs(r,c,matrix,dp);
                next=Math.max(next,n);

            }

        }
        dp[row][col]+=next;//这里容易写错写在for loop里面就错了
        return dp[row][col];
    }
}
