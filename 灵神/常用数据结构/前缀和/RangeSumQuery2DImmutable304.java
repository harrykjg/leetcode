package 灵神.常用数据结构.前缀和;

public class RangeSumQuery2DImmutable304 {
    static void main() {

    }
    //题目说了要o（1）时间复杂度,那肯定是dp先算好的，注意query部分的下标是要加一的，而且和算dp的公式是不一样的，开始想错了以为一样的
    int[][] dp;
    public RangeSumQuery2DImmutable304(int[][] matrix) {
        dp=new int[matrix.length+1][matrix[0].length+1];
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if(i==1&&j==1){
                    dp[i][j]=matrix[i-1][j-1];
                }else if(i==1){
                    dp[i][j]=dp[i][j-1]+matrix[i-1][j-1];
                }
                else if(j==1){
                    dp[i][j]=dp[i-1][j]+matrix[i-1][j-1];
                }else{
                    dp[i][j]=dp[i-1][j]+dp[i][j-1]-dp[i-1][j-1]+matrix[i-1][j-1];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2+1][col2+1]-dp[row2+1][col1]-dp[row1][col2+1]+dp[row1][col1];
    }

}
