package 灵神.网格dfs.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathinaGridwithObstaclesElimination1293 {
    public static void main(String[] args) {

    }
    //1/25/2026不会,看以前的解释
    public int shortestPath(int[][] grid, int k) {
        boolean[][][] memo=new boolean[grid.length][grid[0].length][k+1];//这个k次是只要一个数记录用了几个k还是用k+1个状态呢
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{0,0,0});
        memo[0][0][0]=true;
        int rs=0;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
                if(cur[0]==grid.length-1&&cur[1]==grid[0].length-1&&cur[2]<=k){
                    return rs;
                }
                int[] dx={0,1,0,-1};
                int[] dy={1,0,-1,0};
                for (int j=0;j<4;j++){
                    int r=cur[0]+dx[j];
                    int c=cur[1]+dy[j];
                    if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length){
                        if(grid[r][c]==1&&cur[2]<k&&!memo[r][c][cur[2]+1]){
                            q.offer(new int[]{r,c,cur[2]+1});
                            memo[r][c][cur[2]+1]=true;
                        }else if (grid[r][c]==0&&!memo[r][c][cur[2]]){
                            memo[r][c][cur[2]]=true;
                            q.offer(new int[]{r,c,cur[2]});
                        }
                    }
                }

            }
            rs++;
        }

        return -1;
    }
}
