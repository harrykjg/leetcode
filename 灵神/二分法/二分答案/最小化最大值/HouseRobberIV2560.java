package 灵神.二分法.二分答案.最小化最大值;

public class HouseRobberIV2560 {

    //不会
    //https://leetcode.cn/problems/house-robber-iv/solutions/2093952/er-fen-da-an-dp-by-endlesscheng-m558/
    //如果答案越小，既代表能偷的数目越小，既要找到一个答案刚好能偷k或以上个
    public int minCapability(int[] nums, int k) {

        int b=1;
        int e=0;
        for(int i=0;i<nums.length;i++){
            e=Math.max(e,nums[i]);
        }
        while (b+1<e){
            int m=e-(e-b)/2;
            boolean good=good(nums,m,k);
            if(good){
                e=m;
            }else {
                b=m;
            }
        }
        if(good(nums,b,k)){ //注意这和灵神的开区间写法的区别
            return b;
        }
        return e;

    }
    boolean good(int[]nums,int m,int k){//就是要检查取m的话，能否偷至少k家。
        int[] dp=new int[nums.length+2];//dp[i]的意义就是到i这能偷最多多少家，最后检测是否满足k家，满足说明这个m可能还可以缩小
        for(int i=0;i<nums.length;i++){
            if(nums[i]>m){
                dp[i+2]=dp[i+1];
                continue;
            }
            dp[i+2]=Math.max(dp[i+1],dp[i]+1);
        }
        return dp[dp.length-1]>=k;
    }

    //1/18/2026 一看就是dp，但是仔细想dp是做不了了的，dp[i]=取：dp【i-2】+1 or不取 dp【i-1】得到的只是偷了几个房子的个数，
    // 但是这里还需要记录素有偷房子的方案，从而找出每个方案的最大值，再取最小值，dp是做不了
    // 二分法没想到good方法也是用了dp
    //
}
