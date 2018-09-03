package DataStruct;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Created by yufengzhu on 8/22/18.
 */
public class LongestWordinDictionary {
//自己想的，改了下trie之后accept,但貌似有更快的解法，我用了priorityqueue，别人不用。貌似trie没啥优势，因为反正所有前缀要是存在的话，已经在words里了
    //思路就是所有words建trie，然后从word最长的那个开始，dfs找他的少了最后一个字符的前缀在trie里是否存在，就行了，貌似可以优化，写一个方法，每搜一个字符，就check这个node是不是end
    //https://www.cnblogs.com/grandyang/p/7817011.html  解法3跟不能不用trie
    //https://www.cnblogs.com/mengchunchen/p/7927754.html
    public String longestWord(String[] words) {
        if(words.length==0){
            return "";
        }
        trie root=new trie(' ');
        PriorityQueue<String> pq=new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()==o2.length()){
                    return o1.compareTo(o2);
                }
                return o2.length()-o1.length();
            }
        });
        for(String s:words){
            root.insert(s,root);
            pq.offer(s);
        }
        while (!pq.isEmpty()){//
            String temp=pq.poll();
            if (dfs(temp,root)){
                return temp;
            }
        }
        return "";


    }
    boolean dfs(String cur,trie root){
        if(cur.length()==0){
            return true;
        }
        String ss=cur.substring(0,cur.length()-1);
        if(root.hasWord(ss,root)){
            if(dfs(ss,root)){
                return true;
            }
        }
        return false;
    }
    class trie{
        Character val;
        boolean isEnd;
        HashMap<Character,trie> children;
        public trie(Character val){
            this.val=val;
            children=new HashMap<>();
        }

        boolean hasWord(String s,trie root){
            if(s.length()==0){
                return true;
            }
            trie cur=root;
            char[] ch=s.toCharArray();
            for(int i=0;i<ch.length;i++){
                if(!cur.children.containsKey(ch[i])){
                    return false;
                }

                cur=cur.children.get(ch[i]);
                if(i==ch.length-1&&cur.isEnd){
                    return true;
                }
            }
            return false;
        }
        void insert(String s, trie root){
            if(s.length()==0){
                return ;
            }
            trie cur=root;
            char[] ch=s.toCharArray();
            for(int i=0;i<ch.length;i++){
                if(!cur.children.containsKey(ch[i])){
                    trie t=new trie(ch[i]);
                    cur.children.put(ch[i],t);
                }
                cur=cur.children.get(ch[i]);
                if(i==ch.length-1){
                    cur.isEnd=true;
                    return ;
                }
            }
        }
    }
}
