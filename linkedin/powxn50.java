package linkedin;

public class powxn50 {
    //2/8/2026，还是写的不好
    public double myPow(double x, int n) {
        if(x==0){
            return 0;
        }
        if(n==0){
            return 1;
        }
//        if(n==1){  这一个不需要
//            return x;
//        }
        double half=myPow(x,n/2);
        if (n%2==0){
            if(n>0){
               return half*half;
            }else{
               return half*half;
            }

        }else{
            if(n>0){
               return x*half*half;
            }else{
                return half*half/x; //这里容易搞错想成是1/x*half*half
            }
        }
    }
}
