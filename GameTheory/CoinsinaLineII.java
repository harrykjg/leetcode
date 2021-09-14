package GameTheory;

/**
 * Created by 502575560 on 7/31/17.
 */
public class CoinsinaLineII {
    //题目说的是first选手取得的总值,不会
    //http://blog.csdn.net/u014470581/article/details/52944100
    //http://www.cnblogs.com/grandyang/p/5864323.html
    //https://segmentfault.com/a/1190000007400162
    //代码是抄的,烦,懒得写
    public boolean firstWillWin(int[] values) {
        if(values.length<=2){
            return true;
        }
        int n=values.length;
        int sum=0;
        int[] dp=new int[values.length+1];//注意这里长度要+1，并且dp【n】=0
        dp[n - 1] = values[n - 1];
        dp[n - 2] = values[n - 2] + values[n - 1];
        dp[n - 3] = values[n - 3] + values[n - 2];
        for (int i = n - 4; i >= 0; --i) {
            dp[i] = Math.max(values[i] + Math.min(dp[i + 2], dp[i + 3]), values[i] + values[i + 1] + Math.min(dp[i + 3], dp[i + 4]));
        }
        for (int d : values) {
            sum += d;
        }
        return sum - dp[0] < dp[0];

    }

    //6/19/2021 .还是不会，看以前的
    public boolean firstWillWin2(int[] values) {
        int usm=0;
        for (int v:values){
            usm+=v;
        }
        int[] dp=new int[values.length];
        int len=values.length;
        dp[len-1]=values[len -1];
        dp[len-2]=values[len-1]+values[len-2];
        dp[len-3]=values[len-3]+values[len-2];//为啥是这样，不太好想。就是我现在在倒数第三位上，我这能从这里取2个或者1个我肯定取2个而不是取一个，
                                            // 因为coin肯定是正的
    }
}
