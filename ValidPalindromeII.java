import java.util.HashMap;

/**
 * Created by yufengzhu on 7/24/18.
 */
public class ValidPalindromeII {
    //只会暴力，好方法还是不会
//https://www.cnblogs.com/abc-begin/p/7581719.html
    //https://blog.csdn.net/u014688145/article/details/78056535
    public boolean validPalindrome(String s) {//比如xxxxa.....bxxxx这个字符串，两边往中间扫发现a不等于b，那么就去看从a到b左边一个字母之间的字符串是否回文，或者从a右边一位到b中间的字符串是否回文
        int b=0;
        int e=s.length()-1;
        while (b<e){
            if(s.charAt(b)!=s.charAt(e)){
                return valid(s,b+1,e)||valid(s,b,e-1);
            }
        }
        return true;

    }
    boolean valid(String s,int b,int e){
        while (b<e){

            if (s.charAt(b)!=s.charAt(e)){
                return false;
            }
            b++;
            e--;
        }
        return true;
    }
//8/8/2021,自己想的，还是不如之前的方法好
    public boolean validPalindrome2(String s) {
        if(s.length()<=1){
            return true;
        }
        int i=0;
        int j=s.length()-1;

        while(i<j){
            if(s.charAt(i)!=s.charAt(j)){
                String s11=s.substring(0,i);
                String s12=s.substring(i+1);

                String s21=s.substring(0,j);
                String s22=s.substring(j+1);
                return valid2(s11+s12)||valid2(s21+s22);//就是遇到不等的时候，去掉左边或者右边的字符再比较一次。
            }
            i++;
            j--;
        }
        return true;
    }
    boolean valid2(String s){
        if(s.length()<=1){
            return true;
        }
        int i=0;
        int j=s.length()-1;

        while(i<j){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }

        return true;
    }
}
