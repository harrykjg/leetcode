package 灵神.二分法.二分答案.第k大;

public class KthSmallestNumberinMultiplicationTable668 {
    public static void main(String[] args) {
        System.out.println(findKthNumber(2,3,6));
    }
    //怎么数比某个数小的方法没想到
    public static int findKthNumber(int m, int n, int k) {
        int b=0;
        int e=n*m+1;
        while (b+1<e){
            int mid=e-(e-b)/2;
            boolean bigger=biggerThanK(m,n,k,mid);
            if(bigger){
                e=mid;
            }else{
                b=mid;
            }
        }
        return e;//return b还是e还是不是固定的
    }
    //这里没想出来怎么做
    static boolean biggerThanK(int m,int n,int k,int mid){
        int cur=0;
        for(int i=1;i<=m;i++){
            cur+=Math.min(n,mid/i);//就是看每一行有几列的数小于等于mid，既每一行有mid/i列，或者n列
        }
        return cur>=k;
    }
}
