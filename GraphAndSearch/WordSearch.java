package GraphAndSearch;

/**
 * Created by yufengzhu on 8/15/18.
 */
public class WordSearch {
    //8/15/2018 忘了用memo,还写不对,有些人没用memo，那是因为他visit到某个点的时候把board改了，dfs这一层完了再改回来
    public boolean exist(char[][] board, String word) {
        if(word.length()==0){
            return true;
        }
        boolean[][] memo=new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                memo[i][j]=true;
                if(dfs(i,j,0,word,board,memo)){
                    return true;
                }
                memo[i][j]=false;
            }
        }
        return false;
    }
    boolean dfs(int r,int c,int cur,String word,char[][] board,boolean[][] memo){
        if(board[r][c]!=word.charAt(cur)){
            return false;
        }
        if(cur==word.length()-1){
            return true;
        }
        if(r-1>=0&&board[r-1][c]==word.charAt(cur+1)&&!memo[r-1][c]){
            memo[r-1][c]=true;
            if(dfs(r-1,c,cur+1,word,board,memo)){
                return true;
            }
            memo[r-1][c]=false;
        }
        if(c+1<board[0].length&&board[r][c+1]==word.charAt(cur+1)&&!memo[r][c+1]){
            memo[r][c+1]=true;
            if(dfs(r,c+1,cur+1,word,board,memo)){
                return true;
            }
            memo[r][c+1]=false;
        }
        if(r+1<board.length&&board[r+1][c]==word.charAt(cur+1)&&!memo[r+1][c]){
            memo[r+1][c]=true;
            if(dfs(r+1,c,cur+1,word,board,memo)){
                return true;
            }
            memo[r+1][c]=false;
        }
        if(c-1>=0&&board[r][c-1]==word.charAt(cur+1)&&!memo[r][c-1]){
            memo[r][c-1]=true;
            if(dfs(r,c-1,cur+1,word,board,memo)){
                return true;
            }
            memo[r][c-1]=false;
        }

        return false;
    }

    //9／10／2018,不用memo，就去到某点之后把它改成特殊字符再改回来
    public boolean exist2(char[][] board, String word) {
        if(word.length()==0){
            return true;
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){

                if(dfs(i,j,0,word,board)){
                    return true;
                }
            }
        }
        return false;
    }
    boolean dfs(int r,int c,int cur,String word,char[][] board){
        if(board[r][c]!=word.charAt(cur)){
            return false;
        }
        if(cur==word.length()-1){
            return true;
        }
        board[r][c]='-';;
        if(r-1>=0&&board[r-1][c]==word.charAt(cur+1)){

            if(dfs(r-1,c,cur+1,word,board)){
                return true;
            }
        }
        if(c+1<board[0].length&&board[r][c+1]==word.charAt(cur+1)){
            if(dfs(r,c+1,cur+1,word,board)){
                return true;
            }
        }
        if(r+1<board.length&&board[r+1][c]==word.charAt(cur+1)){
            if(dfs(r+1,c,cur+1,word,board)){
                return true;
            }
        }
        if(c-1>=0&&board[r][c-1]==word.charAt(cur+1)){
            if(dfs(r,c-1,cur+1,word,board)){
                return true;
            }
        }
        board[r][c]=word.charAt(cur);
        return false;
    }
}
