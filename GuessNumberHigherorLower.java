/**
 * Created by 502575560 on 5/5/17.
 */
public class GuessNumberHigherorLower extends GuessGame{
    //开始看不懂题目,原来是可以调用guess这个方法去猜一个1到n的范围的数,就是二分法
    public static void main(String[] args){

    }
    public int guessNumber(int n) {
        int b=1;
        int e=n;
        int m=0;

        while (b<e){
            m=b+(e-b)/2;//这个写法忘了
            int t=guess(m);
            if(t==0){
                return m;
            }
            if(t==1){
                b=m+1;
            }else{
                e=m;
            }
        }
        return e;

    }
    //换个二分法写法,无非就是while(b<e)或者b<=e这两种写法
    public static int guessNumber2(int n) {
        int b=1;
        int e=n;
        int m=b+(e-b)/2;

        while (b<=e){
            m=b+(e-b)/2;
            int t=guess(m);
            if(t==0){
                return m;
            }
            if(t==1){
                b=m+1;
            }else{
                e=m-1;
            }
        }
        return m;//这题怎么返回m或者b或者e都对

    }
    //再换个二分法写法,
    public static int guessNumber3(int n) {
        int b=1;
        int e=n;
        int m=b+(e-b)/2;

        while (b<e){
            m=b+(e-b)/2;
            int t=guess(m);
            if(t==0){
                return m;
            }
            if(t==1){
                b=m+1;//b=m的话不行,比如b=2e=3,如果进了这个if那么b永远都是2,会死循环
            }else{
                e=m-1;
            }
        }
        return b;//这题返回b,e都行,返回m的话b=1,e=2的话不行,奇怪返回e也对?

    }
}
class GuessGame{
    static int i=131;//这里是为了写在intellliJ里面能编译才随便写的这个calss和数字
    static int guess(int n){
        if(n==i){
            return 0;
        }else{
            return n>i?-1:1;
        }
    }
}