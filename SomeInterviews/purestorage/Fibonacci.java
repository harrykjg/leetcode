package SomeInterviews.purestorage;

public class Fibonacci {

    public int fibonacci(int n){
        if(n<=1){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }

    //非递归
    public int fibonacci2(int n){
        if(n<=1){
            return n;
        }
        int pre1=0;
        int pre2=1;
        int rs=0;
        while (n>1){
            rs=pre1+pre2;
            pre1=pre2;
            pre2=rs;
            n--;
        }
        return rs;
    }
}
