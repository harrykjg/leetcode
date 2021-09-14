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

    //6/19/2021,自己想麻烦了，想着看dp【i-1】【j-1】在检测行的一溜和列的一溜是否为1，这样貌似可以其实很难写，特别的检测行一溜和列一溜时，当这一溜中间断了的时候，不能
    //简单的说这个i，j点为右下角的正方形就是1，还要具体算是在哪断的，才能判断这个i，j点的dp值
    public int maximalSquare2(char[][] matrix) {
        int[][] dp=new int[matrix.length][matrix[0].length];
        int rs=0;
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if (matrix[i][j]=='1'){
                    if (i>0&&j>0){
                        int min=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                        dp[i][j]=(int)Math.pow(Math.sqrt(min)+1,2);
                    }else {
                        dp[i][j]=1;
                    }
                }
                rs=Math.max(dp[i][j],rs);
            }
        }
        return rs;
    }
}
