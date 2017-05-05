/**
 * Created by 502575560 on 5/3/17.
 */
public class SuperPow {

    //不会,挺难理解的
    //http://www.cnblogs.com/EdwardLiu/p/6108098.html   //貌似这个没有用快速幂,就是防止了溢出
    //http://blog.csdn.net/xiexingshishu/article/details/51927390 这个和下面一个解法基本一样,就是一个用了递归一个用了非递归求pow
    //http://www.cnblogs.com/grandyang/p/5651982.html
    //http://blog.csdn.net/niuooniuoo/article/details/51878696
    //http://blog.csdn.net/qq508618087/article/details/51860222
    //http://blog.csdn.net/xuruoxin/article/details/8578992

    public int superPow(int a, int[] b) {//右两个方面要考虑,一是防止pow时溢出,二是要用二分法去算pow,即x^k=x^(k/2) *x^(k/2)这样,即不用k的复杂度二是logk就行了
        if(b.length==0){
            return a%1337;
        }
        int rs=1;//思路就是先有定理幂指数相乘定理,和定理xy % 1337 = ((x%1337) * (y%1337)) % 1337,由于b时个数组,那么套这个定理就刚好
        for (int i=0;i<b.length;i++){//这个for循环也很巧妙,先设rs=1,如果b只有一个数的话也对.意义就是上一个mypow即a的b[i-1]次方的结果的10次方,
            rs= mypow(rs,10,1337)*mypow(a,b[i],1337)%1337;//乘以这一轮a的b[i]次方的结果,而如果i=0的话那么上一轮就时1,的10次方就是1
        }
        return rs;
    }
    public int mypow(int a,int b,int c){
        int rs=a%c;
        if(b==0){
            return 1;
        }
        if(b==1){//不加这个的话如果b=1的时候,b-b/2一直是1会爆栈
            return rs;
        }
        return (mypow(a,b/2,c)%c)*(mypow(a,b-b/2,c)%c)%c;  //这个实际上就是二分法了
    }

//    private int pow(int a, int b, int mod)  //http://blog.csdn.net/xiexingshishu/article/details/51927390 他的代码
//    {
//        int ret = 1;
//        int x = a % mod;
//        while (b != 0) {//他这里
//            if ((b & 1) == 1) {//他这里时非递归的写法,b&1是得出这个数是不是奇数,没那么容易理解
//                ret = ret * x % mod;
//            }
//
//            x = x * x % mod;
//
//            b >>= 1;
//        }
//
//        return ret;
//    }
}
