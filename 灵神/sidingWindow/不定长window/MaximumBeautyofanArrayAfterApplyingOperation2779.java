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

}
