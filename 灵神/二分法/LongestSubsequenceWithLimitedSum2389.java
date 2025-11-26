package 灵神.二分法;

import java.util.Arrays;

public class LongestSubsequenceWithLimitedSum2389 {
    //自己写的应该不是最好的方法
    //https://leetcode.cn/problems/longest-subsequence-with-limited-sum/solutions/1781111/fei-bao-li-zuo-fa-qian-zhui-he-er-fen-by-ny4m/ 他是sort之后求prefixsum
    //然后在这个prefixsum基础上找某个数的upperbound就是答案，upperbound的写法和lowerbound的区别就是这里是nums【mid】>key的话right=mid，而lowerbound是nums【mid】<key...
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[] rs=new int[queries.length];
        for(int i=0;i<queries.length;i++){

            int e=0;
            int cur=0;

            while (e<nums.length&&cur+nums[e]<=queries[i]){
                cur+=nums[e];
                e++;
            }
            rs[i]=e;

        }
        return rs;

    }
}
