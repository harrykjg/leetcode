package dp;

public class LongestPalindromicSubstring {

    public static void main(String[] args){
        LongestPalindromicSubstring lp=new LongestPalindromicSubstring();
        lp.longestPalindrome("abadd");

    }

    //04/18/2020,改了一两次，还算比较顺
    public String longestPalindrome(String s) {
        if(s.length()<=1){
            return s;
        }
        int max=Integer.MIN_VALUE;
        String rs="";
        boolean[][] dp=new boolean[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            dp[i][i]=true;
            if(i+1<s.length()&&s.charAt(i)==s.charAt(i+1)){
                dp[i][i+1]=true;
                if(max<i+1){
                    max=2;
                    rs=s.substring(i,i+1+1);//这里容易漏判断
                }
            }
        }

        for(int i=2;i<s.length();i++){
            for(int j=0;j+i<s.length();j++){
                if(dp[j+1][i+j-1]&&s.charAt(j)==s.charAt(i+j)){
                    dp[j][i+j]=true;
                    if(max<i+1){
                        max=i+1;
                        rs=s.substring(j,i+j+1);
                    }
                }
            }
        }
        if(rs.equals("")){
            return s.substring(0,1);
        }
        return rs;
    }
}
