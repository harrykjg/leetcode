package SomeInterviews.snowflake;

import java.util.Arrays;

public class ValidTicTacToeState794 {
    static void main() {
        String[] b={"XXX","   ","OOO"};
        System.out.println(ValidTicTacToeState794.validTicTacToe(b));
    }
    //说是原题扩展板
    //5/22/2026
    //1：x肯定大于等于o 2：x和o相差小于2 3：还有就是如果x赢了，则o肯定少一步，如果o赢则x肯定一样的步数，但是谁先赢呢，这很tricky
    //用一个变量win代表谁赢是不行的，需要两个变量分别表示x和o有没有三连的
    public static boolean validTicTacToe(String[] board) {
        int x=0;
        int o=0;
        boolean xwin=false;
        boolean ywin=false;
        for (int i=0;i<board.length;i++){
            char[] row= board[i].toCharArray();
            for (int j=0;j<3;j++){
                if(row[j]=='X'){
                    x++;
                }else if(row[j]=='O'){
                    o++;
                }
            }
            //列
            char c=board[0].charAt(i);
            if(c==board[0].charAt(i)&&c==board[1].charAt(i)&&c==board[2].charAt(i)){
                if(c=='X'){
                    xwin=true;
                }
                if(c=='O'){
                    ywin=true;
                }
            }
            if(board[i].equals("XXX")){
                xwin=true;
            }
            if ((board[i].equals("OOO"))){
                ywin=true;
            }
        }
        //对角线1
        char c=board[0].charAt(0);
        if(c==board[1].charAt(1)&&c==board[2].charAt(2)){
            if(c=='X'){
                xwin=true;
            }
            if(c=='O'){
                ywin=true;
            }
        }
        //对角线2
        c=board[0].charAt(2);
        if(c==board[1].charAt(1)&&c==board[2].charAt(0)){
            if(c=='X'){
                xwin=true;
            }
            if(c=='O'){
                ywin=true;
            }
        }
        if(xwin&&ywin){//处理["OOO","XXO","XXX"]
            return false;
        }
        if(xwin){
            return x==o+1;
        }
        if(ywin){
            return x==o;
        }
        if(x-o>=2){
            return false;
        }
        if(o>x){
            return false;
        }
        return true;
    }
}
