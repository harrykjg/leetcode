package 灵神.二分法.二分答案.其他;

public class Searcha2DMatrix74 {

    //https://leetcode.cn/problems/search-a-2d-matrix/solutions/2783931/liang-chong-fang-fa-er-fen-cha-zhao-pai-39d74/
    //第一种方法能想到，看他第二种的解法
    public boolean searchMatrix(int[][] matrix, int target) {

        int i=0;
        int j=matrix[0].length-1;
        while (i<matrix.length&&j>=0){
            int cur=matrix[i][j];
            if(cur==target){
                return true;
            }
            if(cur<target){
                i++;
            }else{
                j--;
            }

        }
        return false;
    }
}
