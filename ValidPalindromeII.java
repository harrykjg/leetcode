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
}
