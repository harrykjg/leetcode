package GraphAndSearch;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumKnightMoves {
    public static void main(String[] args){
        MinimumKnightMoves mm=new MinimumKnightMoves();
        System.out.println(mm.minKnightMoves2(300,0));
    }
    //8/20/2021一位是简单的bfs，结果他的因为是没有边界的（说了小于300），所以判断四个方向是否越界时不同，其实就是要把（0，0）点挪到memo数组中间,然后边界那里就不用考虑row col
    //是否小于0或者越觉了；
    //可以看看答案二的双向bfs。以前没见过，就是用两个queue分别从起点和终点做bfs，关键就在于怎么知道他们啥时候相交和相交时的距离。他的做法是分别用2个map，分别记录
    //起点和终点走过的点，点用x+逗号+y表示作为key，value是距离其起点的距离。并且他们q里装的array是3个数，x，y和距离。这样他们相遇与否就可以看自己当前位置的key是否
    // 在对方的map里，然后就可以知道各自离自己起点的距离，相加就是答案。

    public int minKnightMoves(int x, int y) {
        int[] start=new int[]{0,0};
        boolean[][] memo=new boolean[1000][1000];
        Queue<int[]> q=new LinkedList<>();
        q.offer(start);
        memo[0][0]=true;
        int rs=0;
        int[] dx={-1,-1,-2,-2,1,1,2,2};
        int[] dy={2,-2,1,-1,2,-2,1,-1};
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
                if (cur[0]==x&&cur[1]==y){
                    return rs;
                }
                for (int j=0;j<dy.length;j++){
                    int r=cur[0]+dx[j];
                    int c=cur[1]+dy[j];
                    if (memo[r+499][c+499]==true){//开始这里写了r+499<memo.length 啥的条件就错了
                        continue;
                    }
                    q.offer(new int[]{r,c});
                    memo[r+499][c+499]=true;
                }
            }
            rs++;
        }
        return rs;
    }
//10/13/2021又没有那么容易了
    public int minKnightMoves2(int x, int y) {
        int[] start=new int[]{0,0};
        boolean[][] memo=new boolean[605][605];//最少要605。
        Queue<int[]> q=new LinkedList<>();
        q.offer(start);
        memo[0][0]=true;
        int rs=0;
        int[] dx={-1,-1,-2,-2,1,1,2,2};
        int[] dy={2,-2,1,-1,2,-2,1,-1};
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
                if (cur[0]==x&&cur[1]==y){
                    return rs;
                }
                for (int j=0;j<dy.length;j++){
                    int r=cur[0]+dx[j];
                    int c=cur[1]+dy[j];
                    if (memo[r+302][c+302]==true){
                        continue;
                    }
                    q.offer(new int[]{r,c});
                    memo[r+302][c+302]=true;
                }
            }
            rs++;
        }
        return rs;
    }
}
