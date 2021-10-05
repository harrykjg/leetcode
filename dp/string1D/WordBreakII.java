package dp.string1D;

import java.util.*;

/**
 * Created by yufengzhu on 8/11/18.
 */
public class WordBreakII {
    public static void main(String[] args){
        WordBreakII wb=new WordBreakII();
        List<String> list=new ArrayList<>();
        list.add("a");
        list.add("aa");
        list.add("aaa");
        wb.wordBreak5("aaaaaaaa",list);
    }
    //准备uber时再写的
    //现在想dp和bruteforce貌似没有明显的时间复杂度的区别 code ganker也是这样说的，https://blog.csdn.net/linhuanmars/article/details/22452163但是他的dp解法就发杂多了
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> set=new HashSet<>(wordDict);
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<i;j++){
                String temp=s.substring(j,i);
                if(dp[j]&&set.contains(temp)){
                    dp[i]=true;
                    break;
                }
            }
        }
        List<String> rs=new ArrayList<>();
        if(dp[dp.length-1]==false){
            return rs;
        }

        dfs(0,s,"",rs,dp,set);
        return rs;

    }
    void dfs(int b,String sentence,String cur,List<String> rs,boolean[] dp,HashSet<String> set){
        if(b== sentence.length()){
            rs.add(cur.substring(0,cur.length()-1));
            return;
        }
        for(int i=1;i+b<=sentence.length();i++){
            String temp=sentence.substring(b,i+b);
            if(dp[b]&&set.contains(temp)){
                dfs(b+temp.length(),sentence,cur+temp+" ",rs,dp,set);
            }
        }
    }
//再写一个暴力法,一开始是过不了的，因为那个s最终不能被分解的那个例子，于是加了dp去判断最终s能不能被分解，而dfs不用这个dp的信息，运行时间也是和用dp一样的！
    public List<String> wordBreak2(String s, List<String> wordDict) {
        HashSet<String> set=new HashSet<>(wordDict);

        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<i;j++){
                String temp=s.substring(j,i);
                if(dp[j]&&set.contains(temp)){
                    dp[i]=true;
                    break;
                }
            }
        }
        List<String> rs=new ArrayList<>();
        if(dp[dp.length-1]==false){
            return rs;
        }
        dfs2(0,"",s,rs,set);
        return rs;
    }
    void dfs2(int b,String cur,String s,List<String> rs,HashSet<String> set){
        if(b==s.length()){
            rs.add(cur.substring(0,cur.length()-1));
            return;
        }
        for(int i=1;i+b<=s.length();i++){
            String temp=s.substring(b,b+i);
            if(set.contains(temp)){
                dfs2(b+i,cur+temp+" ",s,rs,set);
            }
        }
    }

    //9/9/2018,dfs那里写的有点痛苦
    public List<String> wordBreak3(String s, List<String> wordDict) {
        HashSet<String> set=new HashSet<>(wordDict);
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                String temp=s.substring(j,i);
                if(dp[j]&&set.contains(temp)){
                    dp[i]=true;
                    break;
                }
            }
        }

        List<String> rs=new ArrayList<>();
        if(dp[dp.length-1]==false){
            return rs;
        }
        dfs3("",0,s,dp,rs,set);
        return rs;
    }
    void dfs3(String cur,int b,String s,boolean[] dp,List<String> rs,HashSet<String > set){
        if(b==s.length()){
            cur=cur.substring(0,cur.length()-1);
            rs.add(cur);
        }
        for(int i=b;i<=s.length();i++){
            String temp=s.substring(b,i);//下标和之前写的不一样了
            if(dp[i]&&set.contains(temp)){
                dfs3(cur+temp+" ",i,s,dp,rs,set);
            }
        }
    }

    //8/24/2021  暴力dfs居然一次过。他的test case明显比以前少了很多。以前要dp貌似因为有一个test case很长并且不能分解，直接dfs会超时，
    // 因此用dp先判断整个s能不能被分解。现在不用也直接过了。应该可以用memorization加速。比如aaaaaaaaaa。已经遇到过aaaa了那么就用一个hashmap
    //存aaaa和对应的所有组合，如a a a a，aa a a。 aa aa 。aaa a。 aaaa等作为一个list，在遇到aaaa的话直接memo里取，然后把这堆组合和0到当前的字符串
    //的组合再组合？没仔细想
    public List<String> wordBreak4(String s, List<String> wordDict) {
        HashSet<String> set=new HashSet<>(wordDict);
        List<String> rs=new ArrayList<>();
        for (int i=1;i<=s.length();i++){
            String ss=s.substring(0,i);
            if (set.contains(ss)){
                dfs4(ss,s.substring(i),set,rs);
            }
        }
        return rs;
    }
    void dfs4(String cur,String s,HashSet<String> set,List<String> rs){
        if (s.length()==0){
            rs.add(cur);
            return;
        }
        for (int i=1;i<=s.length();i++){
            String ss=s.substring(0,i);
            if (set.contains(ss)){
                dfs4(cur+" "+ss,s.substring(i),set,rs);
            }
        }
    }
    //https://leetcode.com/problems/word-break-ii/discuss/44167/My-concise-JAVA-solution-based-on-memorized-DFS
    public List<String> wordBreak5(String s, List<String> wordDict) {//别人的代码 不好理解

        HashSet<String> set=new HashSet<>(wordDict);
        return DFS(s, set, new HashMap<String, LinkedList<String>>());
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String>res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");//为什么要加上这个，不加不行吗
            return res;
        }
        for (String word : wordDict) {//比如aaaa，【a，aa】他等于直接dfs到最右边的a，然后对这个a dfs进去发现s是空则返回一个空。然后sublist就装了一个空字符，
            if (s.startsWith(word)) {//然后便产生a+""放入res里，存入map中，返回，即a这个word break出来的结果就是a，然后到上一层即最右边的aa，然后遍历
                //wordDict，假如遇到a，则dfs进去a，发现map里有a了，就返回a，然后这个倒数第二个a就加上了sublist里这个a变成"a a"，然后再是在这最右边的aa这一层，
                //遍历wordDict遇到aa，然后dfs进去返回装有""的sublist，然后res里加上"aa"，现在res里就有了"a a"和"aa"，然后继续返回上层最右边的aaa继续。
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);//sub有可能是下一轮产生的""，如果是空的话则就是word+""+""还是等于word，否则
            } //就是word+" "+ sub
        }
        map.put(s, res);
        return res;
    }
}
