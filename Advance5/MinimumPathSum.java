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

    //05/25/2020,看回以前原来还可以优化成一维空间
    public int minPathSum2(int[][] grid) {
        if(grid.length==0){
            return 0;
        }
        int[][] dp=new int[grid.length][grid[0].length];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(i==0&&j==0){
                    dp[i][j]=grid[i][j];
                }else if(i==0){
                    dp[i][j]=grid[i][j]+dp[i][j-1];
                }else if(j==0){
                    dp[i][j]=grid[i][j]+dp[i-1][j];
                }else{
                    dp[i][j]=Math.min(dp[i-1][j]+grid[i][j],dp[i][j-1]+grid[i][j]);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    //一维的
    public int minPathSum3(int[][] grid) {
        if(grid.length==0){
            return 0;
        }
        int[] dp=new int[grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(i==0&&j==0){
                    dp[j]=grid[i][j];
                }else if(i==0){
                    dp[j]=grid[i][j]+dp[j-1];
                }else if(j==0){
                    dp[j]=grid[i][j]+dp[j];
                }else{
                    dp[j]=Math.min(dp[j]+grid[i][j],dp[j-1]+grid[i][j]);
                }
            }
        }
        return dp[dp.length-1];
    }

}
