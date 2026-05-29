package linkedin;

public class PaintHouse256 {
    static void main() {

    }
    //一次过，不难,就是dp[i]【3】存的是当前i的三种颜色的值。取每种颜色必然依赖于上一个位置的另外两个值的最小的那个。
    public int minCost(int[][] costs) {
        int[][] dp=new int[costs.length][3];
        dp[0][0]=costs[0][0];
        dp[0][1]=costs[0][1];
        dp[0][2]=costs[0][2];

        for (int i=1;i<costs.length;i++){
            dp[i][0]=Math.min(dp[i-1][1],dp[i-1][2])+costs[i][0];
            dp[i][1]=Math.min(dp[i-1][0],dp[i-1][2])+costs[i][1];
            dp[i][2]=Math.min(dp[i-1][0],dp[i-1][1])+costs[i][2];
        }
        return Math.min(dp[dp.length-1][0],Math.min(dp[dp.length-1][1],dp[dp.length-1][2]));
    }
}
