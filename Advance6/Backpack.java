package Advance6;

import java.util.Arrays;

/**
 * Created by yufengzhu on 5/19/18.
 */
public class Backpack {
    //lintcode的,他应该死后指背包的东西只能用一次
    //基本思路还记得，就是有一点写错了，当当前物品大于包的容量时，即A[i-1]>j时，dp[i][j]不一定是false，而是取决于上一行即dp[i-1][j]的值
    public int backPack(int m, int[] A) {
        // write your code here
        boolean[][] dp=new boolean[A.length+1][m+1];
        int rs=0;
        Arrays.sort(A);
        dp[0][0]=true;
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(A[i-1]>j){
                    dp[i][j]=dp[i-1][j];
                }
                if(j>=A[i-1]&&dp[i-1][j-A[i-1]]){
                    dp[i][j]=true;
                    rs=Math.max(rs,j);
                }
            }
        }
        return rs;
    }
//05/21/2020,以为dp是用数字的，结果dp是boolean的
    public int backPack2(int m, int[] A) {
        Arrays.sort(A);
        boolean[][] dp=new boolean[A.length+1][m+1];
        for(int i=0;i<A.length+1;i++){
            dp[i][0]=true;
        }
        int rs=0;
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(A[i-1]>j){
                    dp[i][j]=dp[i-1][j];
                }else{
                    dp[i][j]=dp[i-1][j]||dp[i-1][j-A[i-1]];
                }
                if(dp[i][j]){
                    rs=Math.max(rs,j);
                }
            }
        }
        return rs;

    }
}
