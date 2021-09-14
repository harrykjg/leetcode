package dp.backpack;

import java.util.Arrays;

/**
 * Created by 502575560 on 8/7/17.
 */
public class Backpack {
    public static void main(String[] args){
        System.out.println(backPack2(10,new int[]{3,4,8,5}));
    }
    //http://www.cnblogs.com/EdwardLiu/p/4269149.html
    //http://blog.csdn.net/wutingyehe/article/details/46910103  dp是数字的写法
    //http://www.lintcode.com/en/problem/backpack-ii/
    //写的不顺,貌似题目是每个东西只能用一次,写的时候规则没想清楚,而且初始化了第一列,导致会把某个数字重复使用,它这个条件还挺巧妙的要用到上一行
    //的信息(就可以避免重复使用一个元素),我以为只用当前行就行了
    public static int backPack(int m, int[] A) {
        // write your code here
        if(A.length==0){
            return 0;
        }
        int rs=0;
        boolean[][] dp=new boolean[A.length+1][m+1];
        //开始还是初始化了第一行和第一列,貌似只要初始化dp[0][0]就行了,其实可以初始化第一列,他们没初始化但是内层for循环是从0开始的
        for(int i=0;i<=A.length;i++){
            dp[i][0]=true;
        }
        Arrays.sort(A);//居然不需要sort也行
        dp[0][0]=true;
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(j-A[i-1]>=0){
                    if(dp[i-1][j-A[i-1]]){//这个条件是关键是看上一行的j-A[i-1]列,而不是本行的j-A[i-1]列,这样就避免了重复使用当前元素
                        dp[i][j]=true;
                        rs=Math.max(rs,j);
                    }
                }else{
                    dp[i][j]=dp[i-1][j];//这里不论是true还是false都不用检测rs最大值,因为肯定是上一行它是true时已经检测了
                }
            }
        }
        return rs;
    }

    //九章第二轮9/24/2017,大部分思路还行,就是漏了两点点,就是没初始化第一列,如果不初始化也行,则内层循环要从0开始,还有就是当当前A[i]大于包的大小时,dp[i][j]
    //不一定时false,他要取决于上一行即dp[i-1][j]的值,即不加入当前A[i]时,j大小的包能不能装满
    public static int backPack2(int m, int[] A) {
        boolean[][] dp=new boolean[A.length+1][m+1];
        dp[0][0]=true;
        Arrays.sort(A);
        int rs=Integer.MIN_VALUE;
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(A[i-1]>j){
                    dp[i][j]=dp[i-1][j];
                }else if(j-A[i-1]>=0&&dp[i-1][j-A[i-1]]){
                    dp[i][j]=true;
                    rs=Math.max(rs,j);
                }
            }
        }
        return rs;
    }
//6/5/2021,思路居然和以前的不一样，写的是数字的，以前写的反而不太好理解,画图理解
    public static int backPack3(int m, int[] A) {
        Arrays.sort(A);//不用sort也对
        int[][] dp=new int[A.length+1][m+1];
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(j>=A[i-1]){
                    dp[i][j]=Math.max(dp[i-1][j-A[i-1]]+A[i-1],dp[i-1][j]);
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];


    }
}
