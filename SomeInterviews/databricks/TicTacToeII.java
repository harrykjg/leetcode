package SomeInterviews.databricks;

public class TicTacToeII {
    int[][] board;
    int k=0;
    public TicTacToeII(int m,int n,int k){
        board=new int[m][n];
        this.k=k;
    }

    //和tictatoe1不一样，那里需要整行整列都是一个player才行，所以可以那样做。这里只能是暴力法检测每个方向
    public int move(int row, int col, int player) {
        board[row][col]=player;
        if(checkWin(row,col,player)){
            return player;
        }
        return 0;

    }
    //这里巧妙的的是怎么把往左右上下斜上方向的整合起来写
    private boolean checkWin(int row, int col, int player) {
        int[][] dir={{0,1},{1,0},{1,1},{1,-1}};//这个自己真不好想，然后再一个while loop，比如{0,1}这个数组，r加0，c+1，然后r-0，c-1.这算是算完
        int count=1;      //左右的，然后{1,0}是上下的，{1,1}是正对角方向。。。
        for (int i=0;i<4;i++){
            if (helper(row,col,dir[i],player)){
                return true;
            }
        }
        return false;
    }
    boolean helper(int row,int col,int[] dir,int player){
        int count=1;
        int r=row+dir[0];
        int c=col+dir[1];
        while (r>=0&&r<board.length&&c>=0&&c<board[0].length&&board[r][c]==player){
            count++;
            r+=dir[0];
            c+=dir[1];
        }
        r=row-dir[0];
        c=col-dir[1];
        while (r>=0&&r<board.length&&c>=0&&c<board[0].length&&board[r][c]==player){
            count++;
            r-=dir[0];
            c-=dir[1];
        }

        if (count>=k){
            return true;
        }

        return false;
    }

}
