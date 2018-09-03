package binarysearch;

/**
 * Created by yufengzhu on 1/21/18.
 */
public class search2dmatrix {

    //1/21/2018 九章第二轮,用模版,稍微有点麻烦，好处就是不用想b=mid+1之类的
    // http://www.jiuzhang.com/solution/search-a-2d-matrix/
    public boolean searchMatrix2(int[][] matrix, int target) {

        if(matrix.length==0){
            return false;
        }
        int b=0;
        int e=matrix.length-1;
        int m=0;
        while (b+1<e){
            m=b+(e-b)/2;
            if(matrix[m][0]==target){
                return true;
            }
            if(matrix[m][0]<target){
                b=m;
            }else{
                e=m;
            }
        }
        int row=0;
        if(matrix[b][matrix[0].length-1]<target){
            row=e;
        }else{
            row=b;
        }
        b=0;
        e=matrix[0].length-1;
        while (b+1<e){
            m=b+(e-b)/2;
            if(matrix[row][m]==target){
                return true;
            }
            if(matrix[row][m]<target){
                b=m;
            }else{
                e=m;
            }
        }
        if(matrix[row][b]==target||matrix[row][e]==target){
            return true;
        }
        return false;

    }
    //8/15/2018基本一次过
    public boolean searchMatrix22(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0){
            return false;
        }
        int b=0;
        int e=matrix.length-1;
        int m=0;
        while (b+1<e){
            m=b+(e-b)/2;
            if(matrix[m][0]==target){
                return true;
            }
            if(matrix[m][0]>target){
                e=m;
            }else{
                b=m;
            }
        }
        int index=0;
        if(matrix[e][0]>target){
            index=b;
        }else{
            index=e;
        }
        b=0;
        e=matrix[0].length-1;
        while (b+1<e){
            m=b+(e-b)/2;
            if(matrix[index][m]==target){
                return true;
            }
            if(matrix[index][m]>target){
                e=m;
            }else{
                b=m;
            }
        }
        if(matrix[index][b]==target||matrix[index][e]==target){
            return true;
        }
        return false;
    }
}
