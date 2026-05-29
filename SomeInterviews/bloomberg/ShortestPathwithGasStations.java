package SomeInterviews.bloomberg;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ShortestPathwithGasStations {
    //https://www.hack2hire.com/companies/bloomberg/coding-questions/68dc09c39736cfa4e90623e6/practice?questionId=68dc5856dd5418be11109afb&src=eg1
    //就是说比如有直线直接到终点但是油不够，因此可以拐弯去加油再去，那就说明不是bfs，还得算油，那怎么做？看gpt就还是bfs，就是要maintain一个三维的memo，第三维是fule的数量
    public int shortestPath(String[][] grid, int fuelCapacity) {
        // TODO: Implement shortestPath logic
        int rs=0;
        boolean[][][] memo=new boolean[grid.length][grid[0].length][fuelCapacity+1];
        Queue<int[]> q=new LinkedList<>();
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j].equals("S")){
                    q.offer(new int[]{i,j,fuelCapacity});
                    memo[i][j][fuelCapacity]=true;
                }
            }
        }
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
                int fuel=cur[2];

                if(grid[cur[0]][cur[1]].equals("D")){
                    return rs;
                }
                if(fuel==0){
                    continue;
                }
                for (int j=0;j<4;j++){
                    int r=cur[0]+dx[j];
                    int c=cur[1]+dy[j];
                    if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&!memo[r][c][fuel-1]&&!grid[r][c].equals("#")){
                        if(grid[r][c].equals("G")){
                            q.offer(new int[]{r,c,fuelCapacity});
                            memo[r][c][fuelCapacity]=true;
                        }else if(fuel-1>=0){
                            q.offer(new int[]{r,c,fuel-1});
                            memo[r][c][fuel-1]=true;
                        }
                    }
                }

            }
            rs++;
        }
        return -1;
    }

}
