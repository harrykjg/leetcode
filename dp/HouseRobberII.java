package dp;

/**
 * Created by 502575560 on 7/30/17.
 */
public class HouseRobberII {
    public static void main(String[] args){
        HouseRobberII hr=new HouseRobberII();
        int[] a={4,5,100};
        System.out.print(hr.rob(a));
    }
    public int rob(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        if(nums.length==2){
            return Math.max(nums[0],nums[1]);
        }
        int[] dp1=new int[nums.length];
        int[] dp2=new int[nums.length];
        dp1[0]=nums[0];//抢了第一家
        dp1[1]=Math.max(nums[0],nums[1]);
        dp2[0]=0;//没抢第1家
        dp2[1]=nums[1];//这样写了也不代表一定抢了第二家，只是说如果抢第二家的话就最多可以有抢到nums【1】
        for(int i=2;i< nums.length;i++){//思路就是dp1是记录可以抢第一家的,那么它就不能抢最后一家,dp2是不能抢第一家那么它就可以抢最后一家,最后比较两者谁大
            if(i==nums.length-1){
                dp1[i]=dp1[i-1];
            }else{
                dp1[i]=Math.max(dp1[i-1],dp1[i-2]+nums[i]);
            }
            dp2[i]=Math.max(dp2[i-1],dp2[i-2]+nums[i]);
        }
        return Math.max(dp1[dp1.length-1],dp2[dp2.length-1]);
    }
}
