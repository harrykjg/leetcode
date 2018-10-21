package GameTheory;

/**
 * Created by yufengzhu on 10/8/18.
 */
public class StoneGame {
    public static void main(String[ ]args){
        StoneGame sg=new StoneGame();
        System.out.print(sg.stoneGame(new int[]{5,4,3,5}));
    }
    //顺着predict the winner做的,暴力法超时了,几乎和那题一样
    //https://leetcode.com/problems/stone-game/solution/
    //https://blog.csdn.net/blurryface_/article/details/81538421这几个dp写法有些不同
    //https://blog.csdn.net/perdon123123/article/details/81349507
    //https://blog.csdn.net/Poison520/article/details/70217020
    public boolean stoneGame(int[] piles) {
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
