package GameTheory;

/**
 * Created by yufengzhu on 10/9/18.
 */
public class GoogleCard {
    public static void main(String[] args){
        GoogleCard gc=new GoogleCard();
        System.out.print(gc.maxScore(new int[]{3,-1,-6,1}));
    }

    //谷歌面经
    //两个玩家A和B，每个人每次可以选择1，2或3张牌。从A玩家开始，目标是消耗完所有卡牌A能获取的最大分数。首先明确一个原则，任何一个玩家在选择卡牌时，目标都是要获取最大的分数。
    // 所以任何玩家面对任意多张卡牌时的目标都是获取最大分数。即A先选，A会计算面对剩余的i张卡牌取1，2或者3张获得最大分数。轮到B选择时，B会计算面对剩余的A取之后的卡牌取1，2或者3张怎么获得最大分数。
    //只能从头往后挨个选，不能跳跃

    public int canWin(int[] nums){//求最大分数，居然只要一层循环就行了
        int[]dp=new int[nums.length+1];
        int[] sum=new int[nums.length+1];
        for(int i=nums.length-1;i>=0;i--){//sum代表的是从i到最后的和
            sum[i]+=sum[i+1]+nums[i];
        }
        for(int i=nums.length-1;i>=0;i--){//dp也是从最后一位num开始，即dp的倒数第二位，而dp的倒数第一位为0
            if(i<nums.length){
                dp[i]=nums[i]+sum[i+1]-dp[i+1];//dp[i]的意义就是当前玩家取1个或者2个或者3个牌，能得到的最大的值，如果取只nums[i]的话，那么他能取的值就是i往右的所有的值的和，减去下一个玩家在i+1到最后这段数组能取的最大值
            }
            if(i+1<nums.length){
                dp[i]= Math.max(nums[i]+nums[i+1]+sum[i+2]-dp[i+2],dp[i]);
            }
            if(i+2<nums.length){
                dp[i]=Math.max(nums[i]+nums[i+1]+nums[i+2]+sum[i+3]-dp[i+3],dp[i]);
            }

        }
        return dp[0];


    }

    //别人的代码
    int maxScore(int[] cards) {
        int n = cards.length;
        int[] dp = new int[n + 1];
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += cards[n - i];

            int min = Integer.MAX_VALUE;
            if (i >= 3) min = Math.min(min, dp[i - 3]);
            if (i >= 2) min = Math.min(min, dp[i - 2]);
            if (i >= 1) min = Math.min(min, dp[i - 1]);

            dp[i] = sum - min;
        }

        return dp[n];
    }

}
