package dp.string1D;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yufengzhu on 8/11/18.
 */
public class WordBreakII {
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
}
