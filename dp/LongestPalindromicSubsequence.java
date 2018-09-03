package dp;

/**
 * Created by yufengzhu on 8/19/18.
 */
public class LongestPalindromicSubsequence {
    public static void main(String[] args){
        LongestPalindromicSubsequence l=new LongestPalindromicSubsequence();
        l.longestPalindromeSubseq("abcabcabc");
    }
    //不会,https://leetcode.com/problems/longest-palindromic-subsequence/discuss/99101/Straight-forward-Java-DP-solution
    //https://www.youtube.com/watch?v=OZX1nqaQ_9M  实现看他的
    //https://www.cnblogs.com/liujinhong/p/6418192.html 这个还有递归的方法
    public int longestPalindromeSubseq(String s) {
        if(s.length()==0){
            return 0;
        }
        int[][] dp=new int[s.length()][s.length()];
        for(int i=0;i<dp.length;i++){//开始不想这样初始化，想要放到forloop里，那样的话我这个写法是不行的，要不就看huahua那样的下标
            dp[i][i]=1;
        }
        char[] ch=s.toCharArray();
        for(int i=1;i<dp.length;i++){
            //dp[i][i]=1;//开始这样写初始化是不行的，
            for(int j=0;j+i<ch.length;j++){

                int end=j+i;

                if(ch[j]==ch[end]){
                    dp[j][end]=dp[j+1][end-1]+2;
                }else{
                    dp[j][end]=Math.max(dp[j+1][end],dp[j][end-1]);//这里也不是很好想
                }
            }
        }
        return dp[0][dp.length-1];

    }
}