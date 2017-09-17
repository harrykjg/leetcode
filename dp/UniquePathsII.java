package dp;

/**
 * Created by 502575560 on 9/10/17.
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[] dp=new int[obstacleGrid[0].length];
        for(int i=0;i<obstacleGrid.length;i++){
            for(int j=0;j<obstacleGrid[0].length;j++){
                if(obstacleGrid[i][j]==1){
                    dp[j]=0;
                }else{
                    if(i==0&&j==0){
                        dp[j]=1;
                    }else if(i==0){
                        dp[j]=dp[j-1];
                    }else if(j==0){
                        dp[j]=dp[j];
                    }else{
                        dp[j]=dp[j]+dp[j-1];
                    }
                }
            }
        }
        return dp[dp.length-1];
    }
}
