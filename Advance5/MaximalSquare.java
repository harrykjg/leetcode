package Advance5;

/**
 * Created by yufengzhu on 4/24/18.
 */
public class MaximalSquare {
    //4/24/2018Z九章第二轮，还是不会，记着他的dp就行了
    //https://segmentfault.com/a/1190000003709497
    //http://blog.csdn.net/u012501459/article/details/46553139
    public int maximalSquare(char[][] matrix) {
        if(matrix.length==0){
            return 0;
        }
        int[][] dp=new int[matrix.length][matrix[0].length];
        int rs=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]=='0'){
                    dp[i][j]=0;
                }else{
                    if(i==0||j==0){
                        dp[i][j]=1;
                    }else{
                        dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1]))+1;
                    }

                    rs=Math.max(rs,dp[i][j]);
                }

            }
        }
        return rs*rs;
    }
}
