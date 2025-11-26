package 灵神.DP.经典线性dp.LongestCommonSubsequence;

public class DistinctSubsequences115 {
    public static void main(String[] args) {
        System.out.println(numDistinct("babgbag","bag"));
    }

    /*
           0 r a b b b i
         0 1
         r   1 1 1 1 1 1
         a   0 1 1 1 1 1
         b   0 0 1 2 3 3
         b
         i

s打横 t打竖好像不太好写
           0 b a b g b a g
         0 1
         b   1 1 1 1 1 1 1
         a   0 1 1 1 1 2 2
         g   0 0 0 1 1 1

s打竖 t打横好写些
         0 b a g
      0  1
      b  1 1 0 0
      a  1 1 1 0
      b  1 2 1 0
      g  1 2 1 1
      b  1 3 1 1
      a  1 3 4 1
      g  1 3 4 5
          char相等：  dp[i][j]=dp[i-1][j-1]+dp[i-1][j]  第一部分dp[i-1][j-1]的意义是取他，抵消了t的这一位。第二部分dp[i-1][j]意义是是不取他，也可以表达几个t
              不等： 取不取他都无所谓，既dp【i-1】【j】
     */

    public static int numDistinct(String s, String t) {
        int[][] dp=new int[s.length()+1][t.length()+1];
        for (int i=0;i<dp.length;i++){
            dp[i][0]=1;
        }

        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
