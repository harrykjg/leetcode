import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by yufengzhu on 7/8/18.
 */
public class FractiontoRecurringDecimal {
    public static void main(String[] args){
        FractiontoRecurringDecimal fr=new FractiontoRecurringDecimal();
        System.out.print(fr.fractionToDecimal4(1,6));
    }
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
//7／8／2018还是写不出来，看别人的代码把,这个代码不行的
    public String fractionToDecimal2(int numerator, int denominator) {
        return "";
    }

    //9/6/2018,想着用recursive的写，还是不行,long越界问题很恶心
    public String fractionToDecimal3(int numerator, int denominator) {
        if(denominator==0){
            return "";
        }

        boolean neg=(long)numerator*(long)denominator>=0?false:true;
        long n1=numerator;
        long n2=denominator;
        long a=Math.abs(n1/n2);
        long remainder=n1%n2;
        if(remainder==0){
            if(neg){
                return "-"+a;
            }
            return a+"";
        }
        String rs=a+".";
        String dec=helper2(Math.abs(remainder),Math.abs(n2));
        if(neg){
            return "-"+rs+dec;
        }else{
            return rs+dec;
        }

    }
    String helper2(long remain,long de){
        if(remain==0){
            return "";
        }
        HashMap<Long,Integer> map=new HashMap<>();
        int index=0;
        long cur=remain;
        StringBuilder sb=new StringBuilder();
        while (cur>0){
            if(!map.containsKey(cur)){
                map.put(cur,index);
            }else{
                sb.insert(map.get(cur),"(");
                sb.append(")");
                return sb.toString();
            }

            cur=cur*10;//这里比如4／333的例子，开始以为4要一直乘10直到大于333才行，其实不是，直接乘一次10，得出来的a是0或者不是0都一样append上去就行了

            long a=cur/de;
            cur=cur%de;
            sb.append(a);
            index++;
        }
        return sb.toString();
    }

    //9/16/2018,记得，写的居然还比较顺，但是helper那里顺序好想不太好，看回上一个的比较好
    public String fractionToDecimal4(int numerator, int denominator) {
        if(denominator==0){
            return "";
        }
        String rs="";
        boolean neg=(long)numerator*(long)denominator>=0?false:true;
        rs+=Math.abs((long)numerator/(long)denominator);
        if(numerator%denominator==0){
            if(neg){
                return "-"+rs;
            }
            return rs;
        }

        String deci=helper3(Math.abs((long)(numerator%denominator)),Math.abs((long)denominator));
        if(neg){
            return "-"+rs+"."+deci;
        }
        return rs+"."+deci;
    }
    String helper3(long num,long den){
        HashMap<Long,Integer> map=new HashMap<>();
        int index=0;
        map.put(num,0);
        long cur=num*10;
        StringBuilder sb=new StringBuilder();
        while (cur!=0){
            long a=cur/den;
            long remain=cur%den;
            sb.append(a);
            index++;
            if(!map.containsKey(remain)){
                map.put(remain,index);
            }else{
                int pos=map.get(remain);
                sb.insert(pos,"(");
                sb.append(")");
                return sb.toString();
            }
            cur=remain*10;
        }
        return sb.toString();
    }
}
