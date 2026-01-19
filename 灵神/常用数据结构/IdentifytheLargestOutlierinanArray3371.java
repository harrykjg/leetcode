package 灵神.常用数据结构;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IdentifytheLargestOutlierinanArray3371 {
    static void main() {
        int[] n={-2,-1,-3,-6,4};
        System.out.println(getLargestOutlier(n));
    }
    //自己想的超时，就是暴力
    //正确的想法应该是，设outlier 是x，设S是那些数的和，则可知sum=s+s+x即sum-x=2s。可知sum-x是双数，并且s就是（sum-x）/2
    //那样可以枚举x，看map里是否出现过sum-x）/2这个值。x从小往大枚举
    public static int getLargestOutlier(int[] nums) {

        int sum=Arrays.stream(nums).sum();
        Arrays.sort(nums);
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=nums.length-1;i>=0;i--){
            map.put(nums[i],i);//重复的元素无所谓，因为重复的肯定不可能是S
        }
        for (int i= nums.length-1;i>=0;i--){
            int temp=sum-nums[i];
            if (temp%2!=0){
                continue;
            }
            if (map.containsKey(temp/2)&&map.get(temp/2)!=i){
                return nums[i];
            }
        }
        return -1;
    }
}
