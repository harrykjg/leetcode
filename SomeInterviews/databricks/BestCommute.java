package SomeInterviews.databricks;

import java.util.*;

public class BestCommute {
    /*
    You live in San Francisco city and want to minimize your commute time to the Databricks HQ.
    Given a 2D matrix of the San Francisco grid and the time as well as cost matrix of all the
    available transportation modes, return the fastest mode of transportation. If there are multiple
     such modes then return one with the least cost. Rules:
     1. The input grid represents the city blocks,
      so the commuter is only    allowed to travel along the horizontal and vertical axes.    Diagonal
      traversal is not permitted.
      2. The commuter can only move to the neighboring cells with the same
        transportation mode. 3. Transportation modes in the grid are numbered 1-4 where
          1 = Walk, 2 = Bike, 3 = Car, 4 = Train.
          Sample Input: 2D Grid:
        |3|3|S|2|X|
        |3|1|1|2|X|
        |3|1|1|2|2|
        |3|1|1|1|D|
        |3|3|3|3|4|
        |4|4|4|4|4|
    S = Source  X = Roadblock D = Destination
     1 = Walk, 2 = Bike, 3 = Car, 4 = Train
     Cost Matrix (Dollars/Block): [0, 1, 3, 2]
     Time Matrix (Minutes/Block): [3, 2, 1, 1]
     */
    //还是比较好理解，就是每一个mode做一个bfs，但是写一个bfs方法得出4个mode的bfs很容易写错！
    public int cost(char[][] grid,int[] cost,int[] time){
        int[] rs=new int[4];
        int mode=1;
        outer:
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='S'){
                    for(mode=1;mode<=4;mode++){
                        rs[mode-1]=bfs(grid,i,j,(char)(mode+'0'));
                    }
                    break outer;
                }
            }
        }
        int min=Integer.MAX_VALUE;
        int result=0;
        for (int i=0;i<4;i++){
            if(rs[i]==Integer.MAX_VALUE){//不写会溢出
                continue;
            }
            if(rs[i]*time[i]<min){
                min=rs[i]*time[i];
                result=i;
            }else if(rs[i]*time[i]==min){
                if(rs[i]*cost[i]<rs[result]*cost[result]){
                    result=i;
                }
            }
        }
        return result;
    }
    int bfs(char[][] grid,int row,int col,char mode){
//        int[] dest=new int[2];
//        outer:
//        for (int i=0;i<grid.length;i++){//先要找到D的位置，否则D可能加不进q，因为和mode不一样
//            for (int j=0;j<grid[0].length;j++){
//                if(grid[i][j]=='D'){
//                    dest[0]=i;
//                    dest[1]=j;
//                    break outer;
//                }
//            }
//        }
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{row,col});

        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        int dist=0;
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        memo[row][col] = true;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
                for (int j=0;j<4;j++){
                    int r=cur[0]+dx[j];
                    int c=cur[1]+dy[j];
                    if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&!memo[r][c]){
                        if(grid[r][c]==mode){
                            memo[r][c]=true;
                            q.offer(new int[]{r,c});
                        }else if(grid[r][c]=='D'){
                            return dist+1;
                        }
                    }
                }
            }
            dist++;
        }

        return Integer.MAX_VALUE;
    }
    //follow up是可以切换交通工具，就是dijistra 用改成用priorityq.
    public int cost2(char[][] grid,int[] cost,int[] time){
        int[][] distTime=new int[grid.length][grid[0].length];//这个不太好理解，对于grid的图，他的意义就是起点S到每一个点的距离
        int[][] costTime=new int[grid.length][grid[0].length];

    }
}
