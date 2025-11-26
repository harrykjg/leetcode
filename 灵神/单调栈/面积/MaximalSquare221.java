package 灵神.单调栈.面积;

public class MaximalSquare221 {
    public static void main(String[] args) {

    }
    //不会
    //https://leetcode.cn/problems/maximal-square/solutions/3704858/he-85-ti-yi-yang-de-zuo-fa-pythonjavaccg-az54/
    public int maximalSquare(char[][] matrix) {
        int[][] dp=new int[matrix.length+1][matrix[0].length+1];
        int rs=0;
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(matrix[i-1][j-1]=='0'){
                    dp[i][j]=0;
                }else{
                    int min=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                    dp[i][j]=min+1;
                    rs=Math.max(rs,dp[i][j]);
                }
            }
        }
        return rs*rs;
    }
}
