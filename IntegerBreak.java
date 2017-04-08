/**
 * Created by 502575560 on 10/23/16.
 */
public class IntegerBreak {

    //卧槽自己想的居然一次过,思路就是找规律发现大于4的数字,肯定是分成3+3+3...+4或者3+3+3...+2或者3+3+3+3这样的相乘得出的是最大
    //试试5,6,7,8,10,15就知道了
    //http://www.cnblogs.com/grandyang/p/5411919.html
    //http://blog.csdn.net/liyuanbhu/article/details/51198124
    public int integerBreak(int n) {
        if(n<2){
            return 0;
        }
        if(n==2){
            return 1;
        }
        if(n==3){
            return 2;
        }
        if(n==4){
            return 4;
        }
        int exp=0;
        int remain=0;
        while (n>0){
            n-=3;
            exp++;
            if(n==4){
                remain=4;
                break;
            }
            if(n==2){
                remain=2;
                break;
            }
            if(n==1){
                remain=1;
                break;
            }
        }
        if(remain==0){
            return (int)Math.pow(3,exp);
        }
        return (int)Math.pow(3,exp)*remain;

    }
}
