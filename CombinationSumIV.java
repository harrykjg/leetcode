import java.util.Arrays;

/**
 * Created by 502575560 on 5/7/17.
 */
public class CombinationSumIV {
    public static void main(String[] args){
        int[] n={1,2,3,4};
        System.out.println(combinationSum42(n,4));


    }
    //这两个帖子都没仔细看
    //http://blog.csdn.net/qq508618087/article/details/52064134
    //http://www.cnblogs.com/Deribs4/p/5719474.html
    //自己写的,超时了,题目tag用动态规划
    static int rs=0;
    public static int combinationSum4(int[] nums, int target) {

        if(nums.length==0){
            return 0;
        }
        Arrays.sort(nums);
        helper(0,nums,target);
        return rs;
    }
    public  static void helper(int cur,int[] nums,int t){
        if(cur==t){
            rs++;
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(cur+nums[i]>t){
                break;
            }else{
                helper(cur+nums[i],nums,t);
            }
        }
    }

    //自己想的动态规划,列表找规律,开始还想得有点晕,后来找规律找出来了,d[i]的意义直接想不太好想.靠找规律,dp[i]代表i能被nums表示的个数,dp[i]就是dp[i-numms[0,1,2...]]的和
    public static int combinationSum42(int[] nums, int target) {   //比如target是4,候选数有1,2,3,那么dp[4]就是dp[4-1]+dp[4-2]+dp[4-3],如果候选数是1,2,3,5
        if(nums.length==0){                                //那么5>4所以就不考虑dp[4-5]了
            return 0;
        }
        Arrays.sort(nums);
        int[] dp=new int[target+1];
        dp[0]=1;//意义就是候选数有等于当前target的,就是有一个,如target是4而候选数也是4,那么dp[0]就是1
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<nums.length;j++){
                if(nums[j]>i){
                    break;
                }
                dp[i]+=dp[i-nums[j]];
            }
        }
        return dp[dp.length-1];
    }
//9/9/2018看着像背包问题，但是画图也找不到规律，写不出来
    public  int combinationSum43(int[] nums, int target) {
        if(nums.length==0){
            return 0;
        }
        Arrays.sort(nums);
        int[] dp=new int[target+1];
        dp[0]=1;
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<nums.length;j++){
                if(i-nums[j]>=0){
                    dp[i]+=dp[i-nums[j]];
                }
            }
        }
        return dp[dp.length-1];
    }
}
