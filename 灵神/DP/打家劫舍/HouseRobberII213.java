package 灵神.DP.打家劫舍;

public class HouseRobberII213 {
    public static void main(String[] args) {

    }
    public int rob(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int[] dp1=new int[nums.length+1];
        dp1[1]=nums[0];//可以偷第一个
        int[] dp2=new int[nums.length+1];//第一个肯定没偷

        for(int i=2;i<dp1.length;i++){
            dp1[i]=Math.max(dp1[i-2]+nums[i-1],dp1[i-1]);
            dp2[i]=Math.max(dp2[i-2]+nums[i-1],dp1[i-1]);
        }
        return Math.max(dp1[dp1.length-2],dp2[dp2.length-1]);
    }
}
