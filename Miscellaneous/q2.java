package Miscellaneous;

/**
 * Created by yufengzhu on 2/13/18.
 */
public class q2 {
    public static void main(String[] args){
        System.out.println(getFactorial(10));
    }
    public static long getFactorial(long number){
        if(number==0){
            return 1L;
        }
        if(number<0){
            return -1L;
        }
        return number*getFactorial(number-1);
    }
}
