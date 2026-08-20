package SomeInterviews.snap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ValidSudoku36 {
    static void main() {

    }
    //3/7/2026
    public boolean isValidSudoku(char[][] board) {
        Set<Character> set1=new HashSet<>();
        Map<Integer,Set<Character>> map=new HashMap<>();//每一列存一个set

        for (int i=0;i<9;i++){
            set1.clear();//每一行的set
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
            if(!valid(board,rowStart,colStart)){//9个小方格
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
    //verkadar valid 多线程follow up
    public boolean isValidSudoku2(char[][] board) {
        ExecutorService exe= Executors.newFixedThreadPool(3);
        //抄gpt的，就是3个线程一个搞row，一个col，一个9格。也可以27个线程每行9个，每列9格，每个box 9个
        Future<Boolean> rows = exe.submit(() -> validRows(board));
        Future<Boolean> cols = exe.submit(() -> validCols(board));
        Future<Boolean> boxes = exe.submit(() -> validBoxes(board));
        try {
            return rows.get() && cols.get() && boxes.get();
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            exe.shutdown();
        }
    }
    private boolean validRows(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;
                if (!set.add(c)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean validCols(char[][] board) {
        for (int j = 0; j < 9; j++) {
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < 9; i++) {
                char c = board[i][j];
                if (c == '.') continue;
                if (!set.add(c)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean validBoxes(char[][] board) {
        for (int box = 0; box < 9; box++) {
            int rowStart = box / 3 * 3;
            int colStart = box % 3 * 3;
            Set<Character> set = new HashSet<>();
            for (int i = rowStart; i < rowStart + 3; i++) {
                for (int j = colStart; j < colStart + 3; j++) {
                    char c = board[i][j];
                    if (c == '.') continue;
                    if (!set.add(c)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
