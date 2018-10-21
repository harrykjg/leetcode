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
        int[] dp=new int[values.length];
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
}
