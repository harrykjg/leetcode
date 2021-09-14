package contest;

public class MaximumNumberofPointswithCost {
    public static void main(String[]args){
        MaximumNumberofPointswithCost mn=new MaximumNumberofPointswithCost();
        int[][] a={{1,5},{3,2},{4,2}};
        System.out.println(mn.maxPoints(a));
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
//    long rs=0;
//    public long maxPoints2(int[][] points) {
//        for (int i=0;i<points[0].length;i++){
//            dfs(points[0][i],1,i,points);
//        }
//        return rs;
//    }
//    void dfs(long cur,int row,int col,int[][] m){
//        if (row>=m.length){
//            rs=Math.max(cur,rs);
//        }
//        for (int i=0;i<m[0].length;i++){
//
//        }
//    }
}
