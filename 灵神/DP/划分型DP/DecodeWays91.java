package 灵神.DP.划分型DP;

public class DecodeWays91 {
    public static void main(String[] args) {

    }
    //dp[i]= if valid(s.charat(i)) dp[i]+=dp[i-1]
    //          else dp[i]=0
    //     if valid(s.substring(i-1,i+1)) dp[i]+=dp[i-2] when i>=2
    //大概想到，不想写了
    public int numDecodings(String s) {

    }
}
