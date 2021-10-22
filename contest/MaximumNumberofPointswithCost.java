package contest;

public class MaximumNumberofPointswithCost {
    public static void main(String[]args){
        MaximumNumberofPointswithCost mn=new MaximumNumberofPointswithCost();
        int[][] a={{1,5},{3,2},{4,2}};
        System.out.println(mn.maxPoints2(a));
    }
//7/17/2021 暴力dp时n3次方超时
    public long maxPoints(int[][] points) {
        long[][] dp=new long[points.length][points[0].length];
        for (int i=0;i<dp[0].length;i++){
            dp[0][i]=points[0][i];
        }
        long rs=0;
        for (int i=1;i<dp.length;i++){
            for (int j=0;j<dp[0].length;j++){
                for (int k=0;k<dp[0].length;k++){
                    dp[i][j]=Math.max(dp[i][j],dp[i-1][k]+points[i][j]-Math.abs(k-j));
                }
            }
        }
        for (int i=0;i<dp[0].length;i++){
            rs=Math.max(rs,dp[dp.length-1][i]);
        }
        return rs;
    }
//10/22/2021 暴力超时
    public long maxPoints2(int[][] points) {
        long[][] dp=new long[points.length][points[0].length];
        long rs=0;
        for (int i=0;i<points[0].length;i++){
            dp[0][i]=points[0][i];
            rs=Math.max(rs,dp[0][i]);
        }
        for (int i=1;i<points.length;i++){
            for (int j=0;j<points[0].length;j++){
                for(int k=0;k<points[0].length;k++){
                    dp[i][j]=Math.max(dp[i][j],points[i][j]+dp[i-1][k]-Math.abs(j-k));
                }

            }
        }
        for (int i=0;i<dp[0].length;i++){
            rs=Math.max(rs,dp[dp.length-1][i]);
        }
        return rs;
    }
}
