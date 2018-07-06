/**
 * Created by yufengzhu on 7/5/18.
 */
public class ToeplitzMatrix {
    //妈的还想了挺久怎么写循环,举了好几个例子才发现规律，扫描对角线的次数是m-1+n-1-1，左下角和右上角不扫描。然后分开左下半边和右上半边分开扫描,但是并没有什么卵用
    //https://leetcode.com/problems/toeplitz-matrix/solution/ 答案2这个叼，是2个2个比的，不是答案那样比的,学他的
    public boolean isToeplitzMatrix(int[][] matrix) {
       for(int i=0;i<matrix.length;i++){
           for(int j=0;j<matrix[0].length;j++){
               if(i>0&&j>0){
                   if(matrix[i-1][j-1]!=matrix[i][j]){
                       return false;
                   }
               }
           }
       }
        return true;

    }
}
