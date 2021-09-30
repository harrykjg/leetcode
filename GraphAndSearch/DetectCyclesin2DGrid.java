package GraphAndSearch;

import java.util.Arrays;

public class DetectCyclesin2DGrid {
    //9/16/2021 思路不难，就是对着一个字母dfs，然后不以常规dfs的memo去判断四周该不该走，而是只用一个pre位置判断这个四周的点是不是上一步从这来的点，
    //然后还是得维护一个memo数组看那些点是走过的，当到某个点后判断他周围有一个点，这个点既不是上一个父节点，但却是以前走过的，说明找到环了
    public boolean containsCycle(char[][] grid) {
        int[] pre=new int[2];
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (!memo[i][j]){
                    pre[0]=i;
                    pre[1]=j;//这里第一个开始访问的点，其实pre不写也行
                    memo[i][j]=true;
                    if (dfs(i,j,grid,pre,memo)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    boolean dfs(int row,int col,char[][] grid,int[] pre,boolean[][] memo){
        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};
        char cur=grid[row][col];
        for (int i=0;i<4;i++){
            int r=dx[i]+row;
            int c=dy[i]+col;
            if (r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&grid[r][c]==cur&&(r!=pre[0]||c!=pre[1])){
                int[] newpre={row,col};//这里搞了很久，要搞一个新的数组作为pre，用原来的pre的话会错，比如某个点作为pre，现在这个dfs的当前点是row，col，
                if (memo[r][c]){ //然后现在这个点的四周是r，c，那么dfs到r，c的时候，row，col应该作为pre，如果不新建一个newpre的话，则这个某个方向的dfs
                    return true; //可能会走了层再返回来这一层，此时pre就被这些分支的dfs改变了，返回这一层时就会错，所以说每个dfs要给他一个新的pre
                }else {
                    memo[r][c]=true;
                }
                if (dfs(r,c,grid,newpre,memo)){
                    return true;
                }
            }
        }
        return false;
    }
}
