package 灵神.二分法.二分答案.最小化最大值;

public class SwiminRisingWater778 {

    //想不到用二分法,还是看回以前bfs的方法把
    //https://leetcode.cn/problems/swim-in-rising-water/solutions/3799008/liang-chong-fang-fa-er-fen-da-an-dijkstr-xfcn/
    public int swimInWater(int[][] grid) {
        int b=0;
        int e=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                e=Math.max(e,grid[i][j]);
            }
        }
        while (b+1<e){
            int m=e-(e-b)/2;
            boolean good=good(0,0,grid,m,new boolean[grid.length][grid[0].length]);//每次都要新的memo,可以参考题解的写法
            if (good){
                e=m;
            }else {
                b=m;
            }
        }
        if(good(0,0,grid,b,new boolean[grid.length][grid[0].length])){
            return b;
        }
        return e;

    }

    boolean good(int row,int col,int[][] grid,int m,boolean[][] memo){

        if(grid[row][col]>m){
            return false;
        }
        if(row==grid.length-1&&col==grid[0].length-1){
            return true;
        }
        memo[row][col]=true;
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        for(int k=0;k<dx.length;k++){
            int r=dx[k]+row;
            int c=dy[k]+col;
            if(r>=0&&r<grid.length&&c<grid[0].length&&c>=0){
                if(memo[r][c]){
                    continue;
                }
                if(good(r,c,grid,m,memo)){
                    return true;
                }
            }
        }

        return false;
    }
}
