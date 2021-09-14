package DataStruct;

import java.util.HashMap;

public class DesignAddandSearchWordsDataStructure {
    public static void main(String[] args){
        DesignAddandSearchWordsDataStructure da=new DesignAddandSearchWordsDataStructure();
        da.WordDictionary();
        da.addWord("bad");
        da.addWord("dad");
        da.addWord("mad");
        System.out.println(da.search("b..."));
    }
    Trie root;
    public void WordDictionary() {
        root=new Trie(' ');
    }

    public void addWord(String word) {
        root.add(word);
    }

    public boolean search(String word) {
        return root.search(root,word);
    }
    class Trie{
        char val;
        HashMap<Character,Trie> children=new HashMap<>();
        boolean isEnd;
        public Trie(char val){
            this.val=val;
        }
        void add(String word){
            char[] ch=word.toCharArray();
            Trie cur=this;
            for (int i=0;i<ch.length;i++){
                if (!cur.children.containsKey(ch[i])){
                    Trie node=new Trie(ch[i]);
                    cur.children.put(ch[i],node);
                }
                if (i==ch.length-1){
                    cur.children.get(ch[i]).isEnd=true;
                }
                cur=cur.children.get(ch[i]);
            }
        }
         boolean search (Trie root,String word){
             char[] ch=word.toCharArray();
             Trie cur= root;
             for (int i=0;i<ch.length;i++){
                 if (ch[i]=='.'){
                     for (char c:cur.children.keySet()){
                         if (i==word.length()-1&&cur.children.get(c).isEnd){//如果到了最后一位
                             return true;
                         }
                         if (search(cur.children.get(c),word.substring(i+1))){
                             return true;
                         }
                     }
                     return false;//开始写成了continue就错了
                 }
                 if (!cur.children.containsKey(ch[i])){
                     return false;
                 }
                 if (i==ch.length-1&&cur.children.get(ch[i]).isEnd){
                     return true;
                 }
                 cur=cur.children.get(ch[i]);
             }
             return false;
        }
    }
}
