package Miscellaneous;

/**
 * Created by yufengzhu on 2/13/18.
 */
public class q4 {
    public static void main(String[] args){
        System.out.println(toTernaryNumber(4));
    }
    public static String toTernaryNumber(int decimalNumber){

        String rs="";
        while (decimalNumber>0){
            int remainder=decimalNumber%3;
            rs=remainder+rs;
            decimalNumber/=3;
        }
        return rs;
    }
}
