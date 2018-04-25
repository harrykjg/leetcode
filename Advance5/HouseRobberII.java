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
}
