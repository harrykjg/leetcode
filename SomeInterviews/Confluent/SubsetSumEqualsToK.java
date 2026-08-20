package SomeInterviews.Confluent;

public class SubsetSumEqualsToK {
    /*
    Given a list of positive integers and a target number k, write a function that returns true if there exists a subset of nums that adds up to k, and false otherwise. Note that numbers can appear more than once in the list.

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <=
10
6
10
6

1 <= k <=
10
9
10
9

Example 1:

Input: nums = [12, 1, 61, 5, 9, 2], k = 24
Output: true
Explanation: There exists a subset [12, 9, 2, 1] that sums up to 24.

Example 2:

Input: nums = [3, 34, 4, 12, 5, 2, 2], k = 9
Output: true
Explanation: There exists a subset [4, 5] that sums up to 9.

Example 3:

Input: nums = [5, 3, 9, 2, 7], k = 6
Output: false
     */
    //01背包不可重复用元素，还是画图，二维数组，列是k，行是nums。还是从左到右从上到下
    public boolean findSubsetSum(int[] nums, int k) {
        // TODO: Implement findSubsetSum logic
        boolean[][] dp=new boolean[nums.length+1][k+1];
        for(int i=0;i<dp.length;i++){
            dp[i][0]=true;//必须初始化这个，开始还想不需要，因为dp[i][j]必然是看上一行dp[i-1]的，其实不对，由于下面for循环是从j=1开始的
            //因此dp[i-1][0]这个可能是没有被初始化的，举个例子nums=2，4，k=4画图可以发现
        }
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<=k;j++){
                dp[i][j]=dp[i-1][j];
                if(j>=nums[i-1]&&dp[i-1][j-nums[i-1]]){
                    dp[i][j]=true;
                }
            }
            if(dp[i][k]){
                return true;
            }
        }
        return false;

    }
}
