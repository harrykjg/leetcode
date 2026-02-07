package 灵神.单调栈.面积;

public enum CountSquareSubmatriceswithAllOnes1277 {

    public static void main(String[] args) {

    }

    //基于221题的解法

    public int countSquares(int[][] matrix) {
        int[][] dp=new int[matrix.length+1][matrix[0].length+1];
        int rs=0;
        for (int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(matrix[i-1][j-1]==0){
                    continue;
                }
                dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                rs+=dp[i][j];
            }
        }
        return rs;
    }

    //1/21/2026
    public int countSquares2(int[][] matrix) {
        int[][] dp=new int[matrix.length][matrix[0].length];
        int rs=0;
        for(int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==1){
                    if(i!=0&&j!=0){
                        dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                    }else {
                        dp[i][j]=1;
                    }
                }
                rs+=dp[i][j];
            }
        }
        return rs;
    }



}
