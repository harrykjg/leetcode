package GraphAndSearch;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumMovestoMoveaBoxtoTheirTargetLocation {
    public static void main(String[] args){
//        char[][] grid=new char[][]{ {'#','#','#','#','#','#'},
//                                    {'#','T','#','#','#','#'},
//                                    {'#','.','.','B','.','#'},
//                                    {'#','.','#','#','.','#'},
//                                    {'#','.','.','.','S','#'},
//                                    {'#','#','#','#','#','#'}};
        char[][] grid=new char[][]{
                {'#','.','#'},
                {'.','.','T'},
                {'#','B','#'},
                {'#','.','S'}};
        MinimumMovestoMoveaBoxtoTheirTargetLocation mm=new MinimumMovestoMoveaBoxtoTheirTargetLocation();
        System.out.println(mm.minPushBox2(grid));
    }
    //8/16/2021 有点意思，要推box往一个方向走的条件是他反方向也得有空间给人站那。而且人得能走到那个空间才行,并且每一步人和箱子的位置一起成为一个状态，
    //https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/discuss/709355/Java-use-2-level-BFS-beat-99
    //https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/discuss/431850/Java-BFS-(17ms)-Explained-with-comments 他这个
    //是每一层把当前box设成是'#'，这层完成之后才设回'。'
    //https://www.cnblogs.com/Dylan-Java-NYC/p/5211776.html
//第一个链接的代码
    char[][] g;
    int m, n;
    int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};//右下左上
    public int minPushBox(char[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        int step = 0;
        boolean[][][] vs = new boolean[m][n][4];//直观上记录box和player的状态要4维，前两位是box后两位是player，他们应该直接简化到第三维是4个边

        Queue<int[]> q = new LinkedList<>();
        int[] st = new int[]{-1, -1}, ed = new int[]{-1, -1}, pl = new int[]{-1, -1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 'B') st = new int[]{i, j};
                if (g[i][j] == 'T') ed = new int[]{i, j};
                if (g[i][j] == 'S') pl = new int[]{i, j};
            }
        }
        q.offer(new int[]{st[0], st[1], pl[0], pl[1]});//他这里也不把这个一开始入queue的状态标为true，这样也行吗？第二个链接的代码也是这样的，应该是
        //可以的吧，因为这时候player不在箱子旁边自然也没办法确定是哪个状态，所以等到里面等到player去到4个边上再确定
        while (!q.isEmpty()) {
            for (int i = 0, l = q.size(); i < l; i++) {
                int[] curr = q.poll();
                if (curr[0] == ed[0] && curr[1] == ed[1]) return step;
                for (int j = 0; j < dir.length; j++) {
                    if (vs[curr[0]][curr[1]][j]) continue;
                    int[] d = dir[j];
                    int r0 = curr[0] + d[0], c0 = curr[1] + d[1];  // where pl stands, have room to push;
                    if (r0 < 0 || r0 >= m || c0 < 0 || c0 >= n || g[r0][c0] == '#') continue;
                    int r = curr[0] - d[0], c = curr[1] - d[1];  // box next spots;
                    if (r < 0 || r >= m || c < 0 || c >= n || g[r][c] == '#') continue;
                    if (!reachable(r0, c0, curr)) continue;
                    vs[curr[0]][curr[1]][j] = true;
                    q.offer(new int[]{r, c, curr[0], curr[1]});
                }
            }
            step++;
        }
        return -1;
    }

    private boolean reachable(int i, int j, int[] curr) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{curr[2], curr[3]});
        boolean[][] vs = new boolean[m][n];
        vs[curr[0]][curr[1]] = true;//他这里是把box的位置设为已经访问，但他不把已经入queue的box的位置标记已访问，这样不会导致重复吗？好想确实会。
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == i && cur[1] == j) return true;
            for (int[] d : dir) {
                int r = cur[0] - d[0], c = cur[1] - d[1];  // box next spots;
                if (r < 0 || r >= m || c < 0 || c >= n || vs[r][c] || g[r][c] == '#') continue;
                vs[r][c] = true;
                q.offer(new int[]{r, c});
            }
        }
        return false;
    }

    //10/2/2021
    public int minPushBox2(char[][] grid) {
        g=grid;
        m=grid.length;
        n=grid[0].length;
        boolean[][][] memo = new boolean[grid.length][grid[0].length][4];
        Queue<int[]> q=new LinkedList<>();
        int[] b=new int[2];
        int[] p=new int[2];
        int[] t=new int[2];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]=='B'){
                    b[0]=i;
                    b[1]=j;
                }
                if (grid[i][j]=='T'){
                    t[0]=i;
                    t[1]=j;
                }if (grid[i][j]=='S'){
                    p[0]=i;
                    p[1]=j;
                }
            }
        }
        int[] now=new int[]{b[0],b[1],p[0],p[1]};
        q.offer(now);
        int rs=0;
        int[] dx={1,0,-1,0};
        int[] dy={0,1,0,-1};
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int[] cur=q.poll();
                if (cur[0]==t[0]&&cur[1]==t[1]){
                    return rs;
                }
                for (int j=0;j<4;j++){
                    int r=cur[0]+dx[j];
                    int c=cur[1]+dy[j];
                    if (r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&reachable2(r,c,cur,grid)){
                        int br=cur[0]-dx[j];
                        int bc=cur[1]-dy[j];
                        if (br>=0&&bc>=0&&br<grid.length&&bc<grid[0].length&&!memo[cur[0]][cur[1]][j]&&grid[br][bc]!='#'){
                            q.offer(new int[]{br,bc,cur[0],cur[1]});
                            memo[cur[0]][cur[1]][j]=true;
                        }
                    }
                }
            }
            rs++;
        }
        return -1;
    }
    boolean reachable2(int row,int col,int[] cur,char[][] grid){
        if(grid[row][col]=='#'){
            return false;
        }
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{cur[2],cur[3]});
        memo[cur[2]][cur[3]]=true;
        memo[cur[0]][cur[1]]=true;
        int[] dx={1,0,-1,0};
        int[] dy={0,1,0,-1};
        while (!q.isEmpty()){
            int[] now=q.poll();
            if (now[0]==row&&now[1]==col){
                return true;
            }
            for (int i=0;i<4;i++){
                int r=now[0]+dx[i];
                int c=now[1]+dy[i];
                if (r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&!memo[r][c]&&grid[r][c]!='#'){
                    memo[r][c]=true;
                    q.offer(new int[]{r,c});
                }
            }
        }
        return false;
    }
}
