package dp;

import java.util.Arrays;

/**
 * Created by 502575560 on 8/2/17.
 */
public class ScrambleString {
    //递归的思路好理解一些,
    //http://www.cnblogs.com/easonliu/p/3696135.html
    //http://www.cnblogs.com/grandyang/p/4318500.html
    //http://blog.csdn.net/ljiabin/article/details/44537523
    //http://www.cnblogs.com/yuzhangcmu/p/4189152.html
    public boolean isScramble(String s1, String s2) {
        if(s1.length()==s2.length()&&s1.equals(s2)){
            return true;
        }
        char[] ch1=s1.toCharArray();
        char[] ch2=s2.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        for(int i=0;i<ch1.length;i++){
            if(ch1[i]!=ch2[i]){
                return false;
            }
        }
        for (int i=1;i<ch1.length;i++){
            String s11=s1.substring(0,i);
            String s12=s1.substring(i);
            String s21=s2.substring(0,i);
            String s22=s2.substring((i));
            if(isScramble(s11,s21)&&isScramble(s12,s22)){
                return true;
            }
            s21=s2.substring(s2.length()-i);//注意这里容易忘了写
            s22=s2.substring(0,s2.length()-i);
            if(isScramble(s11,s21)&&isScramble(s12,s22)){
                return true;
            }
        }
        return false;

    }
}
