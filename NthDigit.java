/**
 * Created by 502575560 on 6/6/17.
 */
public class NthDigit {
    public static void main(String[] args){
        System.out.print(NthDigit.findNthDigit(1000));
    }
    //原来题目意思是1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 从左到右第10位数是1,第11位是0,即10是分开来算的
    //看了别人的思路再自己写的有点不同,自己想还真的不太好写,得列出来试
    //http://blog.csdn.net/qq508618087/article/details/52582162
    //http://www.cnblogs.com/grandyang/p/5891871.html
    public static int findNthDigit(int n) {
        if(n<=9){
            return n;
        }
        long cons=9;
        int len=2;
        int left=n-9;
        int i=9;
        while (left>cons*10*len){//1-9有9个数,10到99有90个数,100-999有900个数,并且10到90有90*2个数字,100-999有3*900个数字,这个while先
                          //确定数字在哪个区间,left就是n往右移了多少个数字(注意是digit),i就是9或者99或者999,再加一就是10,100,1000了
            cons*=10;
            left-=cons*len;
            len++;
            i+=cons;
        }
        left-=1;//这里left-1剩下得数,如n=100或101,则left这里就是0或1,再除以len就是第0位数,如果left不减1则会得出第0或1位,不好算
        int m=left/len;
        int r=left%len;
        i+=1+m;//m就是从第10,100,1000..开始第0,1,2..位数
        int m2=(int)Math.pow(10,len-1-r);//现在确定了是哪个数,就剩确定这个数得哪个数字了,比如i是103,r是2,则是从左往右第2位数即3
        int rs=(i/m2)%10;
        return rs;

    }
}
