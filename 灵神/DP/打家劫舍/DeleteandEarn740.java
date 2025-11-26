package 灵神.DP.打家劫舍;

import java.util.HashMap;

public class DeleteandEarn740 {
    public static void main(String[] args) {
        int[] n={1,1,1,2,4,5,5,5,6};
        System.out.println(deleteAndEarn(n));
    }
    //自己想出来的，和答案差不多。思想就是把nums【i】出现过多少次统计出来，并且找出最大的值。然后每一个自然数【1，max】循环，dp【i】的意义就是i点能得的最大值，并不是说一定要取i点。
    //那么dp【i】的值就有两种可能，1：dp【i-2】加取i。2：dp【i-1】，既不取i。
    public static int deleteAndEarn(int[] nums) {

        HashMap<Integer,Integer> map=new HashMap<>();
        int max=0;
        for (int i:nums){
            max=Math.max(max,i);
            map.put(i,map.getOrDefault(i,0)+1);
        }
        if(nums.length<=1){
            return nums[0];
        }
        int[] dp=new int[max+1];
        dp[1]=map.get(1)==null?0:map.get(1);
        if(dp.length<2){
            return dp[1];
        }
        dp[2]=Math.max(map.get(2)==null?0:map.get(2)*2,dp[1]);
        int rs=0;
        for(int i=2;i<dp.length;i++){//关键是这里是按一个一个自增的，不是按nums里的元素的
            if(!map.containsKey(i)){
                dp[i]=dp[i-1];
                continue;
            }

            dp[i]=Math.max(dp[i-2]+map.get(i)*i,dp[i-1]);
            rs=Math.max(rs,i);

        }
        return rs;
    }
}
