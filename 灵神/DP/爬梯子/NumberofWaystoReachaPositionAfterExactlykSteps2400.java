package 灵神.DP.爬梯子;

public class NumberofWaystoReachaPositionAfterExactlykSteps2400 {
    static void main() {

    }
    //12/14/2025
    //自己能想到大概dp[pos][step]=dp[pos-1][step-1]+dp[pos+1][step-1]但是总以为pos是依赖左边和右边的，那么这个dp如何从base case
    //推进呢？其实是想错了，应该按step推进而不是pos
    public int numberOfWays(int startPos, int endPos, int k) {
        int mod=(int)Math.pow(10,9)+7;
        int diff=Math.abs(endPos-startPos);
        if(diff>k||(k-diff)%2!=0){//(k-diff)%2!=0就是走了diff步到达endpos了，这时还剩下几步，那么这几步必须是向左再向右或者反过来还是到达
            return 0;     //endpos，所以说必须是2的整数倍，不是的话就不可能
        }
        int[][] dp=new int[2*k+1][k+1];//pos的范围是start-k到start+k，即2k+1个位置
        int offset=startPos-k;//用来平移position的范围，使得最左端从0开始，举个例子，start=1，k=3，和start=5，k=3
        int start=startPos-offset;
        int end=endPos-offset;
        dp[start][0]=1;//base case，即开始位置时还剩0步，则只有1种走法走到这个位置
        for(int i=1;i<dp[0].length;i++){//按step来推进
            for (int j=0;j<dp.length;j++){
                if(j-1>=0){
                    dp[j][i]+=dp[j-1][i-1]%mod;
                }
                if(j+1<dp.length){
                    dp[j][i]+=dp[j+1][i-1]%mod;
                }
            }
        }
        return dp[end][k]%mod;
    }
}
