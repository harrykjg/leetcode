package SomeInterviews.verkarda;

import java.util.Arrays;
import java.util.Collections;

public class ClosestSum {
    /*
    给一组数，要求分成2 parts，使得| sum(part1) - sum(part2)| 差最小
    ，求这个最小差，比如[2, 3, 10, 7, 5], 分成[3,10], [2,7,5] 最小差为1，返回1
     */
    //还挺难想的
    //参考gpt，像背包问题，是0/1背包，不能重复使用元素。就是找是否能达成sum/2，因为sum/2肯定是小于等于另一半的。那么差值肯定最小。
    public int minDifference(int[] nums) {
        int rs=Integer.MAX_VALUE;
        int sum=0;
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        Arrays.sort(nums);
        int target=sum/2;
        boolean[] dp=new boolean[target+1];
        dp[0]=true;
        for (int i=0;i<nums.length;i++){
            //这里写j>=nums[i]也行，那么里面的if j-nums[i]>=0就不用写了
            for (int j=target;j>=0;j--){//这里注意，这是0/1背包即每个数字只能用一次，因此必须从后往前，否则同一个数字可能被用多次
                //这里要从后往前的话是因为他压缩成1维dp了，二维的话还是从0往target走的吧
                if(j-nums[i]>=0&&dp[j-nums[i]]){
                    dp[j]=true;
                }
            }
        }
        for (int i=dp.length-1;i>=0;i--){
            if(dp[i]){
                return (sum-i)-i;
            }
        }
        return rs;

    }
}
