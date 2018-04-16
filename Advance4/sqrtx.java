package Advance4;

/**
 * Created by yufengzhu on 4/15/18.
 */
public class sqrtx {
    public int mySqrt(int x) {
        if(x<=1){
            return x;
        }
        int b=1;//开始想的从2开始，其实就该从1开始
        int e=x/2;
        int m=0;
        while (b<e-1){
            m=b+(e-b)/2;
            if(m==x/m){
                return m;
            }
            if(m<x/m){
                b=m;
            }else{
                e=m;
            }
        }
        if(e==x/e){//b和e都不完全等于x的话就return小的就对了
            return e;
        }
        return b;
    }
}
