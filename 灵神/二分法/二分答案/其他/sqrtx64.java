package 灵神.二分法.二分答案.其他;

public class sqrtx64 {
    public static void main(String[] args) {
        System.out.println(mySqrt(9));
    }
    public static int mySqrt(int x) {
        if(x<=1){
            return x;
        }
        int b=1;
        int e=x/2;
        while (b+1<e){
            int m=e-(e-b)/2;
            if(m==x/m){
                return m;
            }else if(m>x/m){
                e=m;
            }else{
                b=m;
            }
        }
        if(e>x/e){
            return b;
        }
        return e;
    }
}
