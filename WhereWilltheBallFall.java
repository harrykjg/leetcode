public class WhereWilltheBallFall {
    public static void main(String[] args){
        WhereWilltheBallFall ww=new WhereWilltheBallFall();
        int[][] grid={{1,1,1,-1,-1},{1,1,1,-1,-1},{-1,-1,-1,1,1},{1,1,1,1,-1},{-1,-1,-1,-1,-1}};
        System.out.println(ww.findBall(grid));
    }
    //9/11/2021
    //不难，就是if else直接写，改了几次ac
    public int[] findBall(int[][] grid) {
        int[] rs=new int[grid[0].length];
        for (int i=0;i<grid[0].length;i++){
            helper(0,i,i,rs,grid);
        }
        return rs;
    }
    void helper(int row,int col,int index,int[] rs,int[][] grid){//开始忘了要写index来记住是哪个球
        if (col==0&&grid[row][col]==-1){//卡左边墙
            rs[index]=-1;
            return;
        }
        if (col==grid[0].length-1&&grid[row][col]==1){
            rs[index]=-1;
            return;
        }
        if (grid[row][col]==1){
            if (col+1<grid[0].length&&grid[row][col+1]==-1){
                rs[index]=-1;
                return;
            }else {
                if (row+1==grid.length){
                    rs[index]=col+1;
                    return;
                }else {
                    helper(row+1,col+1,index,rs,grid);
                }
            }
        }
        if (grid[row][col]==-1){
            if (col>0&&grid[row][col-1]==1){
                rs[index]=-1;
                return;
            }else {
                if (row+1==grid.length){
                    rs[index]=col-1;
                    return;
                }else {
                    helper(row+1,col-1,index,rs,grid);
                }
            }
        }
    }
}
