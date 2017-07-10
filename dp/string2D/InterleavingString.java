package dp.string2D;

/**
 * Created by 502575560 on 7/8/17.
 */
public class InterleavingString {
    public static void main(String[] args){
        System.out.println(isInterleave("a","","a"));
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
}
