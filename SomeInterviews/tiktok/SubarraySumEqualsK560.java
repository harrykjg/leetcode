package SomeInterviews.tiktok;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK560 {
    static void main() {
        int[] a={1};
        SubarraySumEqualsK560.subarraySum(a,0);
    }
    //5/20/2026
    //            0,1,0,1,0
    //prefixsum 0,0,1,1,2,2  看这个例子发现答案是4，即要一个map记录某个数出现过几次.写的不好，多用了prefix数组，其实不需要，只要一个sum边走边加就行了
    public static int subarraySum(int[] nums, int k) {
        long sum=0;
        Map<Long,Integer> map=new HashMap<>();
        map.put(0L,1);//但是nums=【1】，k=0怎么处理？如果用prefixsum数组则有这个问题，但是边走边加的话就没这个问题。

        int rs=0;
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
            System.out.println(((Object) sum).getClass().getSimpleName());//这个证明long+int的话还是long
            if(map.containsKey(sum-k)){
                rs+=map.get(sum-k);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return rs;

    }
}
