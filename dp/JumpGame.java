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
    //05/21/2020,居然一次过,写的是while循环，以前没这样写过
    public boolean canJump2(int[] nums) {
        if(nums.length<=1){
            return true;
        }
        int can=nums[0];
        int i=0;
        while (i<=can){
            if(i>=nums.length-1){
                return true;
            }
            can=Math.max(can,i+nums[i]);
            i++;
        }
        return false;

    }
//6/4/2021,改了一下对了
    public boolean canJump3(int[] nums) {
        if(nums.length==1){
            return true;
        }

        int max=nums[0];
        for(int i=0;i<nums.length&&i<=max;i++){
            max=Math.max(nums[i]+i,max);
            if(max>=nums.length-1){//开始没写-1就错了
                return true;
            }
        }
        return false;

    }
}
