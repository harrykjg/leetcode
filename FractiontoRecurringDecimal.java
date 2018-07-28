import java.util.HashMap;
import java.util.Map;

/**
 * Created by yufengzhu on 7/8/18.
 */
public class FractiontoRecurringDecimal {
    //别人的代码,16年左右写的
    public static String fractionToDecimal(int numerator, int denominator) {

        long num = numerator;
        long den = denominator;
        boolean neg = num * den < 0;
        num = Math.abs(num);
        den = Math.abs(den);

        String res = neg ? "-" + Long.toString(num / den) : Long.toString(num / den);
        long remainder = num % den;
        //先取得整数部分，再去构造小数部分
        return (remainder == 0) ? res : (res + "." + getDec(remainder, den));
    }

    private static  StringBuilder getDec(long remainder, long den) {
        //就是模拟除法，每次得到一个整数和余数，余数为0的话就完事，否则就继续除
        //然后用一个map，存的key是值就是余数（不是remainder/den，因为只要余数已经出现过了就说明remainder/den的出来的数就会重复），
        //i就是从小数点之第一个就是0，然后记录当前除出来的值的位置，这样做是为了当循环小数出现时知道何时出现循环，

        Map<Long, Integer> map = new HashMap<Long, Integer>();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (remainder != 0 && !map.containsKey(remainder)) {
            map.put(remainder, i);
            ++i;
            remainder *= 10;//这样是为了下次再除的时候不会变成不会小数点
            sb.append(remainder / den);
            remainder %= den;
        }

        if (remainder != 0) {
            sb.insert((int) map.get(remainder), '(');
            sb.append(')');
        }
        return sb;
    }
//7／8／2018还是写不出来，看别人的代码把
    public String fractionToDecimal2(int numerator, int denominator) {
        if(numerator%denominator==0){
            return String.valueOf(numerator/denominator);
        }
        int integer=numerator/denominator;
        int rem=numerator%denominator;

        String deci=helper(rem,denominator);
        return integer+"."+deci;

    }
    String helper(long rem,int den){
        Map<Long, Integer> map=new HashMap<>();
        int index=0;
        StringBuilder sb=new StringBuilder();
        long copyRem=rem;

        while (copyRem!=0){
            long integer=rem*10/den;//rem肯定不会大于den，如果不乘以10的话integer肯定是0了
             copyRem=rem*10%den;//注意这里容易漏了rem*10，否则非循环小数也变成循环了，比如1／2
            if(!map.containsKey(copyRem)){
                sb.append(integer);
                map.put(copyRem,index);
            }else{
                sb.insert(map.get(copyRem),"(");
                sb.append(")");
                return sb.toString();
            }
            index++;

        }
        return sb.toString();
    }
}
