package Advance5;

/**
 * Created by yufengzhu on 4/28/18.
 */
public class CoinsinaLine {
    //4/28/2018，dp的意义没想清楚，dp[i-1]和dp[i-2]要同时是true才能判断dp[i]是false，开始写成dp[i-1]||dp[i-2]就不对了
    public boolean firstWillWin(int n) {
        if(n<=0){
            return false;
        }
        if(n<=2){
            return true;
        }
        boolean[] dp=new boolean[n];
        dp[0]=true;
        dp[1]=true;
        for(int i=2;i<n;i++){
            if(dp[i-1]&&dp[i-2]){
                dp[i]=false;
            }else{
                dp[i]=true;
            }
        }
        return dp[dp.length-1];
    }
}
