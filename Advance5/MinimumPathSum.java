package Advance5;

/**
 * Created by yufengzhu on 4/28/18.
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if(grid.length==0){
            return 0;
        }
        int[] dp=new int[grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(i==0){
                    if(j==0){
                        dp[j]=grid[0][0];
                    }else{
                        dp[j]=dp[j-1]+grid[i][j];
                    }
                }else{
                    if(j==0){
                        dp[j]=dp[j]+grid[i][j];
                    }else{
                        dp[j]=Math.min(dp[j-1],dp[j])+grid[i][j];
                    }
                }
            }
        }
        return dp[dp.length-1];
    }

}
