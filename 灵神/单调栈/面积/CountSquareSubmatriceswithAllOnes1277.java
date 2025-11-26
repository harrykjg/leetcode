package 灵神.单调栈.面积;

public enum CountSquareSubmatriceswithAllOnes1277 {

    public static void main(String[] args) {

    }

    //参考1504题

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


}
