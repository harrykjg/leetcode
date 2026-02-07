package 灵神.DP.打家劫舍;

public class HouseRobberII213 {
    public static void main(String[] args) {

    }
    //看起来用1个dp数组不好做，空间是可以优化的
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
    //优化空间的，写的有点丑，就是第一遍循环从0找到倒数第二个数，第二个循环从1找到最后
    public int rob2(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int first=nums[0];
        int sec=Math.max(nums[1],nums[0]);


        for(int i=2;i<nums.length;i++){
            int cur=Math.max(nums[i]+first,sec);
            first=sec;
            sec=cur;
        }
        int rs1=first;//first就是倒数第二个数
        first=0;
        sec=nums[1];
        for(int i=2;i<nums.length;i++){
            int cur=Math.max(nums[i]+first,sec);
            first=sec;
            sec=cur;
        }
        return Math.max(rs1,sec);

    }
}
