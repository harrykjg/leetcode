package binarysearch;

/**
 * Created by 502575560 on 7/29/17.
 */
public class MaximumAverageSubarrayII {
    public static void main(String[] args){
        MaximumAverageSubarrayII ma=new MaximumAverageSubarrayII();
        int[] n={1,12,-5,-6,50,3};
        ma.findMaxAverage(n,4);
    }
    //不会
    //http://blog.csdn.net/qq_34153219/article/details/56298842 二分法on 值,就是找到一个mid值看这个数组是否有一个subarray的average大于等于mid
    //如果有的话说明这个mid值还可以试试更大的
    //http://www.jiuzhang.com/solutions/maximum-average-subarray/ 代码看这个
    public double findMaxAverage(int[] nums, int k) {

        double b=Integer.MAX_VALUE;
        double e=Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < b)
                b = nums[i];//找数组中最小和最大的数字,那么average肯定再这两者之间
            if (nums[i] > e)
                e = nums[i];
        }

        double mid=0;
        while (e-b>1e-6){
            mid=(e-b)/2d;
            if(check(nums,k,mid)){
                b=mid;//注意这里就不用mid+1了,因为mid是double,不会产生int那种(6+7)/2=6然后b再等于6会死循环这种问题
            }else {
                e=mid;
            }
        }
        return b;
    }
    boolean check(int[] nums,int k,double mid){
        double[] sum=new double[nums.length+1];
        double preMin=0;
        for(int i=1;i<=nums.length;i++){//这个sum数组要理解清楚,它装的实际上是从0到i这一段nums的数的值减去mid之后的和,因为每个数都减去mid了
            sum[i]=sum[i-1]+nums[i-1]-mid; //所以当i<k时就一直加(不管这个nums[i]-mid是否大于0,加到i>=k时,符合了长度大于等于k的条件,此时
            if(i>=k&&sum[i]-preMin>=0){//sum[i]-preMin是否大于等于0,是的话就说明找到了,这里不太好理解,举个例子,nums数组是10,-5,20,0,10,20,k=3,mid=10试试
                return true;//想,现在看sum数组,sum[i]的意义就是nums0到i-1的总和,不管前面是正负数字我都加上了,那么我应该记录一下从0到i-k这之间的sum值谁最小,
            }
            if(i>=k){//然后我把这个小的这一段之前全部砍掉,得出的数如果>=0就说明i-k到i这一段的average大于mid了,因此要记录preMin
                preMin=Math.min(preMin,sum[i-k+1]);
            }
        }
        return false;
    }
}
