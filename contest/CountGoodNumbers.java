package contest;

import java.math.BigInteger;
import java.util.Map;

public class CountGoodNumbers {
    //题目说了1 <= n <= 10的15次方，所以说Math.pow(5,even/odd）这个double都越界了？https://stackoverflow.com/questions/9956471/wrong-result-by-java-math-pow
//好的解法类似powxn那题
// https://leetcode.com/problems/count-good-numbers/discuss/1314319/c%2B%2B-Full-Explanation-power-fast-power-modular-power
    //https://leetcode.com/problems/count-good-numbers/discuss/1314505/modPow
    public int countGoodNumbers(long n) {
//        long odd=n/2;
//        long even=n-odd;
//        double mod= Math.pow(10,9)+7;
//        return (int)(((Math.pow(5,even)%(long)mod)*(Math.pow(4,odd)%(long)mod)%mod));//这种方法应该越界错了

        //这个用biginteger也没有对半分也accept了
        BigInteger mod=new BigInteger(String.valueOf((int)Math.pow(10,9)+7));
        BigInteger nn=new BigInteger(String.valueOf(n));
        BigInteger odd=new BigInteger(String.valueOf(n/2));
        BigInteger even=nn.subtract(odd);
        BigInteger five=new BigInteger(String.valueOf(5));
        BigInteger four=new BigInteger(String.valueOf(4));
        int rs= five.modPow(even,mod).multiply(four.modPow(odd,mod)).mod(mod).intValue();

        return rs;
    }
}
