package GameTheory;

public class CoinsinaLineIII {
    //6/6/2021 不会
    //https://zhuanlan.zhihu.com/p/55977493
    public boolean firstWillWin(int[] piles) {
        // write your code here
        int[][] dp=new int[piles.length][piles.length];
        for(int i=0;i<dp.length;i++){
            dp[i][i]=piles[i];
        }

        for(int i=1;i<dp.length;i++){
            for(int j=0;j+i<dp.length;j++){//dp的意义还是不太能理解，有两种写法，lc答案那种不太好理解
                dp[j][j+i]=Math.max(piles[j]-dp[j+1][i+j],piles[j+i]-dp[j][j+i-1]);//这里我理解的是，dp的意义是第一个玩家所能取的值，减去下一轮第二个玩家能取得值，得出的最大的数就是dp[j][j+i]的值
            }
        }
        return dp[0][dp.length-1]>0;
    }
}
