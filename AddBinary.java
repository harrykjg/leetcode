import java.math.BigInteger;

public class AddBinary {
    //8/25/2021
    public String addBinary(String a, String b) {
        int i1=a.length()-1;
        int i2=b.length()-1;
        int carry=0;
        StringBuilder sb=new StringBuilder();//'1'char对应的integer不是1
        while (i1>=0&&i2>=0){
            int cur=(a.charAt(i1)-'0')+(b.charAt(i2)-'0')+carry;
            sb.append(cur%2);
            if (cur>=2){
                carry=1;
            }else {
                carry=0;
            }
            i1--;
            i2--;
        }

        while (i1>=0){
            int cur=(a.charAt(i1)-'0')+carry;
            sb.append(cur%2);
            if (cur>=2){
                carry=1;
            }else {
                carry=0;
            }
            i1--;
        }
        while (i2>=0){
            int cur=(b.charAt(i2)-'0')+carry;
            sb.append(cur%2);
            if (cur>=2){
                carry=1;
            }else {
                carry=0;
            }
            i2--;
        }
        if (carry!=0){
            sb.append(1);
        }
        return sb.reverse().toString();
    }
    //10/3/2021 这样写好一些
    public String addBinary2(String a, String b) {
        StringBuilder sb=new StringBuilder();
        int carry=0;
        int i1=a.length()-1;
        int i2=b.length()-1;
        while(i1>=0&&i2>=0){
            int temp=a.charAt(i1)-'0'+b.charAt(i2)-'0'+carry;
            sb.append(temp%2);
            carry=temp/2;
            i1--;
            i2--;
        }
        while(i1>=0){
            int temp=a.charAt(i1)-'0'+carry;
            sb.append(temp%2);
            carry=temp/2;
            i1--;
        }
        while(i2>=0){
            int temp=b.charAt(i2)-'0'+carry;
            sb.append(temp%2);
            carry=temp/2;
            i2--;
        }
        if(carry==1){
            sb.append(1);
        }
        return sb.reverse().toString();//居然是不会有leading 0的。因为有leading 0说明计算时的最后一位是0，但是如果计算时最后一位是0的话只可能是2个数的那一位都是0，但输入不会这样。

    }
    //lc答案里说facebook的题是给你两个number。不用加号加起来
    public String addBinary3(String a, String b) {//这个不怎么看得懂
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        BigInteger carry, answer;
        while (y.compareTo(zero) != 0) {
            carry = x.and(y);
            x = x.xor(y);
            y = carry.shiftLeft(1);
        }
        return x.toString(2);
    }
     int Add(int x, int y)//这个是https://www.geeksforgeeks.org/add-two-numbers-without-using-arithmetic-operators/的代码
    {
        // Iterate till there is no carry
        while (y != 0)
        {
            // carry now contains common
            // set bits of x and y
            int carry = x & y;

            // Sum of bits of x and
            // y where at least one
            // of the bits is not set    1111
            x = x ^ y;             //比如 0010 xor的意思就是这两个数相加。但是是没有进位的，1111和0010xor就成了1101。现在缺进位。而进位可以通过
                                    //&操作。1111和0010AND得出0010，再左移一位得出0100就是正确的进位。当进位不为0时即你要把进位的也加到x上来，因此while循环继续，
            // 此时被加数已经从y变成进位的那和数了（只是还是用y表示），因此同样先算算进位x&y，然后把进位加到x来，再把正确的进位赋给y，为啥
            //不直接写成y=x&y《1呢，而是在这最后再赋给y呢，因为要是先写了y=x&y《1那么x = x ^ y的时候y就变了。
            // Carry is shifted by
            // one so that adding it
            // to x gives the required sum
            y = carry << 1;
        }
        return x;
    }
    //这样写string会越界。因此还得像答案那样转成biginteger才行
    public String addBinary4(String a, String b) {
        int aa=Integer.parseInt(a, 2);
        int bb=Integer.parseInt(b, 2);
        while(bb>0){
            int carry=aa&bb;
            aa=aa^bb;
            bb=carry<<1;
        }
        return Integer.toBinaryString(aa);
    }
}
