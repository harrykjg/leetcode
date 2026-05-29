package SomeInterviews.snap;

public class PowerofTwo231 {
    static void main() {

    }
    //3/4/2026以为用二分法还是不行，只能覆盖大部分情况，用math。pow再转回int精度也可能不行，应该直接除以2
    public boolean isPowerOfTwo(int n) {
        if(n<=0){
            return false;
        }
        //巧妙，只有是整除的情况下才去不断除以而，否则只有一种情况是可以的，即n=1的情况是true的,而如果n是偶数，那么一直除2的话最后也坑定等于1
        while (n%2==0){
            n/=2;
        }
        return n==1;
    }
}
