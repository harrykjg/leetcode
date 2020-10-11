package binarysearch;

public class Searcha2DMatrix {
    public static void main(String[] args){
        int[][] m={{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        Searcha2DMatrix searcha2DMatrix=new Searcha2DMatrix();
        searcha2DMatrix.searchMatrix(m,3);
    }
    //05/28/2020，写的很烂。这代码是以前的
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0){
            return false;
        }
        int b=0;
        int e=matrix.length-1;
        while (b<e-1){
            int m=b+(e-b)/2;
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
        if(matrix[e][0]>target){//关键在这里，之前想复杂了，这里就剩b和e两行，target小于matrix[e][0]就去b找，不用管target小于matrix[b][0]，那更不可能
            index=b;
        }else{
            index=e;
        }
        b=0;
        e=matrix[0].length-1;
        while (b+1<e){
            int m=b+(e-b)/2;
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
