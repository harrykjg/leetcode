package bit;

public class Numberof1Bits {
    //04/21/2020，改了一下对了
    public int hammingWeight(int n) {
        int rs=0;
        for(int i=0;i<32;i++){
            if((n&(1<<i))!=0){//这里些==1是不对的，因为他可能是1，2，4，8，16。。
                rs++;
            }
        }
        return rs;
    }
}
