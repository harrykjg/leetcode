package dp.backpack;

import java.util.Arrays;

/**
 * Created by 502575560 on 8/7/17.
 */
public class Backpack {
    public static void main(String[] args){
        System.out.println(backPack(10,new int[]{3,4,8,5}));
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
}
