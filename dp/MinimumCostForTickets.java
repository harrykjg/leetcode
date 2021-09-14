package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinimumCostForTickets {
    public static void main(String[] args){
        MinimumCostForTickets mc=new MinimumCostForTickets();
        int[] days={1,4,6,7,8,20};
        int[] costs={7,2,15};
        System.out.println(mc.mincostTickets(days,costs));
    }
    //8/18/2021.思路是有的，用的是Maximum Profit in Job Scheduling的思路.写起来不顺。下标也噁心
    //https://www.youtube.com/watch?v=BgWDlQy5JR0  别人的用dp 【i】是1-365的，好写多了
    //https://leetcode.com/problems/minimum-cost-for-tickets/discuss/227130/Java-DP-Solution-with-detailed-comment-and-explanation
    public int mincostTickets(int[] days, int[] costs) {//这个写法不好，看别人的
        int[] dp=new int[days.length+1];//加1是想dp【0】=0，后面天数减1，7，30的时候如果得出0则方便一些
        int max=Math.max(costs[0],Math.max(costs[1],costs[2]));
        for (int i=1;i<dp.length;i++){
            dp[i]=i*max;
            if (days[i-1]-30>=0){
                int j=0;
                while (j<i-1&&days[j]<=days[i-1]-30){//找30天以前最靠近30天的那一天
                    j++;
                }
                dp[i]=Math.min(dp[j]+costs[2],dp[i]);
            }else {
                dp[i]=Math.min(costs[2],dp[i]);
            }
            if (days[i-1]-7>=0){
                int j=0;
                while (j<i-1&&days[j]<=days[i-1]-7){
                    j++;
                }
                dp[i]=Math.min(dp[j]+costs[1],dp[i]);
            }else {
                dp[i]=Math.min(costs[1],dp[i]);
            }
            if (days[i-1]-1>=0){
                int j=0;
                while (j<i-1&&days[j]<=days[i-1]-1){
                    j++;
                }
                dp[i]=Math.min(dp[j]+costs[0],dp[i]);
            }else {
                dp[i]=Math.min(costs[0],Math.min(costs[1],costs[2]));
            }
        }
        return dp[dp.length-1];
    }
    //好的写法.dp[i]的意义是前i天，不管你是每天都要旅游还是只是中间有几天要旅游，所要花的钱，因此dp[i]=min(dp[i-1]+cost[0],dp[i-7]+cost[1],dp[i-30]+cost[2])
    //那么如果第i天不存在于days里咋办？那就说明第i天和第i-1天的花费是一样的！
    public int mincostTickets2(int[] days, int[] costs) {
        int[] dp=new int[366];//
        Set<Integer> set =new HashSet<>();
        for (int d:days){
            set.add(d);
        }
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for (int i=1;i<dp.length;i++){
            if (!set.contains(i)){
                dp[i]=dp[i-1];
                continue;
            }
            if (i-1>=0){
                dp[i]=Math.min(dp[i-1]+costs[0],dp[i]);
            }
            if (i-7>=0){
                dp[i]=Math.min(dp[i-7]+costs[1],dp[i]);
            }else {
                dp[i]=Math.min(costs[1],dp[i]);
            }
            if (i-30>=0){
                dp[i]=Math.min(dp[i-30]+costs[2],dp[i]);
            }else {
                dp[i]=Math.min(costs[2],dp[i]);
            }
        }
        return dp[dp.length-1];
    }
}
