package Advance3;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by yufengzhu on 4/14/18.
 */
public class slidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] rs=new int[nums.length-k+1];
        if(nums.length==0||k==0){
            return new int[0];
        }
        int index=0;
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<nums.length;i++){
            if(pq.size()<k){
                pq.add(nums[i]);
            }
            if (pq.size()==k){
                rs[index]=pq.peek();
                index++;
                pq.remove(nums[i+1-k]);
            }

        }
        return rs;
    }
}
