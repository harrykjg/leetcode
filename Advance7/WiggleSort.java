package Advance7;

import java.util.Arrays;

/**
 * Created by 502575560 on 8/10/17.
 */
public class WiggleSort {
    public static void main(String[] a){
        WiggleSort ws=new WiggleSort();
        int[] n={3,5,2,1,6,4};
        ws.wiggleSort2(n);
    }
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
    //6/24/2018,自己想的是排序后分成前后两段，然后把后段的第一个插到前段的第一个后面这样。看回以前的链接的方法,还是比较神奇
    public void wiggleSort2(int[] nums) {
        if(nums.length<=1){
            return;
        }
        for(int i=1;i<nums.length;i++){
            if(i%2==0){
                if(nums[i]>nums[i-1]){
                    int temp=nums[i];
                    nums[i]=nums[i-1];
                    nums[i-1]=temp;
                }
            }else{
                if(nums[i]<nums[i-1]){
                    int temp=nums[i];
                    nums[i]=nums[i-1];
                    nums[i-1]=temp;
                }
            }
        }
    }

}

