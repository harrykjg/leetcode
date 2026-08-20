package SomeInterviews.snap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistinctProductOfPrimeSubsets {
    /*
    Distinct Products Of Prime Subsets
Medium
Backtracking


Interview Stages
Screening
Onsite
Frequency
Asked By
SNAPCHAT-icon
Last Reported
2 months ago

AI Insights
(This question is a variation of the LeetCode question 90. Subsets II. If you haven't completed that question yet, it is recommended to solve it first.)

Given an array primes containing n integers, where each value is a prime number (with possible duplicates), find all distinct products that can be formed by multiplying the elements of any non-empty subset of the array.

Constraints:

1 ≤ n ≤ 15
2 ≤ primes[i] ≤ 50 (every primes[i] is a prime number)
The output must not contain duplicate products.
Example 1:

Input: primes = [2, 3]
Output: [2, 3, 6]
Explanation: All non-empty subsets are [2], [3], [2, 3]. Their products are 2, 3, 6.

Example 2:

Input: primes = [2, 2, 3]
Output: [2, 3, 4, 6, 12]

Example 3:

Input: primes = [5]
Output: [5]
     */
    //不知道他hack2hire的答案为什么那样写,开始还以为是只选两个数相乘，其实是一直乘下去
    //还不是很好写，貌似用memo不好写？
    public List<Integer> distinctProducts(int[] primes) {
        List<Integer> rs=new ArrayList<>();
        Arrays.sort(primes);
        boolean[] memo=new boolean[primes.length];
        memo[0]=true;
        dfs(0,1,primes,rs);//要一开始用carry=1而不是第一个元素，否则自己乘自己的就没算了
        return rs;
    }
    void dfs(int b,int carry,int[] primes,List<Integer> rs){
        for (int i=b;i< primes.length;i++){
            if(i>b&&primes[i]== primes[i-1]){
                continue;
            }
            int cur=carry *primes[i];
            rs.add(cur);
            if(i+1<primes.length){
                dfs(i+1,cur,primes,rs);
            }
        }

    }
}
