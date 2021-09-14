package GraphAndSearch;

import java.util.Map;

public class PathwithMaximumGold {
    //7/30/2021 直接dfs一次过
    int rs=0;
    public int getMaximumGold(int[][] grid) {
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]!=0){
                    memo[i][j]=true;
                    dfs(0,i,j,memo,grid);
                    memo[i][j]=false;
                }
            }
        }
        return rs;
    }
    void dfs(int cur,int i,int j,boolean[][] memo,int[][] grid){
        int temp=cur+grid[i][j];
        rs=Math.max(temp,rs);

        if (i>0&&grid[i-1][j]!=0&&!memo[i-1][j]){
            memo[i-1][j]=true;
            dfs(temp,i-1,j,memo,grid);
            memo[i-1][j]=false;
        }
        if (j+1<grid[0].length&&grid[i][j+1]!=0&&!memo[i][j+1]){
            memo[i][j+1]=true;
            dfs(temp,i,j+1,memo,grid);
            memo[i][j+1]=false;
        }
        if (i+1<grid.length&&grid[i+1][j]!=0&&!memo[i+1][j]){
            memo[i+1][j]=true;
            dfs(temp,i+1,j,memo,grid);
            memo[i+1][j]=false;
        }
        if (j>0&&grid[i][j-1]!=0&&!memo[i][j-1]){
            memo[i][j-1]=true;
            dfs(temp,i,j-1,memo,grid);
            memo[i][j-1]=false;
        }
    }
}
