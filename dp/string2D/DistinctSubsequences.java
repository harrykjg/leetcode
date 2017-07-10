package dp.string2D;

/**
 * Created by 502575560 on 7/7/17.
 */
public class DistinctSubsequences {
    public static void main(String[] args){
        System.out.println(numDistinct("b","b"));
    }
    //lintcode上再做,还是不会,dp的思维不太好想,画图的话试着填数据再推通向也不太容易,因为涉及到初始化,图是S为列T为行或者反过来其实都可以,只是通向推出来不一样
    ////http://blog.csdn.net/linhuanmars/article/details/23589057 他们两都是S为列,我试试T为列
//http://www.blogjava.net/menglee/archive/2013/12/31/408231.html
    public static int numDistinct(String S, String T) {
        if(S.length()==0){
            return 0;
        }
        if(T.length()==0){
            return 1;
        }
        int[][] dp=new int[T.length()+1][S.length()+1];
        for(int i=0;i<dp[0].length;i++){
            dp[0][i]=1;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(T.charAt(i-1)!=S.charAt(j-1)){
                    dp[i][j]=dp[i][j-1];
                }else{
                    dp[i][j]=dp[i][j-1]+dp[i-1][j-1];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
