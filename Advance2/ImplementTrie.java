package Advance2;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by 502575560 on 7/19/17.
 */
public class ImplementTrie {
//也几乎是一次过,有一些小差错,记得要children用hashmap做,而且注意trie一般不用删除某个单词
    private TrieNode root;

    public ImplementTrie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur=root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(!cur.children.containsKey(c)){
                TrieNode n=new TrieNode(c);
                cur.children.put(c,n);
            }
            cur=cur.children.get(c);
            if(i==word.length()-1){
                cur.isEnd=true;
            }
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode cur=root;
        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            if(!cur.children.containsKey(c)){
                return false;
            }
            cur=cur.children.get(c);
            if(i==word.length()-1){
                return cur.isEnd;
            }
        }
        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode cur=root;
        for(int i=0;i<prefix.length();i++) {
            char c = prefix.charAt(i);
            if(!cur.children.containsKey(c)){
                return false;
            }
            cur=cur.children.get(c);//这个方法和search的区别就是不管最后这个字符这是否isEnd
        }
        return true;
    }
}
class TrieNode {
    // Initialize your data structure here.
    boolean isEnd;
    HashMap<Character,TrieNode> children;
    char val;
    public TrieNode() {
        children=new HashMap<>();
    }
    public TrieNode(char c){
        val=val;
        children=new HashMap<>();
    }

}