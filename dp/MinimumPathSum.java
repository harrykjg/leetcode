package dp;

/**
 * Created by 502575560 on 9/4/17.
 */
public class MinimumPathSum {
    public static void main(String[] args){
        MinimumPathSum mp=new MinimumPathSum();
        int[][] grid={{1,3,1},{1,5,1},{4,2,1}};
        mp.minPathSum2(grid);
    }
    //妈的还写不对,就是下标
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[][] dp=new int[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(i==0&&j==0){
                    dp[i][j]=grid[i][j];
                }
                else if(i==0){
                    dp[i][j]=dp[i][j-1]+grid[i][j];
                }else if(j==0){
                    dp[i][j]=dp[i-1][j]+grid[i][j];
                }else{
                    dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
                }

            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    //6/4/2021,直接写1维的，初始化第一行时没写对
    public int minPathSum2(int[][] grid) {
        if(grid.length==0){
            return 0;
        }
        int[] dp=new int[grid[0].length];
        dp[0]=grid[0][0];
        for(int i=1;i<grid[0].length;i++){
            dp[i]=dp[i-1]+grid[0][i];
        }
        for(int i=1;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(j>0){
                    dp[j]=Math.min(dp[j-1],dp[j])+grid[i][j];
                }else{
                    dp[j]+=grid[i][j];
                }
            }
        }
        return dp[grid[0].length-1];
    }
}
