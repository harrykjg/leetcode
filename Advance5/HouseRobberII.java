package Advance5;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by yufengzhu on 4/21/18.
 */
public class HouseRobberII {
    public static void main(String[] args){
        HouseRobberII hr=new HouseRobberII();
        int[] a={0,0};
        hr.rob(a);
    }
    //4／21／2018九章第二轮，改了一次typo居然也对了
    public int rob(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int[] dp=new int[nums.length+1];
        dp[0]=0;
        dp[1]=0;//不偷第一个
        for(int i=2;i<dp.length;i++){//可以偷最后一个
            dp[i]=Math.max(nums[i-1]+dp[i-2],dp[i-1]);
        }
        int rs=dp[dp.length-1];
        Arrays.fill(dp,0);
        dp[1]=nums[0];//这样写了也不代表一定抢了第1家，只是说如果抢第1家的话就最多可以有抢到nums【0】
        for(int i=2;i<dp.length-1;i++){//不可以偷最后一个
            dp[i]=Math.max(nums[i-1]+dp[i-2],dp[i-1]);
        }
        return Math.max(rs,dp[dp.length-2]);

    }

    //8/15/2018,也是改了2次accept，就是忘了第一个和最后一个不能同时偷
    public int rob2(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if (nums.length==1){
            return nums[0];
        }
        int[] dp1=new int[nums.length];
        int[] dp2=new int[nums.length];
        dp1[0]=nums[0];
        dp1[1]=Math.max(nums[0],nums[1]);
        dp2[0]=0;
        dp2[1]=nums[1];
        for(int i=2;i<nums.length;i++){
            if(i!=nums.length-1){
                dp1[i]=Math.max(dp1[i-1],nums[i]+dp1[i-2]);
            }
            dp2[i]=Math.max(dp2[i-1],nums[i]+dp2[i-2]);
        }
        return Math.max(dp1[dp1.length-2],dp2[dp2.length-1]);

    }
    //再试试优化空间复杂度,有点恶心，关键是定义了dp[i-2],dp[i-1],dp[i]这三个数所对应的变量之后，每扫一次还要更新前2个，并且只在nums。length>2时才行
    public int rob3(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if (nums.length==1){
            return nums[0];
        }
        if (nums.length==2){
            return Math.max(nums[0],nums[1]);
        }

        int one1=nums[0];
        int two1=Math.max(nums[0],nums[1]);
        int cur1=two1;
        int one2=0;
        int two2=nums[1];
        int cur2=0;
        for(int i=2;i<nums.length;i++){
            if(i!=nums.length-1){
                cur1=Math.max(two1,nums[i]+one1);
                one1=two1;
                two1=cur1;

            }
            cur2=Math.max(two2,nums[i]+one2);
            one2=two2;
            two2=cur2;
        }
        return Math.max(cur2,cur1);

    }
}
