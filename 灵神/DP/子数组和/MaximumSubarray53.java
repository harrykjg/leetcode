package 灵神.DP.子数组和;

public class MaximumSubarray53 {
    public static void main(String[] args) {
        int[] n={-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(n));
    }
    /*
    注意：答案不是 f[n−1]，这仅仅表示以 nums[n−1] 结尾的最大子数组和。或者说 f[n−1] 意味着 nums[n−1] 一定要选，但这不一定正确。
   问：为什么不能用「选或不选 nums[i]」的思路做？
   答：选或不选无法保证子数组是连续的。选或不选适用于子序列问题（例如 0-1 背包问题），对于子数组问题，更适合用「拼接」的思路，即如果 nums[i] 左边的子数组元素和是负的，就不用和左边的子数组拼在一起了。
https://leetcode.cn/problems/maximum-subarray/solutions/2533977/qian-zhui-he-zuo-fa-ben-zhi-shi-mai-mai-abu71/
     */
    public static int maxSubArray(int[] nums) {
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        int rs=dp[0];
        for(int i=1;i<dp.length;i++){
            if(dp[i-1]>=0){//等于0也要加，如1,0,0,1，可连起来
                dp[i]=dp[i-1]+nums[i];//必须加nums【i】就算他是负数,那样的话，dp可能就是中间有最大值，而不是dp的最后一位了
            }else{
                dp[i]=nums[i];
            }
            rs=Math.max(rs,dp[i]);
        }
        return rs;
    }
}
