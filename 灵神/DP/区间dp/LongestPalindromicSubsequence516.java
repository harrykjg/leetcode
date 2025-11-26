package 灵神.DP.区间dp;

public class LongestPalindromicSubsequence516 {
    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("abcd"));
    }
    //不会，容易和palindrompartition记混
    public static int longestPalindromeSubseq(String s) {
        int[][] dp=new int[s.length()][s.length()];
        for (int i=0;i<s.length();i++){
            if (i+1<dp.length){
                if (s.charAt(i+1)==s.charAt(i)){
                    dp[i][i+1]=2;
                }
            }
            dp[i][i]=1;
        }
        for (int i=1;i<s.length();i++){//这里i从1开始，开始写的从2开始，是和palindrompartition记混了，那样就错了
            for(int j=0;j+i<s.length();j++){
                int end=j+i;
                if(s.charAt(end)==s.charAt(j)){
                    dp[j][end]=Math.max(dp[j+1][end-1]+2,dp[j][end]);
                }else{
                    dp[j][end]=Math.max(dp[j][end-1],dp[j+1][end]);//这里还是不好理解，开始以为当前end不取，因此是dp[j][end-1]，这对substring是对的，
                    //但是由于题目说的是subsequence既前后都可以删字符，因此另一种可能是保留end，不取左边那个。如caa，看0-2的字符，如果不取右边的a则dp值是1，如果不取c则dp值是2
                }
            }
        }
        return dp[0][dp.length-1];


    }
}
