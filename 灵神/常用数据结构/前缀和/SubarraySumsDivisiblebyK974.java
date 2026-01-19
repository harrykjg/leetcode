package 灵神.常用数据结构.前缀和;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisiblebyK974 {
    static void main() {
        int[] n={4,5,0,-2,-3,1};
        System.out.println(subarraysDivByK(n,5));
    }
    //看提示hashmap想的，之前也是有一题1497取模的差不多。有了prefixsum 之后就是看那个数减哪个数之后可以被k整除
    // 而有(x-y)%k=0推出x%k=y%k.所以可以便利prefixsum，取得当前数模k的值，如果在这之前map里也有这个数，就知道有了符合答案的子数组了
    public static int subarraysDivByK(int[] nums, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        int rs=0;
        int[] prefix=new int[nums.length+1];
        for (int i=1;i<prefix.length;i++){
            prefix[i]=prefix[i-1]+nums[i-1];

        }
        for (int i=1;i<prefix.length;i++){
            int mod=prefix[i]%k;
            if(mod<0){
                mod+=k;//少了这个就处理不了负数的情况
            }
            rs+=map.getOrDefault(mod,0);

            map.put(mod,map.getOrDefault(mod,0)+1);//这个时候才加进map里，而不是计算prefix的时候就加
        }
        return rs;
    }
}
