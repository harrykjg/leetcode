package GraphAndSearch;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathinaGridwithObstaclesElimination {
    public static void main(String[] args){
        ShortestPathinaGridwithObstaclesElimination sp=new ShortestPathinaGridwithObstaclesElimination();
        int[][] grid={{0,0,0,1,0},
                      {0,1,0,1,0},
                      {0,1,0,1,1},
                      {0,1,1,0,1},
                      {1,1,0,0,0}};
        System.out.println(sp.shortestPath(grid,14));
    }
    //9/3/2021 自己的思路是先bfs，然后遇到1的话就用另一个bfs去求1那个点到最后的结果，加上这个bfs到当前点的值。而后一个bfs遇到1的话再递归调用bfs，然后加上从
    //0，0点到自己当前bfs的起点的距离，听起来不错但是写起来貌似是错的，因为我递归bfs的时候，实际上走的是dfs的路径，而这个路径不能保证是最小的，比如上面的例子
    //从0，3点到最后结果距离是5，没毛病，但是从0，0到0，3这个距离不是通过bfs过来的，而是通过dfs过来的（从1，3过来的，而1，3又是从，2，3过来的。。）所以是错的
    //https://www.youtube.com/watch?v=2pLhH2eLaP8 见3：45解释的图帮助理解
    //https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/discuss/451796/Java-Straightforward-BFS-O(MNK)-time-or-O(MNK)-space
    //想，为啥遇到一个点就要把他变成可以穿过的？如果你现在变了那么以后更需要的时候不久用不了了吗？其实应该这样想，这个bfs的状态加上了可穿墙的数量k，即当前遇到1并且
    //把他设为可穿墙放到q里只是我把这个状态作为一个可能放进去了，而不是说必须要这样做，而且当前点的上下左右不需要穿墙的点我也放进q里了，所以说到时真的必须要穿墙的时候
    //再用k的可能性我是保留的了
    public int shortestPath(int[][] grid, int k) {
        Queue<int[]> q=new LinkedList<>();
        boolean[][][] memo=new boolean[grid.length][grid[0].length][k+1];//0到k有k+1个状态
        memo[0][0][0]=true;
        q.offer(new int[]{0,0,0});
        int rs=0;
        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};
        int max=Integer.MAX_VALUE;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
                if (cur[0]==grid.length-1&&cur[1]==grid[0].length-1){
                    return rs;
                }
                for (int j=0;j<4;j++){
                    int r=cur[0]+dx[j];
                    int c=cur[1]+dy[j];
                    int used=cur[2];
                    if (r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&memo[r][c][used]==false){
                        if (grid[r][c]==1&&used<k&&memo[r][c][used+1]==false){//memo[r][c][used+1]==false 这个容易漏
                            q.offer(new int[]{r,c,used+1});
                            memo[r][c][used+1]=true;
                        }else if (grid[r][c]==0){
                            memo[r][c][used]=true;
                            q.offer(new int[]{r,c,used});
                        }
                    }
                }
            }
            rs++;
        }
        return -1;
    }
}
