package 灵神.网格dfs.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge934 {
    public static void main(String[] args) {

    }
    //1/25/2026 多个起点和终点,就是先用一个dfs找到某一岛的所有点加入queue，把他们的值改成0，然后第二个岛不用找。单后bfs这个q，找到
    //1的点就是找到桥了，答案是rs-1
    public int shortestBridge(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        boolean[][] memo=new boolean[m][n];
        Queue<int[]> q=new LinkedList<>();
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if(grid[i][j]==1&&!memo[i][j]){
                    memo[i][j]=true;
                    dfs(0,0,memo,grid,q);
                    break;
                }
            }
            if (q.size()>0){
                break;
            }
        }
        int rs=0;
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        memo=new boolean[m][n];
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
                memo[cur[0]][cur[1]]=true;
                if(grid[cur[0]][cur[1]]==1){
                    return rs-1;
                }
                for (int j=0;j<4;j++){
                    int nx=cur[0]+dx[j];
                    int ny=cur[1]+dy[j];
                    if(nx>=0&&nx<m&&ny>=0&&ny<n&&!memo[nx][ny]){
                        memo[nx][ny]=true;
                        q.offer(new int[]{nx,ny});
                    }
                }
            }
            rs++;
        }
        return -1;
    }

    void dfs(int row,int col,boolean[][] memo,int[][] grid,Queue<int[]> q){
        q.offer(new int[]{row,col});
        grid[row][col]=0;
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        for (int i=0;i<4;i++){
            int r=row+dx[i];
            int c=col+dy[i];
            if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&grid[r][c]==1){
                dfs(r,c,memo,grid,q);
            }
        }
    }
}
