/**
 * Created by yufengzhu on 7/30/18.
 */
//array increasing subsequence/subarray
public class NumberofLongestIncreasingSubsequence {
    public static void main(String[] args){
        NumberofLongestIncreasingSubsequence nl=new NumberofLongestIncreasingSubsequence();
        int[] a={1,2,4,3,5,4,7,2};//
        System.out.print(nl.findNumberOfLIS(a));
    }
    //开始以为能写对longedtincreasingsubsequence的，其实他的dp意义还是理解错了
    //然后我觉得肯定可以边dp寻找最长子序的时候，记录碰到多少个最长的子序了，其实不是的，比如1,2,4,3,5,4,7,2，debug以下才知道，不好解释这里
    //https://www.cnblogs.com/Atanisi/p/7596135.html
    //这题相当不好理解，特别是count的dp
    public int findNumberOfLIS(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int[] dp=new int[nums.length];
        int[] count=new int[nums.length];
        dp[0]=1;
        count[0]=1;
        int len=1;
        int rs=0;
        for(int i=1;i<nums.length;i++){
            count[i]=1;//count[i]的意义是以i为结尾的最长上升子串有几个，如果这个i是很小的一个数，那么他自少count是1就是他自己，如果说这个nums[i]没有大过任何0到i-1之间的数，那么他就是1，如果大于了某个数
            for(int j=0;j<i;j++){  //那么再看下面的逻辑
                if(nums[i]>nums[j]){
                    if(dp[j]+1>dp[i]){
                        count[i]=count[j];//比如说1，1，7，现在到了如果i到了7，j为0，发现dp[j]+1>dp[i]，那么就说明以找到了现今为止最长的子串，那么7为结尾的子串出现了几次？那肯定是从1那里继承过来的，
                        dp[i]=dp[j]+1;     //然后j到了第二个1，此时dp[i]已经更新到最大值（2），发现7还是大于1，并且dp[j]+1等于最大值，说明最长子串又出现了，所以count[i]要加上从这个j继承过来的次数
                    }else if(dp[j]+1==dp[i]){
                        count[i]+=count[j];
                    }
                }else{//dp[i]的意义不是到i时最大上升子序的长度，比如1，2，4，1，4，如果说到了第二个1（index为3），那么dp【3】应该是1，而不是dp[2]的值（3），否则dp[4】就是4了，那就错了
                    dp[i]=Math.max(dp[i],1);
//                    count[i]=Math.max(1,count[j]);//这个开始写的这个就错了,应该在前面直接初始化为1
                }
                len=Math.max(len,dp[i]);
            }
        }
        for(int i = 0; i < nums.length; ++i) {
            if (dp[i] == len) {//发现当前i有最长子串时，rs加上以i结尾时有几个子串
                rs += count[i];
            }
        }
        return rs;
    }


}
