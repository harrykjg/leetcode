package 灵神.网格dfs.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix542 {
    public static void main(String[] args) {

    }
    public int[][] updateMatrix(int[][] mat) {
        int[][] rs=new int[mat.length][mat[0].length];
        boolean[][] memo=new boolean[mat.length][mat[0].length];

        Queue<int[]> q=new LinkedList<>();
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                if(mat[i][j]==0){
                    memo[i][j]=true;
                    q.offer(new int[]{i,j});
                }
            }
        }
        int dist=0;
        while (!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                int[] cur=q.poll();

                int[] dx=new int[]{0,1,0,-1};
                int[] dy=new int[]{1,0,-1,0};
                for(int j=0;j<dx.length;j++){
                    int row=cur[0]+dx[j];
                    int col=cur[1]+dy[j];
                    if(row>=0&&row<mat.length&&col>=0&&col<mat[0].length&&!memo[row][col]&&mat[row][col]==1){
                        rs[row][col]=dist+1;
                        q.offer(new int[]{row,col});
                        memo[row][col]=true;
                    }
                }
            }
            dist++;
        }
        return rs;
    }
}
