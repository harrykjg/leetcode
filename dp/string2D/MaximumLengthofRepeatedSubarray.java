package dp.string2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 10/9/18.
 */
public class MaximumLengthofRepeatedSubarray {
    public static void main(String[] args){
        MaximumLengthofRepeatedSubarray ml=new MaximumLengthofRepeatedSubarray();
        ml.findLength3(new int[]{1,2,3,2,1,5},new int[]{5,3,2,1,4,7});
    }
    //不是subsequence是subarray要连续的,和longest common substring一样
    public int findLength(int[] A, int[] B) {
        if(A.length==0&&B.length==0){
            return 0;
        }
        int[][] dp=new int[A.length+1][B.length+1];
        int max=0;
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(A[i-1]==B[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;

                }else{
                    dp[i][j]=0;//开始写成了math.max(0,dp[i-1][j-1])，还是要举例子才行
                }
                max=Math.max(max,dp[i][j]);

            }
        }
        return max;
    }
    //谷歌follow up打印其中一个解,画图的话，可以之间看谁是最大的，然后从这一点往前数max这个长度就得到了一个substring就是答案了？貌似没那么简单，还要想清楚这个index是A还是B的index
    //https://www.geeksforgeeks.org/print-longest-common-substring/ 但是这个要用rol和col去记录
    public String findLength2(int[] A, int[] B) {
        if(A.length==0&&B.length==0){
            return null;
        }
        int[][] dp=new int[A.length+1][B.length+1];
        int max=0;
        int index=0;
        ArrayList<Integer> al=new ArrayList<>();
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(A[i-1]==B[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                    if(dp[i-1][j-1]+1>max){
                        max=dp[i][j];
                        dp[i][j]=dp[i-1][j-1]+1;
                        index=i-1;//是i的index而不是j的，因为是拿着这个i去遍历各个j。注意还要-1才能对应成A里的数
                    }

                }else{
                    dp[i][j]=0;//开始写成了math.max(0,dp[i-1][j-1])，还是要举例子才行
                }

            }
        }
        String s="";
        for(int i=index-max+1;i<=index;i++){//下标有点恶心
            s=s+A[i];
        }
        System.out.print(s);

        return s;

    }

    //7/25/2021 不会了。dp的意义也理解错了，dp【i】【j】的意义是i和j必须取的情况
    public int findLength3(int[] A, int[] B) {
        int[][] dp=new int[A.length+1][B.length+1];
        int rs=0;
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if (A[i-1]==B[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    dp[i][j]=0;//这里不太好理解了
                }
                rs=Math.max(rs,dp[i][j]);
            }
        }
        return rs;
    }
}
