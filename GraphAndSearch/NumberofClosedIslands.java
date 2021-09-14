package GraphAndSearch;

public class NumberofClosedIslands {
    //7/28/2021开始不知道怎么做。看了别人知道就是把和边界相连的改成水。剩下的都是enclosed的了
    //https://leetcode.com/problems/number-of-closed-islands/discuss/425150/JavaC%2B%2B-with-picture-Number-of-Enclaves
    public int closedIsland(int[][] grid) {
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==0&&(i==0||j==0||i==grid.length-1||j==grid[0].length-1)){
                    fill(i,j,grid);
                }
            }
        }
        int rs=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]==0){
                    fill(i,j,grid);//再遇见0的就dfs fill成1
                    rs++;
                }
            }
        }
        return rs;
    }
    void fill(int i,int j,int[][] grid){
        grid[i][j]=1;

        if (i>0&&grid[i-1][j]==0){
            fill(i-1,j,grid);
        }
        if (j+1<grid[0].length&&grid[i][j+1]==0){
            fill(i,j+1,grid);
        }
        if (i+1<grid.length&&grid[i+1][j]==0){
            fill(i+1,j,grid);
        }
        if (j>0&&grid[i][j-1]==0){
            fill(i,j-1,grid);
        }
    }
}
