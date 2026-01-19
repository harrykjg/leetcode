package 灵神.sidingWindow;

import java.util.Arrays;

public class  FrequencyoftheMostFrequentElement1838 {
    public static void main(String[] args) {
        int[] nums={7,1,5,5};
        System.out.println(        maxFrequency(nums,2));

    }

    //不会，看答案 https://leetcode.com/problems/frequency-of-the-most-frequent-element/editorial
    //就是先sort，假设选某个target，那么要把谁加上一个数能达到这个target呢，那只能是比他小的数。所以只看左边的元素。
    //那么如何有效的看target左边的一对元素能否加到target呢，就用sliding window统计这个范围的sum，因为最终要到达x*target
    //因此计算出来的x*target-sum就可知要加几个operation，是否小于等于k即可。因此可以用begin和end扫数组，找出最长的那段

    public static int maxFrequency(int[] nums, int k) {
        int rs=1;
        int b=0;
        int e=1;
        Arrays.sort(nums);
        long sum=nums[0];
        while (e<nums.length){
            long gap=0;
            long target=0;
            while (e<nums.length){
                target=nums[e];
                int len=e-b+1;
                sum+=nums[e];
                gap=target*len-sum;
                if (gap>k){
                    break;
                }else {
                    rs=Math.max(rs,len);
                    e++;
                }

            }
            while (b<=e&&b<nums.length&&gap>k){

                sum-=nums[b];
                //这里miss了搞了很久，就是gap要再update，b也容易忘
                gap=(e-(b+1)+1)*target-sum;
                b++;
            }
            e++;
        }
        return rs;
    }
//1/16/2026第二次想错了，以为就是sliding window 设b和e，只要b+k能reach到e那就能把他们连在一块就是答案，其实不对，其实是要把b到e-1的所有的
    //数增加到e才行
    public static int maxFrequency2(int[] nums, int k) {
        Arrays.sort(nums);
        int b=0;
        int e=1;
        long sum=nums[0];
        int rs=1;
        while (e<nums.length){
            int len=e-b+1;
            sum+=nums[e];
            long target=nums[e]*len;
            if (target-sum<=k){
                e++;
                rs=Math.max(rs,len);
            }else{
                while (b<=e&&b<nums.length&&target-sum>k){
                    sum-=nums[b];
                    target-=nums[e];//这里也漏了更新target！
                    b++;
                }
                e++;
            }
        }
        return rs;
    }
}
