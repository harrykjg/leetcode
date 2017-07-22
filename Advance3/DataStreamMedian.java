package Advance3;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by 502575560 on 7/20/17.
 */
public class DataStreamMedian {
    //自己想了一下以为一个堆就可以,其实还是要一个最大堆和最小堆,最大堆比最小堆多一个元素,那么最大堆堆顶的就时中位数了
    //http://blog.csdn.net/xudli/article/details/46389077
    public static void main(String[] args){
        int[] n=new int[]{1,2,3,4,5};
        medianII(n);
    }
    public static int[] medianII(int[] nums) {
        // write your code here
        PriorityQueue<Integer> q1=new PriorityQueue<>(10,Collections.reverseOrder());//lintcode 没有只接受comparator的构造器,只有多加个size
        PriorityQueue<Integer> q2=new PriorityQueue<>();//最小堆
        int[] rs=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            if(q1.isEmpty()||nums[i]<=q1.peek()){//遍历到当前数字时,看判断到底去哪个堆,才能使得最大堆装的是最小的元素,最小堆装的是最大的元素
                q1.offer(nums[i]);//最大堆要装最小的元素,所以如果当前元素小于最大堆的最大的元素,就应该加进来,否则就应该加进最小堆然后把最小堆最小的元素
            }                    //加到最大堆里来,如1,2,3现在q1装了1,q2装了2,现在3来了,则3先进最小堆,然后最小堆再把2给最大堆就对了
            else {
                q2.offer(nums[i]);
            }
            while (q2.size()>q1.size()){
                q1.offer(q2.poll());
            }
            while (q2.size()<q1.size()-1){
                q2.offer(q1.poll());
            }

            rs[i]=q1.peek();

        }
        return rs;
    }
}
