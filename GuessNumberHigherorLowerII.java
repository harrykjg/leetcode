/**
 * Created by 502575560 on 5/5/17.
 */
public class GuessNumberHigherorLowerII {
    //不太理解题目,gurarantee a win啥意思
    //原来这题算很难的我觉得
    //理解之后还算写的顺
    //http://blog.csdn.net/adfsss/article/details/51951658 看他的分析,也看他下面的评论,他的代码其实没怎么看,他是递归的
    //https://segmentfault.com/a/1190000008345539 代码基本参考他的
    //http://blog.csdn.net/wdlsjdl2/article/details/51942317
    //http://blog.csdn.net/corpsepiges/article/details/51943496 递归写法没怎么看
    public int getMoneyAmount(int n) {
        if(n==1){
            return 0;
        }
        //dp[i][j]的意义就是数字i到j中间猜到一个数字所要给的代价,比如1到10中间,不管要猜的数是哪一个,我可以算出我连续猜1,2,3,4..10所付的代价
        //也可以算我猜5,7,9这样二分法猜所付的代价,这题没说是用二分法,因此要覆盖所有取法,在算每一个取法时又时算的这种取法的最坏情况,再在这么多种
        // 取法种选得出的值的最小的一种,比如1到3中间,用二分法猜,只要我第一个数猜2,那么无论要猜的值是1,2,3
        //我最多只要付2块钱就能猜对,这个2就是dp的意义,扩展到1到n中间,我猜中间的那个数k,可能付出的代价是0,k+dp[1][k-1],k+dp[k+1][n]这三个中的
        //最大值,就能保证我能猜对. 这个dp所以就从长度i和j中间长度为1开始算起扩展到dp[1][n]
        int[][] dp=new int[n][n];//1到n即有n个值,那么dp[0][1]的意义就是猜1到2中间的值,其实也可以把dp设为长度是n+1那么下标就可以对上
        for(int i=0;i<dp.length-1;i++){//这个循环是算i到i+1这个间隔为1的情况下需要猜中所要的值
            dp[i][i+1]=i+1;
        }
        for(int i=2;i<n;i++){
            for(int j=0;j<n-i;j++){
                int temp=Integer.MAX_VALUE;
                for(int k=j+1;k<j+i;k++){//这个for循环有点不好理解,这里k是i到j中间一步步往右移,每一个k其实时代表一种取法(二分法也是一种取法),
                   temp =Math.min(temp,Math.max(k+1+dp[j][k-1],k+1+dp[k+1][j+i]));//算当前这种取法时要算他的最坏情况,这就是为什么用math.max
                }//,但是这里temp要得出的时这么多种取k的取法种最小的那个成本,把它付给dp[i][j]
                dp[j][j+i]=temp;

            }
        }
        return dp[0][n-1];

    }
}
