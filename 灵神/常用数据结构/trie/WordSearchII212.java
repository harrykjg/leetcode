package 灵神.常用数据结构.trie;

import java.util.*;

public class WordSearchII212 {
    static void main() {
        WordSearchII212 ws=new WordSearchII212();
        char[][] b={{'a','a'}};
        String[] s={"aa"};
        ws.findWords2(b,s);
    }
    //12/9/2025,看了答案，这是超时的，因为trie是从头开始search整个word，应该是从某一个trie的node开始
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> rs=new HashSet<>();
        ImplementTriePrefixTree t=new ImplementTriePrefixTree();
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
    void dfs(String cur,int r,int c,char[][] board,boolean[][] memo,ImplementTriePrefixTree t, Set<String> rs){
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

    //3/12/2026 写的还是不好，主要是怎么去下一个string，答案的trie结构是有一个完整的string的，其实就是插入的时候到最后一个node的时候设上完整的string
    //而且这里的trie和208题implementtrie有一点不一样，那个class 嵌套了trie，这里直接就是trie
    //https://leetcode.cn/problems/word-search-ii/solutions/1000172/dan-ci-sou-suo-ii-by-leetcode-solution-7494/
    public List<String> findWords2(char[][] board, String[] words) {
        ImplementTriePrefixTree t=new ImplementTriePrefixTree();
        for (String s:words){
            t.insert(s);
        }
        Set<String> set=new HashSet<>();
        boolean[][] memo=new boolean[board.length][board[0].length];

        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[0].length;j++){
                if(t.children.containsKey(board[i][j])){//这里有点不好想，我要从i,j开始的话，意味着这个字符已经算在cur里了吗？我这里写是的，
                                                // 我觉得放进dfs里判断也是可以的，那就是不同的写法
                    dfs2(board[i][j]+"",i,j,board,memo,t,set);
                }
            }
        }
        return new ArrayList<>(set);
    }
    void dfs2(String cur,int row,int col,char[][] board,boolean[][]memo, ImplementTriePrefixTree t, Set<String> set){
        memo[row][col]=true;
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        ImplementTriePrefixTree now=t.children.get(board[row][col]);
        if(now.word!=null&&now.word.equals(cur)){
            set.add(cur);
        }
        for (int i=0;i<4;i++){
            int r=row+dx[i];
            int c=col+dy[i];
            if(r>=0&&r<board.length&&c>=0&&c<board[0].length&&!memo[r][c]){
                String next=cur+board[r][c];
                if(now.children.containsKey(board[r][c])){
                    dfs2(next,r,c,board,memo,now,set);
                }
            }
        }
        memo[row][col]=false;
    }

}

class ImplementTriePrefixTree {
    Map<Character, ImplementTriePrefixTree> children;
    boolean isEnd;
    String word;

    public ImplementTriePrefixTree() {
        children=new HashMap<>();
    }

    public void insert(String word) {
        if(word.length()==0){
            return;
        }
        char[] ch=word.toCharArray();
        ImplementTriePrefixTree cur=this;//注意208题写的是=head，这里是this
        for (int i=0;i<ch.length;i++){
            if(!cur.children.containsKey(ch[i])){
                ImplementTriePrefixTree child=new ImplementTriePrefixTree();
                cur.children.put(ch[i],child);
            }
            cur=cur.children.get(ch[i]);
            if(i==ch.length-1){
                cur.isEnd=true;
                cur.word=word;
            }
        }


    }

    public boolean search(String word) {
        if(word.length()==0){
            return true;
        }
        char[] ch=word.toCharArray();
        ImplementTriePrefixTree cur=this;
        for (int i=0;i<ch.length;i++){
            if(!cur.children.containsKey(ch[i])){
                return false;
            }
            cur=cur.children.get(ch[i]);
        }
        return cur.isEnd;

    }
        //不需要这个
    public boolean startsWith(String prefix) {
        if(prefix.length()==0){
            return true;
        }
        char[] ch=prefix.toCharArray();
        ImplementTriePrefixTree cur=this;
        for (int i=0;i<ch.length;i++){
            if(!cur.children.containsKey(ch[i])){
                return false;
            }
            cur=cur.children.get(ch[i]);
        }
        return true;
    }
}

