/**
 * Created by 502575560 on 7/6/16.
 */
public class DistinctSubsequences {

    public static void main (String[] args){
        DistinctSubsequences ds=new DistinctSubsequences();
        System.out.println(ds.numDistinct("bbb","bb"));
    }

    //2017年7月,开始想的递归的好像不太好写,看会以前的还是动态规划
    public int numDistinct(String s, String t) {
       if(t.length()>s.length()){
           return 0;
       }
        int[][] dp=new int[t.length()][s.length()];
        for(int i=0;i<t.length();i++){
            for(int j=0;j<s.length();j++){
                if(t.charAt(i)==s.charAt(j)){
                    if(i==0&&j==0){
                        dp[i][j]=1;
                        continue;
                    }
                    if(i==0){
                        dp[i][j]=dp[i][j-1]+1;
                        continue;
                    }
                    if(j==0&&i>j){//这里要画图看,容易错
                        dp[i][j]=0;
                        continue;
                    }
                    dp[i][j]=dp[i-1][j-1]+dp[i][j-1];
                }else{
                    if(j!=0){
                        dp[i][j]=dp[i][j-1];
                    }else{
                        dp[i][j]=0;
                    }
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];

    }
}
