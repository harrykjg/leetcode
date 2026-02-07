package 灵神.bit;

public class ReverseBits190 {
    public static void main(String[] args) {

    }
    //1/25/2026 写的不好要练
    public int reverseBits(int n) {
        int rs=0;
        for(int i=0;i<32;i++){
            int num=n&1;
            rs<<=1;
            rs|=num;//开始写的rs<<=1在rs|num之后就错了，因为那样写会多移一位，相当于i=31结束时rs又移了一位。但是先左移的话就没事，相当于
            //第一次当rs=0时浪费了一次吧，那样就刚刚好。

            n=n>>>1;// >>就是带符号的，如果n是负数则右移以为的话左边会一直补1 如n = 11111111 11111111 11111111 11111110
                //n >> 1 = 11111111 11111111 11111111 11111111   (还是 -1)而>>>是不带符号的，左移的话左边补的是0，因此想补0就用>>>
        }
        return rs;
    }
}
