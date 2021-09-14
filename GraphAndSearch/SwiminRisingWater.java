package GraphAndSearch;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SwiminRisingWater {
    //9/12/2021,自己想复杂了，其实pq里poll出的点，只要看上下左右，而不需要去看这个点能dfs溜去哪。比如lc第二个例子，现在我pq poll出来16了，那不用dfs
    //溜去答案那，只需要看4周，入q，再poll出来的就是小于16的点，时间不需要增加。
    int[] dx={-1,0,1,0};
    int[] dy={0,1,0,-1};
    public int swimInWater(int[][] grid) {
        PriorityQueue<Pair> pq=new PriorityQueue<Pair>((p1,p2)->p1.val-p2.val);
        pq.offer(new Pair(0,0,grid[0][0]));
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        memo[0][0]=true;
        int t=0;
        while (!pq.isEmpty()){
            Pair cur=pq.poll();
            t=Math.max(cur.val,t);
            if (cur.i==grid.length-1&&cur.j==grid[0].length-1){
                return t;
            }
            for (int i=0;i<4;i++){
                int r=cur.i+dx[i];
                int c=cur.j+dy[i];
                if (r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&memo[r][c]==false){
                    pq.offer(new Pair(r,c,grid[r][c]));
                    memo[r][c]=true;
                }
            }
        }
        return t;
    }
    class Pair{
        int i;
        int j;
        int val;
        public Pair(int i,int j,int val){
            this.i=i;
            this.j=j;
            this.val=val;
        }
    }
}
