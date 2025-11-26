package 灵神.bit;

public class Numberof1Bits191 {
    public static void main(String[] args) {

    }
    //我写的相当于更改了n，看别人的解法
    //https://leetcode.cn/problems/number-of-1-bits/solutions/672082/wei-1de-ge-shu-by-leetcode-solution-jnwf/
    //他的第二个方法
    //while (n != 0) {
    //        n &= (n - 1); // 实际上就是一个一个的除去右边第一个1 ，如1010110,看gpt
    //        count++;
    //    }
    public int hammingWeight(int n) {
        int rs=0;
        while (n>0){
            if((n&1)==1){
                rs++;
            }
            n>>=1;
        }
        return rs;
    }
}
