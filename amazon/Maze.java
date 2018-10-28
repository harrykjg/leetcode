package amazon;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 10/25/18.
 */
public class Maze {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] grid = {
                {1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1},
                {0, 1, 0, 9, 0},
                {0, 1, 1, 1, 1}};
        Maze maze=new Maze();

        System.out.print(maze.maze(grid));
    }

    public int maze(int[][] grid) {

        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{0,0});
        int rs=0;
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        memo[0][0]=true;
        int count=1;
        int count2=0;
        while (!q.isEmpty()){
            int[] cur=q.poll();
            int r=cur[0];
            int c=cur[1];
            count--;
            if(grid[r][c]==9){
                return rs;
            }
            if(r>0&&grid[r-1][c]!=0&&memo[r-1][c]==false){
                q.offer(new int[]{r-1,c});
                memo[r-1][c]=true;
                count2++;
            }
            if(c+1<grid[0].length&&grid[r][c+1]!=0&&memo[r][c+1]==false){
                q.offer(new int[]{r,c+1});
                memo[r][c+1]=true;
                count2++;
            }
            if(r+1<grid.length&&grid[r+1][c]!=0&&memo[r+1][c]==false){
                q.offer(new int[]{r+1,c});
                memo[r+1][c]=true;
                count2++;
            }
            if(c>0&&grid[r][c-1]!=0&&memo[r][c-1]==false){
                q.offer(new int[]{r,c-1});
                memo[r][c-1]=true;
                count2++;
            }
            if(count==0){
                count=count2;
                count2=0;
                rs++;
            }

        }
        return -1;

    }
}
