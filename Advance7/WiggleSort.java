package Advance7;

import java.util.Arrays;

/**
 * Created by 502575560 on 8/10/17.
 */
public class WiggleSort {
    //自己只能想到把数组排序,然后最小的放第一位,后面放最大的,再放倒数第二小的再放倒数第二大的这样穿插,他们的方法应该更好
    //比wiggle sort2简单因为可以是大于等于的,而2是不行的
    //http://www.cnblogs.com/grandyang/p/5177285.html
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for(int i=2;i<nums.length;i+=2){
            int temp=nums[i];
            nums[i]=nums[i-1];
            nums[i-1]=temp;
        }
    }
}
