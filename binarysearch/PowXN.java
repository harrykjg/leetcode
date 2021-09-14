package binarysearch;

public class PowXN {
    //04/19/2020还是不会,只能暴力法
    public double myPow(double x, int n) {
        double rs=1;
        int nn=Math.abs(n);
        for(int i=0;i<nn;i++){
            rs*=x;
        }
        if(n<0){
            return 1/rs;
        }
        return rs;
    }//二分的//https://www.unclegem.cn/2018/08/11/Leetcode%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0-50-Pow-x-n/
    public double myPow2(double x, int n) {
        if(n==0){
            return 1;
        }
        if(x==0){
            return 0;
        }
//        double half=myPow(x, n/2);
//        if(n%2!=0){
//            if(n>0){
//                return half*half*x;
//            }else{
//                return half*half/x;
//            }
//        }else{//他这里却是不用判断n>0的，不好想
//            return half*half;
//        }
        double half=myPow(x, n/2);
        if(n>0){//这样写好理解一些
            if(n%2==0){
                return half*half;
            }else{
                return half*half*x;
            }
        }else{
            if(n%2==0){
                return half*half;
            }else{
                return half*half/x;
            }
        }

    }

    //6/9/2021,自己想的先把n弄成正的，再去做，结果有个case n=-2147483648就会报错了。不然是对的. 以前的写法return half*half/x;还是不太好想
    public double myPow3(double x, int n) {
        if(x==0){
            return 0;
        }
        if(x==1||n==0){
            return 1d;
        }
        if(n==1){
            return x;
        }
        int abs=Math.abs(n);
        double half=myPow3(x,abs/2);
        if(n>0){
            if(n%2==0){
                return half*half;
            }
            return half*half*x;
        }else {
            if(n%2==0){
                return 1/half*1/half;
            }
            return 1/half*1/half/x;
        }

    }
    //8/13/2021 居然写出和答案基本一样的
    public double myPow4(double x, int n) {
        if(n==0){
            return 1;
        }
        if(x==0){
            return 0;
        }
        double half=myPow4(x,n/2);
        if(n%2==0){
            if(n>0){
                return half*half;
            }else{
                return half*half;
            }
        }else{
            if(n>0){
                return half*half*x;
            }else{
                return half*half/x;
            }
        }
    }
    //8/30/2021
    public double myPow5(double x, int n) {
        if(n==0){
            return 1;
        }
        double half=myPow(x,n/2);
        if(n>0){
            if(n%2==0){
                return half*half;
            }else{
                return half*half*x;
            }
        }else{
            if(n%2==0){
                return half*half;
            }else{
                return half*half/x;
            }
        }
    }
}
