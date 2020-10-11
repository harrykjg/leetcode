package contest;

public class MaximumNonNegativeProductinaMatrix {

    public static void main(String[] args){
        int[][] grid={{2,1,3,0,-3,3,-4,4,0,-4},{-4,-3,2,2,3,-3,1,-1,1,-2},{-2,0,-4,2,4,-3,-4,-1,3,4},{-1,0,1,0,-3,3,-2,-3,1,0},{0,-1,-2,0,-3,-4,0,3,-2,-2},{-4,-2,0,-1,0,-3,0,4,0,-3},{-3,-4,2,1,0,-4,2,-4,-1,-3},{3,-2,0,-4,1,0,1,-3,-1,-1},{3,-4,0,2,0,-2,2,-4,-2,4},{0,4,0,-3,-4,3,3,-1,-2,-2}};
        MaximumNonNegativeProductinaMatrix mn=new MaximumNonNegativeProductinaMatrix();
        System.out.println(mn.maxProductPath(grid));
    }
    //自己想的，改了有点久，就是记录dp[i][j]记录的是负数的最小值和正数的最大值，别人的思路也是这样
    //https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/discuss/855059/Java-Bottom-Up-min-max-dp
    public int maxProductPath(int[][] grid) {
        point[][] dp=new point[grid.length][grid[0].length];
        double mod=Math.pow(10,9)+7;
        int rs=Integer.MIN_VALUE;
        for(int i=0;i<dp.length;i++){
            dp[i][0]=new point();
            if(i==0){
                    dp[i][0].pos=grid[0][0];
                    dp[i][0].neg=grid[0][0];
                continue;
            }
            dp[i][0].pos=(int)((dp[i-1][0].pos) * (grid[i][0]));//这里想，pos和neg都不一定非得是正或者负，因为反正后面主循环里把四个可能性都算出来，取最大/最小数
            dp[i][0].neg=(int)((dp[i-1][0].pos) * (grid[i][0]));
        }
        for(int i=1;i<dp[0].length;i++){
            dp[0][i]=new point();
            dp[0][i].pos=(int)((dp[0][i-1].pos) * (grid[0][i]));
            dp[0][i].neg=(int)((dp[0][i-1].pos) * (grid[0][i]));
        }

        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                dp[i][j]=new point();

                long cur1=(dp[i-1][j].pos * grid[i][j]);
                long cur2=(dp[i][j-1].pos * grid[i][j]);
                long cur3=(dp[i-1][j].neg * grid[i][j]);
                long cur4=(dp[i][j-1].neg * grid[i][j]);
                dp[i][j].pos=Math.max(dp[i][j].pos,Math.max(cur1,Math.max(cur2,Math.max(cur3,cur4))));
                dp[i][j].neg=Math.min(dp[i][j].pos,Math.min(cur1,Math.min(cur2,Math.min(cur3,cur4))));
            }
        }
         if (dp[dp.length-1][dp[0].length-1].pos>=0){
             return (int)(dp[dp.length-1][dp[0].length-1].pos%mod);
         }
         return -1;
    }
    class point{
        long neg=Integer.MAX_VALUE;
        long pos=Integer.MIN_VALUE;
    }
}
