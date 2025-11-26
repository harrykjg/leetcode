package 灵神.DP.网格dp;

public class MaximumNonNegativeProductinaMatrix1594 {
    public static void main(String[] args) {

    }
    public int maxProductPath(int[][] grid) {
        int mod=(int)Math.pow(10,9)+7;
        long[][][] dp=new long[grid.length][grid[0].length][2];

        for (int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(i==0&&j==0){
                    dp[i][j][0]=grid[i][j];
                    dp[i][j][1]=grid[i][j];

                }else if(i==0){
                    dp[i][j][0]=((grid[i][j]*dp[i][j-1][0]));
                    dp[i][j][1]=dp[i][j][0];
                }else if(j==0){
                    dp[i][j][0]=((grid[i][j]*dp[i-1][j][0]));
                    dp[i][j][1]=dp[i][j][0];
                }else{
                    long leftone=((dp[i][j-1][0]*grid[i][j]));//不要中间用mod。直接最后才用mod，不然会错。
                    long lefttwo=((dp[i][j-1][1]*grid[i][j]));
                    long leftmax=Math.max(leftone,lefttwo);
                    long leftmin=Math.min(leftone,lefttwo);

                    long upone=((dp[i-1][j][0]*grid[i][j]));
                    long uptwo=((dp[i-1][j][1]*grid[i][j]));
                    long upmax=Math.max(upone,uptwo);
                    long upmin=Math.min(upone,uptwo);
                    dp[i][j][0]=Math.max(leftmax,upmax);
                    dp[i][j][1]=Math.min(leftmin,upmin);
                }
            }
        }
        if(dp[dp.length-1][dp[0].length-1][0]>=0){
            return (int)((dp[dp.length-1][dp[0].length-1][0])%mod);
        }
        return -1;

    }
}
