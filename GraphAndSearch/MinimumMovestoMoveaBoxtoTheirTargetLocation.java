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
        System.out.println(mm.minPushBox(grid));
    }
    //8/16/2021 有点意思，要推box往一个方向走的条件是他反方向也得有空间给人站那。而且人得能走到那个空间才行,并且每一步人和箱子的位置一起成为一个状态，
    //https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/discuss/709355/Java-use-2-level-BFS-beat-99
    //https://leetcode.com/problems/minimum-moves-to-move-a-box-to-their-target-location/discuss/431850/Java-BFS-(17ms)-Explained-with-comments 他这个
    //是每一层把当前box设成是'#'，这层完成之后才设回'。'
    //https://www.cnblogs.com/Dylan-Java-NYC/p/5211776.html

//    public int minPushBox(char[][] grid) {//自己的代码，不对，以后再改
//        boolean[][] memo=new boolean[grid.length][grid[0].length];
//        int playerRow=0;
//        int playerCol=0;
//        int boxRow=0;
//        int boxCol=0;
//        int targetRow=0;
//        int targetCol=0;
//        for (int i=0;i<grid.length;i++){
//            for (int j=0;j<grid[0].length;j++){
//                if (grid[i][j]=='S'){
//                    playerRow=i;
//                    playerCol=j;
//                }
//                if (grid[i][j]=='B'){
//                    boxRow=i;
//                    boxCol=j;
//                }
//                if (grid[i][j]=='T'){
//                    targetRow=i;
//                    targetCol=j;
//                }
//            }
//        }
//        Queue<int[]> q=new LinkedList<>();
//        q.offer(new int[]{boxRow,boxCol,playerRow,playerCol});
//        memo[boxRow][boxCol]=true;
//        int rs=0;
//        while (!q.isEmpty()){
//            int size=q.size();
//            for (int i=0;i<size;i++){
//                int[] box=q.poll();
//                int r=box[0];
//                int c=box[1];
//                int pr=box[2];
//                int pc=box[3];
//                if (r==targetRow&&c==targetCol){
//                    return rs;
//                }
//                if (r-1>=0&&grid[r-1][c]=='.'&&canGo(pr,pc,r+1,c,grid)&&memo[r-1][c]==false){
//                    memo[r-1][c]=true;
//                    q.offer(new int[]{r-1,c,r,c});
//                }
//                if (c+1<grid[0].length&&grid[r][c+1]=='.'&&canGo(pr,pc,r,c-1,grid)&&memo[r][c+1]==false){
//                    memo[r][c+1]=true;
//                    q.offer(new int[]{r,c+1,r,c});
//                }
//                if (r+1<grid.length&&grid[r+1][c]=='.'&&canGo(pr,pc,r-1,c,grid)&&memo[r+1][c]==false){
//                    memo[r+1][c]=true;
//                    q.offer(new int[]{r+1,c,r,c});
//                }
//                if (c-1>=0&&grid[r][c-1]=='.'&&canGo(pr,pc,r,c-1,grid)&&memo[r][c-1]==false){
//                    memo[r][c-1]=true;
//                    q.offer(new int[]{r,c-1,r,c});
//                }
//            }
//            rs++;
//        }
//        return -1;
//    }

//    boolean canGo(int r,int c, int tr,int tc,char[][] grid){
//        if (grid[r][c]=='#'){
//            return false;
//        }
//        Queue<int[]> q=new LinkedList<>();
//        boolean[][][] memo=new boolean[grid.length][grid[0].length][4];
//        memo[r][c]=true;
//        q.offer(new int[]{r,c});
//        while (!q.isEmpty()){
//            int size=q.size();
//            for (int i=0;i<size;i++){
//                int[] cur=q.poll();
//                if (cur[0]==tr&&cur[1]==tc){
//                    return true;
//                }
//                int rr=cur[0];
//                int cc=cur[1];
//                if (rr>0&&(grid[rr-1][cc]=='.'||(grid[rr-1][cc]=='B'&&(rr-1!=tr||cc!=tc)))&&memo[rr-1][cc]==false){
//                    memo[rr-1][cc]=true;
//                    q.offer(new int[]{rr-1,cc});
//                }
//                if (cc+1<grid[0].length&&(grid[rr][cc+1]=='.'||(grid[rr][cc+1]=='B'&&(rr!=tr||cc+1!=tc)))&&memo[rr][cc+1]==false){
//                    memo[rr][cc+1]=true;
//                    q.offer(new int[]{rr,cc+1});
//                }
//                if (rr+1<grid.length&&(grid[rr+1][cc]=='.'||(grid[rr+1][cc]=='B'&&(rr+1!=tr||cc!=tc)))&&memo[rr+1][cc]==false){
//                    memo[rr+1][cc]=true;
//                    q.offer(new int[]{rr+1,cc});
//                }
//                if (cc>0&&(grid[rr][cc-1]=='.'||(grid[rr][cc-1]=='B'&&(rr!=tr||cc-1!=tc)))&&memo[rr][cc-1]==false){
//                    memo[rr][cc-1]=true;
//                    q.offer(new int[]{rr,cc-1});
//                }
//            }
//        }
//        return false;
//    }
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
}
