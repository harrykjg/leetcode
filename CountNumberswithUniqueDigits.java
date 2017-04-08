/**
 * Created by 502575560 on 2/12/17.
 */
public class CountNumberswithUniqueDigits {
    //这都做不出,日了,开始想着用排列组合算出重复不符合条件的,用总数减去就可以了,其实也行但是不好算
    //http://blog.csdn.net/qq508618087/article/details/51656771
    //http://www.cnblogs.com/grandyang/p/5582633.html
    public int countNumbersWithUniqueDigits(int n) {
        if(n<=0){
            return 1;
        }
        int rs=0;
        for(int i=1;i<=n;i++){//长度为i的数字符合条件的加起来
            //长度为i的数字符合条件的
            rs+=count(i);
        }
        return rs;
    }
    public int count(int n){
        if(n==1){
            return 10;
        }
        if(n==2){
            return 9*9;
        }
        int rs=81;
        int j=8;
        for(int i=n-2;i>0;i--){
            rs*=j;
            j--;
        }

        return rs;
    }
}
