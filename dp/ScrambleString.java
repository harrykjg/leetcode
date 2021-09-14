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

    //九章第二轮,还是不会
    public boolean isScramble2(String s1, String s2) {
        if(s1.equals(s2)){
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
        for(int i=1;i<ch1.length;i++){
            String s11=s1.substring(0,i);
            String s12=s1.substring(i);
            String s21=s2.substring(0,i);
            String s22=s2.substring(i);
            if(isScramble2(s11,s21)&&isScramble2(s12,s22)){
                return true;
            }
            s21=s2.substring(s2.length()-i);
            s22=s2.substring(0,s2.length()-i);
            if(isScramble2(s11,s21)&&isScramble2(s12,s22)){
                return true;
            }
        }
        return false;

    }

    //6/6/2021,dfs也没写对,没写sort char然后找不存在的字符就超时。现在递归版本都会超时
    public boolean isScramble3(String s1, String s2) {
        if(s1.equals(s2)){
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
        for(int i=1;i<=s1.length();i++){
            String s11=s1.substring(0,i);
            String s12=s1.substring(i);
            String s21=s2.substring(0,i);
            String s22=s2.substring(i);
            if(isScramble3(s11,s21)&&isScramble3(s12,s22)){
                return true;
            }
            s11=s1.substring(0,s1.length()-i);
            s12=s1.substring(s1.length()-i);
            if(isScramble3(s11,s22)&&isScramble3(s12,s21)){
                return true;
            }
            //            if(isScramble(s12+s11,s2)){//这样是错的，题目指循序分一半后每一半再swap，这样写的话就会导致string可以越过分界线swap，想象一下
            //                return true;
            //            }

        }
        return false;
    }
}
