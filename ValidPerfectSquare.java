/**
 * Created by 502575560 on 4/28/17.
 */
public class ValidPerfectSquare {
    public static void main(String[] args){
        System.out.println(isPerfectSquare(1));
    }
    //就是判断有没有整数的平方根把,自己想的就是用二分法找平方根,改了几次accept
    //http://www.cnblogs.com/grandyang/p/5619296.html 解法二不错,注意解法三的二分法
    //http://blog.csdn.net/xiexingshishu/article/details/51782062 他的二分法最后用的是low
    public static boolean isPerfectSquare(int num) {
        if(num<=0){
            return false;
        }
        if(num==1){
            return true;
        }
        int b=1;
        int e=num/2;
        int m=0;
        while(b<e){
            m=(b+e)/2;
            if(m==num/m&&num%m==0){
                return true;
            }
            if(m<num/m){
                b=m+1;
            }else{
                e=m;
            }
        }
        return e*e==num;

    }
}
