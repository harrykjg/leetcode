package 灵神.DP.划分型DP;

public class PalindromePartitioningII132 {
    public static void main(String[] args) {
        System.out.println(minCut("aab"));
    }
    //debug才想起来还要一个boolean数组记录i到j是否是回文,但是判断回文的dp数组我以为是用二维的，其实是把判断回文和回文最小切割数弄混了导致dp的意义不对。
    //
    public static int minCut(String s) {
        int[] dp=new int[s.length()];
        boolean[][] memo=new boolean[s.length()][s.length()];
        for(int i=0;i<dp.length;i++){
            memo[i][i]=true;
            dp[i]=i;//dp也初始化了
            if(i+1<dp.length){
                if(s.charAt(i)==s.charAt(i+1)){
                    memo[i][i+1]=true;
                }else{
                }
            }
        }
        for (int i=2;i<s.length();i++){
            for(int j=0;j+i<dp.length;j++){
                if(s.charAt(j)==s.charAt(j+i)&&memo[j+1][i+j-1]){
                    memo[j][i+j]=true;
                }
            }
        }
        for(int i=1;i<dp.length;i++){//思路就是外层i指0到i这个字符串要切几刀,那么默认一个字符切一道即dp[i]=i,然后内层j属于0到i,看j到i这之间是回文的话,就更新dp[i]
            for (int j=0;j<=i;j++){//等于号容易漏
                if(memo[j][i]){
                    if(j==0){
                        dp[i]=0;
                        break;
                    }else{
                        dp[i]=Math.min(dp[j-1]+1,dp[i]);//后面j到i这一段是回文，则是加上0到j-1这一段的最小切割数
                    }
                }
            }
        }

        return dp[dp.length-1];
    }
}
