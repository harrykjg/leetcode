package 灵神.DP.网格dp;

import java.util.Arrays;

public class MaximumPathScoreinaGrid3742 {
    public static void main(String[] args) {

    }
    //不好写，有大概思路但是第三维不太好理解。参考别人的代码
    //https://leetcode.cn/problems/maximum-path-score-in-a-grid/solutions/3827224/3742-wang-ge-zhong-de-fen-zui-da-de-lu-j-pglw/

    public int maxPathScore(int[][] grid, int k) {
        int[][][] dp=new int[grid.length][grid[0].length][k+1];//第三维为什么k+1？因为可以用0到k次那是k+1个状态，第三维代表用了k个cost
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                Arrays.fill(dp[i][j],Integer.MIN_VALUE);
                int cost=grid[i][j]==0?0:1;
                for(int x=cost;x<=k;x++){
                    if(i==0&&j==0){
                        dp[i][j][x]=0;//题目说了第一个元素是0
                    }else if(i==0){
                        dp[i][j][x]=dp[i][j-1][x-cost]+grid[i][j];//这个写法确实没有考虑k这个状态的前一个状态是否是不可到达的，但由于考虑的都是正数所以也无所谓
                    }else if(j==0){
                        dp[i][j][x]=dp[i-1][j][x-cost]+grid[i][j];
                    }else{
                        dp[i][j][x]=Math.max(dp[i-1][j][x-cost],dp[i][j-1][x-cost])+grid[i][j];
                    }
                }

            }
        }
        if(dp[dp.length-1][dp[0].length-1][k]>0){//这个直接最后一个虽然能accept，但是意义上似乎更应该是便利第三维，取最大的那个，因为不一定说一定要用掉k个cost的才是最大
            return dp[dp.length-1][dp[0].length-1][k];
        }
        return -1;

    }
}
