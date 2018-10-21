import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by yufengzhu on 10/9/18.
 */
public class stickerString {
    public static void main(String[]arfgs){
        stickerString ss=new stickerString();
        System.out.println(ss.Solution("zbca","znzbnbzbcaca"));
    }
//谷歌面经
    /*
    For example,
A = ‘ZAZA’
B = ‘BAZ’
You can form string by concatenating three copies of string B as follows: ‘##Z’ + ‘#AZ’ + ‘#A#’.(# denotes the characters removed from string B).
先问了个能不能从B 生成A
再问了个最少用多少个B 生成A
     */
    //看了别人的想法：
    /*
    先预处理B,把所有子串存到hashmap里，O(N^2). Waral 博客有更多文章,
    然后动态规划， f[i] 代表前i个字母最少需要几次，f[i] = min(f[j])+1,  B[j~i] 在hashmap 里， O(N^2)
    总O(N^2)，不知道有没有更优解法
    */
    public  int Solution(String s1, String s2){
        HashSet<String > set=new HashSet<>();
        for(int i=1;i<=s2.length();i++){
            for(int j=0;j+i<=s2.length();j++){
                set.add(s2.substring(j,j+i));
            }
        }
        int[] dp=new int[s1.length()+1];//这个dp和palindrome Partition 2是一个意思了
        for(int i=1;i<dp.length;i++){
            dp[i]=i;
            for(int j=1;j<=i;j++){//下标还是比较恶心
                String temp=s1.substring(j-1,i);
                if(set.contains(temp)){
                    dp[i]=Math.min(dp[i],dp[j-1]+1);
                }
            }
        }
        return dp[dp.length-1];
    }
}
