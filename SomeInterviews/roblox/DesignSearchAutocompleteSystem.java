package SomeInterviews.roblox;

import java.util.*;

public class DesignSearchAutocompleteSystem {
    Trie root;
    String prefix="";
    public AutocompleteSystem(String[] sentences, int[] times) {
        root=new Trie(' ');
        for (int i=0;i<sentences.length;i++){
            root.insert(sentences[i],times[i]);
        }
    }

    public List<String> input(char c) {
        if(c=='#'){
            root.insert(prefix,1);
            prefix="";//漏了清空
            return new ArrayList<String>();
        }
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->{
            if(a.count==b.count){
                return a.s.compareTo(b.s);
            }
            return b.count-a.count;
        });
        prefix=prefix+c;
        Trie cur=root;
        for (int i=0;i<prefix.length();i++){
            if(cur.children.containsKey(prefix.charAt(i))){
                cur=cur.children.get(prefix.charAt(i));
            }else{
                return new ArrayList<String>();
            }
        }
        for (String s:cur.sentences.keySet()){
            pq.offer(new Pair(s,cur.sentences.get(s)));
        }
        List<String> rs=new ArrayList<>();
        for (int i=0;i<3&& !pq.isEmpty();i++){//漏了检查pq是否为空
            rs.add(pq.poll().s);
        }
        return rs;




    }
}
class Trie{//这个trie和常规的就是多了一个sentences的map，他其实就是对于这个string的每个path上的节点都记录了同样路径下有什么完整的string出现过以及次数，方便
    //查找，就不要一定要查到底才行。isEnd也不需要了，甚至character c也不用

    Map<Character,Trie> children;
    Map<String, Integer> sentences;
    Character c;
    public Trie(char c){
        this.c=c;
        children=new HashMap<>();
        sentences=new HashMap<>();
    }

    //带次数的inset
    void insert(String s,int count){
        char[] ch=s.toCharArray();
        Trie cur=this;
        for (int i=0;i<ch.length;i++){
            if(!cur.children.containsKey(ch[i])){
                Trie next=new Trie(ch[i]);
                cur.children.put(ch[i],next);
            }
            cur=cur.children.get(ch[i]);
            cur.sentences.put(s,cur.sentences.getOrDefault(s,0)+count);
        }

    }

}
class Pair{
    String s;
    int count;
    public Pair(String s, int count){
        this.s=s;
        this.count=count;
    }
}

