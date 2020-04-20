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
}
