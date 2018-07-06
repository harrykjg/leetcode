import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 7/4/18.
 */
//图算法
public class BricksFallingWhenHit {
    public static void main(String[] args){
        BricksFallingWhenHit bf=new BricksFallingWhenHit();
        int[][] grid={{0,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{0,0,0,1,0,0,1,1,1},{0,0,1,1,0,1,1,1,0},{0,0,0,0,0,1,1,1,1},{0,0,0,0,0,0,0,1,0}};
        int[][] hit={{1,8},{2,1},{1,4},{3,0},{3,4},{0,7},{1,6}};
       System.out.print( bf.hitBricks(grid,hit));
    }

    //题意就是一堵墙，删掉一个砖头，如果和他挨边的砖头没有连上第一行的砖头的话就会掉
    //自己想的会超时,就是对每次hit，做上下左右四个bfs，每个bfs返回一个int代表几个brick被删掉，要想清楚bfs是单独的，所以memo也是分开的，比如第一个bfs和第二个bfs都会遇到某个点，如果这个点被会被删除的话
    //第一个bfs已经把它删除了，那么第二个bfs也不会去这个点了（只去为1的点）。再想，如果一个点bfs走过一条向上的路径到达第一行说明安全了，另一个点bfs也要路过第一个路径到达第一行，如果说共用memo的话就不行了
    //下面是别人的解法
    //https://blog.csdn.net/brazy/article/details/79678332
    //https://leetcode.com/problems/bricks-falling-when-hit/discuss/119829/Python-Solution-by-reversely-adding-hits-bricks-back
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[] rs=new int[hits.length];

        int index=0;
        for(int[] hit:hits){
            int row=hit[0];
            int col=hit[1];

            grid[row][col]=0;
            int cur=0;
            if(row>0&&grid[row-1][col]==1){
                boolean[] memo=new boolean[grid.length*grid[0].length];
                cur+=check(grid,row-1,col,memo);
            }
            if(col+1<grid[0].length&&grid[row][col+1]==1){
                boolean[] memo=new boolean[grid.length*grid[0].length];
                cur+=check(grid,row,col+1,memo);
            }
            if(row+1<grid.length&&grid[row+1][col]==1){
                boolean[] memo=new boolean[grid.length*grid[0].length];
                cur+=check(grid,row+1,col,memo);
            }
            if(col-1>=0&&grid[row][col-1]==1){
                boolean[] memo=new boolean[grid.length*grid[0].length];
                cur+=check(grid,row,col-1,memo);
            }
            rs[index]=cur;
            index++;
        }
        return rs;

    }
    int check(int[][] grid,int row,int col,boolean[] memo){
        int cord=row*grid[0].length+col;
        if(memo[cord]==true){
            return 0;
        }
        ArrayList<int[]> dispear=new ArrayList<>();//还有一个难点就是bfs到最后才发现这一个路径的点都要删除，怎么处理？我就想的一个办法就是维持一个count，用来记录
        //路径的长度，和一个arraylist记录整个路径，如果这个路径能达到第一行则count就是0返回了，如果不能达到第一行那么bfs所走过的所有路径都已经被记录下来，再遍历把他们都删除

//        memo[cord]=true;
        int count=0;
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{row,col});
        while (!q.isEmpty()){
            int[] cur=q.poll();
            int r=cur[0];
            int c=cur[1];
            if(memo[r*grid[0].length+c]==true){
                continue;
            }
            memo[r*grid[0].length+c]=true;
            if(r==0&&grid[r][c]==1){
                return 0;
            }
            dispear.add(new int[]{r,c});
            count++;
            if(r>0&&grid[r-1][c]==1&&memo[(r-1)*grid[0].length+c]==false){
                q.offer(new int[]{r-1,c});
            }
            if(c+1<grid[0].length&&grid[r][c+1]==1&&memo[r*grid[0].length+c+1]==false){
                q.offer(new int[]{r,c+1});
            }
            if(r+1<grid.length&&grid[r+1][c]==1&&memo[(r+1)*grid[0].length+c]==false){
                q.offer(new int[]{r+1,c});
            }
            if(c-1>=0&&grid[r][c-1]==1&&memo[r*grid[0].length+c-1]==false){
                q.offer(new int[]{r,c-1});
            }

        }
        if(count!=0){
            for(int[] d:dispear){
                grid[d[0]][d[1]]=0;
            }
        }

        return count;

    }
}
