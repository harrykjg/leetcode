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
}
