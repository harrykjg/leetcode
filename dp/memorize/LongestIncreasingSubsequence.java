package dp.memorize;

/**
 * Created by 502575560 on 7/31/17.
 */
//array //increasing subsequence/subarray
//应该也是记忆化搜索吧
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        // lintcode写的，对dp的理解有点偏差导致错了
        if(nums.length==0){
            return 0;
        }
        int[] dp=new int[nums.length];
        int rs=Integer.MIN_VALUE;
        dp[0]=1;
        for(int i=1;i<nums.length;i++){//自己之前想的是dp[i]存的是数组到i位最大的上升序列长度，比如1，2，3，1这个数组，dp［3］按我的想法就应该
            //是3了，其实不对，如果后面再加一个数字2，则发现2大于1那么dp［4］就是4了，所以说dp［i］存的应该是
            for(int j=i-1;j>=0;j--){    //这个位置的数与之前的数能组成的最长上升序列长度，而不是村的整个数组到这个位置的最长上升序列长度
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            dp[i]=Math.max(1,dp[i]);//默认值是1，因为就他一个数也算是长度为1的上升序列
            rs=Math.max(dp[i],rs);
        }


        return rs==Integer.MIN_VALUE?1:rs;
    }
    //九章第二轮
    public int lengthOfLIS2(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int[] dp=new int[nums.length];
        dp[0]=1;
        int rs=1;
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){//这个j的顺序和上次写的不一样了
                if(nums[i]>nums[j]){
                    dp[i]=Math.max(dp[j]+1,dp[i]);
                }else {
                    dp[i]=Math.max(1,dp[i]);//这个喀什直接写成dp[i]=1就错了,因为dp[i]在内层遍历j的时候可能已经有一个值大于1了
                }

            }
            rs=Math.max(rs,dp[i]);
        }
        return rs;
    }
//6/4/2021,一次过，感觉比以前写的还好一点。居然还有nlogn的解法，https://segmentfault.com/a/1190000003819886 看不懂
    public int lengthOfLIS3(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int[] dp=new int[nums.length];
        dp[0]=1;
        int max=1;
        for(int i=1;i<nums.length;i++){
            dp[i]=1;
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j]){//dp的意义是以nums【i】结尾（一定要选i）的最长长度，而不是以nums[i]结尾但不一定要选nums【i】的最长长度
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            max=Math.max(max,dp[i]);
        }
        return max;
    }
}
