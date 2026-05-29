package SomeInterviews.snap;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays91 {
    static void main() {

    }
    //3/8/2026只想到dfs直接算，一次超时，再加上memo就过了，看别人写是dp快
    public int numDecodings(String s) {
        int rs=0;
        Map<String,Integer> memo=new HashMap<>();

        rs+=dfs(s,memo);
        return rs;

    }
    int dfs(String s,Map<String,Integer> map){
        if(s.length()==0){
            return 1;
        }
        if(map.containsKey(s)){
            return map.get(s);
        }
        int rs=0;
        String sub1=s.substring(0,1);
        if(Integer.valueOf(sub1)<=9&&Integer.valueOf(sub1)!=0){
            rs=dfs(s.substring(1),map);
        }

        if(s.length()>=2){
            String sub2=s.substring(0,2);
            if(!sub2.startsWith("0")&&Integer.valueOf(sub2)<=26&&Integer.valueOf(sub2)>0){
                rs+=dfs(s.substring(2),map);
            }
        }
        map.put(s,rs);
        return rs;
    }

    //dp写法,dp[i]的意义是加进来的i自己是valid，则dp[i]=dp[i-1]，如果是i和i-1组成valid，则dp[i]+=dp[i-1];
    //没想到dp 长度+1就好写了
    public int numDecodings2(String s) {
        int[] dp=new int[s.length()+1];
        if(s.startsWith("0")){
            return 0;
        }
        dp[0]=1;

        for (int i=1;i<dp.length;i++){
            if(s.charAt(i-1)!='0'){
                dp[i]=dp[i-1];
            }
            if(i>=2&&s.charAt(i-2)!='0'&&Integer.valueOf(s.substring(i-2,i))<=26){
                dp[i]+=dp[i-2];
            }
        }
        return dp[dp.length-1];
    }
}
