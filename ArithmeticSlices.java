/**
 * Created by 502575560 on 6/24/17.
 */
public class ArithmeticSlices {
    public static void main(String[] args){
        ArithmeticSlices as=new ArithmeticSlices();
        System.out.println(as.numberOfArithmeticSlices(new int[]{4,1,2,3,4,6,8,10}));//
    }
    //自己想到的就是sliding window,然后再想了一下,能不能优化,假如1234678,当前window长度是6,则从1开始算到46这里不行了,那么下一次从2开始算也肯定不行,
    //所以得从6这里开始,居然这样也accept了
    //后来又想好像可以动态规划,发现规律,假如123符合,则dp[2]=1,假如是1234,则dp[3]=3,假如12345,则dp[4]=6,假如123456,则dp[5]=10,即1,3,6,10
    //是等差数列,那么dp[i]=a[i]与a[i-1]得差是不是等于之前那个等差数列得差?是的话则dp[i-1]+gap(gap是1,2,3,4...),否则dp[i]=dp[i-1],如果出现不连续
    //的话那么gap要reset

    //http://blog.csdn.net/camellhf/article/details/52824234 和我的思路有点像,但实现不一样,它空间是O(1)
    //http://www.cnblogs.com/grandyang/p/5968340.html 看解法二
    public int numberOfArithmeticSlices(int[] A) {
        if(A.length<3){
            return 0;
        }
        int rs=0;
        int[] dp=new int[A.length];
        int gap=A[1]-A[0];
        int gap2=1;//这个是123和1234和12345这样得数列的包含长度为3的等差数列的总数的差
        //先找到第一个符合长度为3得等差数列得位置,否则好像不好进行dp

        for(int j=2;j<A.length;j++){//从2开始,因为gap一已经算了
            if(A[j]-A[j-1]==gap){
                dp[j]=dp[j-1]+gap2;
                gap2++;
            }else{
                dp[j]=dp[j-1];
                gap=A[j]-A[j-1];
                gap2=1;

            }
        }
        return dp[dp.length-1];
    }
//第一次得dp版本,先找出了第一个等差数列得开始点,所以看起来比较长,但范儿运行时间比较短一点
    public int numberOfArithmeticSlices3(int[] A) {
        if(A.length<3){
            return 0;
        }
        int rs=0;
        int[] dp=new int[A.length];
        int gap=0;
        int gap2=2;
        //先找到第一个符合长度为3得等差数列得位置,否则好像不好进行dp
        int i=0;
        while (i+2<A.length){
            if(A[i+2]-A[i+1]==A[i+1]-A[i]){
                gap=A[i+2]-A[i+1];
                dp[i+2]=1;
                i=i+2;

                break;
            }
            i++;
        }
        if(i==A.length){//说明上面那个while循环没有找到一个等差数列
            return 0;
        }
        for(int j=i+1;j<A.length;j++){
            if(A[j]-A[j-1]==gap){
                dp[j]=dp[j-1]+gap2;
                gap2++;
            }else{
                dp[j]=dp[j-1];
                gap=A[j]-A[j-1];
                gap2=1;

            }
        }
        return dp[dp.length-1];
    }

    int rs=0;
    public int numberOfArithmeticSlices2(int[] A) {
        if(A.length<3){
            return rs;
        }
        for(int i=3;i<=A.length;i++){
            for(int j=0;j<=A.length-i;){
                int v=valide(A,j,i);
                if(v==-1){
                    rs++;
                    j++;
                }else {
                    j=v;//注意这里容易写错成j+=v
                }
            }
        }
        return rs;
    }
    int valide(int[]a,int b,int len){
        int gap=a[b+1]-a[b];
        for(int i=b+2;i<a.length&&i<b+len;i++){
            if(a[i]-a[i-1]!=gap){
                return i-1;//比如1,2,3,4,6,7,8,这里我取window是7,则到这里发现6-4不对了,则下一个window应该从4开始扫了,自己试试对应下标
            }
        }
        return -1;
    }
}
