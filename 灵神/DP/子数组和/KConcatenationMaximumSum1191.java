package 灵神.DP.子数组和;

public class KConcatenationMaximumSum1191 {
    public static void main(String[] args) {
        int[] n={-5,-2,0,0,3,9,-2,-5,4};
        System.out.println(kConcatenationMaxSum(n,5));
    }

    //就是分类讨论k=1,2，>=3的情况。情况有点多不好搞。dp方面就是maximumsubarray比较简单。因此这题我就没研究了，以下是能过20多个test的代码。有些情况是不对的有些事对的
    //https://leetcode.cn/problems/k-concatenation-maximum-sum/solutions/3675237/fu-yong-53-ti-dai-ma-jian-ji-xie-fa-pyth-qmtp/
    public static int kConcatenationMaxSum(int[] arr, int k) {
        if(arr.length==0){
            return 0;
        }
        int mod=(int)Math.pow(10,9)+7;

        int[] dp=new int[arr.length];
        int rs=0;
        dp[0]=Math.max(arr[0],0);
        rs=dp[0];
        int sum=arr[0]%mod;
        for(int i=1;i<dp.length;i++){
            if(dp[i-1]>=0){
                dp[i]=(dp[i-1]%mod+arr[i]%mod)%mod;
            }else{
                dp[i]=arr[i]%mod;
            }
            sum+=arr[i]%mod;
            rs=Math.max(dp[i],rs);
        }
        if(rs<=0){
            return 0;
        }
        if(rs>0){
            if(sum>0&&arr[0]>=0&&arr[arr.length-1]>=0){//这个情况错了。-5,-2,0,0,3,9,-2,-5,4
                return Math.max(rs, (int)((long)rs*k)%mod);
            }else if(sum<=0&&arr[0]>=0&&arr[arr.length-1]>=0){//sum<0的情况，如1,1-10,1，-2，1的情况
                int[] arr2=new int[arr.length*2];
                for( int i=0;i<arr2.length;i++){
                    arr2[i]=arr[i%arr.length];
                }
                return maxSubArray(arr2);
            }else{//-10,3,-10的情况
                return rs;
            }
        }else{
            return 0;
        }
    }
    static int maxSubArray(int[] nums) {
        int[] dp=new int[nums.length];
        dp[0]=nums[0];
        int rs=dp[0];
        for(int i=1;i<dp.length;i++){
            if(dp[i-1]>=0){//等于0也要加，如1,0,0,1，可连起来
                dp[i]=dp[i-1]+nums[i];//必须加nums【i】就算他是负数,那样的话，dp可能就是中间有最大值，而不是dp的最后一位了
            }else{
                dp[i]=nums[i];
            }
            rs=Math.max(rs,dp[i]);
        }
        return rs;
    }
}
