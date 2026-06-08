package SomeInterviews.snowflake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KVStorewithPrefixSearch {
    //这个是hack2hire原题，我看答案是map直接存int，和trie是共存的关系。
    Map<String,Integer> map;
    Trie head;
    public KVStore() {
        // TODO: Initialize KVStore
        map=new HashMap<>();
        head=new Trie('0');
    }

    public void set(String key, int value) {
        // TODO: Implement set logic
        map.put(key,value);
        insert(key,value);
    }

    public int get(String key) {
        // TODO: Implement get logic
        return map.getOrDefault(key,-1);
    }

    public void update(String key, int value) {
        // TODO: Implement update logic
        map.put(key,value);
        updateTrie(key,value);
    }

    public void deleteKey(String key) {
        // TODO: Implement deleteKey logic
        map.remove(key);
        deleteTire(key);
    }

    public List<Integer> prefixSearch(String prefix) {
        // TODO: Implement prefixSearch logic
        Trie cur=head;
        char[] ch=prefix.toCharArray();
        List<Integer> rs=new ArrayList<>();
        for (int i=0;i<ch.length;i++){
            if(cur.children.containsKey(ch[i])){
                cur=cur.children.get(ch[i]);
            }else{
                return rs;
            }
            if(i==ch.length-1){
               collect(cur,rs);
            }
        }
        return rs;
    }
    void collect(Trie node,List<Integer> rs){
        if(node.value!=null){
            rs.add(node.value);
        }
        for(Trie nei:node.children.values()){
            collect(nei,rs);
        }
    }
    void insert(String s,int val){
        char[] ch=s.toCharArray();
        Trie cur=head;
        for (int i=0;i<ch.length;i++){
            if(!cur.children.containsKey(ch[i])){
                Trie node=new Trie(ch[i]);
                cur.children.put(ch[i],node);
            }
            cur=cur.children.get(ch[i]);
        }
        cur.value=val;
        cur.key=s;
    }
    //只是找到对应的点然后把value设成空，因为可能他还有children呢
    void deleteTire(String s){
        char[] ch=s.toCharArray();
        Trie cur=head;
        for (int i=0;i<ch.length;i++){
            if(cur.children.containsKey(ch[i])){
                cur=cur.children.get(ch[i]);
                continue;
            }else{
                return;
            }
        }
        cur.value=null;

    }
    void updateTrie(String key,int val){
        char[] ch=key.toCharArray();
        Trie cur=head;
        for (int i=0;i<ch.length;i++){
            if(cur.children.containsKey(ch[i])) {
                cur = cur.children.get(ch[i]);
            }else{
                return;
            }
            if(i==ch.length-1){
                cur.value=val;
            }
        }
    }
}
class Trie{
    Character c;
    Integer value;//写成integer就可以判断是否为null
    String key;//相当于isString才有值
    Map<Character,Trie> children;
    public Trie(Character c){
        this.c=c;
        children=new HashMap<>();
    }
}
