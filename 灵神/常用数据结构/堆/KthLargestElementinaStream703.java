package 灵神.常用数据结构.堆;

import java.util.PriorityQueue;

public class KthLargestElementinaStream703 {
    static void main() {

    }
    //开始还没绕过来，用大小为k的最小堆存的就是最大的k个元素，而此时栈里有k个元素，栈顶的元素就是k个中间最小的那个，即第k大的
    PriorityQueue<Integer> pq=new PriorityQueue<>();
    int k=0;
    public KthLargestElementinaStream703(int k, int[] nums) {
        this.k=k;
        for(int i=0;i<nums.length;i++){
            add(nums[i]);//这个不是pq。add，是这里定义的add
        }
    }

    public int add(int val) {
        if(pq.size()<k){
            pq.offer(val);
        }else if(pq.peek()<val){
            pq.poll();
            pq.offer(val);
        }
        //否则直接不加进去
        return pq.peek();
    }
}
