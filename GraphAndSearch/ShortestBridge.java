package GraphAndSearch;

import java.net.Inet4Address;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class ShortestBridge {
    public static void main(String[] args){
        ShortestBridge sb=new ShortestBridge();
        int[][] m={{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,0,0,0},{0,0,0,0,1,0,0},{0,0,0,0,1,0,0},{1,1,0,1,1,0,0},{1,1,0,1,1,0,0}};
//        int[][] m={{0,1},{1,0}};
        System.out.println(sb.shortestBridge(m));
    }
    //7/10/2021.思路没问题，就是写的时候容易错，就是先dfs找到一堆target和一堆begin，然后所有begin放进q里作为起点，bfs找上下左右是否属于target
    public int shortestBridge(int[][] grid) {
        HashSet<Integer> target=new HashSet<>();
        HashSet<Integer> begin=new HashSet<>();
        Set<Integer> memo=new HashSet<>();
        boolean found=false;
        int col=grid[0].length;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++) {
                if (grid[i][j]==1&&!found){
                    found=true;
                    dfs(i,j,grid,begin);
                }else if (grid[i][j]==1&&!begin.contains(i*col+j)){
                    dfs(i,j,grid,target);
                }
            }
        }
        int rs=0;
        int count=0;
        int count1=0;
        Queue<Integer> q=new LinkedList<>();
        for (int b:begin){
            q.offer(b);
            count++;
        }
        while (!q.isEmpty()){
            int temp=q.poll();
            int i=temp/col;
            int j=temp%col;
            memo.add(temp);
            count--;
            if (i>0&&target.contains((i-1)*col+j)){
                return rs;
            }
            if (i>0&&grid[i-1][j]==0){
                if (!memo.contains((i-1)*col+j)){
                    q.offer((i-1)*col+j);
                    memo.add((i-1)*col+j);
                    count1++;
                }
            }
            if (j+1<col&&target.contains(i*col+j+1)){
                return rs;
            }
            if (j+1<col&&grid[i][j+1]==0){

                if (!memo.contains(i*col+j+1)) {
                    q.offer(i * col + j + 1);
                    memo.add(i * col + j + 1);
                    count1++;
                }
            }
            if (i+1<grid.length&&target.contains((i+1)*col+j)){
                return rs;
            }
            if (i+1<grid.length&&grid[i+1][j]==0){
                if (!memo.contains((i+1)*col+j)) {
                    q.offer((i + 1) * col + j);
                    memo.add((i + 1) * col + j);
                    count1++;
                }
            }
            if (j>0&&target.contains(i*col+j-1)){
                return rs;
            }
            if (j>0&&grid[i][j-1]==0){
                if (!memo.contains(i*col+j-1)) {
                    q.offer(i * col + j - 1);
                    memo.add(i * col + j - 1);
                    count1++;
                }
            }
            if (count==0){
                count=count1;
                count1=0;
                rs++;
            }

        }

        return rs;
    }
    void dfs(int i, int j, int[][] grid, Set<Integer> set){
        if (set.contains(i*grid[0].length+j)){
            return;
        }
        int col=grid[0].length;
        set.add(i*grid[0].length+j);
        if (i>0&&grid[i-1][j]==1&&!set.contains((i-1)*col+j)){
            dfs(i-1,j,grid,set);
        }
        if (j+1<col&&grid[i][j+1]==1&&!set.contains(i*col+j+1)){
            dfs(i,j+1,grid,set);
        }
        if (i+1<grid.length&&grid[i+1][j]==1&&!set.contains((i+1)*col+j)){
            dfs(i+1,j,grid,set);
        }
        if (j>0&&grid[i][j-1]==1&&!set.contains(i*col+j-1)){
            dfs(i,j-1,grid,set);
        }
    }

    //10/12/2021 简化一些，只需要dfs所有起点并memo记起来，然后bfs再遇到memo里没有的1就是终点了
    int[] dx={-1,0,1,0};
    int[] dy={0,1,0,-1};
    public int shortestBridge2(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        boolean[][] memo=new boolean[m][n];
        Queue<int[]> q=new LinkedList<>();
        boolean found=false;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(!found&&grid[i][j]==1){
                    memo[i][j]=true;
                    q.offer(new int[]{i,j});
                    dfs(i,j,grid,q,memo);
                    found=true;
                }
            }
        }
        int rs=0;
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                int[] cur=q.poll();
                for(int j=0;j<4;j++){
                    int r=cur[0]+dx[j];
                    int c=cur[1]+dy[j];
                    if(r>=0&&r<m&&c>=0&&c<n&&!memo[r][c]&&grid[r][c]==1){
                        return rs;
                    }
                    if(r>=0&&r<m&&c>=0&&c<n&&!memo[r][c]&&grid[r][c]==0){
                        memo[r][c]=true;
                        q.offer(new int[]{r,c});
                    }
                }
            }
            rs++;
        }
        return -1;
    }
    void dfs(int row,int col,int[][] grid,Queue<int[]>q,boolean[][] memo){

        for(int i=0;i<4;i++){
            int r=row+dx[i];
            int c=col+dy[i];
            if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&!memo[r][c]&&grid[r][c]==1){
                memo[r][c]=true;
                q.offer(new int[]{r,c});
                dfs(r,c,grid,q,memo);
            }
        }
    }
}
