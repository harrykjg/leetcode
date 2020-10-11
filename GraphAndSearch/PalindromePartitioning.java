package GraphAndSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 502575560 on 7/13/17.
 */
public class PalindromePartitioning {
    public static void main(String[] args){
        PalindromePartitioning pp=new PalindromePartitioning();
        pp.partition3("aab");
    }

    // 先用二维dp记录每一小段是palindorme的位置,再根据这个dfs就行了,但这个dfs还没那么好理解,改了好几次
    //结果看回以前的其实不用二维dp记录每一小段是否回文,那样写似乎更简单
    List<List<String>> rs=new ArrayList<>();
    public List<List<String>> partition(String s) {

        boolean[][] dp=new boolean[s.length()][s.length()];
        if(s.length()==0){
            rs.add(new ArrayList<>());
            return rs;
        }
        for (int i=0;i<s.length();i++){
            dp[i][i]=true;
            if(i+1<s.length()&&s.charAt(i+1)==s.charAt(i)){
                dp[i][i+1]=true;
            }
        }
        for (int i=2;i<s.length();i++){
            for(int j=0;j<s.length()-i;j++){
                if(dp[j+1][j+i-1]&&s.charAt(j)==s.charAt(i+j)){
                    dp[j][i+j]=true;
                }
            }
        }
        ArrayList<String> al=new ArrayList<>();
        for(int i=0;i<s.length();i++){//开始这里写了二重循环,其实只要一层,i代表长度,肯定是从0,0+i这段字符串开始判断的
            if(dp[0][i]){
                al.add(s.substring(0,i+1));
                dfs(s,i+1,al,dp);
                al.remove(al.size()-1);
            }

        }
        return rs;

    }
    void dfs(String s,int b,ArrayList<String> al,boolean[][] dp){
        if(b>=s.length()){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=0;i+b<s.length();i++){//这里也开始写了二重循环
            if(dp[b][b+i]){
                al.add(s.substring(b,b+i+1));
                dfs(s,b+i+1,al,dp);
                al.remove(al.size()-1);
            }
        }
    }
//九章第二轮，3／11／2018,好想以前的更简单，二维dp的构造复杂度比较高（没利用上dp），还是没写好，还是犯了用了二重循环的错,还有dfs的各种index也容易错
    public List<List<String>> partition2(String s) {
        boolean[][] dp=new boolean[s.length()][s.length()];

        for(int i=0;i<s.length();i++){
            for(int j=0;j+i<s.length();j++){
                if(valid(s,i,i+j)){
                    dp[i][i+j]=true;
                }
            }
        }
        List<String> ls=new ArrayList<>();
        dfs2(0,ls,dp,s);
        return rs;
    }
    void dfs2(int b,List<String> ls,boolean[][] dp,String s){
        if(b==s.length()){
            rs.add(new ArrayList<>(ls));
            return;
        }
        for(int i=0;i+b<s.length();i++){//注意这个i是长度，容易理解错
            if(dp[b][i+b]){
                ls.add(s.substring(b,i+b+1));
                dfs2(i+b+1,ls,dp,s);
                ls.remove(ls.size()-1);
            }
        }

    }
    boolean valid(String s,int b,int e){
        if(b==e){
            return true;
        }
        while (b<e){
            if(s.charAt(b)!=s.charAt(e)){
                return false;
            }
            b++;
            e--;
        }
        return true;
    }
    //04/14/2020,写的不好，dp部分没写对
    public List<List<String>> partition3(String s) {
        List<List<String>> rs=new ArrayList<>();
        boolean[][] memo=new boolean[s.length()][s.length()];
        for(int i=0;i<memo.length;i++){
            memo[i][i]=true;
        }
        char[] ch=s.toCharArray();
        for(int i=0;i+1<ch.length;i++){
            if(ch[i]==ch[i+1]){
                memo[i][i+1]=true;
            }
        }
        for(int i=2;i<s.length();i++){
            for(int j=0;j+i<ch.length;j++){
                if(memo[j+1][j+i-1]&&ch[j]==ch[j+i]){
                    memo[j][j+i]=true;
                }
            }
        }
        ArrayList<String> al=new ArrayList<>();
        dfs3(0,ch,al,rs,memo);
        return rs;

    }
    void dfs3(int b,char[] ch,List<String> al,List<List<String>> rs,boolean[][] memo){
        if(b>=ch.length){
            rs.add(new ArrayList<>(al));
            return;
        }
        String s=new String(ch);
        for(int i=0;i+b<ch.length;i++) {//开始还是写了二重循环还以为想的挺清楚的，其实这里就是要一层循环长度0，1，2，3。。起点就是b，看b到b+i是否回文
            if (memo[b][b + i]) {
                al.add(s.substring(b, i + b + 1));
                dfs3(b + i + 1, ch, al, rs, memo);
                al.remove(al.size() - 1);
            }
        }
    }
//05/25/2020,一次过
    public List<List<String>> partition4(String s) {
        List<List<String>> rs=new ArrayList<>();
        List<String> al=new ArrayList<>();
        char[] ch=s.toCharArray();
        boolean[][] dp=new boolean[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            dp[i][i]=true;
        }
        for(int i=1;i<s.length();i++){
            if(s.charAt(i-1)==s.charAt(i)){
                dp[i-1][i]=true;
            }
        }
        for(int len=2;len<s.length();len++){
            for(int i=0;i+len<s.length();i++){
                int e=i+len;
                if(s.charAt(i)==s.charAt(e)&&dp[i+1][e-1]){
                    dp[i][e]=true;
                }
            }
        }
        dfs4(0,s,al,rs,dp);
        return rs;
    }
    void dfs4(int b,String s,List<String> al,List<List<String>> rs,boolean[][] dp){
        if(b>=s.length()){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=0;b+i<s.length();i++){
            if(dp[b][b+i]){
                al.add(s.substring(b,b+i+1));
                dfs4(i+b+1,s,al,rs,dp);
                al.remove(al.size()-1);
            }
        }
    }
}
