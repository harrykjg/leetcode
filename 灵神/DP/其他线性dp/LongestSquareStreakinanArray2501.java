package 灵神.DP.其他线性dp;

import java.util.Arrays;

public class LongestSquareStreakinanArray2501 {
    public static void main(String[] args) {

    }
    //自己写的就是sort再dp超时了,因为是n方
    //答案就是for loop用set判断某个数的平方是否存在
    //https://leetcode.com/problems/longest-square-streak-in-an-array/solutions/5968361/longest-square-streak-in-an-array-by-lee-uril/
    public int longestSquareStreak(int[] nums) {
        int[] dp=new int[nums.length];
        Arrays.fill(dp,1);
        Arrays.sort(nums);
        int max=1;
        for(int i=1;i<nums.length;i++){
            for (int j=0;j<i;j++){
                if(nums[i]==(long)nums[j]*nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }else if((long)nums[j]*nums[j]>nums[i]){
                    break;
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max==1?-1:max;
    }
}
