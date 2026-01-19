package 灵神.常用数据结构.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII212 {
    static void main() {

    }
    //12/9/2025,看了答案
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> rs=new HashSet<>();
        ImplementTriePrefixTree208 t=new ImplementTriePrefixTree208();
        for(String s:words){
            t.insert(s);
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(t.search(board[i][j]+"")){
                    rs.add(board[i][j]+"");
                }
                dfs(board[i][j]+"",i,j,board,new boolean[board.length][board[0].length],t,rs);
            }
        }
        return new ArrayList<>(rs);
    }
    void dfs(String cur,int r,int c,char[][] board,boolean[][] memo,ImplementTriePrefixTree208 t, Set<String> rs){
        memo[r][c]=true;
        int[] dx={0,-1,0,1};
        int[] dy={1,0,-1,0};
        for(int i=0;i<4;i++){
            int row=r+dx[i];
            int col=c+dy[i];
            if(row>=0&&row<board.length&&col>=0&&col<board[0].length&&!memo[row][col]){
                String next=cur+board[row][col];
                if(t.search(next)){
                    rs.add(next);
                }
                if(t.startsWith(next)){
                    memo[row][col]=true;
                    dfs(next,row,col,board,memo,t,rs);
                    memo[row][col]=false;
                }
            }
        }
        memo[r][c]=false;
    }

}

