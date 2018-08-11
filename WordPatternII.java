import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by yufengzhu on 8/7/18.
 */
public class WordPatternII {
    public static void main(String[] args){
        WordPatternII wp=new WordPatternII();
        System.out.print(wp.wordPatternMatch("abab","redblueredblue"));
    }

    //大概知道思路要dfs，基本上是自己写的，还算顺，关键是dfs那里还是写了比较久，要总结
    //https://leetcode.com/problems/word-pattern-ii/discuss/73664/share-my-java-backtracking-solution
    public boolean wordPatternMatch(String pattern, String str) {
        if(pattern.length()==0){
            return str.length()==0;
        }
        if (pattern.length()>str.length()){
            return false;
        }
        HashMap<Character, String> map=new HashMap<>();
        HashSet<String> set=new HashSet<>();//这里用set就够了，比如'a'对应了red，则如果'b'又对应了red就不行了，以前想的是用map，其实当a对应red时，就把red加进set里，b又对应red的话，要先检查
                                            //set里有没有red，如果说又来了个'a'对应red呢？那其实就再map的那一步检查到'a'又对应的string了就不会用上set了

        if(helper(pattern,0,str,0,map,set)){//从pattern 0开始和str 0开始，因为pattern肯定是一个一个char读的，而str是需要从长度为1到。。n不停去试的，这里应该到不了n就应该停止了，但是条件不太好写，没再优化
            return true;
        }
        return false;
    }
    boolean helper(String p,int b1,String str, int b2,HashMap<Character,String> map,HashSet<String> set){
        if(b1==p.length()&&b2==str.length()){
            return true;
        }
        if(b2>=str.length()||b1>=p.length()){
            return false;
        }
        char c=p.charAt(b1);
        if(!map.containsKey(c)){
            for(int i=1;i+b2<=str.length();i++){
                String cp=str.substring(b2,b2+i);
                if(set.contains(cp)){//发现set里已经又别人对应cp了，就说明这条路走不通，继续试下一个
                    continue;
                }
                set.add(cp);
                map.put(c,cp);
                if(helper(p,b1+1,str,b2+i,map,set)){
                    return true;
                }
                set.remove(cp);
                map.remove(c);

            }
        }else{
            String pp=map.get(c);
            if(b2+pp.length()>str.length()){
                return false;
            }
            String cp=str.substring(b2,b2+pp.length());
            if(!cp.equals(pp)){
                return false;
            }
            return helper(p, b1+1,str,b2+pp.length(),map,set);
        }

        return false;
    }

}
