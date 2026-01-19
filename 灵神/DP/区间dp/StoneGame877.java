package 灵神.DP.区间dp;

public class StoneGame877 {
    static void main() {

    }
    //12/18/2025
    //看了gpt的思路写的,dp[i][j]意义就是从i到j这一段，先手的人能比后手的人多几分。那么这个人先手就是两种选择，拿左边或者拿右边
    //拿左边就是pile【i】-dp【i+1】【j】，关键就是这里拿了左边那么后手的人就会在剩下的数组里尽量多拿，就是dp【i+1】【j】。
    //所以dp[i][j]=Math.max(piles[i]-dp[i+1][j],piles[j]+dp[i][j-1])。那么要如何推进这个公式呢，想到base case就是
    //只有1个情况即dp【0】【0】，后来再想到就是长度为0的时候即所有dp【i】【i】=piles【i】。再想到是通过i到j的长度去推进的

    public boolean stoneGame(int[] piles) {
        int[][] dp=new int[piles.length][piles.length];
        for(int i=0;i<piles.length;i++){
            dp[i][i]=piles[i];
        }
        dp[dp.length-1][dp[0].length-1]=piles[piles.length-1];
        for (int i=0;i<piles.length;i++){
            for(int j=1;i+j<piles.length;j++){
                dp[i][j]=Math.max(piles[i]-dp[i+1][j],piles[j]+dp[i][j-1]);
            }
        }
        return dp[0][dp.length-1]>0;
    }

}
