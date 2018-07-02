/**
 * Created by yufengzhu on 7/1/18.
 */
public class PeakIndexinaMountainArray {
    //就是二分法findpeakelement
    public int peakIndexInMountainArray(int[] A) {
        int m=0;
        int b=0;
        int e=A.length-1;
        while (e-b>1){
            m=b+(e-b)/2;
            if(A[m]>A[m-1]&&A[m]>A[m+1]){
                return m;
            }
            if(A[m]<=A[m-1]){
               e=m-1;
            }else{
                b=m+1;
            }
        }
        if(A[b]>A[b-1]&&A[b]>A[b+1]){
            return b;
        }
        return e;
    }
}
