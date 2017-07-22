import java.util.Arrays;

/**
 * Created by 502575560 on 7/10/17.
 */
public class ThreeSumClosest {
    //居然一次过
    public int threeSumClosest(int[] nums, int target) {
        if(nums.length<=2){
            return target;
        }
        Arrays.sort(nums);
        int min=Integer.MAX_VALUE;
        int rs=Integer.MAX_VALUE;
        for(int i=0;i<nums.length-2;i++){
            if(i>-0&&nums[i]==nums[i-1]){
                continue;
            }
            int b=i+1;
            int e=nums.length-1;
            while (b<e){
                int gap=Math.abs(nums[i]+nums[b]+nums[e]-target);
                if(gap<min){
                    rs=nums[i]+nums[b]+nums[e];
                    min=gap;
                }
                if(gap==0){
                    return target;
                }
                if(nums[i]+nums[b]+nums[e]>target){
                    e--;
                }else{
                    b++;
                }

            }

        }
        return rs;
    }
}
