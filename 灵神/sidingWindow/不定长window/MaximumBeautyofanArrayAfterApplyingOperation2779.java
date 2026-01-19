package 灵神.sidingWindow.不定长window;

import java.util.Arrays;

public class MaximumBeautyofanArrayAfterApplyingOperation2779 {
    public static void main(String[] args) {

    }

    //没想出来，看的hint。 sort之后，用sliding window，可求出如果nums[e]-k<=(nums[b]+k)的话既nums【b]和nums【e】可以变成一个数。
    //那么就是可以求出longest subarray that 符合这个条件的，就是答案
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int rs=1;
        int b=0;
        int e=0;
        for(e=0;e< nums.length;e++){//这里的写法是e肯定加进来再说，然后再缩b。
            int len=e-b+1;
            if(nums[e]-nums[b]<=2*k){
                rs=Math.max(len,rs);
                continue;
            }
            while (b<e&&nums[e]-nums[b]>2*k){
                b++;
            }
        }
        return rs;

    }
    //第二轮还是没想出来，但是有想到差分数组或者二分答案（应该不行），这个答案说了几种写法。
    //难点还是直接想到这个不等式等价于sliding window这一步不好想
    //https://leetcode.cn/problems/maximum-beauty-of-an-array-after-applying-operation/solutions/2346307/python3javac-er-fen-hua-dong-chuang-kou-rdkls/#%E4%BA%8C%E5%88%86%E8%A7%A3%E6%B3%95

}
