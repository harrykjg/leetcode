package 灵神.DP.划分型DP;

import java.util.*;

public class WordBreakII140 {
    static void main() {

    }

    //看gpt解释。自己写的话就是正常的dfs，从左边开始切单词。但是这样写没法记忆化。
    //记忆化的写法需要先递归到最右边的字符，然后回溯的时候就是把刚才计算过的index右边的这一段能产生的list加到当前层来。
    //关键是为什么要这样递归到最深再返回才能记忆化搜索？其实不一定，关键是这个子问题是明确的一个子问题，可以复用（其实还是比较抽象）
    // gpt说是问题是“组合式” → 要把多个子问题的结果组合成大结果。
    //这个是gpt的代码
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<Integer,List<String>> memo=new HashMap<>();
        Set<String> set=new HashSet<>(wordDict);
        return backtrack(0,s,set,memo);
    }

    List<String> backtrack(int b,String s,Set<String> set,Map<Integer,List<String>> memo){
        if(memo.containsKey(b)){
            return memo.get(b);
        }
        List<String> al = new ArrayList<>();
        if(b==s.length()){
            al.add("");
            memo.put(b,al);
            return al;
        }

        for(int j=b;j<=s.length();j++){
            String temp=s.substring(b,j);
            if(set.contains(temp)){
                List<String> bt=backtrack(j,s,set,memo);
                for(String n:bt){
                    if(n.isEmpty()){
                        al.add(temp);
                    }else{
                        al.add(temp+" "+n);
                    }
                }
            }
        }
       memo.put(b,al);
        return al;
    }
}

