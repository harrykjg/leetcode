package SomeInterviews.moveworks;

public class MinimumReplacementstoSorttheArray2366 {
    //直接看答案,
    //https://leetcode.com/problems/minimum-replacements-to-sort-the-array/solutions/3978548/easy-to-understandfull-explanationdone-i-mtta/?envType=company&envId=moveworks&favoriteSlug=moveworks-all

    public long minimumReplacement(int[] nums) {
        int n = nums.length;
        int last = nums[n - 1];  // Initialize 'last' with the last element
        long ans = 0;  // Initialize the total operations count

        // Traverse the array in reverse order
        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] > last) {  // If the current element needs replacement
                int t = nums[i] / last;  // Calculate how many times the element needs to be divided
                if (nums[i] % last != 0) {
                    t++;  // If there's a remainder, increment 't'
                }
                //不是简单的从后往前看两个数差值的倍数-1，还需要把拆分后的那个数记录作为last，为啥是除以t而不是除以last？
                //这里其实不好想，比如15，6这样的，15这个得分成3分，是3，6，6呢还是4，5，6，还是5，5，5，其实是要5，5，5这样的尽量
                //平均的，因为前两种的最小值是3和4，对左边的都是不友好。再入7，3这个例子，7是分成1，3，3还是2，2，3，应该是2，2，3，因此就是除以t
                last = nums[i] / t;  // Update 'last' for the next comparison
                ans += t - 1;  // Add (t - 1) to 'ans' for the number of operations
            } else {
                last = nums[i];  // Update 'last' without replacement
            }
        }
        return ans;  // Return the total number of operations
    }
}
