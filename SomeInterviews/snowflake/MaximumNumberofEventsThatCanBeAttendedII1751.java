package SomeInterviews.snowflake;

public class MaximumNumberofEventsThatCanBeAttendedII1751 {
    //不会，https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended-ii/description/
    //dp[i][k]代表才前i个event最多参加k个的时候的最大值，因此dp[i][k]= 如果参加第i个event：=dp[j][k-1]+event[i]的值，其中j为以i event开始
    //天之前的那个最大的结束日期的event，并且只参加了k-1个event的值。或者不参加第i个event，即dp[i-1][k]的值。
    public int maxValue(int[][] events, int k) {

    }
}
