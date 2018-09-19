package GraphAndSearch;

/**
 * Created by yufengzhu on 9/9/18.
 */
public class Minesweeper {
    public static void main(String[] args){
        Minesweeper ms=new Minesweeper();
        char[][] board={{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
        ms.updateBoard(board,new int[]{3,0});
    }
    //一开始不懂他的规则，开了这个就懂了https://leetcode.com/problems/minesweeper/discuss/99826/Java-Solution-DFS-+-BFS
    //https://leetcode.com/problems/minesweeper/discuss/99841/Straight-forward-Java-solution
    //写起来还是没写对，还一定要先count周边的mine才行，否则不能正确stop dfs
    public char[][] updateBoard(char[][] board, int[] click) {

        if(board.length==0){
            return board;
        }
        boolean[][] memo=new boolean[board.length][board[0].length];
        dfs(click[0],click[1],board,memo);
        return board;
    }
    void dfs(int row,int col,char[][] board,boolean[][] memo){
        if(memo[row][col]){
            return;
        }
        memo[row][col]=true;
        if(board[row][col]=='M'){
            board[row][col]='X';
            memo[row][col]=false;
            return;
        }
        if(board[row][col]-'0'>0&&board[row][col]-'0'<=8){
            memo[row][col]=false;
            return;
        }
        int[] x={-1,-1,0,1,1,1,0,-1};
        int[] y={0,1,1,1,0,-1,-1,-1};
        board[row][col]='B';//这个放下面dfs那里的话就解决不了[["E"]][0,0]这个case了
        for(int i=0;i<8;i++){
            if(row+x[i]>=board.length||row+x[i]<0||col+y[i]>=board[0].length||col+y[i]<0){
                continue;
            }
            int count=countMine(board,row,col);
            if(count!=0){
                board[row][col]=(char)('0'+count);
            }else{
                dfs(row+x[i],col+y[i],board,memo);
            }
        }

    }
    int countMine(char[][] board,int row,int col){
        int count=0;
        int[] x={-1,-1,0,1,1,1,0,-1};
        int[] y={0,1,1,1,0,-1,-1,-1};
        for(int i=0;i<8;i++){
            if(row+x[i]>=board.length||row+x[i]<0||col+y[i]>=board[0].length||col+y[i]<0){
                continue;
            }
            if(board[row+x[i]][col+y[i]]=='M'){
                count++;
            }
        }
        return count;
    }
}
