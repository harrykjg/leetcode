package DataStruct;

/**
 * Created by 502575560 on 7/12/17.
 */
public class Heapify {
    public void heapify(int[] A) {
        // write your code here
        int start=A.length/2-1;//这个下标要记住
        for(int i=start;i>=0;i--){
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
    //九章第二轮，12／11／2017，没有考虑应该从哪里开始pushdown，看回之前的，还要画图理解，才知道不是从第一位到最后以为push的,而且还必须是从start往前走才对，
    //要画图理解，而且写的也不好，第一次的写法那么简介还不好写
    public void heapify2(int[] A) {
        int start=A.length/2-1;
        for(int i=start;i>=0;i--){
            pushDown2(A,i);
        }
    }

    void pushDown2(int[] a,int i){
        int left=i*2+1;
        int right=i*2+2;
        if(right<a.length&&a[left]<a[right]){
            if(a[i]>a[left]){
                int temp=a[i];
                a[i]=a[left];
                a[left]=temp;
                pushDown2(a,left);
            }
        }else if(right<a.length&&a[left]>=a[right]){
            if(a[i]>a[right]){
                int temp=a[i];
                a[i]=a[right];
                a[right]=temp;
                pushDown2(a,right);
            }
        }else if(left<a.length&&a[i]>a[left]){//只有左子树并且左子树比较小
            int temp=a[i];
            a[i]=a[left];
            a[left]=temp;
            pushDown2(a,left);
        }
    }
}
