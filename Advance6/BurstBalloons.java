package Advance6;

/**
 * Created by yufengzhu on 4/29/18.
 */
public class BurstBalloons {
    public static void main(String[] args){
        int[] n={3,5,1,8};
        BurstBalloons bb=new BurstBalloons();
        System.out.print(bb.maxCoins(n));

    }
    //4/29/2018还是不会，难想，dp的意义也不太好理解,
    //https://leetcode.com/problems/burst-balloons/discuss/76228/share-some-analysis-and-explanations
    public int maxCoins(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int[] a=new int[nums.length+2];
        for(int i=0;i<nums.length;i++){
            a[i+1]=nums[i];
        }
        a[0]=1;
        a[a.length-1]=1;
        int[][] dp=new int[a.length][a.length];

        for(int k=0;k<nums.length;k++){//这里我想的dp[i][j]的意义是i，j是包含i，j的，他们是不包含的，也行，因此我这里就要从dp[i][i]开始，所以k就要从0开始，而他们是从2开始
            for(int b=1;b<a.length-1-k;b++){
                int e=b+k;
                for(int i=b;i<=e;i++){
                    dp[b][e]=Math.max(dp[b][e],dp[b][i-1]+dp[i+1][e]+a[b-1]*a[i]*a[e+1]);//a[b-1]*a[i]*a[e+1]这里我一直以为是i乘以他左边和他右边的数，其实是
                }//乘以左右边界外的那两个点
            }
        }
        return dp[1][dp.length-2];
    }
}
