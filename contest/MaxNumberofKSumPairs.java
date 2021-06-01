package contest;

import java.util.HashMap;
import java.util.Map;

public class MaxNumberofKSumPairs {
    public static void main(String[] args) {
        MaxNumberofKSumPairs mn=new MaxNumberofKSumPairs();
        int[] a={3,1,3,4,3};
        mn.maxOperations(a,6);
    }
    public int maxOperations(int[] nums, int k) {
        int rs=0;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]==k-nums[i]){
                if(map.containsKey(nums[i])&&map.get(nums[i])>1){
                    rs++;
                    map.put(nums[i],map.get(nums[i])-2);
                    if(map.get(nums[i])==0){
                        map.remove(nums[i]);
                    }
                }
            }else{
                if(map.containsKey(nums[i])&&map.get(nums[i])>0&&map.containsKey(k-nums[i])&&map.get(k-nums[i])>0){
                    rs++;
                    map.put(nums[i],map.get(nums[i])-1);
                    if(map.get(nums[i])==0){
                        map.remove(nums[i]);
                    }
                    map.put(k-nums[i],map.get(k-nums[i])-1);
                    if(map.get(k-nums[i])==0){
                        map.remove(k-nums[i]);
                    }
                }
            }

        }
        return rs;

    }
}
