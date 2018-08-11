import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 8/8/18.
 */
public class ReplaceWords {
    //自己想的用trie，几乎一次过
    //https://leetcode.com/problems/replace-words/discuss/105767/Java-SimpleClassical-Trie-questionsolution-(Beat-96)  基本一样
    Trie root=new Trie(' ');
    public String replaceWords(List<String> dict, String sentence) {
        String[] words=sentence.split(" ");
        for(String s:dict){
            root.insert(s,root);//就是用dict建树，然后对于sentence里的每个word，搜索trie，见到有前缀就取得前缀，没有就用sentence里的word
        }
        StringBuilder sb=new StringBuilder();
        for(String s:words){
            String temp=root.search(s,root);
            if(temp==""){
                sb.append(s);
                sb.append(" ");
            }else{
                sb.append(temp);
                sb.append(" ");
            }
        }
        return sb.toString().substring(0,sb.length()-1);

    }
    class Trie{
        Character val;
        HashMap<Character,Trie> children=new HashMap<>();
        boolean end;
        public Trie(Character c){
            val=c;
        }
        void insert(String s,Trie root){
            if(s.length()==0){
                return;
            }
            char[] ch=s.toCharArray();
            Trie cur=root;
            for(int i=0;i<ch.length;i++){
                if(!cur.children.containsKey(ch[i])){
                    Trie t=new Trie(ch[i]);
                    cur.children.put(ch[i],t);
                }
                cur=cur.children.get(ch[i]);
                if(i==ch.length-1){
                    cur.end=true;
                }
            }
        }
        String search(String s,Trie root){
            if(s.length()==0){
                return "";
            }
            char[] ch=s.toCharArray();
            StringBuilder sb=new StringBuilder();
            Trie cur=root;
            for(int i=0;i<ch.length;i++){
                if(!cur.children.containsKey(ch[i])){
                    return "";
                }
                cur=cur.children.get(ch[i]);
                sb.append(ch[i]);
                if(cur.end){//先找到的的那个是end的节点肯定是最短的那个
                    return sb.toString();
                }
            }
            return "";
        }
    }

}
