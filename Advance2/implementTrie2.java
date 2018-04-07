package Advance2;

import java.util.HashMap;

/**
 * Created by yufengzhu on 4/4/18.
 */
public class implementTrie2 {
    //九章第二轮,除了typo之外一次过
    public static void main(String[] args){
        implementTrie2 it=new implementTrie2();
        it.insert("abc");
        it.search("abc");
        it.startsWith("ab");
    }
    trieNode root;
    /**
     * Initialize your data structure here.
     */
    public implementTrie2() {
        root = new trieNode(' ');
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] ch = word.toCharArray();
        trieNode cur = root;
        for (int i = 0; i < ch.length; i++) {
            if (!cur.children.containsKey(ch[i])) {
                trieNode node = new trieNode(ch[i]);
                cur.children.put(ch[i], node);
            }
            if (i == ch.length - 1) {
                cur.children.get(ch[i]).end = true;
            }
            cur = cur.children.get(ch[i]);
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        if (word == null) {
            return true;
        }
        char[] ch = word.toCharArray();
        trieNode cur = root;
        for (int i = 0; i < ch.length; i++) {
            if (!cur.children.containsKey(ch[i])) {
                return false;
            }
            cur = cur.children.get(ch[i]);
            if (i == word.length() - 1) {
                if (cur.end == true) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (prefix == null) {
            return true;
        }
        char[] ch = prefix.toCharArray();
        trieNode cur = root;
        for (int i = 0; i < ch.length; i++) {
            if(!cur.children.containsKey(ch[i])){
                return false;
            }
            cur=cur.children.get(ch[i]);
        }
        return true;

    }

    class trieNode {
        Character value;
        HashMap<Character, trieNode> children;
        boolean end;

        public trieNode(Character c) {
            children = new HashMap<>();
            value = c;
        }
    }
}