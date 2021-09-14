package binarysearch;

public class MissingElementinSortedArray {
    //8/20/2021
    //只能想到O N的，二分法不好想. 关键是利用nums[i]-nums[j]-(i-j)=missing的数字的个数这个性质
    //https://www.e-learn.cn/topic/3751197  相当巧妙，while的时候条件是b+1<e.保证了mid和b是不同的，然后退出while的是欧刚好又是要用小的那个元素加上剩余的k的
    public int missingElement(int[] nums, int k) {
        int b=0;
        int e=nums.length;//这里还非得nums。length，减1的话就不对了
        while (b+1<e){
            int m=(b+e)/2;
            int missing=nums[m]-nums[b]-(m-b);
            if (missing>=k){//这里漏了等号就错了，想如果说missing和k相等的话，说面左边这里包含了missing的数字了，但是你必须找到小的那个nums【b】再加上k得到
                e=m;       //missing的数，所以说如果相等的时候k-=missing为0了，然后b=m还跑去右边找，那么最后nums【b】+0肯定是错的
            }else {
                k-=missing;
                b=m;
            }
        }
        return nums[b]+k;

    }
}
