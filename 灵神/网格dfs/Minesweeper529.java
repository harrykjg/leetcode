package 灵神.网格dfs;

public class Minesweeper529 {
    public static void main(String[] args) {

    }
    public char[][] updateBoard(char[][] board, int[] click) {
        int r=click[0];
        int c=click[1];
        if(board[r][c]=='M'){
            board[r][c]='X';
            return board;
        }

        if(board[r][c]=='E'){
            dfs(r,c,board);
        }
        return board;

    }
    void dfs(int r,int c,char[][] board){
        if(board[r][c]=='E'){
           int count=findMines(r,c,board);
           if (count==0){
                board[r][c]='B';
           }else {
                board[r][c]=(char)(count+'0');
                return; //这里不好理解，少了就错了第一个例子，那个雷正上面的那个地方应该是不会被访问的，如果不return的话他就会被访问到。
           }
        }
        int[] dx={0,1,1,1,0,-1,-1,-1};
        int[] dy={1,1,0,-1,-1,-1,0,1};
        for(int i=0;i<dx.length;i++){
            int row=r+dx[i];
            int col=c+dy[i];
            if(row>=0&&row<board.length&&col>=0&&col<board[0].length&&board[row][col]=='E'){
                dfs(row,col,board);
            }
        }
    }
    int findMines(int r,int c,char[][] b){
        int[] dx={0,1,1,1,0,-1,-1,-1};
        int[] dy={1,1,0,-1,-1,-1,0,1};
        int rs=0;
        for(int i=0;i<dx.length;i++){
            int row=r+dx[i];
            int col=c+dy[i];
            if(row>=0&&row<b.length&&col>=0&&col<b[0].length&&b[row][col]=='M'){
                rs++;
            }
        }
        return rs;
    }
}
