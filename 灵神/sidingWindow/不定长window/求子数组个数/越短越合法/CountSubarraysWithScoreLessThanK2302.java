package 灵神.sidingWindow.不定长window.求子数组个数.越短越合法;

public class CountSubarraysWithScoreLessThanK2302 {
    public static void main(String[] args) {
        int[] nums={2,1,4,3,5};
        System.out.println(countSubarrays(nums,10));
    }
//1/13/2026第二轮想，假如sliding 的时候【1，2，3，4，5】是符合条件的，再加就不行了，此时可以算这里有几个子数组。
// 此时左边缩一位，假如右边又加一位，那么变成【2，3，4，5，6】那么也可以算有几个子数组，此时2，3，4，5部分就重叠了啊怎么办？
    //其实不是说到了【1，2，3，4，5】才开始算有几个子数组的，应该是像1658一样，是边走边加的

    //https://leetcode.cn/problems/count-subarrays-with-score-less-than-k/solutions/1595722/by-endlesscheng-b120/
    public static long countSubarrays(int[] nums, long k) {
        long rs=0;
        int b=0;
        int e=0;
        long cursum=0;
        while (e<nums.length&&b<=e){//他这个是e不用判断直接加进来，也不用while继续拓展e，然后再缩b，而是直接while缩b。感觉一般都是不用判断e是否能加进来。
            cursum+=nums[e];
            int len=e-b+1;
            while (b<=e&&cursum*len>=k){
                cursum-=nums[b];
                len=e-b;
                b++;
            }
            if(cursum*len<k){
                rs+=e-b+1; //见参考帖子解释
            }
            e++;
        }
        return rs;

    }


}
