import java.util.HashMap;

/**
 * Created by yufengzhu on 7/29/18.
 */
public class MaximumSizeSubarraySumEqualsk {
    public static void main(String[] args){
        int[] a={-2,-1,2,1};//{1,-1,5,-2,3};
            //            sum:0,-2,-3,2,0,3
        MaximumSizeSubarraySumEqualsk ms=new MaximumSizeSubarraySumEqualsk();
        System.out.print(ms.maxSubArrayLen(a,1));
    }
    //看到提示用hashmap就知道是前缀和，但是脑子有点混乱改了好久，因为这里是检测sum-k，然后存的还是sum
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums.length==0){
            return 0;
        }
        int rs=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(map.containsKey(sum-k)){
                int len=i-map.get(sum-k);
                rs=Math.max(rs,len);
            }
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return rs;

    }
}
