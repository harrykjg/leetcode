package SomeInterviews.snap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements347 {
    static void main() {

    }
    //3/5/2026虽然一次过但是pq可以只保留k个，这样复杂度是nlogk，否则就是nlogn.
    //更好的方法是按frequency 做quick select，因为题目说了return 任意order，因此select到k这个点之后这一半返回就行了
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        Map<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        for (Map.Entry<Integer,Integer> ent:map.entrySet()){
            pq.offer(new int[]{ent.getKey(),ent.getValue()});
            if(pq.size()>k){
                pq.poll();
            }
        }
        int[] rs=new int[k];
        int index=0;
        while (index<k){
            rs[index++]=pq.poll()[0];
        }
        return rs;
    }

}
