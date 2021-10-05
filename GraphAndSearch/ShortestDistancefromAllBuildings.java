package GraphAndSearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 8/13/18.
 */
public class ShortestDistancefromAllBuildings {
    //https://leetcode.com/problems/shortest-distance-from-all-buildings/discuss/76891/Java-solution-with-explanation-and-time-complexity-analysis
    //看了一下讨论，貌似也没有特别好的方法，原始的想法就是每个0去bfs到各个1，记录下该点到每个1的距离加起来，取最小的就完了。
    // 答案是从每个1，bfs到所有的0，记录下每个0到各个1的距离，取最小的就完了.即每个1都进行全局的bfs，挺暴力的
    //https://www.youtube.com/watch?v=F7AM7AGJZYE
    //8/17/2021
    int[][] dist;
    int[][] count;
    public int shortestDistance(int[][] grid) {
        dist=new int[grid.length][grid[0].length];
        count=new int[grid.length][grid[0].length];
        int num=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    bfs(i,j,grid);
                    num++;
                }
            }
        }
        int rs=Integer.MAX_VALUE;
        for (int i=0;i<dist.length;i++){
            for (int j=0;j<dist[0].length;j++){
                if (count[i][j]==num){
                    rs=Math.min(rs,dist[i][j]);
                }
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }
    int[] dx={-1,0,1,0};
    int[] dy={0,1,0,-1};
    void bfs(int i,int j,int[][] grid){
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{i,j});
        memo[i][j]=true;
        int rs=0;
        while (!q.isEmpty()){
            int size=q.size();
            for (int s=0;s<size;s++){
                int[] cur=q.poll();
                int x=cur[0];
                int y=cur[1];
                for (int k=0;k<dx.length;k++){
                    int xx=x+dx[k];
                    int yy=y+dy[k];
                    if (xx>=0&&xx<grid.length&&yy>=0&&yy<grid[0].length&&memo[xx][yy]==false&&grid[xx][yy]==0){
                        memo[xx][yy]=true;
                        dist[xx][yy]+=rs+1;
                        count[xx][yy]+=1;
                        q.offer(new int[]{xx,yy});
                    }
                }
            }
            rs++;
        }
    }
//8/23/2021
    public int shortestDistance2(int[][] grid) {
        dist=new int[grid.length][grid[0].length];
        count=new int[grid.length][grid[0].length];
        Queue<int[]> q=new LinkedList<>();
        int n=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    q.offer(new int[]{i,j});
                    n++;
                }
            }
        }
        while (!q.isEmpty()){
            int[] cur=q.poll();
            bfs2(cur[0],cur[1],grid);
        }
        int rs=Integer.MAX_VALUE;
        for (int i=0;i<dist.length;i++){
            for (int j=0;j<dist[0].length;j++){
                if (count[i][j]==n){
                    rs=Math.min(rs,dist[i][j]);
                }
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }
    void bfs2(int i,int j,int[][] grid){
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{i,j});
        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};
        memo[i][j]=true;
        int rs=0;
        while (!q.isEmpty()){
            int size=q.size();
            for (int k=0;k<size;k++){
                int[] cur=q.poll();
                if (grid[cur[0]][cur[1]]==0){
                    dist[cur[0]][cur[1]]+=rs;
                    count[cur[0]][cur[1]]+=1;
                }
                for (int x=0;x<4;x++){
                    int r=cur[0]+dx[x];
                    int c=cur[1]+dy[x];
                    if (r<0||r>=grid.length||c<0||c>=grid[0].length||memo[r][c]||grid[r][c]!=0){
                        continue;
                    }
                    memo[r][c]=true;
                    q.offer(new int[]{r,c});
                }
            }
            rs++;
        }
        return;
    }
//10/3/2021
    int[][] dist3;
    int[][] count3;
    public int shortestDistance3(int[][] grid) {
        dist3=new int[grid.length][grid[0].length];
        count3=new int[grid.length][grid[0].length];
        int num=0;
        for (int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    num++;
                    bfs3(i,j,grid);
                }
            }
        }
        int min=Integer.MAX_VALUE;
        for (int i=0;i<dist3.length;i++){
            for (int j=0;j<dist3[0].length;j++){
                if (count3[i][j]==num&&dist3[i][j]<min){
                    min=dist3[i][j];
                }
            }
        }
        return min==Integer.MAX_VALUE?-1:min;
    }
    void bfs3(int row,int col,int[][] grid){
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{row,col});
        memo[row][col]=true;
        int rs=0;
        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
//                dist3[cur[0]][cur[1]]+=rs;
//                count3[cur[0]][cur[1]]++;//开始写在这里其实不太对，因为这个点可能是1本身，不应该count++，应该放在下面那
                for (int j=0;j<4;j++){
                    int r=cur[0]+dx[j];
                    int c=cur[1]+dy[j];
                    if (r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&!memo[r][c]&&grid[r][c]==0){
                        q.offer(new int[]{r,c});
                        memo[r][c]=true;
                        dist3[r][c]+=rs+1;
                        count3[r][c]++;
                    }
                }
            }
            rs++;
        }
    }
}
