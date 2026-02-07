package linkedin;

import java.util.Map;

public class MaxConsecutiveOnesIII1004 {
    //1/31/2026写的比之前好
    public int longestOnes(int[] nums, int k) {
        int count=0;
        int b=0;
        int e=0;
        int rs=0;
        while (e<nums.length){
            while (e<nums.length){
                if(nums[e]==0&&count<k){
                    count++;
                    e++;
                }else if(nums[e]==1){
                    e++;
                }else{
                    break;
                }
            }
            rs= Math.max(e-b,rs);
            if(k==0){
                e++;
                b=e;
            }else{
                while (b<e&&nums[b]==1){
                    b++;
                }
                count--;
                b++;
            }
        }
        return rs;
    }
}
