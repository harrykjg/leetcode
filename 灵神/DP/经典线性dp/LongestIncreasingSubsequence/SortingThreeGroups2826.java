package 灵神.DP.经典线性dp.LongestIncreasingSubsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingThreeGroups2826 {
    public static void main(String[] args) {

        List<Integer> al= Arrays.asList(2,3,1,2);
        System.out.println(minimumOperations(al));
    }
    //写不出来，画图找规律的话遇到2,3,1,2这个例子不好写。我还想可能是单调栈做，问了gpt说单调栈只能用来局部最优就能保证全局最优的，这里局部最优（单调栈就是看见一个原素就立马按大小决定是否入栈）
    //无法获得全局最优解。
    //问了gpt，如果题目没有说是nums只有1,2,3的话，可以先求longest increasing subsequence，再把长度减去LIS就行了
    public static int minimumOperations(List<Integer> nums) {
        int[] dp=new int[nums.size()];
        Arrays.fill(dp, 1);
        int lis=1;
        for (int i=1;i<nums.size();i++){

            for(int j=0;j<i;j++){
               if(nums.get(i)>=nums.get(j)){
                   dp[i]=Math.max(dp[i],dp[j]+1);
               }
            }
            lis=Math.max(lis,dp[i]);

        }
        return nums.size()-lis;
    }
    /*
    这是gpt写的好的方法，既遇到1的话，1只能接在以1结尾的序列后面，2只能接到1或2结尾的非递减子序列，3能借到1或2或3后面
    class Solution {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        int dp1 = 0, dp2 = 0, dp3 = 0; // 分别是以 1/2/3 结尾的最长非递减子序列长度

        for (int x : nums) {
            if (x == 1) {
                dp1 = dp1 + 1;
            } else if (x == 2) {
                dp2 = Math.max(dp1, dp2) + 1;
            } else { // x == 3
                dp3 = Math.max(dp1, Math.max(dp2, dp3)) + 1;
            }
        }

        int keep = Math.max(dp1, Math.max(dp2, dp3));
        return n - keep;
    }
}
     */
}
