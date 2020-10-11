package dp.string1D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 502575560 on 7/6/17.
 */
public class PalindromePartitioningII {
    public static void main(String[] args){
        System.out.println(minCut3("aab"));
    }//abcccb
//lintcode写的,下标还是很恶心,那个判断字串是否回文那里的下标已经很恶心了,而且lintcode的dp的意义和以前写的不一样,lintcode这个要dp最后一个值-1
    public static int minCut(String s) {
        // write your code here
        if(s.length()==1){
            return 0;
        }
        boolean[][] dp1=new boolean[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            dp1[i][i]=true;
            if(i>0&&s.charAt(i-1)==s.charAt(i)){
                dp1[i-1][i]=true;
            }
        }
        for(int i=3;i<=s.length();i++){
            for(int j=0;j<=s.length()-i;j++){
                int k=j+i;
                if(s.charAt(j)==s.charAt(k-1)&&dp1[j+1][k-1-1]){
                    dp1[j][k-1]=true;
                }
            }
        }
        int[] dp=new int[s.length()+1];
        for(int i=0;i<dp.length;i++){
            dp[i]=i;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<i;j++){
                if(dp1[j][i-1]){
                    dp[i]=Math.min(dp[i],dp[j]+1);
                }
            }
        }
        return dp[dp.length-1]-1;
    }

    //九章第二轮,9/10/2017,还是下标比较恶心
    public static int minCut2(String s) {

        boolean[][] cut=new boolean[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            cut[i][i]=true;
        }
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==s.charAt(i-1)){
                cut[i-1][i]=true;
            }
        }
        for(int i=2;i<s.length();i++){
            for(int j=0;j+i<s.length();j++){
                int end=j+i;
                if(cut[j+1][end-1]&&s.charAt(j)==s.charAt(end)){
                    cut[j][end]=true;
                }
            }
        }

        int[] dp=new int[s.length()];
        for(int i=0;i<s.length();i++){
            dp[i]=i;//思路就是外层i指0到i这个字符串要切几刀,那么默认一个字符切一道即dp[i]=i,然后内层j属于0到i,看j到i这之间是回文的话,就更新dp[i]
            for(int j=0;j<=i;j++){
                if(cut[j][i]){
                    if(j==0){//这里写的比较奇怪不过也对了,其实以前也是差不多这样的,就是为了避开j=0时j-1为负数的情况
                        dp[i]=0;
                    }else{
                        dp[i]=Math.min(dp[i],dp[j-1]+1);
                    }

                }
            }
        }
        return dp[dp.length-1];
    }
    //05/25/2020,后面的dp不熟，要练
    public static int minCut3(String s) {
        boolean[][] memo=new boolean[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            memo[i][i]=true;
        }
        for(int i=1;i<s.length();i++){
            if(s.charAt(i-1)==s.charAt(i)){
                memo[i-1][i]=true;
            }
        }
        for(int len=2;len<s.length();len++){
            for(int i=0;i+len<s.length();i++){
                int e=i+len;
                if(s.charAt(i)==s.charAt(e)&&memo[i+1][e-1]){
                    memo[i][e]=true;
                }
            }
        }
        int rs=Integer.MAX_VALUE;
        int[] dp=new int[s.length()];
        for(int i=0;i<dp.length;i++){
            dp[i]=i;
        }
        for(int i=1;i<dp.length;i++){
           for(int j=0;j<=i;j++){//少写等号不行
               if(memo[j][i]){
                   if(j==0){
                       dp[i]=0;
                   }else{
                       dp[i]=Math.min(dp[j-1]+1,dp[i]);
                   }
               }
           }
        }
        return dp[dp.length-1];
    }
}
