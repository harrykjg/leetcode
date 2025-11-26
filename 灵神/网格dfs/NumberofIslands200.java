package 灵神.网格dfs;

public class NumberofIslands200 {
    public static void main(String[] args) {

    }
    int rs=0;
    public int numIslands(char[][] grid) {
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'&&!memo[i][j]){
                    dfs(i,j,grid,memo);
                    rs++;
                }
            }
        }

        return rs;
    }
    void dfs(int r,int c,char[][] grid,boolean[][] memo){
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        for(int i=0;i<dx.length;i++){
            int row=r+dx[i];
            int col=c+dy[i];
            if(row>=0&&row<grid.length&&col>=0&&col<grid[0].length&&grid[row][col]=='1'&&!memo[row][col]){
                memo[row][col]=true;
                dfs(row,col,grid,memo);
            }
        }

    }

}
