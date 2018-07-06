import java.util.*;

/**
 * Created by yufengzhu on 7/3/18.
 */
public class WordSquares {
    public static void main(String[] a){
        WordSquares ws=new WordSquares();
        String[] s={"baba","abat","wall","lady","ball"};
        ws.wordSquares(s);

    }
//原来words里的string可以重复使用，就是dfs，valid那个方法想的有点久，其实就是检测新加入的字符串的从0到倒数第二个字母是否和这个squre的左后一列的第0到倒数第二是否相同就行了。要画图理解
    //结果还是超时,要用trie去优化，使得能快速找到下一个应该加进来的string
    //https://leetcode.com/problems/word-squares/discuss/91333/Explained.-My-Java-solution-using-Trie-126ms-1616
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> rs=new ArrayList<>();
        List<String> ls=new ArrayList<>();
        for(int i=0;i<words.length;i++){//开始写成没有forloop直接dfs的，那样的话只考虑了从第一个string出发的情况，所以错了
            ls.add(words[i]);
            dfs(words,i,ls,rs);
            ls.remove(ls.size()-1);
        }
        return rs;
    }
    void dfs(String[] words,int b,List<String> ls,List<List<String>> rs){
        if(ls.size()==words[0].length()){
            rs.add(new ArrayList<>(ls));
            return;
        }
        for(int i=0;i<words.length;i++){
            String coming=words[i];
            int col=ls.size();
            ls.add(coming);
            if (valid(ls)){
                dfs(words,i+1,ls,rs);
            }
            ls.remove(ls.size()-1);
        }
    }
    boolean valid(List<String> ls){
        int size=ls.size();
        String lastRow=ls.get(ls.size()-1);
        for(int i=0;i<ls.size()-1;i++){
            if(lastRow.charAt(i)!=ls.get(i).charAt(size-1)){
                return false;
            }
        }
        return true;
    }
    //用trie的
    public List<List<String>> wordSquares2(String[] words) {
        List<List<String>> rs=new ArrayList<>();
        List<String> ls=new ArrayList<>();
        for(int i=0;i<words.length;i++){//开始写成没有forloop直接dfs的，那样的话只考虑了从第一个string出发的情况，所以错了
            ls.add(words[i]);
            dfs2(words,i,ls,rs);
            ls.remove(ls.size()-1);
        }
        return rs;
    }
    void dfs2(String[] words,int b,List<String> ls,List<List<String>> rs){
        if(ls.size()==words[0].length()){
            rs.add(new ArrayList<>(ls));
            return;
        }
        for(int i=0;i<words.length;i++){
            String coming=words[i];
            int col=ls.size();
            ls.add(coming);
            if (valid(ls)){
                dfs(words,i+1,ls,rs);
            }
            ls.remove(ls.size()-1);
        }
    }

}
class Trie2{
    Character val;
    Set<Trie2> children;
    boolean end;
    public Trie2(Character val){
        this.val=val;
        children=new HashSet<>();
    }
}
