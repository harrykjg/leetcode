public class ClumsyFactorial {
    //7/30/2021,就是直接模拟，改了几次ac了
    public int clumsy(int n) {
        if (n<=1){
            return 1;
        }
        int rs=0;
        int sign=1;
        while (n>=2){//至少要2位数
            int a=n;
            int b=n-1;
            n-=2;
            if (n>=1){//说明有第三个数，那就是a*b/c了
                rs+=sign*a*b/n;
                n--;
            }else {//否则就是a*b
                rs+=sign*a*b;
                break;
            }
            if (n>=1){//如果还有1个数，加上
                rs+=n;
                n--;
            }
            //后面的就是下一个乘法周期了
            if (n>=2&&sign==1){//开始想成sign要正负正负的变，其实就是第一次之外其余的都是负的
                sign=-1;
            }

        }
        if (n>=1){//如果只剩一个那肯定是减的，因为乘 除 加 都已经算了
            rs-=1;
        }
        return rs;
    }
}
