import java.util.ArrayList;

/**
 * Created by 502575560 on 5/2/17.
 */
public class SumofTwoIntegers {
    //要用bit的不会,还是很难理解他们的写法
    //http://blog.csdn.net/qq508618087/article/details/51789576
    //http://www.cnblogs.com/DarrenChan/p/5648629.html
    //http://blog.csdn.net/pwiling/article/details/51842393
    //http://www.cnblogs.com/grandyang/p/5631814.html
    //http://blog.csdn.net/u014237408/article/details/25907431 说为啥负数也能成立的,没怎么看懂
    //http://www.360doc.com/content/12/0801/17/6828497_227700914.shtml
    public static void main(String[] args){
        System.out.println(getSum(20,30));
    }

    public static int getSum(int a, int b) {
        if(b==0){//这样想,a是一个数,b是进位,如果b等于0了那么就返回a
            return a;
        }
        int carry=0;
        while(b!=0){
            a=a^b;//这里就想成a^b就是a+b但不考虑进位,而a&b得出的就是进位,但是这个进位要左移一位以便下次拿aa来喝他相加,因为如1+1写成竖式来看,1&1得出1,而
            carry=(a&b);
            b=carry<<1;//这个1要加到前面一位那,即要左移1位
        }
        return a;

    }


    //这个时自己瞎写的貌似负数处理不了.思路就是每次取两个数的各自最后一位,(&1就是取了最后一位了),然后异或他们,即0和1就是真即1,0和0则为0,1和1则为0
    public static int getSum2(int a, int b) { //然后a和b各自右移一位,即把最后一位去掉了,下个for循环继续取最后一位,得出的数存在一个array里,最后
        int carry=0;                         //再算出最终得数
        ArrayList<Integer> al=new ArrayList<>();
        while(a!=0||b!=0||carry!=0){
            int aa=a&1;
            int bb=b&1;
            if(carry==0){
                al.add(aa^bb);
                if(aa==1&&bb==1){
                    carry=1;
                }

            }else{
                al.add((aa^bb)^carry);
                if(((aa^bb)^carry)==1&&aa!=1){
                    carry=0;
                }
            }
            a=a>>1;
            b=b>>1;
        }
        int rs=0;
        for(int i=0;i<al.size();i++){
            rs+=al.get(i)<<i;
        }
        return rs;

    }
}
