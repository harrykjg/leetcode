package dp.string1D;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 502575560 on 9/10/17.
 */
public class WordBreak {
    //九章第二轮,还要改几次,就是下标问题
    public boolean wordBreak(String s, List<String> dict) {
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;//dp[0]代表substring(0,0)即空
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){//开始j写成1就不对了
                String temp=s.substring(j,i);
                if(dp[j]&&dict.contains(temp)){//开始写成dp[j-1]也错了,实际上应该是dp[j-1+1],j-1代表j前面的那段string,而string的开头被插入了一个空string
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
        HashSet<String> set=new HashSet<>(dict);
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
//6/4/2021,改了一次对了
    public boolean wordBreak5(String s, List<String> dict) {
        Set<String> set=new HashSet<>();
        set.addAll(dict);
        boolean[] dp=new boolean[s.length()];//这次dp长度没有+1我感觉好理解一些。之前的反而不好理解为啥用dp【j】而不是dp【j-1】

        for(int i=0;i<dp.length;i++){
            for(int j=0;j<=i;j++){//这里少写等号就错了
                String cur=s.substring(j,i+1);
                if(j>0&&dp[j-1]&&set.contains(cur)){
                    dp[i]=true;
                    break;
                }else if(j==0&&set.contains(cur)){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[dp.length-1];
    }
//8//23/2021 写的不是很顺，还是dp是长度加1的，想的是如果s直接存在于wordDict的话，那么dp【0】就要true然后后面这块存在于wordDict里
    public boolean wordBreak6(String s, List<String> wordDict) {
        boolean[] dp=new boolean[s.length()+1];
        dp[0]=true;
        Set<String> set=new HashSet<>();
        set.addAll(wordDict);
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<i;j++){//有等号也对
                if(dp[j]&&set.contains(s.substring(j,i))){//开始没想清楚i的意义，就是string的结尾，之前想成ValidPalindromeIII那样是end是j+i
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[dp.length-1];
    }
    //10/3/2021 想的又不一样了，不太好，以前的好些
    public boolean wordBreak7(String s, List<String> wordDict) {
        boolean[] dp=new boolean[s.length()+1];
        Set<String> set=new HashSet<>(wordDict);
        dp[0]=true;
        for(int i=1;i<dp.length;i++){
            for(int j=0;j+i<=s.length();j++){
                int end=j+i;
                String temp=s.substring(i-1,end);
                if(dp[i-1]&&set.contains(temp)){
                    dp[end]=true;
                }
            }
        }
        return dp[dp.length-1];
    }
}
