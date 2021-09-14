package Advance7;

public class SubArraysumII {
    //6/22/2021自己想用dp结果还不如直接prefix sum加暴力.就这二重循环都没想清楚
    //https://zhengyang2015.gitbooks.io/lintcode/content/subarray_sum_ii_404.html
    //https://www.cnblogs.com/lz87/p/7203547.html
    public int subarraySumII(int[] A, int start, int end) {
        // write your code here
        int[] sum=new int[A.length+1];//前缀和经常忘了长度要+1
        sum[0]=0;
        for (int i=1;i<=A.length;i++){
            sum[i]=sum[i-1]+A[i-1];
        }
        int rs=0;
        for (int i=0;i<A.length;i++){
            for (int j=i+1;j<=A.length;j++){
                int cursum=sum[j]-sum[i];
                if (cursum<=end&&cursum>=start){
                    rs++;
                }

            }
        }
        return rs;

    }
}
