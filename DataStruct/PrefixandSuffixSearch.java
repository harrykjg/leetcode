package DataStruct;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yufengzhu on 8/2/18.
 */
public class PrefixandSuffixSearch {
    public static void main(String[] args){
        String[] words={"pop"};
        PrefixandSuffixSearch ps=new PrefixandSuffixSearch(words);
        ps.f("p","");


    }
    //不会，只会暴力用2个trie然后各自得出的结果集再去比较
    //试了一下，发现空字符串是所有字符串的前缀和后缀

    //https://www.youtube.com/watch?v=a-4WbFqalIA   //他们的方法都没有用map，没太仔细看为什么
    //https://leetcode.com/problems/prefix-and-suffix-search/solution/
    //https://blog.csdn.net/u014688145/article/details/78766624  答案三的方法但是不用trie
    //下面的代码是看了他们思路再自己写的，好想比他们的复杂，而且用了map
    Trie root;
    HashMap<String,Integer> map=new HashMap<>();
    public PrefixandSuffixSearch(String[] words) {
        root=new Trie(' ');
        for(int i=0;i<words.length;i++){
            char[]ch=words[i].toCharArray();
            StringBuilder sb=new StringBuilder();
            sb.append('#');
            sb.append(words[i]);//这里容易漏了插入#apple这样的，否则的话就解决不了surffix和prefix都是""的情况
            root.insert(sb.toString(),i);
            map.put(sb.toString(),i);
            for(int j=ch.length-1;j>=0;j--){
                sb.insert(0,ch[j]);
                root.insert(sb.toString(),i);
                map.put(sb.toString(),i);
            }

        }

    }

    public int f(String prefix, String suffix) {
        String s=suffix+'#'+prefix;
        if(!root.startsWith(s)){
            return -1;
        }
        int rs=Integer.MIN_VALUE;
        ArrayList<String> ls=root.search(s);
        for(String ss:ls){
            rs=Math.max(rs,map.get(ss));
        }
        return rs;

    }

    class Trie{
        Character c;
        HashMap<Character,Trie> children=new HashMap<>();
        int weight;
        boolean end;
        public Trie(Character c){
            this.c=c;
        }
        public boolean startsWith(String prefix) {
            if (prefix == null) {
                return true;
            }
            char[] ch = prefix.toCharArray();
            Trie cur = root;
            for (int i = 0; i < ch.length; i++) {
                if(!cur.children.containsKey(ch[i])){
                    return false;
                }
                cur=cur.children.get(ch[i]);
            }
            return true;

        }
        public void insert(String word,int weight) {
            if (word == null) {
                return;
            }
            char[] ch = word.toCharArray();
            Trie cur = root;
            for (int i = 0; i < ch.length; i++) {
                if (!cur.children.containsKey(ch[i])) {
                    Trie node = new Trie(ch[i]);
                    cur.children.put(ch[i], node);
                }
                if (i == ch.length - 1) {
                    cur.children.get(ch[i]).end = true;
                    cur.children.get(ch[i]).weight=weight;
                }
                cur = cur.children.get(ch[i]);
            }
        }

        /**
         * Returns if the word is in the trie.
         */
         ArrayList<String> search(String word) {
             ArrayList<String> rs=new ArrayList<>();
            if (word == null) {
                return rs;
            }
            char[] ch = word.toCharArray();
            Trie cur = root;
            for (int i = 0; i < ch.length; i++) {
                cur=cur.children.get(ch[i]);
            }
            dfs(cur,word,rs);
             return rs;

        }
        void dfs(Trie n,String cur,ArrayList<String> al){
            if(n.children.size()==0){
                al.add(cur);
                return;
            }
            if(n.end==true){
                al.add(cur);
            }
            for(Character c:n.children.keySet()){
                dfs(n.children.get(c),cur+String.valueOf(c),al);
            }
        }
    }
}
