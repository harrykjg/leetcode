package Advance2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 502575560 on 7/19/17.
 */
public class WordSquares {
    //http://www.cnblogs.com/EdwardLiu/p/6201232.html  这个解释好,题目也在这
    //http://www.cnblogs.com/grandyang/p/6006000.html
    //https://segmentfault.com/a/1190000008226321
    ArrayList<List<String>> rs=new ArrayList<>();
    public List<List<String>> wordSquares(String[] words) {
        ImplementTrie trie=new ImplementTrie();

        ArrayList<String> al=new ArrayList<>();
        for(int i=0;i<words.length;i++){
            trie.insert(words[i]);
        }
        for(int i=0;i<words.length;i++){
            al.add(words[i]);
            dfs(al,words,trie);
            al.remove(al.size()-1);
        }
        return rs;
    }
    void dfs(ArrayList<String> al,String[] words,ImplementTrie trie){
        if(al.size()==al.get(0).length()){//al的size肯定只能等于单词的长度
            rs.add(new ArrayList<>(al));
            return;
        }
        StringBuilder sb=new StringBuilder();
        int index=al.size();
        for(int i=0;i<al.size();i++){
            sb.append(al.get(i).charAt(index));
        }
        String prefix=sb.toString();
        for(int i=0;i<words.length;i++){
            String s=words[i].substring(0,prefix.length());
            if(trie.startsWith(prefix)&&s.equals(prefix)){
                al.add(words[i]);
                dfs(al,words,trie);
                al.remove(al.size()-1);
            }
        }
    }
    //6/16/2021,自己想的就是对每个单词建树，然后dfs找出完整的square再去检查，而看回以前是边建square边检查更好.而且不用set，因为同一个单词可以重复用
    //本来想写dfs中的for循环放在dfs里的，但是由于trie的startwith方法不是返回所有startwith的单词，所以还是改回以前的写法把
    ImplementTrie trie=new ImplementTrie();
    public List<List<String>> wordSquares2(String[] words) {

        for (String w:words){
            trie.insert(w);
        }
        List<List<String>> rs=new ArrayList<>();
        List<String> al=new ArrayList<>();
        for (int i=0;i<words.length;i++){
            al.add(words[i]);
            dfs2(words,al,rs);
            al.remove(al.size()-1);
        }
        return rs;
    }
    void dfs2(String[] words, List<String> al,List<List<String>> rs){
        if (al.size()==words[0].length()){
            rs.add(new ArrayList<>(al));
            return;
        }
        int index=al.size();
        String prefix="";
        for (int i=0;i<al.size();i++){
            prefix+=al.get(i).charAt(index);
        }
        for (int i=0;i<words.length;i++){
            String curPre=words[i].substring(0,prefix.length());
            if (trie.startsWith(prefix)&&curPre.equals(prefix)){
                al.add(words[i]);
                dfs2(words,al,rs);
                al.remove(al.size()-1);
            }

        }
    }


}
