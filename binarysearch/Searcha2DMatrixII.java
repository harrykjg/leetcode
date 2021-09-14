package binarysearch;

public class Searcha2DMatrixII {
    //7/18/2021 容易和 Kth Smallest Element in a Sorted Matrix 搞混。不会
    //https://www.cnblogs.com/grandyang/p/4669134.html
    //思路就是从左下角开始或者从右上角开始搜。

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length==0){
            return false;
        }
        int i=0;
        int j=matrix[0].length-1;
        while (i<matrix.length&&j>=0){
            int m=matrix[i][j];
            if (m==target){
                return true;
            }
            if (m<target){
                i++;
            }else {
                j--;
            }
        }
        return false;
    }
}
