package dp.string2D;

public class MinimumInsertionStepstoMakeaStringPalindrome {
    //8/9/2021不会
    //别人说就是longest palindormic subsequence 反过来，没有理解
    //https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/discuss/470740/516.-Longest-Palindromic-Subsequence
    //https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/discuss/470740/516.-Longest-Palindromic-Subsequence
    public int minInsertions(String s) {
        if (s.length()<=1){
            return 0;
        }
        int[][] dp=new int[s.length()][s.length()];
        for (int i=0;i<s.length();i++){
            dp[i][i]=1;
        }
        for (int i=1;i<s.length();i++){//注意这里写i=2然后把相邻字符相等的初始化放在之前的for循环里的话其实是不对的（上一次写的那样，其实是不对的），那会导致
            for (int j=0;j+i<s.length();j++){  //如dp[0][1]这样的其实值是1但是没得到赋值导致值是0；
                if (s.charAt(j)==s.charAt(i+j)){
                    dp[j][i+j]=dp[j+1][i+j-1]+2;
                }else {
                    dp[j][i+j]=Math.max(dp[j+1][i+j],dp[j][i+j-1]);
                }
            }
        }
        return s.length()-dp[0][dp[0].length-1];
    }
}
