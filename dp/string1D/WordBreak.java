package dp.string1D;

import java.util.HashSet;
import java.util.List;

/**
 * Created by 502575560 on 9/10/17.
 */
public class WordBreak {
    //九章第二轮,还要改几次,就是下标问题
    public boolean wordBreak(String s, List<String> dict) {
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){//开始j写成1就不对了
                String temp=s.substring(j,i);
                if(dp[j]&&dict.contains(temp)){//开始写成dp[j-1]也错了,实际上应该是dp[j-1+1],j-1代表j前面的那段string,而dp[i]长度比s长度多一位
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[dp.length-1];
    }
    //8/11/2018,还是想的是二维数组，其实一维就够了，二维的下标也有点没想明白
    public boolean wordBreak2(String s, List<String> dict) {
        HashSet<String> set=new HashSet<>(dict);
        boolean[][] dp=new boolean[s.length()+1][s.length()+1];
        dp[0][0]=true;
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<=i;j++){
                String temp=s.substring(j+1-1,i);
                if(dp[0][j]&&set.contains(temp)){
                    dp[0][i]=true;
                    break;
                }
            }
        }
        return dp[0][dp.length-1];
    }
    public boolean wordBreak3(String s, List<String> dict) {
        HashSet<String> set=new HashSet<>(dict);//dp[i]的意义是从0到i左开右闭区间的substring能否break，比如leetcode这个长度为8的字符串，dp[0]代表substring(0,0)即空，而dp[0,8]代表substring(0,8)能否break，即第0个字母到第7个字母
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<i;j++){//这里j其实小于等于都行
                String temp=s.substring(j,i);
                if(dp[j]&&set.contains(temp)){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[dp.length-1];
    }
//
    public boolean wordBreak4(String s, List<String> dict) {

        HashSet<String> set=new HashSet<>(dict);
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                String temp=s.substring(j,i);
                dp[i]=s.contains(temp)&&dp[j]?true:false;
                if(dp[i]){
                    break;
                }
            }
        }
        return dp[dp.length-1];
    }
}
