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
        ws.findWords(b,new String []{"ab","ac","bd"});
    }
    HashSet<String> rs = new HashSet<>();
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
                {
                    dfs(row + 1, col, cur, memo, trie, board);
                }
            }
            if (col - 1 >= 0) {
                dfs(row, col - 1, cur, memo, trie, board);
            }
        }
        memo[row][col] = false;
    }
}
