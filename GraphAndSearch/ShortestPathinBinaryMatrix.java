package GraphAndSearch;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ShortestPathinBinaryMatrix {
    //7/23/2021直接bfs，改了一次过
    public int shortestPathBinaryMatrix(int[][] grid) {
        int rs=1;
        if (grid[0][0]!=0){
            return -1;
        }
        Queue<int[]> q=new LinkedList<>();
        Set<Integer> set=new HashSet<>();
        q.add(new int[]{0,0});
        int count=1;
        int count2=0;
        int col=grid[0].length;
        set.add(0);
        while (!q.isEmpty()){
            int[] cur=q.poll();
            int i=cur[0];
            int j=cur[1];
            if (i==grid.length-1&&j==grid[0].length-1){
                return rs;
            }
            count--;
            if (i>0&&grid[i-1][j]==0&&!set.contains((i-1)*col+j)){//上
                q.offer(new int[]{i-1,j});
                set.add((i-1)*col+j);//开始是set。add的位置放在while循环便利的时候，改成这里就过了，想象有一点可能同时是当前层别的节点的邻居，那样的话可能回重复加进q
                count2++;
            }
            if (i>0&&j+1<col&&grid[i-1][j+1]==0&&!set.contains((i-1)*col+j+1)){//右上
                q.offer(new int[]{i-1,j+1});
                set.add((i-1)*col+j+1);
                count2++;
            }
            if (j+1<col&&grid[i][j+1]==0&&!set.contains((i)*col+j+1)){
                q.offer(new int[]{i,j+1});
                set.add((i)*col+j+1);
                count2++;
            }
            if (i+1<grid.length&&j+1<col&&grid[i+1][j+1]==0&&!set.contains((i+1)*col+j+1)){
                q.offer(new int[]{i+1,j+1});
                set.add((i+1)*col+j+1);
                count2++;
            }
            if (i+1<grid.length&&grid[i+1][j]==0&&!set.contains((i+1)*col+j)){
                q.offer(new int[]{i+1,j});
                set.add((i+1)*col+j);
                count2++;
            }
            if (i+1<grid.length&&j>0&&grid[i+1][j-1]==0&&!set.contains((i+1)*col+j-1)){
                q.offer(new int[]{i+1,j-1});
                set.add((i+1)*col+j-1);
                count2++;
            }
            if (j>0&&grid[i][j-1]==0&&!set.contains((i)*col+j-1)){
                q.offer(new int[]{i,j-1});
                set.add((i)*col+j-1);
                count2++;
            }
            if (i>0&&j>0&&grid[i-1][j-1]==0&&!set.contains((i-1)*col+j-1)){
                q.offer(new int[]{i-1,j-1});
                set.add((i-1)*col+j-1);
                count2++;
            }
            if (count==0){
                count=count2;
                count2=0;
                rs++;
            }
        }

        return -1;
    }
}
