package Advance3;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by 502575560 on 7/23/17.
 */
public class SlidingWindowMedian {
    public static void main(String[] args){
        int[] n={1,3,-1,-3,5,3,6,7};
//        int[] n={1,2};
//        int[] n={1,2,3,4,2,3,1,4,2};
//        int[] n={1,3,1,4,1,2,3};
        double[] rs=medianSlidingWindow(n,3);
        for(double d:rs){
            System.out.println(d);
        }
    }
    public static double[] medianSlidingWindow(int[] nums, int k) {
        if(nums==null||nums.length<1){
            return new double[0];
        }
        double[] rs=new double[nums.length-k+1];
        int k1=k/2;
        int k2=k-k1;//这样弄的话k2是>=k1的
        int index=0;
        PriorityQueue<Integer> p1=new PriorityQueue<>();//默认升序,是最小堆,存的是前k1大的
        PriorityQueue<Integer> p2=new PriorityQueue<>(10, Collections.reverseOrder());//装的最小
        for(int i=0;i<nums.length;i++){
            int begin=i-k+1;
            if(!p1.isEmpty()&&p1.peek()<nums[i]){//这里开始写的是不管当前数字是多少全都扔给p2,其实不对,是要判断的,才能确保q1装的是这一段的前k1大,q2装的是最小的数
                p1.offer(nums[i]);  //如果说当前的数字大于p1的peek,说明这个数字的确应该来p1,如果说i=0时,即p1是空的话,那么其实当前数字其实先去p1还是p2都行
            }else{                  //如果像我开始写的那样不管当先数字大小,全都丢给p2是不行的,比如p1有3,p2有1,现在5来了,直接进了p2,则p2.peek得出的是5就错了,
                p2.offer(nums[i]);   //应该让5先进p1,发现p1的size比p2大了再把p1最小的倒给p2
            }
            if(p1.size()>p2.size()){
                p2.offer(p1.poll());
            }
            if(p2.size()>p1.size()+1){
                p1.offer(p2.poll());
            }
            if(p1.size()+p2.size()<k){
                continue;
            }
            if(k%2==0){
                rs[index]=p1.peek()/2d+p2.peek()/2d;//注意先加起来再除以二则会超时
                index++;
            }else if(k%2==1){
                rs[index]=(double)p2.peek();
                index++;
            }
            if(p1.contains(nums[begin])){
                p1.remove(nums[begin]);
            }else{
                p2.remove(nums[begin]);
            }

        }
        return rs;
    }
}
