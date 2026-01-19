package 灵神.链表二叉树回溯.回溯;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning131 {
    static void main() {
        System.out.println(partition("efe"));
    }
    //12/27/2025改了一次过了
    public static List<List<String>> partition(String s) {
        boolean[][] dp=new boolean[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            dp[i][i]=true;
            if(i>0&&s.charAt(i)==s.charAt(i-1)){
                dp[i-1][i]=true;
            }
        }
        for(int i=2;i<s.length();i++){
            for(int j=0;j+i<s.length();j++){
                if(s.charAt(j)==s.charAt(j+i)&&dp[j+1][i+j-1]){
                    dp[j][i+j]=true;
                }
            }
        }
        List<List<String>> rs=new ArrayList<>();
        List<String> al=new ArrayList<>();
        dfs(0,al,rs,dp,s);
        return rs;
    }
    static void dfs(int b,List<String> al,List<List<String>> rs,boolean[][] dp,String s){
        if(b==s.length()){
            rs.add(new ArrayList<>(al));
            return;
        }

        for(int j=0;j+b<s.length();j++){
            if(dp[b][b+j]){
                al.add(s.substring(b,b+j+1));
                dfs(b+j+1,al,rs,dp,s);
                al.removeLast();
            }
        }

    }
}
