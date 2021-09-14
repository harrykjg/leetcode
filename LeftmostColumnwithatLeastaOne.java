import java.util.List;

public class LeftmostColumnwithatLeastaOne {
    //8/16/2021,只想到是每一行二分法
    //最好的方法是答案第三个方法，从右上开始遇到一向左走，遇到0向下走
    //https://leetcode.com/problems/leftmost-column-with-at-least-a-one/discuss/590828/Java-Binary-Search-and-Linear-Solutions-with-Picture-Explain-Clean-Code
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimentions=binaryMatrix.dimensions();
        int r=0;
        int c=dimentions.get(1)-1;
        int rs=Integer.MAX_VALUE;
        boolean found=false;
        while (r<dimentions.get(0)&&c>=0){
            if (binaryMatrix.get(r,c)==1){
                c--;
                rs=Math.min(c,rs);
                found=true;
                continue;
            }else {
                r++;
            }
        }
        if (found){
            return c+1;
        }
        return -1;
    }
}
class BinaryMatrix {
      public int get(int row, int col)
      {
          return 1;
      }
      public List<Integer> dimensions (){
          return null;
      }
  }
