package 灵神.DP.划分型DP;

import java.util.*;

public class WordBreak139 {
    public static void main(String[] args) {
        System.out.println(wordBreak("leetcode",new ArrayList<>(Arrays.asList("leet","code"))));
    }
    //写的还行
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set=new HashSet<>();
        for(String ss:wordDict){
            set.add(ss);
        }
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for (int i=1;i<dp.length;i++){
            for (int j=dp.length-1;j>=i;j--){
                if(set.contains(s.substring(i-1,j))&&dp[i-1]){
                    dp[j]=true;
                }
            }
        }
        return dp[dp.length-1];
     }

     //5/7/2026,这次想的是i是结束点，j在0和i之间试
    public static boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp=new boolean[s.length()+1];
        Set<String> set=new HashSet<>(wordDict);
        dp[0]=true;
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<=i;j++){
                if(set.contains(s.substring(j-1,i))&&dp[j-1]){
                    dp[i]=true;
                }
            }
        }
        return dp[dp.length-1];
    }
}
