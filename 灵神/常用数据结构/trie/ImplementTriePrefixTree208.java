package 灵神.常用数据结构.trie;

import java.util.*;

public class ImplementTriePrefixTree208 {
    static void main() {

    }
    trie head;
    public ImplementTriePrefixTree208() {
        head=new trie();
    }

    public void insert(String word) {
        if(word.length()==0){
            return;
        }
        char[] ch=word.toCharArray();
        trie cur=head;
        for (int i=0;i<ch.length;i++){
            if(!cur.children.containsKey(ch[i])){
                trie child=new trie();
                child.c=ch[i];
                cur.children.put(ch[i],child);
            }
            cur=cur.children.get(ch[i]);
            if(i==ch.length-1){
                cur.isEnd=true;
            }
        }


    }

    public boolean search(String word) {
        if(word.length()==0){
            return true;
        }
        char[] ch=word.toCharArray();
        trie cur=head;
        for (int i=0;i<ch.length;i++){
            if(!cur.children.containsKey(ch[i])){
                return false;
            }
            cur=cur.children.get(ch[i]);
        }
        return cur.isEnd;

    }

    public boolean startsWith(String prefix) {
        if(prefix.length()==0){
            return true;
        }
        char[] ch=prefix.toCharArray();
        trie cur=head;
        for (int i=0;i<ch.length;i++){
            if(!cur.children.containsKey(ch[i])){
                return false;
            }
            cur=cur.children.get(ch[i]);
        }
        return true;
    }
}
class trie{
    Map<Character, trie> children;
    boolean isEnd;
    Character c;
    public trie(){
        children=new HashMap<>();
    }
}
