package SomeInterviews.snap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidSudoku36 {
    static void main() {

    }
    //3/7/2026
    public boolean isValidSudoku(char[][] board) {
        Set<Character> set1=new HashSet<>();
        Map<Integer,Set<Character>> map=new HashMap<>();

        for (int i=0;i<9;i++){
            set1.clear();
            for (int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    continue;
                }
                if(set1.contains(board[i][j])){
                    return false;
                }
                set1.add(board[i][j]);
                Set<Character> set2=map.getOrDefault(j,new HashSet<>());
                if(set2.contains(board[i][j])){
                    return false;
                }
                set2.add(board[i][j]);
                map.put(j,set2);//这里容易漏这个，因为这个set第一次来是default的，因此从来没加进来过
            }
            int rowStart=i/3*3;
            int colStart=i%3*3;
            if(!valid(board,rowStart,colStart)){
                return false;
            }
        }
        return true;
    }
    boolean valid(char[][] board,int row,int col){
        Set<Character> set=new HashSet<>();
        for (int i=row;i<row+3;i++){
            for (int j=col;j<col+3;j++){
                if(board[i][j]=='.'){
                    continue;
                }
                if(!set.add(board[i][j])){
                    return false;
                }
            }
        }
        return true;
    }
}
