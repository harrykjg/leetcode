import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SubarraySumEqualsK {

    //8/9/2021
    //直接prefix sum就行，答案的的解法就把prefix sum空间减少了，没看怎么做
    //https://leetcode.com/problems/subarray-sum-equals-k/discuss/102106/Java-Solution-PreSum-%2B-HashMap 用map的
    public int subarraySum(int[] nums, int k) {
        long[] sum=new long[nums.length+1];
        for (int i=1;i<sum.length;i++){
            sum[i]=nums[i-1]+sum[i-1];
        }
        int rs=0;
        for (int i=1;i<sum.length;i++){
            for (int j=0;j<i;j++){
                if (sum[i]-sum[j]==k){
                    rs++;
                }
            }
        }
        return rs;
    }
    //8/15/2021 写的用map的方法，时间复杂度是n而上面的是n方.注意这里map的value不是以前别的subarray题那样存的是index，这里存index没用，存的是这个值出现的次数
    //拿[1,-1,0】k=0 试试就知道了
    public int subarraySum2(int[] nums, int k) {
        int rs=0;
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        int sum=0;
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
            if (map.containsKey(sum-k)){
                rs+=map.get(sum-k);
            }
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return rs;
    }

}
