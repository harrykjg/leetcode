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
        if(n==2){//比如xy两位数,应该从左到右看,没有重复数字这个限制的话,x只能选9个(除去0),y能选10个;
            // 有了没有重复数字这个限制的话,x能选9个(除去0),y能选8+1=9个(x能选就那么y只能选8,但y还可以选0),可使结果没有重复digit
            return 9*9;
        }
        int rs=81;
        int j=8;//比如xyz三位数,yz已经知道这两位数可以有81种没有重复digit的结果,其实还是应该从左往右看,x可选9个(除去0),y能选9个,z能选8个,而现在一直rs=9*9=81了,
        //所以这里j世纪指的是z,可以选8个,比较烧脑
        for(int i=n-2;i>0;i--){
            rs*=j;
            j--;
        }

        return rs;
    }
}
