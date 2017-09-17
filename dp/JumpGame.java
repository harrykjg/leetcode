package dp;

/**
 * Created by 502575560 on 9/10/17.
 */
public class JumpGame {
    //九章第二轮
    public boolean canJump(int[] nums) {
        int scope=nums[0];
        if(nums.length==1){
            return true;
        }
        for(int i=1;i<nums.length&&i<=scope;i++){
            scope=Math.max(i+nums[i],scope);
            if(scope>=nums.length-1){
                return true;
            }
        }
        return false;
    }
}
