package 灵神.DP.博弈;

public class DivisorGame2015 {
    static void main() {

    }
    //总结规律的话就是双数能赢
    //看dp的写法 https://leetcode.cn/problems/divisor-game/solutions/369921/ji-yi-hua-di-gui-dong-tai-gui-hua-shu-xue-fang-fa-/
    public boolean divisorGame(int n) {
        if(n==1){
            return false;
        }
        boolean[] dp=new boolean[n+1];
        dp[1]=false;
        dp[2]=true;
        for(int i=3;i<dp.length;i++){

            for(int j=1;j<i/2+1;j++){
                if(i%j==0&&dp[i-j]==false){//如果alice找到bob必须会输的一个case，那么alice就选这个
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
