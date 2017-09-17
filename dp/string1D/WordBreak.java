package dp.string1D;

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
}
