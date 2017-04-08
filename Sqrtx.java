/**
 * Created by 502575560 on 7/12/16.
 */
public class Sqrtx {

public static void main(String[] args){
    System.out.print(mySqrt(63));

}
    public static int mySqrt(int x) {
        if(x<=0){
            return x;
        }
        int b=0;
        int e=x;
        while(b<=e){//搞不懂为什么一定要加等号
            int half=b-(b-e)/2;
            if(half==0){//处理b=0,e=1的情况
                return 1;
            }
            if(half==0){
                return 1;
            }
            if(half==x/half){
                return half;
            }
            if(half>x/half){
                e=half-1;

            }else{
                b=half+1;
            }
        }
        return e;//return e就是这个e总是小于或等于真正的平方根的,如63算出来也是7,算出来是8就不行

    }
}
