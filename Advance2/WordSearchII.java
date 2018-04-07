package Advance2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 502575560 on 7/19/17.
 */
public class WordSearchII {
    //wordSearch第一题只需要找一个单词,思路就是dfs,而第二题要找一堆word,思路就不同了,不是对于每个word去board里找,那样的话应该会有很多重复搜索,
    //思路是对words建一个trie,然后dfs board里的所有字符,以每个字符为起点继续,上下左右dfs,判断是否进行dfs的条件就是判断当前dfs的路径形成的单词
    //是否在trie里
    //http://blog.csdn.net/ljiabin/article/details/45846527  这第一,二个链接result其实都是先用set装,再转成list的..貌似如果不实现trie删除的话就只能这样了
    //http://blog.csdn.net/xudli/article/details/45864915
    //http://bookshadow.com/weblog/2015/05/19/leetcode-word-search-ii/ 这个trie还有删除方法
    public static void main(String[] args){
        WordSearchII ws=new WordSearchII();
        char[][] b=new char[][]{{'a','b'},{'c','d'}};
//        char[][] b=new char[][]{{'a'}};
        ws.findWords2(b,new String []{"abcd"});
    }
    HashSet<String> rs = new HashSet<>();//结果集要用set的原因是，因为是用的是target建的树（为啥不用board建树？因为board建树的话就等于要dfs所有可能的字串了，那不如直接dfs了）
                                                // ，而我们从board里dfs出各种可能性去match字典树，因此board的有可能有重复的字串，所以要用set
    public List<String> findWords(char[][] board, String[] words) {
        ImplementTrie trie = new ImplementTrie();
        for (String s : words) {
            trie.insert(s);
        }
        boolean[][] memo = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(i, j, "", memo, trie, board);
            }
        }
        return new ArrayList<>(rs);

    }

    public void dfs(int row, int col, String cur, boolean[][] memo, ImplementTrie trie, char[][] board) {
        if (memo[row][col]) {
            return;
        }

        memo[row][col] = true;
        cur = cur + board[row][col];
        if (trie.search(cur)) {
            rs.add(cur);//注意这里之前写了return,其实不用,因为这个路径上继续走可能还能找到要找的单词
        }
        if (trie.startsWith(cur)) {
            if (row - 1 >= 0) {
                dfs(row - 1, col, cur, memo, trie, board);
            }
            if (col + 1 < board[0].length) {
                dfs(row, col + 1, cur, memo, trie, board);
            }
            if (row + 1 < board.length) {

                dfs(row + 1, col, cur, memo, trie, board);
            }
            if (col - 1 >= 0) {
                dfs(row, col - 1, cur, memo, trie, board);
            }
        }
        memo[row][col] = false;
    }
//4/4/2018九章第二轮,自己想的话估计只能dfs了，看了视频后知道用trie枝剪,写的不顺,要练
    public List<String> findWords2(char[][] board, String[] words) {
        ImplementTrie trie = new ImplementTrie();
        for(String word:words){
            trie.insert(word);
        }
        boolean[][] memo=new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                dfs2(board,i,j,"",trie,memo);//这个dfs，开始两层for是再dfs里面的，那样写貌似不好写对，要放在外面，代表每个点东从""开始搜，而且只能往上下左右去扩展，
                //而如果两层for循环写在dfs里面的话，就会导致从board的第一行最后一个可以扩展到第二行第一个这样的情况，比较难想，而且容易和
                //combinationsum和permutation那些搞混，那些就是for循环都在dfs里的
            }
        }

        ArrayList<String> al=new ArrayList<>(rs);
        return al;
    }

    void dfs2(char[][] board,int row,int col,String cur,ImplementTrie trie,boolean[][] memo){
        if(memo[row][col]){
            return;
        }
        memo[row][col]=true;
        if(trie.search(cur)){
            rs.add(cur);
        }

        if(trie.search(cur+board[row][col])){
            rs.add(cur+board[row][col]);
        }
        if(trie.startsWith(cur+board[row][col])){

            if(row>0){
                dfs2(board,row-1,col,cur+board[row][col],trie,memo);
            }
            if(col+1<board[0].length){
                dfs2(board,row,col+1,cur+board[row][col],trie,memo);
            }
            if(row+1<board.length){
                dfs2(board,row+1,col,cur+board[row][col],trie,memo);
            }
            if(col>0){
                dfs2(board,row,col-1,cur+board[row][col],trie,memo);
            }

        }

        memo[row][col]=false;
    }


}
