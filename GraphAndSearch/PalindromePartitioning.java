package GraphAndSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 502575560 on 7/13/17.
 */
public class PalindromePartitioning {
    public static void main(String[] args){
        PalindromePartitioning pp=new PalindromePartitioning();
        pp.partition("abbab");
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
}
