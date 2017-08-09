package dp;

/**
 * Created by 502575560 on 8/1/17.
 */
public class BurstBalloons {
    //难想
    //http://bookshadow.com/weblog/2015/11/30/leetcode-burst-balloons/
    //http://www.jianshu.com/p/a671b48c3d43
    public int maxCoins(int[] nums) {
        int[] a=new int[nums.length+2];
        for(int i=1;i<=nums.length;i++){
            a[i]=nums[i-1];
        }
        a[0]=1;
        a[a.length-1]=1;
        int[][]dp=new int[a.length][a.length];//注意dp[i][j]的意义是i到j(i,j都不包括)之间的气球的最大爆法
        for(int len=2;len<=a.length;len++){
            for(int j=0;j<a.length-len;j++){
                int right=j+len;
                for(int k=j+1;k<right;k++){
                    dp[j][right]=Math.max(dp[j][right],a[j]*a[k]*a[right]+dp[j][k]+dp[k][right]);
                }
            }
        }
        return dp[0][dp.length-1];

    }
}
