    package ArrayListAndNumbers;

public class NumberofSubarrayswithBoundedMaximum {
    //9/30/2021 不会，还是挺难想的
    //https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/discuss/117595/Short-Java-O(n)-Solution
    //https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/discuss/117723/Python-standard-DP-solution-with-explanation 思路
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int rs=0;
        int count=0;
        int b=0;
        for(int i=0;i<nums.length;i++){
            if (nums[i]>=left&&nums[i]<=right){
                rs+=i-b+1;
                count=i-b+1;
            }else if (nums[i]>right){
                count=0;
                b=i+1;
            }else{
                rs+=count;
            }
        }
        return rs;
    }
}
