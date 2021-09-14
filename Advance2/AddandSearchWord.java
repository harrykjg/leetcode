package Advance2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 502575560 on 7/19/17.
 */
public class AddandSearchWord {
    public static void main(String[] args){
        AddandSearchWord as=new AddandSearchWord();
        as.addWord2("aa");
//        as.addWord("ab");
        System.out.println(as.search2("."));
//        System.out.println(as.search("a."));
//        System.out.println(as.search("ab"));
//        System.out.println(as.search(".a"));
//        System.out.println(as.search(".b"));
//        System.out.println(as.search("ab."));
//        System.out.println(as.search("."));
//        System.out.println(as.search(".."));
    }

    TrieNode root;
    public AddandSearchWord() {
        root=new TrieNode();
    }
//没看懂以前leetcode写的啥玩意有个min的,反正我这里就是用了trienode,无非就是遇到'.'的时候递归,已当前节点的每一个children中的node为起点,除去'.'的substring为word再去搜
    /** Adds a word into the data structure. */
    public void addWord(String word) {
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

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        TrieNode cur=root;
        return helper(root,word);
    }
    boolean helper(TrieNode r,String word){

        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(c!='.'){
                if(!r.children.containsKey(c)){
                    return false;
                }
                r=r.children.get(c);
                if(i==word.length()-1){
                    return r.isEnd;
                }
            }else {
                if(r.children.isEmpty()){
                    return false;
                }else {
                    for(Character cha:r.children.keySet()){
                        TrieNode temp=r.children.get(cha);
                        String next=word.substring(i+1);
                        if(next.length()==0){
                            return temp.isEnd;
                        }
                        if(helper(temp,word.substring(i+1))){
                            return true;
                        }
                    }
                    return false;
                }
            }

        }
        return false;
    }

    //6/15/2021,思路基本记得，改了几次accept，主要是细节想清楚，isend要考虑
    Trie root2=new Trie(' ');
    public void addWord2(String word) {
        // write your code here
        Trie cur=root2;
        char[] ch=word.toCharArray();
        for (int i=0;i<ch.length;i++){
            if (!cur.children.containsKey(ch[i])){
                Trie next=new Trie(ch[i]);
                cur.children.put(ch[i],next);
            }
            if (i==ch.length-1){
                cur.children.get(ch[i]).isEnd=true;
            }
            cur=cur.children.get(ch[i]);
        }
    }

    /*
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search2(String word) {
        if (word.length()==0){
            return true;
        }
        // write your code here
        return helper2(root2,word);
    }
    boolean helper2(Trie node, String word){
        if (word.length()==0){
            return node.isEnd;
        }
        Trie cur=node;
        char[] ch=word.toCharArray();
        for (int i=0;i<word.length();i++){
            if (ch[i]=='.'){
                if (cur.children.isEmpty()){
                    return false;
                }

                for (Trie t:cur.children.values()){
                    if (helper2(t,word.substring(i+1))){
                        return true;
                    }
                }
                return false;
            }else {
                if (!cur.children.containsKey(ch[i])){
                    return false;
                }else {
                    cur=cur.children.get(ch[i]);
                }
                if (i==word.length()-1&&cur.isEnd){
                    return true;
                }
            }
        }
        return false;
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
class Trie{
    char val;
    Map<Character,Trie> children;
    boolean isEnd;
    public Trie(char val){
        this.val=val;
        children=new HashMap<>();
    }
}
