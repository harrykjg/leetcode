package SomeInterviews.snowflake;

import java.util.List;

public class UnequalElements {
    /*
You are given an integer array elements. You may select any subsequence (i.e., delete zero or more elements
without changing the order of the remaining elements).
Return the maximum possible length of a subsequence such that there are at most k adjacent pairs with different values.
In other words, for a subsequence [x₀, x₁, …, xₘ₋₁], the number of indices i with 0 ≤ i < m - 1 and xᵢ ≠ xᵢ₊₁ must be at most k.
Constraints:
1 ≤ elements.length ≤ 10³
1 ≤ k ≤ elements.length
1 ≤ elements[i] ≤ 10⁹
Example 1:
Input: elements = [1, 1, 2, 3, 2, 1], k = 2
Output: 5
Explanation: The longest valid subsequence is [1, 1, 2, 2, 1] and it has exactly two unequal adjacencies: [1, 2] and [2, 1].
Example 2:
Input: elements = [1, 2, 3, 3, 4, 3, 2, 2, 2, 1, 1], k = 2
Output: 8
     */
    //挺难的，先不考虑dp
    int ans = 0;
    public int maxLenBrute(int[] nums, int k) {
        dfs(nums, 0, k, -1, 0);
        return ans;
    }
    // index: 当前考虑 nums[index]
// remain: 还剩多少次相邻不同机会
// last: 上一个选的数
// len: 当前 subsequence 长度
    private void dfs(int[] nums, int index, int remain, int last, int len) {
        if (index == nums.length) {
            ans = Math.max(ans, len);
            return;
        }
        // 选择1：不选 nums[index]
        dfs(nums, index + 1, remain, last, len);
        // 选择2：选 nums[index]
        int x = nums[index];
        if (len == 0) {
            // 第一个元素随便选，不产生相邻不同
            dfs(nums, index + 1, remain, x, len + 1);
        } else {
            if (x == last) {
                // 和上一个选的值一样，不消耗 remain
                dfs(nums, index + 1, remain, x, len + 1);
            } else if (remain > 0) {
                // 和上一个选的值不同，消耗一次
                dfs(nums, index + 1, remain - 1, x, len + 1);
            }
        }
    }
}
