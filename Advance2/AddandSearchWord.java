package Advance2;

/**
 * Created by 502575560 on 7/19/17.
 */
public class AddandSearchWord {
    public static void main(String[] args){
        AddandSearchWord as=new AddandSearchWord();
        as.addWord("a");
        as.addWord("ab");
        System.out.println(as.search("a"));
        System.out.println(as.search("a."));
        System.out.println(as.search("ab"));
        System.out.println(as.search(".a"));
        System.out.println(as.search(".b"));
        System.out.println(as.search("ab."));
        System.out.println(as.search("."));
        System.out.println(as.search(".."));
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
}
