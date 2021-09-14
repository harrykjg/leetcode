package dp.string2D;

/**
 * Created by 502575560 on 7/7/17.
 */
public class DistinctSubsequences {
    public static void main(String[] args){
        System.out.println(numDistinct("b","b"));
    }
    //lintcode上再做,还是不会,dp的思维不太好想,画图的话试着填数据再推通向也不太容易,因为涉及到初始化,图是S为列T为行或者反过来其实都可以,只是通向推出来不一样
    ////http://blog.csdn.net/linhuanmars/article/details/23589057 他们两都是S为列,我试试T为列
//http://www.blogjava.net/menglee/archive/2013/12/31/408231.html
    public static int numDistinct(String S, String T) {
        if(S.length()==0){
            return 0;
        }
        if(T.length()==0){
            return 1;
        }
        int[][] dp=new int[T.length()+1][S.length()+1];
        for(int i=0;i<dp[0].length;i++){
            dp[0][i]=1;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(T.charAt(i-1)!=S.charAt(j-1)){
                    dp[i][j]=dp[i][j-1];
                }else{
                    dp[i][j]=dp[i][j-1]+dp[i-1][j-1];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    public static int numDistinct2(String s, String t) {
        if(s.length()==0){
            return 0;
        }
        if(t.length()==0){
            return 1;
        }
        int[][] dp=new int[s.length()+1][t.length()+1];
        for(int i=0;i<dp.length;i++){
            dp[i][0]=1;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(j>i){
                    dp[i][j]=0;
                    continue;
                }
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    //九章第二轮,还是要画个图,还算顺利
    public static int numDistinct3(String s, String t) {
        if(s.length()==0){
            return 0;
        }
        if(t.length()==0){
            return 1;
        }
        int[][] dp=new int[s.length()+1][t.length()+1];

        for(int i=0;i<=s.length();i++){
            dp[i][0]=1;
        }
        for(int i=1;i<dp.length;i++){//if(s.charat(i)==t.charat(j)则dp[i][j]=dp[i-1][j-1]+dp[i-1][j],否则dp[i][j]=dp[i-1][j]
            for(int j=1;j<dp[0].length;j++){
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
//05/26/2020,画图，居然是在s.charat(i)==t.charat(j)的情况下，dp[i][j]左上和右边的和.代码就不写了，和第一次的基本一样，是T为行，S为列
    public static int numDistinct4(String s, String t) {
        return 0;
    }

//6/5/2021,画图是可以找规律，但是最好还是理解dp的意义。把t写成行，s写成列画图，当s【i】==t【j】时有2个选择，1是算s【i】有用把t【j】抵消了，
    //那么此时就要看s从0到i-1这个字符串能构成几个t从0到j-1的字符串（因为如果t中间一段是s不能match的，就算
//  s【i】=t【j】match了也没用），2是不算s【i】，看s从0到i-1这字符串能构成几个t从0到j。画图才能体会
    public static int numDistinct5(String s, String t) {
        int[][] dp=new int[s.length()+1][t.length()+1];

        //忘了第一列还要初始化,还以为只要初始化第一个点就行，其实不是的
        for (int i=0;i<dp.length;i++){
            dp[i][0]=1;
        }
        for (int i=1;i<dp.length;i++){
            for (int j=1;j<dp[0].length;j++){
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
