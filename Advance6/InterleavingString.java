package Advance6;

/**
 * Created by yufengzhu on 5/9/18.
 */
public class InterleavingString {
    public static void main(String[] args) {
        InterleavingString il=new  InterleavingString();
        System.out.println(il.isInterleave2("db", "b", "cbb"));
    }
    //递归写法还没写对，应该b1那就返回了。dp写法还是不会。递归leetcode会超时
    public static boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()){
            return false;
        }
        if(s1.length()==0&&s2.length()==0&&s3.length()==0){
            return true;
        }
        boolean b1=false;
        boolean b2=false;
        if(s1.length()>0){
            if(s1.charAt(0)==s3.charAt(0)){
                 b1=isInterleave(s1.substring(1),s2,s3.substring(1));
            }
        }
        if(b1){//这里开始还是没想明白，其实b1是true的话就是整个s1和s2和s3都跑完了都符合，直接返回就行了，b1不行再看b2
            return true;
        }
        if(s2.length()>0){
            if (s2.charAt(0)==s3.charAt(0)){
                b2=isInterleave(s1,s2.substring(1),s3.substring(1));
            }
        }
        return b2;

    }
//思路就是s1的前i个字母和s2的前j个字母能否组成s3的前i+j个字母，还是写的不好,现初始化dp第一行和第一列再写就清晰一点，见string2D包里的interleavingString
    public static boolean isInterleave2(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()){
            return false;
        }
        boolean dp[][]=new boolean[s1.length()+1][s2.length()+1];
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(i==0&&j==0){
                    dp[0][0]=true;
                    continue;
                }
                if(j==0){
                    dp[i][j]=dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1);
                }else if(i==0){
                    dp[i][j]=dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1);
                }else{
                    if(dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1)){
                        dp[i][j]=true;
                    }else if(dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1)){
                        dp[i][j]=true;
                    }
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

}
