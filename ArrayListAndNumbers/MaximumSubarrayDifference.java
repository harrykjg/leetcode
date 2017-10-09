package ArrayListAndNumbers;

/**
 * Created by 502575560 on 10/8/17.
 */
public class MaximumSubarrayDifference {

    //http://www.jiuzhang.com/solution/maximum-subarray-difference/

    //leetcode没有,自己的思路就是分成2个dp,一个找最大的subarray的和,一个找最小的,和maximum subarray ii一样的思路,结果看了答案要四个dp,即从左往右的要记录
    //2个dp最大和最小,从右往左也要2个dp,懒得写了
    public int maxDiffSubArrays(int[] nums) {
        // write your code here
        if(nums.length==0){
            return 0;
        }
        int[] dp1=new int[nums.length];
        int[] dp2=new int[nums.length];
        int pre=nums[0];
        dp1[0]=pre;
        for(int i=1;i<nums.length;i++){
            if(pre>0){
                dp1[i]=Math.max(pre+nums[i],dp1[i-1]);
                pre=nums[i]+pre;
            }else{
                dp1[i]=Math.max(nums[i],dp1[i-1]);
                pre=nums[i];
            }
        }
        pre=nums[nums.length-1];
        dp2[dp2.length-1]=pre;
        for(int i=nums.length-2;i>=0;i--){
            if(pre<0){
                dp2[i]=Math.min(pre+nums[i],dp2[i+1]);
                pre=nums[i]+pre;
            }else{
                dp2[i]=Math.min(nums[i],dp2[i+1]);
                pre=nums[i];
            }
        }
        int rs=Integer.MIN_VALUE;
        for(int i=0;i<dp1.length-1;i++){
            rs=Math.max(Math.abs(dp1[i]-dp2[i+1]),rs);
        }
        return rs;

    }
}
