package binarysearch;

/**
 * Created by 502575560 on 5/8/17.
 */
public class KthSmallestElementinaSortedMatrix {
    public static void main(String[] args){
        int[][] m={{1,5,9},{10,11,13},{12,13,15}};
//        int[][] m={{1,2},{1,3}};
        System.out.println(kthSmallest(m,8));

    }
    //想不到好方法,用堆的话不就没意思了吗,不过好像也能accept,用二分法的方法才有意思,自己想不出来
    //
    //https://discuss.leetcode.com/topic/52948/share-my-thoughts-and-clean-java-code/2  这题算非主流二分法,看他分析,其实就是九章说的二分on值而不是on索引
    //http://www.cnblogs.com/grandyang/p/5727892.html  他们用了c++的vector的某些方法,应该是和leetcode discuss的思想是一样的吧
    //http://www.jianshu.com/p/902e13f7eee5
    public static int kthSmallest(int[][] m, int k) {
        if(m.length==0){
            return -1;
        }
        int count=0;
        int b=m[0][0];
        int e=m[m.length-1][m[0].length-1]+1;//这里不加1也能accept,不知道他为啥要+1
        int mid=b+(e-b)/2;                    //他这个二分法有点奇怪,想法是找一个mid值,然后遍历整个matrix取算出小于等于这个mid值的个数,
        while (b<e){                          //如果个数小于k那么就说明这个mid值太小,把b=mid+1再去找整个matrix去算有几个值小于等于他.如果是
            mid=b+(e-b)/2;                      //得出的个数大于等于k那么说明mid值太大了,很奇怪这样找出的mid能确保在数组中存在?而且边界条件
            count=0;                            //为啥是返回b?
            for(int i=0;i<m.length;i++){
                int j=m[0].length-1;
                for(;j>=0;j--){
                    if(m[i][j]<=mid){ //注意容易忘了等号,意义是这行数组从后往前找,如果发现第一个m[i][j]<=mid了就说明j左边全部是小于mid的,
                        break;        //所以j+1就是这个数组种小于等于mid的个数
                    }
                }
                count+=j+1;
            }
            if(count<k){//为啥不是count==k的时候直接返回是错的?为啥一定要count<k?好像还是二分法的两种可能,要是count<k的时候b肯定要增加
                b=mid+1; //才可能是正确答案,要是b==e那就完事了返回b,否则就是e=mid,要是b==e了那么返回e还是b都是一样
            }else{
                e=mid;
            }
        }
        return b;
    }
//https://discuss.leetcode.com/topic/52948/share-my-thoughts-and-clean-java-code/2  的代码
    public static int kthSmallest2(int[][] matrix, int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0,  j = matrix[0].length - 1;
            for(int i = 0; i < matrix.length; i++) {
                while(j >= 0 && matrix[i][j] > mid){
                    j--;
                }
                count += (j + 1);
            }
            if(count < k) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
