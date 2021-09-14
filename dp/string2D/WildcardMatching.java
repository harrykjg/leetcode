package dp.string2D;

public class WildcardMatching {
    //6/24/2021dp居然一次就写出来了，画图找规律
    public boolean isMatch(String s, String p) {

        boolean[][] dp=new boolean[p.length()+1][s.length()+1];
        dp[0][0]=true;
        for (int i=1;i<dp.length;i++){
            for (int j=0;j<dp[0].length;j++){//注意这里是从0开始，因为检测j为0即s的前0个char能否match前i个p，p为*是可以match的，因为*可以取为空
                if (j==0){
                    if (p.charAt(i-1)=='*'){
                        dp[i][j]=dp[i-1][j];
                    }else dp[i][j]=false;
                }else {
                    if (p.charAt(i-1)=='*'){
                        dp[i][j]=dp[i-1][j-1]||dp[i-1][j]||dp[i][j-1];
                    }else if (p.charAt(i-1)=='?'){
                        dp[i][j]=dp[i-1][j-1];
                    }else {
                        if (p.charAt(i-1)==s.charAt(j-1)){
                            dp[i][j]=dp[i-1][j-1];
                        }
                    }
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    //暴力法
    //https://leetcode-cn.com/problems/wildcard-matching/solution/44-tong-pei-fu-pi-pei-shuang-zhi-zhen-by-guohaodin/ 贪心法，见回复注解
    //https://blog.csdn.net/ww32zz/article/details/50359572
    public boolean isMatch2(String s, String p) {
        if (s.length()==0&&p.length()==0){
            return true;
        }
        int i=0;
        int j=0;
        while (i<s.length()&&j<p.length()){
            if (p.charAt(j)=='?'){
                i++;
                j++;
                continue;
            }
            if (p.charAt(j)=='*'){
                if (isMatch2(s.substring(i),p.substring(j+1))){
                    return true;
                }
                if (isMatch2(s.substring(i+1),p.substring(j))){
                    return true;
                }else {
                    return false;
                }
            }else if (s.charAt(i)==p.charAt(j)){
                i++;
                j++;
            }else {
                return false;
            }
        }
        while (j<p.length()){//上面写的是f (isMatch2(s.substring(i+1),p.substring(j)))的话，比如"a"，"***"这样的例子，p永远不会前进，所以要加这个,
                                //而 old里第5个写法上面有for的情况p会加1就不需要这个while了
            if (p.charAt(j)=='*'){
                j++;
            }else{
                break;
            }
        }
        return i==s.length()&&j==p.length();
    }
}
