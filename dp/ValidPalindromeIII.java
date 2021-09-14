package dp;

public class ValidPalindromeIII {
    public static void main(String[] args){
        ValidPalindromeIII vp=new ValidPalindromeIII();
        System.out.println(vp.isValidPalindrome("abcdeca",2));
    }
    //8/19/2021 不会
    //https://leetcode.com/problems/valid-palindrome-iii/discuss/397606/Find-Longest-Palindromic-Subsequence.他这个是找longest palindromic subsequence
    //然后再用s的长度减去dp的数看是否小于k，感觉多此一举，答案二的更好理解，dp直接的意义就是找第i和j之间的字符，要多少个删除操作能成palindrome，然后检查是否小于k就行了
    public boolean isValidPalindrome(String s, int k) {//这是看的答案二的写法
        int[][] dp=new int[s.length()][s.length()];
        //不要想成longest了。只有求longest的时候才要初始化dp[i][i]=1;这里是找需要删多少个，所以不用
        for (int i=1;i<dp.length;i++){
            for (int j=0;j+i<dp.length;j++){
                int end=i+j;
                if (s.charAt(j)==s.charAt(end)){
                    dp[j][end]=dp[j+1][end-1];
                }else {
                    dp[j][end]=Math.min(dp[j+1][end],dp[j][end-1])+1;
                }
            }
        }
        return dp[0][dp.length-1]<=k;
    }
}
