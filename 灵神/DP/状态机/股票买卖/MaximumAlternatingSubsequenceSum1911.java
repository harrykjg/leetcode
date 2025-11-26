package 灵神.DP.状态机.股票买卖;

public class MaximumAlternatingSubsequenceSum1911 {
    public static void main(String[] args) {
        int[] nums={4,2,5,3};
        System.out.println(maxAlternatingSum(nums));
    }
    //写不出来，试着先写dfs，想着从dfs再推出dp。但是看他下面有个评论就这这样的但是dfs是从后往前推的
    //https://leetcode.cn/problems/maximum-alternating-subsequence-sum/solutions/846375/dong-tai-gui-hua-by-endlesscheng-d92a/

    int rs=0;
    public static long maxAlternatingSum(int[] nums) {
        return dfs(0,0,false,nums);
    }

    static int dfs(int b,int cursum,boolean odd,int[] nums){
        if(b>=nums.length){
            return cursum;
        }
        if(odd){
            return Math.max(dfs(b+1,cursum-nums[b],false,nums),dfs(b+1,cursum,true,nums));//开始这里不选的时候odd写错了，跳过时应该odd就不变了，因为odd指的是当前i
        }else{
            return Math.max(dfs(b+1,cursum+nums[b],true,nums),dfs(b+1,cursum,false,nums));
        }
    }
}
//然后看dfs的入参，就是b和odd和cursum，自然就想到dp[i][j]=cursum其中i是b，j是0或1代表奇偶。这我我选0是偶，1是奇.dp意义是到第i位的最大奇偶差,而0代表这位取的话就是偶数
//dp[i][0]=Max(dp[i-1][1]+nums[i],dp[i-1][0])
//dp[i][1]=max(dp[i-1][0]-nums[i],dp[i-1][1] 看起来应该对吧，但我想的是从0开始的既buttom up，但gpt说我这个0,1的意义其实是持有和非持有，而不是奇偶性
/*
gpt说一般top down（既从后往前写dp），会用在“从状态i出发会得到什么结果”的题上，还是太深奥
 */
