public class DivideTwoIntegers {
    public int divide(int d1, int d2) {//https://www.youtube.com/watch?v=XKuFGEGt5zo
        if (d2 == 0 || d1 == Integer.MIN_VALUE && d2 == -1) {
            return Integer.MAX_VALUE;
        }

        int sign=0;
        if((d1<0&&d2>0)||(d1>0&&d2<0)){
            sign=-1;
        }else{
            sign=1;
        }
        long dd1=Math.abs((long)d1);//注意这里abs里面也要类型转换
        long dd2=Math.abs((long)d2);
        if(dd1<dd2){
            return 0;
        }
        long rs=1;
        long ddd2=dd2;
        while(dd1-ddd2>=0){//他这个循环并不需要把d1每次减掉d2，他的目的只是找到小于这个d1的最大的那个数相对于d2的系数
            if(dd1>=ddd2<<1){ //如87除以4，他就是找出来64是小于87的最大的那个数，因为64再乘以2就是128大于87了。
                //而64等于4*16，所以我们先得到结果是16，再去递归处理87-64=23这个数除以4所得的结果，加上16就行了
                ddd2<<=1;
                rs<<=1;
            }else{
                break;
            }

        }
        int remain=(int)(dd1-ddd2);
        return (int)(sign*rs+sign*divide(remain,Math.abs(d2)));
    }
    //8/23/2021  自己瞎写改了几次ac但是很慢而且用了long而且用了sign相乘，直接看答案1把比较好理解
    public int divide2(int dividend, int divisor) {//这是答案1的代码

            // Special case: overflow.
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            int negatives = 2;
            if (dividend > 0) {//先假设他们两都是negatives，如果是正的话就negatives--。这样到最后如果一正一负即netatives=1就说明结果是负的，否则是正的。
                negatives--;  //至于为啥要先把正数弄成负数呢，Integer.MIN_VALUE 比maxvalue大一个，所以负着的操作就避开了把负转成正时越界的可能
                dividend = -dividend;
            }
            if (divisor > 0) {
                negatives--;
                divisor = -divisor;
            }

            /* Count how many times the divisor has to be added
             * to get the dividend. This is the quotient. */
            int quotient = 0;
            while (dividend - divisor <= 0) {//相隔负数的减法还是-=，条件却变成是<=0了。比如10除以3。答案是3，如果用dividend>0则答案会变成4。还有1除以1，发现必须要等号
                quotient--;
                dividend -= divisor;
            }

            /* If there was originally one negative sign, then
             * the quotient remains negative. Otherwise, switch
             * it to positive. */
            if (negatives != 1) {
                quotient = -quotient;
            }
            return quotient;
        }
    }
}
