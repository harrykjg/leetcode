package SomeInterviews.snap;

public class MaximumIslandPerimeter {
    static void main() {
        MaximumIslandPerimeter mi=new MaximumIslandPerimeter();
//        int[][] g={{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        int[][] g={{1,1,0},{1,0,0},{1,1,0}};
        System.out.println(mi.maxPerimeter(g));
    }
    //LC上好想没有
    //https://www.hack2hire.com/questions/coding-questions/6933c5bb5f306485cc5f07bf/practice
    //3/8/2026 应该就是看这个island周围有几个邻居，那么这个island贡献的周长就是4-邻居数量
    //试了几个应该对
    public int maxPerimeter(int[][] grid) {
        int rs=0;
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                 if (grid[i][j]==1&&!memo[i][j]){
                     int cur=dfs(i,j,grid,memo);
                     rs=Math.max(rs,cur);
                 }
            }
        }
        return rs;
    }
    int dfs(int row,int col,int[][] grid,boolean[][] memo){
        memo[row][col]=true;
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        int rs=4;
        int nei=0;
        for (int i=0;i<4;i++){
            int r=dx[i]+row;
            int c=dy[i]+col;
            if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&grid[r][c]==1){
                nei++;
                if(!memo[r][c]){
                   rs+=dfs(r,c,grid,memo);
                }
            }
        }
        rs-=nei;
        return rs;
    }
}
