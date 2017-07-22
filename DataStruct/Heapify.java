package DataStruct;

/**
 * Created by 502575560 on 7/12/17.
 */
public class Heapify {
    public void heapify(int[] A) {
        // write your code here
        int start=A.length/2-1;//这个下标要记住
        for(int i=start;i>=0;i++){
            pushDown(A,i);
        }
        return;
    }
    void pushDown(int[] a,int i){
        int left=2*i+1;
        int right=2*i+2;
        int smallest=i;
        if (left<a.length && a[left]<a[smallest])
            smallest = left;
        if (right<a.length && a[right]<a[smallest])
            smallest = right;
        if(i!=smallest){
            int temp=a[i];
            a[i]=a[smallest];
            a[smallest]=temp;
            pushDown(a,smallest);
        }
    }
}
