package dp.string2D;

/**
 * Created by 502575560 on 7/7/17.
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String A, String B) {
        // lintcode
        //http://blog.csdn.net/waltonhuang/article/details/52032463
        //http://blog.chinaunix.net/uid-26548237-id-3374211.html   看它的图,挂了
        int[][] dp=new int[A.length()+1][B.length()+1];
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(A.charAt(i-1)==B.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    //还是不太熟
    public int longestCommonSubsequence2(String A, String B) {
        int[][] dp=new int[A.length()+1][B.length()+1];
        char[] ch1=A.toCharArray();
        char[] ch2=B.toCharArray();
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if(ch1[i-1]==ch2[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    //还是要画图才好理解,下标注意
    public int longestCommonSubsequence3(String A, String B) {
        int[][] dp=new int[A.length()+1][B.length()+1];
        for(int i=1;i<A.length();i++){
            for(int j=1;j<B.length();j++){
                if(A.charAt(i-1)==B.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    //9/24/2018,subsequence是np hard问题，和substring不一样,要总结,试试例子eaccb和abcd.  注意和is subsequence对比
    public int longestCommonSubsequence4(String A, String B) {
        int[][] dp=new int[A.length()+1][B.length()+1];//还想着能不能优化空间，是不行的，看图理解
        for(int i=1;i<=A.length();i++){
            for(int j=1;j<=B.length();j++){
                if(A.charAt(i-1)==B.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];

    }
//6/4/2021,没画图写不对，
    public int longestCommonSubsequence5(String A, String B) {
        int[][] dp=new int[A.length()+1][B.length()+1];
        for(int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if(B.charAt(j-1)==A.charAt(i-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);//这里不好理解，为啥是i-1或者j-1，靠画图理解
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
