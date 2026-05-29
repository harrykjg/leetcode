package SomeInterviews.roblox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomlyPartitionArray {


    //就是直接先shuffle数组，再分到k个子数组里。。

    public List<List<Integer>> randomlyPartition(int[] nums, int k) {
        Random ran=new Random();
        //这个是最直观的想法，但是根据gpt这不对
        //因为第 i 轮时，前面已经处理过的位置又可能被后面改掉。
        //所以不是“每个位置只确定一次”，而是会被反复扰动，导致概率不均匀。
//        for (int i=0;i<nums.length;i++){
//            int next=ran.nextInt(nums.length);
//            int temp=nums[i];
//            nums[i]=nums[next];
//            nums[next]=temp;
//        }

        for (int i=nums.length-1;i>0;i--){//一般是反着写，而且注意i>0
            int next=ran.nextInt(i+1);
            int temp=nums[i];
            nums[i]=nums[next];
            nums[next]=temp;
        }


        //懒得写，就是分成k个然后加进rs里。
        List<List<Integer>> rs=new ArrayList<>();


    }
}
