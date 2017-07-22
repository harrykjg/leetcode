package Advance2;

import java.util.ArrayList;
import java.util.List;

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

}
