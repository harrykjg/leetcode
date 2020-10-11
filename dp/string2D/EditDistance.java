package dp.string2D;

/**
 * Created by 502575560 on 7/7/17.
 */
public class EditDistance {
    public static void main(String[]a){
        EditDistance oe=new EditDistance();
        System.out.println(oe.minDistance("ab","acb"));
    }
    //http://blog.163.com/gjx_12358@126/blog/static/895363452014232191498/
    //lintcode写的,但是还是不太顺,还是dp的理解问题,还有index要注意
    public int minDistance(String word1, String word2) {
        int[][] dp=new int[word2.length()+1][word1.length()+1];
        for(int i=0;i<dp[0].length;i++){//开始初始化的时候写成了整个二维数组都初始化了
            dp[0][i]=i;
        }
        for(int i=0;i<dp.length;i++){
            dp[i][0]=i;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(word1.charAt(j-1)==word2.charAt(i-1)){
                    dp[i][j]=dp[i-1][j-1];//这里开始写成math.min(dp[i][j],dp[i-1][j-1])，那就错了，i，j不可能是0了，所以dp[i][j]还没初始化，是0。
                    // https://www.jiuzhang.com/solutions/edit-distance/,看他第二个写法那样就对
                }else{
                    dp[i][j]=Math.min(dp[i-1][j]+1,Math.min(dp[i][j-1]+1,dp[i-1][j-1]+1));
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

//还是差一点,没彻底想清楚dp加减替换的操作,比如dp[i-1][j]的含义我就卡住了,看九章ppt的advance dp下的18页的图,可以理解kar怎么转换成ma
    public int minDistance2(String word1, String word2) {
        int[][] dp=new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<dp.length;i++){
            dp[i][0]=i;
        }
        for (int i=0;i<dp[0].length;i++){
            dp[0][i]=i;
        }
        for (int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                    continue;
                }
                dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    //九章第二轮,9/17/2017,画个图勉强能写出来了,但是链接的那个解答为啥要从后往前说? 他的解答的添加对应画图不要好理解,删除和替换对应图还好理解一些
    public int minDistance3(String word1, String word2) {
        if(word1.length()==0){
            return word2.length();
        }
        if(word2.length()==0){
            return word1.length();
        }

        int[][] dp=new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<=word2.length();i++){
            dp[0][i]=i;
        }
        for(int j=0;j<=word1.length();j++){
            dp[j][0]=j;
        }
        for(int i=1;i<=word1.length();i++){
            for(int j=1;j<=word2.length();j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(dp[i][j-1]+1,Math.min(dp[i-1][j]+1,dp[i-1][j-1]+1));
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    //4/28/2018，基本是记得然后写的，画图看，初始化时搞错了一下，下标也写错了一下，改了几次对了
    public int minDistance4(String word1, String word2) {
        if(word1.length()==0){
            return word2.length();
        }
        int[][] dp=new int[word2.length()+1][word1.length()+1];
        for(int i=0;i<dp[0].length;i++){
            dp[0][i]=i;
        }
        for(int i=0;i<dp.length;i++){
            dp[i][0]=i;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(word1.charAt(j-1)==word2.charAt(i-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    //9/15/2018。一次过
    public int minDistance5(String word1, String word2) {
        int[][] dp=new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(i==0){
                    dp[i][j]=j;
                    continue;
                }
                if(j==0){
                    dp[i][j]=i;
                    continue;
                }
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];//不用再比较[i-1][j]和[i][j-1]的值了
                }else{
                    dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    //04/22/2020居然一次过了，画图理解
    public int minDistance6(String word1, String word2) {
        int[][] dp=new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<dp.length;i++){
            dp[i][0]=i;
        }
        for(int i=0;i<dp[0].length;i++){
            dp[0][i]=i;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
