/**
 * Created by yufengzhu on 7/1/18.
 */
public class FlippinganImage {
    //简单题但是还是写的不是很好，应该用j<（A[0].length+1）/2这个条件，再取异或1就行了
    public int[][] flipAndInvertImage(int[][] A) {
        for(int i=0;i<A.length;i++){
            for(int j=0;j<(A[0].length+1)/2;j++){
                int temp=A[i][A[0].length-1-j];
                A[i][A[0].length-1-j]=A[i][j]^1;
                A[i][j]=temp^1;
            }
        }
        return A;
    }
}
