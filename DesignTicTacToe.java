/**
 * Created by yufengzhu on 11/6/18.
 */
public class DesignTicTacToe {

    //只想得到暴力法每个move都去检查周边的棋子会不会连成n个，我原来以为连城3个就行了，结果是要连城n个
    //https://leetcode.com/problems/design-tic-tac-toe/discuss/81898/Java-O(1)-solution-easy-to-understand
    int[] rows;
    int[] cols;
    int dig1;
    int dig2;
    public DesignTicTacToe(int n) {
        rows=new int[n];
        cols=new int[n];

    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if(player==1){
            rows[row]++;
            cols[col]++;
            if(row==col){
                dig1++;
            }
            if(row==rows.length-1-col){
                dig2++;
            }
        }else {
            rows[row]--;
            cols[col]--;
            if(row==col){
                dig1--;
            }
            if(row==rows.length-1-col){
                dig2--;
            }
        }

        if(rows[row]==rows.length||cols[col]==rows.length||dig1==rows.length||dig2==rows.length){
            return 1;
        }
        if(rows[row]==-rows.length||cols[col]==-rows.length||dig1==-rows.length||dig2==-rows.length){
            return 2;
        }
        return 0;
    }
}
