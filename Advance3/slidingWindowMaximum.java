package Advance3;

import java.util.*;

/**
 * Created by yufengzhu on 4/14/18.
 */
public class slidingWindowMaximum {
    public static void main(String[] args){
        slidingWindowMaximum sw=new slidingWindowMaximum();
        int[] a={1,-1};
        sw.maxSlidingWindow2(a,1);
    }

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
    //9／10／2018,有更好的解法
    // https://www.youtube.com/watch?v=2SXqBsTR6a8
    //https://segmentfault.com/a/1190000003903509
    //妈的这个下标巨难写,原来是q装的不是元素，而是下标！
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        Deque<Integer> q = new ArrayDeque<>();
        int[] rs = new int[nums.length - k + 1];

        int index=0;
        for(int i=0;i<nums.length;i++){
            if(!q.isEmpty()&&q.peekFirst())

        }
        return rs;
    }

}
