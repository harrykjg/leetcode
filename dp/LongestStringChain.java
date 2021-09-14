package dp;

import java.util.*;

public class LongestStringChain {
    public static void main(String[] args){
        LongestStringChain ls=new LongestStringChain();
        System.out.println(ls.longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"}));
    }
    //9/2/2021 只想到dfs，应该要+memo.看的是答案一的思路就是先把words放到set里，然后遍历，对当前string，试着删除一个字母，看删除后的string在不在set里，在的话说明
    //的他的longest chain是1+1=2，衍生的string再dfs下去。用map记录dfs过的string。注意一个string删除不同的位置的字符可得到不同的string.dfs不太好写,
    //他某个单词删掉一个字符之后去下个单词时，根本不需要带一个cur参数代表现在是第几级chain了，他肯定是一个一个加的，这和大多数dfs题不一样
     //https://leetcode.com/problems/longest-string-chain/discuss/458069/JAVA-Easy-Solution-with-Explanation-(MUST-READ)! 答案二的思路，没搞懂哪里快了
    //而且答案二的复杂度比1的还大
    public int longestStrChain(String[] words) {
        Set<String> set=new HashSet<>();
        for (int i=0;i<words.length;i++){
            set.add(words[i]);
        }
        int rs=0;
        HashMap<String,Integer> map=new HashMap<>();
        for (String s:words){//这个方法排序貌似没必要，排序的runtime比没排的慢
            int count=dfs(s,set,map);
            rs=Math.max(rs,count);
        }
        return rs;
    }
    int dfs(String s, Set<String> set, Map<String,Integer> map){//开始多谢了个参数cur，代表现在第几个chain了，其实不对的
        if (map.containsKey(s)){
            return map.get(s);
        }
        if (s.length()==0){
            return 0;
        }
        int max=1;

        for (int i=0;i<s.length();i++){
            StringBuilder sb=new StringBuilder(s);
            String ns=sb.deleteCharAt(i).toString();
            if (set.contains(ns)){
                int ncur=1+dfs(ns,set,map);//每次都是加一级的，而不是加累计的级别的。和大多数dfs不一样
                max=Math.max(max,ncur);
            }
        }
        map.put(s,max);
        return max;
    }
}
