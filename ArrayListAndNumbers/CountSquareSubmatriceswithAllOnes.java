package ArrayListAndNumbers;

public class CountSquareSubmatriceswithAllOnes {
    //9/5/2021 还以为用prefixsum做，其实要变，不然你只知道prefixsum只能快速求2个点之间是不是正方形，要求所有的就不太好了（应该可以，从每个点看他对角线右下方的点）
    //https://leetcode.com/problems/count-square-submatrices-with-all-ones/discuss/643429/Python-DP-Solution-%2B-Thinking-Process-Diagrams-(O(mn)-runtime-O(1)-space)
    //还可以优化用o1 space，就是用输入数组记录
    public int countSquares(int[][] matrix) {
        int rs=0;
        int[][] dp=new int[matrix.length+1][matrix[0].length+1];
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if (matrix[i-1][j-1]==1){
                    dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1]))+1;
                    rs+=dp[i][j];
                }

            }
        }
        return rs;
    }
}
