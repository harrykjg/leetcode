package 灵神.贪心;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinimumCosttoMakeArrayEqual2448 {
    static void main() {

    }
    //1/4/2026枚举是n方不行。答案是二分法或者加权平均数（不好想，还要知道知道怎么取加权中位数）
    //这的二分法还不是一般的二分法，是需要比较mid和mid+1的值才知道target应该往左还是右移动，因为这个函数的形状是最小值附近呈 “∪” 形，
    //而不是单调的。但是写起来到是很容易
    //https://leetcode.com/problems/minimum-cost-to-make-array-equal/solutions/2734162/javacpython-binary-search-by-lee215-t8kt/

    public long minCost(int[] nums, int[] cost) {

       long left=1;
       long right=1;
       for(int i:nums){
           left=Math.min(left,i);
           right=Math.max(right,i);
       }
       while (left+1<right){
           long m=right-(right-left)/2;
           long c1=helper(m,nums,cost);
           long c2=helper(m+1,nums,cost);
           if(c1<c2){//居然这样就行了
               right=m;
           }else{
               left=m;
           }
       }
       long c1=helper(left,nums,cost);
       long c2=helper(right,nums,cost);
       if(c1>c2){
           return c2;
       }
       return c1;
    }
    long helper(long target,int[] nums,int[] costs){
        long rs=0;
        for (int i=0;i<nums.length;i++){
            rs+=(long)Math.abs(nums[i]-target)*costs[i];
        }
        return rs;
    }

}
