package 灵神.常用数据结构.前缀和;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumofDistances2615 {
    static void main() {

    }
    //统计每个数出现的位置，再便利计算。计算的时候再对每一个数的位置的数组构建的prefix sum，算 从0到i这一段的和，和i到最后的的这一段的和
    public long[] distance(int[] nums) {
        HashMap<Integer, List<Integer>> map=new HashMap<>();
        long[] rs=new long[nums.length];
        for (int i=0;i<nums.length;i++){
            map.putIfAbsent(nums[i],new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        for(Map.Entry<Integer,List<Integer>> entry:map.entrySet()){
            List<Integer> values=entry.getValue();
            long[] prefix=new long[values.size()+1];
            for (int i=1;i<prefix.length;i++){
                prefix[i]=prefix[i-1]+values.get(i-1);
            }
            for(int i=0;i<values.size();i++){//对第i个元素，算他与别的位置的距离的和
                long left=(long)values.get(i)*(i+1)-prefix[i+1];
                long right=prefix[prefix.length-1]-prefix[i]-(values.size()-i)*(long)values.get(i);//这个不好算，要举例
                rs[values.get(i)]=left+right;
            }


        }
        return rs;
    }
}
