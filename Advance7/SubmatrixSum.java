package Advance7;

/**
 * Created by 502575560 on 8/9/17.
 */
public class SubmatrixSum {
    //https://segmentfault.com/a/1190000004878083 很难哦
    //想象下面的解释，搞懂就理解了
    //从0开始，确定两行i1、i2，再将第k列的sum[i1][k]和sum[i2][k]相减，就得到matrix[i1][0]到matrix[i2][k-1]的子矩阵（i2-i1行，k列）
    // 求和diff，存入map。还是在这两行，假设计算到第j列，得到了相等的和diff。说明从i1到i2行，从k到j列的子矩阵求和为0
//    public int[][] submatrixSum(int[][] matrix) {
//        // Write your code here
//    }

    //6/19/2018还是不会
    //6/21/2021还是不会 LC的363. Max Sum of Rectangle No Larger Than K和这题差不多
    //https://my.oschina.net/u/3790485/blog/1634780

}
