package contest;

public class MaximumLengthofSubarrayWithPositiveProduct {
    //没想到O（n）的方法，只想到n方的方法
    //https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/discuss/819278/Java-O(n)-time-O(1)-space
    public int getMaxLen(int[] nums) {
        int zero=-1;
        int firstNegative=-1;
        int numOfNegative=0;
        int rs=0;
        for(int i=0;i<nums.length;i++){//还不太好想，是指以i为终点的这前面一段subarray里能找到最长的subarray
            if(nums[i]==0){
                zero=i;
                firstNegative=-1;
                numOfNegative=0;
                continue;
            }
            if(nums[i]<0){
                numOfNegative++;
                if(firstNegative==-1){
                    firstNegative=i;
                }
            }
            if(numOfNegative%2==0){
                rs=Math.max(i-zero,rs);
            }else{
                rs=Math.max(i-firstNegative,rs);
            }
        }
        return rs;
    }
}
