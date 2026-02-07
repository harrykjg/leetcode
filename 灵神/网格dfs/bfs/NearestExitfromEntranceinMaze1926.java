package 灵神.网格dfs.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class NearestExitfromEntranceinMaze1926 {
    public static void main(String[] args) {
        char[][] m={{'+','+','.','+'},
                {'.','.','.','+'}};
        int[] ent={1,2};
        System.out.println(nearestExit(m,ent));

    }
    //就是bfs
    public static int nearestExit(char[][] maze, int[] entrance) {
        boolean[][] memo=new boolean[maze.length][maze[0].length];
        Queue<int[]> q=new LinkedList<>();
        q.offer(entrance);
        memo[entrance[0]][entrance[1]]=true;
        int dist=0;
        while (!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                int[] cur=q.poll();
                if(cur[0]==0||cur[0]==maze.length-1||cur[1]==0||cur[1]==maze[0].length-1){
                    if(cur[0]!=entrance[0]||cur[1]!=entrance[1]){
                        return dist;
                    }
                }
                int[] dx={0,1,0,-1};
                int[] dy={1,0,-1,0};
                for(int j=0;j<dx.length;j++){
                    int row=dx[j]+cur[0];
                    int col=dy[j]+cur[1];
                    if(row>=0&&row<maze.length&&col>=0&&col<maze[0].length&&maze[row][col]=='.'&&!memo[row][col]){
                        q.offer(new int[]{row,col});
                        memo[row][col]=true;
                    }
                }
            }
            dist++;
        }
        return -1;
    }
}
