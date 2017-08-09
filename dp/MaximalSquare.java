package dp;

/**
 * Created by 502575560 on 7/30/17.
 */
public class MaximalSquare {
    //不会
    //https://segmentfault.com/a/1190000003709497
    //http://blog.csdn.net/u012501459/article/details/46553139
    public int maximalSquare(char[][] matrix) {
        int rs=0;
        if(matrix.length==0){
            return 0;
        }
        int[][] dp=new int[matrix.length][matrix[0].length];//dp[i][j]的意义是,以i,j点为右下角的正方形的边长
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(i==0&&j==0){
                    dp[i][j]=matrix[i][j]=='0'?0:1;
                }else if(i==0){
                    dp[i][j]=matrix[i][j]=='0'?0:1;
                }
                else if(j==0){
                    dp[i][j]=matrix[i][j]=='0'?0:1;
                }
                else if(matrix[i][j]=='0'){
                    dp[i][j]=0;
                }else{
                    dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                }
                rs=Math.max(rs,dp[i][j]);
            }
        }
        return rs*rs;

    }
}
