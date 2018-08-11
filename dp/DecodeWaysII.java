package dp;

/**
 * Created by yufengzhu on 8/4/18.
 */
public class DecodeWaysII {
    public static void main(String[] args){
        DecodeWaysII dw=new DecodeWaysII();
        dw.numDecodings("204");
    }
    //https://www.youtube.com/watch?v=65j9zS-YWZo  看花花的就够了
    public int numDecodings(String s) {
        if(s.length()==0){
            return 0;
        }

        int mod=1000000000+7;
        long[] dp=new long[s.length()+1];
        dp[0]=1;
        char[] ch=s.toCharArray();
        for(int i=1;i<=s.length();i++){
            if(i==1){
                dp[i]=helper(s.substring(i-1,i))*dp[i-1];
                continue;
            }
            dp[i]=((helper(s.substring(i-1,i))*(dp[i-1]%mod))%mod+(helper(s.substring(i-2,i))*(dp[i-2])%mod)%mod)%mod;//先mod了防止越界,以为用int就行了，结果还是不行，还得用long
        }
        return (int)dp[dp.length-1];

    }
    int helper(String s){
        char[] ch=s.toCharArray();

        if(ch.length==1){
            if(ch[0]=='*'){

                return 9;
            }
            if(ch[0]-'1'>=0&&ch[0]-'1'<=8){
                return 1;
            }
        }
        if(ch.length==2){
            if(ch[0]=='*'&&ch[1]=='*'){
                return 9+6;//11,12,13,14,15,16,17,18,19,21,22,23,24,25,26
            }
            int rs=0;
            if(ch[0]=='*'){
                if(ch[1]-'0'>=0&&ch[1]-'0'<=9){
                    rs+=1;
                }
                if(ch[1]-'0'>=0&&ch[1]-'0'<=6){
                    rs+=1;
                }
                return rs;
            }
            if(ch[1]=='*'){
                if(ch[0]=='1'){

                    return 9;
                }
                if(ch[0]=='2'){

                    return 6;
                }
            }
            if(ch[0]=='1'&&ch[1]-'0'>=0&&ch[1]-'0'<=9){//ch[0]是1，并且ch[1]属于0到9,或者ch[0]是2，ch[1]是0到6才行
                return 1;
            }
            if(ch[0]=='2'&&ch[1]-'0'>=0&&ch[1]-'0'<=6){
                return 1;
            }
            return 0;
        }
        return 0;
    }
}
