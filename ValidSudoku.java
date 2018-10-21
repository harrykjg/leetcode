import java.util.HashSet;

/**
 * Created by yufengzhu on 8/10/18.
 */
public class ValidSudoku {
    //8／10／2018准备uber时
    //意思应该是'.'就算没问题，不用检测
    //https://leetcode.com/problems/valid-sudoku/discuss/15450/Shared-my-concise-Java-code 这个写法好
    public boolean isValidSudoku(char[][] board) {
        if(board.length!=9||board[0].length!=9){
            return false;
        }
        for(int i=0;i<board.length;i++){
            HashSet<Character> set=new HashSet<>();
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]!='.'){
                    if(set.contains(board[i][j])){
                        return false;
                    }else{
                        set.add(board[i][j]);
                    }
                }
            }

            set=new HashSet<>();
            for(int j=0;j<board.length;j++){
                if(board[j][i]!='.'){
                    if(set.contains(board[j][i])){
                        return false;
                    }else{
                        set.add(board[j][i]);
                    }
                }
            }
        }

        for(int i=0;i<9;i++){
            HashSet<Character> set=new HashSet<>();
            for(int j=i/3*3;j<i/3*3+3;j++){ //这个横坐标和纵坐标都忘了再乘以3了，注意i/3*3不等与i哦，是先算的i/3再乘以3的
                for(int k=i%3*3;k<i%3*3+3;k++){
                    if(board[j][k]!='.'){
                        if(set.contains(board[j][k])){
                            return false;
                        }else{
                            set.add(board[j][k]);
                        }
                    }
                }
            }
        }
        return true;
    }
    //9/29/2019,写错了，还写的有点慢,不知道哪里错了懒得debug
    public boolean isValidSudoku2(char[][] board) {

    }
}
