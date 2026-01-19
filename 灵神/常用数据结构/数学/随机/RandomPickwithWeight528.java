package 灵神.常用数据结构.数学.随机;

import java.util.Random;

public class RandomPickwithWeight528 {
    static void main() {

    }

    //12/14/2025还是忘了，感觉是prefixsum，但是sum出来的应该如何random 数字没想到
    int[] sum;
    public RandomPickwithWeight528(int[] w) {
        sum=new int[w.length+1];
        for(int i=1;i<sum.length;i++){
            sum[i]=w[i-1]+sum[i-1];
        }
    }

    //用模版二分法真的写不对,不知道为啥，看回以前的吧，这下面的代码是错的
    public int pickIndex() {
        Random ran =new Random();
        int r=ran.nextInt(sum[sum.length-1]);//random出这个范围的数【0，k）左闭右开,注意不是卸载上面
        int b=0;
        int e=sum.length-1;
        while (b+1<e){
            int m=e-(e-b)/2;
            if(sum[m]>r&&sum[m-1]<=r){
                return m-1;
            }
            if(sum[m]<r){
                b=m;
            }else{
                e=m;
            }
        }
        if(sum[e]>r&&sum[e-1]<=r){
            return e-1;
        }
        return b-1;

    }
}
