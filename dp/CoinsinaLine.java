package dp;

/**
 * Created by 502575560 on 7/31/17.
 */
public class CoinsinaLine {
    public boolean firstWillWin(int n) {
        // write your code here
        if(n<=0){
            return false;
        }
        if(n<=2){
            return true;
        }
        boolean[] dp=new boolean[n];//dpde意义代表该点能不能被选手取得
        dp[0]=true;
        dp[1]=true;
        for(int i=2;i<n;i++){
            if(dp[i-1]&&dp[i-2]){//就拿n=3看,如果第一个和第二个子都能被取得,那么第三个肯定不行,否则肯定行
                dp[i]=false;
            }else{
                dp[i]=true;
            }

        }
        return dp[dp.length-1];

    }
}
