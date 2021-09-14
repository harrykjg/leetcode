package dp.string2D;

/**
 * Created by 502575560 on 7/8/17.
 */
public class InterleavingString {
    public static void main(String[] args){
        System.out.println(isInterleave4("db","b","cbb"));
    }
    //http://blog.csdn.net/u011095253/article/details/9248073  还是靠画图再推公式
    public static boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length()!=s1.length()+s2.length()){
            return false;
        }
        boolean[][] dp=new boolean[s2.length()+1][s1.length()+1];//我这里画的图是s1是横的s2是竖的,和链接的不同
        dp[0][0]=true;
        for(int i=1;i<s1.length()+1;i++){//开始这两个for没有初始化,就会错(s1或s2其中一个是""的话,就进入不了下面的循环了)
            dp[0][i]=s1.charAt(i-1)==s3.charAt(i-1)&&dp[0][i-1];
        }
        for(int i=1;i<s2.length()+1;i++){
            dp[i][0]=s2.charAt(i-1)==s3.charAt(i-1)&&dp[i-1][0];
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(s3.charAt(i+j-1)==s1.charAt(j-1)&&dp[i][j-1]){
                    dp[i][j]=true;
                }else if(s3.charAt(i+j-1)==s2.charAt(i-1)&&dp[i-1][j]){
                    dp[i][j]=true;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    //还是不能完整写出代码,忘记行和列的初始化,其实也不算忘记,这个初始化其实这是分出来单独处理而已,如果主要的那个for循环从0,0开始,然后再
    //单独处理第0行和第一列其实也是一样
    public static boolean isInterleave2(String s1, String s2, String s3) {
        if(s3.length()!=s1.length()+s2.length()){
            return false;
        }
        boolean[][] dp=new boolean[s1.length()+1][s2.length()+1];
        dp[0][0]=true;
        for(int i=1;i<=s1.length();i++){
            if(s1.charAt(i-1)==s3.charAt(i-1)&&dp[i-1][0])
            dp[i][0]=true;
        }
        for(int i=1;i<=s2.length();i++){
            if(s2.charAt(i-1)==s3.charAt(i-1)&&dp[0][i-1])
                dp[0][i]=true;
        }
        for(int i=1;i<=s1.length();i++){
            for(int j=1;j<=s2.length();j++){
                if(dp[i-1][j]&& s1.charAt(i-1)==s3.charAt(i+j-1)){
                    dp[i][j]=true;
                }
                if(dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1)){
                    dp[i][j]=true;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    //九章第二轮 09/23/2017,想了一会,还算ok
    public static boolean isInterleave3(String s1, String s2, String s3) {
        if(s1==null||s1.length()==0){
            return s2.equals(s3);
        }
        if(s2==null||s2.length()==0){
            return s1.equals(s3);
        }
        if(s1.length()+s2.length()!=s3.length()){
            return false;
        }
        boolean[][] dp=new boolean[s1.length()+1][s2.length()+1];
        for(int i=1;i<dp.length;i++){
            dp[i][0]= s1.substring(0,i).equals(s3.substring(0,i))?true:false;
        }
        for(int i=1;i<dp[0].length;i++){
            dp[0][i]= s2.substring(0,i).equals(s3.substring(0,i))?true:false;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if((s1.charAt(i-1)==s3.charAt(i+j-1)&&dp[i-1][j])||(s2.charAt(j-1)==s3.charAt(i+j-1)&&dp[i][j-1])){
                    dp[i][j]=true;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    //9／15／2018,还是不会
    public static boolean isInterleave4(String s1, String s2, String s3) {

    }

    //6／5／2021,还是不会
    public static boolean isInterleave5(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()){
            return false;
        }
        boolean[][] dp=new boolean[s1.length()+1][s2.length()+1];
        dp[0][0]=true;
        for(int i=1;i<dp.length;i++){
            if(s1.charAt(i-1)==s3.charAt(i-1)&&dp[i-1][0]){//要看连续的
                dp[i][0]=true;
            }
        }
        for(int i=1;i<dp[0].length;i++){
            if(s2.charAt(i-1)==s3.charAt(i-1)&&dp[0][i-1]){
                dp[0][i]=true;
            }
        }
        for(int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){

                if(s1.charAt(i-1)==s3.charAt(i+j-1)&&dp[i][j-1]){
                    dp[i][j]=true;
                }
                if(s2.charAt(j-1)==s3.charAt(i+j-1)&&dp[i-1][j]){
                    dp[i][j]=true;
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }


}
