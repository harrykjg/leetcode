package 灵神.DP.打家劫舍;

public class CountNumberofWaystoPlaceHouses2320 {
    public static void main(String[] args) {
        System.out.println(countHousePlacements(3));
    }
    //参考他的,虽然写起来很简单但是想起来不容易。看他解释
    //https://leetcode.cn/problems/count-number-of-ways-to-place-houses/solutions/1625979/d-by-endlesscheng-gybx/
    public static int countHousePlacements(int n) {
        int[] dp=new int[n+1];
        int mod=(int)Math.pow(10,9)+7;
        dp[0]=1;
        dp[1]=2;
        for(int i=2;i<=n;i++){
            dp[i]=(dp[i-2]+dp[i-1])%mod;
        }
        return (int)((long)dp[dp.length-1]*dp[dp.length-1]%mod);
    }
}
