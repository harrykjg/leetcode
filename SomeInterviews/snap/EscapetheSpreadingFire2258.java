package SomeInterviews.snap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EscapetheSpreadingFire2258 {
    static void main() {
        int[][] g={{0,0,0},{2,2,0},{1,2,0}};
        EscapetheSpreadingFire2258 es=new EscapetheSpreadingFire2258();
        System.out.println(es.maximumMinutes(g));
    }

    //3/6/2026看了提示，先算fire到每个点的时间。可知用bfs算起点到终点的时候需要判断当前时间+1的时间四周邻居是否着火来判断可否经过。那么就用二分法
    //在值上判断能最多再起点呆多久，下限是0，上限是火到终点的时间-起点到终点的最短时间吧。
    public int maximumMinutes(int[][] grid) {
        int max=1000000000;
        Queue<int[]> q=new LinkedList<>();
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                if (grid[i][j]==1){
                    q.offer(new int[]{i,j});
                    memo[i][j]=true;
                }
            }
        }
        int[][] g=new int[grid.length][grid[0].length];
        for (int[] i:g){
            Arrays.fill(i,max);
        }
        int dist=0;
        int[] dx=new int[]{0,1,0,-1};
        int[] dy=new int[]{1,0,-1,0};
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
                g[cur[0]][cur[1]]=Math.min(g[cur[0]][cur[1]],dist);
                for (int j=0;j<4;j++){
                    int r=cur[0]+dx[j];
                    int c=cur[1]+dy[j];
                    if(r>=0&&r<g.length&&c>=0&&c<grid[0].length&&!memo[r][c]&&grid[r][c]!=2){
                        q.offer(new int[]{r,c});
                        memo[r][c]=true;
                    }
                }
            }
            dist++;
        }
        int b=0;
        int e=g[g.length-1][g[0].length-1];//如果火永远烧不到的情况下还得单独处理，因为用good处理的话会最多用到e-起点到终点的最短距离
        if(g[g.length-1][g[0].length-1]==max&&good(grid,g,0)){//单单等于max还不行，害得看通不通
            return e;
        }
        while (b+1<e){
            int m=e-(e-b)/2;
            if(good(grid,g,m)){
                b=m;
            }else{
                e=m;
            }
        }
        if(good(grid,g,e)){
            return e;
        }
        if(good(grid,g,b)){
            return b;
        }
        return -1;

    }
    boolean good(int[][] grid,int[][] g,int m){
        Queue<int[]> q=new LinkedList<>();
        if(m>=g[0][0]){
            return false;
        }
        boolean[][] memo=new boolean[g.length][g[0].length];
        memo[0][0]=true;
        q.offer(new int[]{0,0});
        int dist=0;
        int[] dx=new int[]{0,1,0,-1};
        int[] dy=new int[]{1,0,-1,0};
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
                if(cur[0]==g.length-1&&cur[1]==g[0].length-1){
                    return true;
                }
                for (int j=0;j<4;j++){
                    int r=cur[0]+dx[j];
                    int c=cur[1]+dy[j];
                    //这里还要分别判断终点是允许和火一起到达的，而别的点事不行的

                    if(r>=0&&r<g.length&&c>=0&&c<g[0].length&&!memo[r][c]&&grid[r][c]==0){
                        if(r==g.length-1&&c==g[0].length-1&&g[r][c]>=dist+m+1){
                            memo[r][c]=true;
                            q.offer(new int[]{r,c});
                        }else if(g[r][c]>dist+m+1){
                            memo[r][c]=true;
                            q.offer(new int[]{r,c});
                        }
                    }
                }
            }
            dist++;
            if(dist+m>g[g.length-1][g[0].length-1]){
                return false;
            }
        }
        return false;
    }

}
