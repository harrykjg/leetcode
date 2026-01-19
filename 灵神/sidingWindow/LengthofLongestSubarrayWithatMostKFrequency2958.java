package 灵神.sidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LengthofLongestSubarrayWithatMostKFrequency2958 {
    public static void main(String[] args) {
        int[] nums=new int[]{2,1,2,3};
        System.out.println(maxSubarrayLength(nums,1));

    }
    public static int maxSubarrayLength(int[] nums, int k) {
        int rs=0;
        int b=0;
        int e=0;
        Map<Integer,Integer> hm=new HashMap<>();
        while(e<nums.length){
            int remove=-1;
            while(e<nums.length){
                if (hm.containsKey(nums[e])){
                    if(hm.get(nums[e])<k){
                        hm.put(nums[e],hm.get(nums[e])+1);
                    }else{
                        remove=nums[e];

                        break;
                    }
                }else{
                    hm.put(nums[e],1);
                }
                e++;
            }
            int len=e-b;
            rs=Math.max(rs,len);
            while(b<e){
                hm.put(nums[b],hm.get(nums[b])-1);
                b++;
                if(nums[b-1]==remove){
                    break;
                }
            }
        }

        return rs;
    }

    //1/17/2026,应该是基本操作
    public static int maxSubarrayLength2(int[] nums, int k) {

    }
}
