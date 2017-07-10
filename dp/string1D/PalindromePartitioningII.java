package dp.string1D;

/**
 * Created by 502575560 on 7/6/17.
 */
public class PalindromePartitioningII {
    public static void main(String[] args){
        System.out.println(minCut("abcccb"));
    }
//lintcode写的,下标还是很恶心,那个判断字串是否回文那里的下标已经很恶心了,而且lintcode的dp的意义和以前写的不一样,lintcode这个要dp最后一个值-1
    public static int minCut(String s) {
        // write your code here
        if(s.length()==1){
            return 0;
        }
        boolean[][] dp1=new boolean[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            dp1[i][i]=true;
            if(i>0&&s.charAt(i-1)==s.charAt(i)){
                dp1[i-1][i]=true;
            }
        }
        for(int i=3;i<=s.length();i++){
            for(int j=0;j<=s.length()-i;j++){
                int k=j+i;
                if(s.charAt(j)==s.charAt(k-1)&&dp1[j+1][k-1-1]){
                    dp1[j][k-1]=true;
                }
            }
        }
        int[] dp=new int[s.length()+1];
        for(int i=0;i<dp.length;i++){
            dp[i]=i;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<i;j++){
                if(dp1[j][i-1]){
                    dp[i]=Math.min(dp[i],dp[j]+1);
                }
            }
        }
        return dp[dp.length-1]-1;
    }
}
