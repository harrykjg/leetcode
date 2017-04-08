import java.util.HashMap;

/**
 * Created by 502575560 on 7/13/16.
 */
public class Trie {

    //july2017有个typo一次过.root是不存character的,都去children存了
    public static void main(String[] args){
        Trie t=new Trie();
        t.insert("ab");
        System.out.println(t.search("a"));
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if(word==null||word.equals("")){
            return;
        }

        TrieNode cur=root;

        for(int i=0;i<word.length();i++){
            HashMap<Character,TrieNode> children=cur.children;
            if(children.containsKey(word.charAt(i))){
                cur=children.get(word.charAt(i));
            }else{
                TrieNode node=new TrieNode(word.charAt(i));
                children.put(word.charAt(i),node);
                cur=node;
            }
            if(i==word.length()-1){
                cur.isEnd=true;
            }

        }

    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if(word==null||word.length()==0){
            return false;
        }
        TrieNode cur=root;
        for(int i=0;i<word.length();i++){
            HashMap<Character,TrieNode> children=cur.children;
            if(children.containsKey(word.charAt(i))){
                cur=children.get(word.charAt(i));
            }else {
                return false;
            }
            if(i==word.length()-1){
                if(cur.isEnd==true){
                    return true;
                }
            }
        }
        return false;

    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if(prefix==null||prefix.length()==0){
            return false;
        }
        TrieNode cur=root;
        for(int i=0;i<prefix.length();i++){
            HashMap<Character,TrieNode> children=cur.children;
            if(children.containsKey(prefix.charAt(i))){
                cur=children.get(prefix.charAt(i));
            }else {
                return false;
            }
        }
        return true;

    }
}

class TrieNode {
    // Initialize your data structure here.
    boolean isEnd=false;
    Character c;
    HashMap<Character,TrieNode> children=new HashMap<>();
    public TrieNode() {

    }
    public TrieNode(Character c) {
       this.c=c;
    }
}