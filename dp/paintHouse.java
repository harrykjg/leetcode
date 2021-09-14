package dp;

public class paintHouse {
    //7/19/2021自己想的一次过。dp的意义就是取第i个房子第j个颜色的前提下，他的上一个房子只能取另外两个颜色。想像成从上到下找路径，然后便利最后一排3个颜色选最小的那个
    public int minCost(int[][] costs) {
        int[][]dp=new int[costs.length][3];
        dp[0][0]=costs[0][0];
        dp[0][1]=costs[0][1];
        dp[0][2]=costs[0][2];
        for (int i=1;i<costs.length;i++){
            for (int j=0;j<3;j++){
                dp[i][j]=Math.min(dp[i-1][(j+1)%3],dp[i-1][(j+2)%3])+costs[i][j];
            }
        }
        return Math.min(dp[dp.length-1][0],Math.min(dp[dp.length-1][1],dp[dp.length-1][2]));
    }
    //8/11/2021 看了别人的，都不需要建dp直接用输入参数改就行了
    public int minCost2(int[][] costs) {
        for(int i=1;i<costs.length;i++){
            costs[i][0]=Math.min(costs[i-1][1],costs[i-1][2])+costs[i][0];
            costs[i][1]=Math.min(costs[i-1][0],costs[i-1][2])+costs[i][1];
            costs[i][2]=Math.min(costs[i-1][0],costs[i-1][1])+costs[i][2];
        }
        return Math.min(costs[costs.length-1][0],Math.min(costs[costs.length-1][1],costs[costs.length-1][2]));
    }
}
