package dp;

public class DecodeWays {
    public static void main(String[] args){
        DecodeWays dw=new DecodeWays();
        dw.numDecodings("202");
    }
    //05/18/2020,忘了考虑中间有02这样substring的情况,还有一些挺麻烦的检测,写的不好，见old里面的第一种方法的dfs写法,
    //还是要写dp的
    public int numDecodings(String s) {
        if(s.length()==0){
            return 0;
        }
        if(s.charAt(0)>'9'||s.charAt(0)<='0'){
            return 0;
        }
        int[] dp=new int[s.length()+1];

        dp[0]=1;

        for(int i=1;i<=s.length();i++){
            if(s.charAt(i-1)<='9'&&s.charAt(i-1)>'0'){
                dp[i]=dp[i-1];
            }
            if(i-2>=0&&Integer.parseInt(s.substring(i-2,i))<=26&&Integer.parseInt(s.substring(i-2,i))>=10){
                dp[i]+=dp[i-2];
            }
        }
        return dp[dp.length-1];
    }
}
