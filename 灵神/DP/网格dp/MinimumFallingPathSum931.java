package 灵神.DP.网格dp;

public class MinimumFallingPathSum931 {
    public static void main(String[] args) {
        int[][] m={{2,1,3},{6,5,4},{7,8,9}};
        System.out.println(minFallingPathSum(m));
    }
    public static int minFallingPathSum(int[][] matrix) {
        int[][] dp=new int[matrix.length][matrix[0].length];
        int rs=Integer.MAX_VALUE;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(i==0){
                    dp[i][j]=matrix[i][j];
                }else {
                    if(j>0&&j+1<matrix[0].length){
                        dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i-1][j+1]))+matrix[i][j];
                    }else if(j>0){
                        dp[i][j]=Math.min(dp[i-1][j],dp[i-1][j-1])+matrix[i][j];
                    }else if(j+1<matrix[0].length){
                        dp[i][j]=Math.min(dp[i-1][j],dp[i-1][j+1])+matrix[i][j];
                    }else{
                        dp[i][j]=dp[i-1][j]+matrix[i][j];
                    }
                }

            }
        }
        for (int i=0;i<matrix[0].length;i++){
            rs=Math.min(rs,dp[dp.length-1][i]);
        }
        return rs;

    }
}
