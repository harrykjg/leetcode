package dp.string2D;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonContinuousSubarray {
    public static void main(String[] args){
        LongestCommonContinuousSubarray lc=new LongestCommonContinuousSubarray();
//        lc.find(new String[]{"8","2","1","4","7"},new String[]{"1","2","8","2","1"});
        lc.find2(new String[]{"3234.html", "xys.html", "7hsaa.html"},new String[]{"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"});
    }
    //9/18/2021 karat面经，
    //https://www.jianshu.com/p/fdbcba5fe5bc
    // 应该就是链接这题只是改成是string array
    //https://www.geeksforgeeks.org/longest-common-subarray-in-the-given-two-arrays/
    /*
           0  8  2  1  4  7
         0 0
         1 0  0  0  1  0  0
         2 0  0  1  0  0  0
         8    1  0  0  0  0
         2       2
         1           3

     */
    public List<String> find(String[] a1,String[] a2){
        List<String> rs=new ArrayList<>();
        int[][] dp=new int[a1.length+1][a2.length+1];
        int max=0;
        int index=0;
        for(int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if (a1[i-1].equals(a2[j-1])){
                    dp[i][j]=1+dp[i-1][j-1];
                    if (dp[i][j]>max){
                        max=dp[i][j];
                        index=j;
                    }
                }
            }
        }
        for (int i=index-max;i<index;i++){
            rs.add(a2[i]);
        }
        return rs;
    }
//9/29/2021 不太会了.如果某个位置a1和a2不等，则dp【i】【j】直接等于0，还有后面index有点恶心
    public List<String> find2(String[] a1,String[] a2){
        int max=0;
        int index=0;
        List<String> rs=new ArrayList<>();
        int[][] dp=new int[a1.length+1][a2.length+1];
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if (a1[i-1].equals(a2[j-1])){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                if (dp[i][j]>max){
                    max=dp[i][j];
                    index=j;
                }
            }
        }
        for (int i=index-max+1;i<=index;i++){//上面记录的j是dp的坐标，对应是a2的坐标，dp和a2的坐标还差一位。于是算出开头的坐标，要举例子
            rs.add(a2[i-1]);
        }
        return rs;
    }
}
