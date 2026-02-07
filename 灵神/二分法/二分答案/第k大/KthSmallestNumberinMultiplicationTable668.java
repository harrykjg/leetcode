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

    //1/19/2026还是没想到怎么写good函数，原来是利用他本来就是乘法表，因此每一行的数字就是这一行的第一个数字的倍数，每一行的第一个数字就是i，
    // 因此要找这一行有几个数是小于x的就是x/i，，最大值也最多是列数。还有第k小的数说明至少含有>=k个小于他的数，注意不是恰好等于
    //还有就是必然是返回e这里不好想，稳一点的话还是加上检测，如果b是符合biggerThanK的话就返回b，我测过是能过的
}
