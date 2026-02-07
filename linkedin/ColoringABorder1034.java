package linkedin;

public class ColoringABorder1034 {

    //改了一次对了，不用额外空间染色，就是dfs的时候先判断这个点是不是boarder，如果是也不要立马染色，因为染了之后就影响判断邻居是不是和原来的颜色
    //相同了，因此dfs的递的时候不要染色，归的时候再染色，举例子想想
    /*
    举例子看这个例子如果要【1，1】位置染色，dfs递的时候不染色就可以找到同颜色的邻居，1个点可能dfs4次，当4次dfs归的时候再染当前点就ok了，因为
    四个dfs归的时候所有邻居也都已经遍历过了。
       1 1 1 1
       1 1 1 1
       1 1 1 1
     */
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        memo[row][col]=true;
        dfs(row,col,color,memo,grid);
        return grid;

    }
    void dfs(int row,int col,int color,boolean[][] memo,int[][] grid){

        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        boolean isBoarder=check(row,col,color,grid);
        for (int i=0;i<4;i++){
            int r=dx[i]+row;
            int c=dy[i]+col;
            if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&grid[r][c]==grid[row][col]&&!memo[r][c]){
                memo[r][c]=true;
                dfs(r,c,color,memo,grid);
            }
        }
        if(isBoarder){
            grid[row][col]=color;
        }
    }
    boolean check(int row,int col,int color,int[][] grid){
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        if(row==0||row==grid.length-1||col==0||col==grid[0].length-1){
            return true;
        }
        for (int i=0;i<4;i++) {
            int r = dx[i] + row;
            int c = dy[i] + col;
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length ) {

                if(grid[r][c]!=grid[row][col]){
                    return true;
                }
            }
        }
        return false;
    }

}
