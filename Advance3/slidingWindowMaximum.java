package Advance3;

import java.util.*;

/**
 * Created by yufengzhu on 4/14/18.
 */
public class slidingWindowMaximum {
    public static void main(String[] args){
        slidingWindowMaximum sw=new slidingWindowMaximum();
        int[] a={1,3,1,2,0,5};
        sw.maxSlidingWindow2(a,3);
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
    //妈的这个下标巨难写,原来是q装的不是元素，而是下标！这个写法是这样而已，九章的解法貌似就是存元素的.装下标的原因是因为当dq等于size的
    //时候要remove window的最后一位，但是最后一位不一定在dq的最后一位上了
    // 11/11/2018还是不会这个比较好的方法
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        Deque<Integer> q = new ArrayDeque<>();
        int[] rs = new int[nums.length - k + 1];
        int index=0;
        for(int i=0;i<nums.length;i++){

            if(q.isEmpty()){
                q.add(i);
            }else{
                if(nums[i]>nums[q.peekFirst()]){
                    q.clear();
                    q.offerFirst(i);
                }else{//这里是要保证queue里是单调下降的，即把q里比当前nums[i]要小的数都丢掉，再加进i进来
                    while (!q.isEmpty()&&nums[q.peekLast()]<nums[i]){
                        q.pollLast();
                    }
                    q.addLast(i);
                }
            }
            if(i-k+1<0){
                continue;
            }
            rs[index]=nums[q.peekFirst()];
            if(q.peekFirst()==i-k+1){
                q.pollFirst();
            }
            index++;

        }
        return rs;
    }

    //6/17/2021,除了用pq之外想不到别的方法
    //10/10/2021再写，还是不会dq的方法
    public int[] maxSlidingWindow3(int[] nums, int k) {
        Deque<Integer> dq=new LinkedList<>();
        int[] rs=new int[nums.length-k+1];
        int index=0;
        for(int i=0;i<nums.length;i++){//dq想象成头是在左边，尾在右边，sliding window不断把元素放在尾部
            // 先把元素往dq里面加，完了在检测dq的最后一位是不是再下一轮的时候就该删了
            if(dq.size()==0){
                dq.offerLast(i);
            }else{
                if (nums[dq.peekFirst()]<nums[i]){
                    dq.clear();
                    dq.offerLast(i);
                }else{
                    while (!dq.isEmpty()&&nums[dq.peekLast()]<nums[i]){
                        dq.pollLast();
                    }
                    dq.offerLast(i);
                }
            }
            if (i+1<k){
                continue;
            }
            rs[index++]=nums[dq.peekFirst()];
            if (dq.peekFirst()==i-k+1){
                dq.pollFirst();
            }
        }
        return rs;
    }

}
