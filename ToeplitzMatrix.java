import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 7/5/18.
 */
public class ToeplitzMatrix {

    public static void main(String[] args){
        ToeplitzMatrix tm=new ToeplitzMatrix();
        int[][] a={{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
        tm.isToeplitzMatrix3(a);
    }

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

    //9/25/2018记得是2个2个比的，再画个图看看一次过
    public boolean isToeplitzMatrix2(int[][] matrix) {
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(i>0&&j>0&&matrix[i-1][j-1]!=matrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    //pinterest面经，要输出所有对角线的元素，左下角到开始,画图找到了规律，就是从最后一行开始从左到右，然后从下到上扫，发现规律每个元素所在的对角线的序号，然后加进去就行了，
    public List<List<Integer>> isToeplitzMatrix3(int[][] matrix) {
        List<List<Integer>> rs=new ArrayList<>();
        for(int i=0;i<matrix.length+matrix[0].length-1;i++){
            rs.add(new ArrayList<>());
        }
        int count=-1;
        int count2=0;
        for(int i=matrix.length-1;i>=0;i--) {
            count++;
            count2=count;
            for (int j = 0; j < matrix[0].length; j++) {
                rs.get(count2++).add(0,matrix[i][j]);
            }
        }
        //这个代码也行，但是不如上面的好吧
//        for(int i=matrix.length-1;i>=0;i--){
//            int j=0;
//            int ii=i;
//            rs.add(new ArrayList<>());
//            rs.get(rs.size()-1).add(matrix[i][j]);
//            while (ii+1<matrix.length&&j+1<matrix[0].length){
//                rs.get(rs.size()-1).add(matrix[ii+1][j+1]);
//                ii++;
//                j++;
//            }
//        }
//        for(int i=1;i<matrix[0].length;i++){
//            rs.add(new ArrayList<>());
//            rs.get(rs.size()-1).add(matrix[0][i]);
//            int ii=i;
//            int row=0;
//            while (ii+1<matrix[0].length&&row+1<matrix.length){
//                rs.get(rs.size()-1).add(matrix[row+1][ii+1]);
//                ii++;
//                row++;
//            }
//        }
        return rs;
    }

    //8/22/2021 看到以前说是两个两个比的，才好写把
    public boolean isToeplitzMatrix4(int[][] matrix)  {
        for (int i=0;i<matrix.length-1;i++){
            for (int j=0;j<matrix[0].length;j++){
                int a=matrix[i][j];
                if (j+1<matrix[0].length){
                    if (a!=matrix[i+1][j+1]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
