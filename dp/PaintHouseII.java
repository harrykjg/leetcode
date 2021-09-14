package dp;

/**
 * Created by yufengzhu on 7/28/18.
 */
public class PaintHouseII {
    //这个dp有2个版本，一个是nkk的复杂度，一个是nk的
    //还是挺难想的，nkk的复杂度，关键是想明白当前颜色和之前颜色的关系，比如说上一次层选颜色1，2都行，都是最小值，但是到了这一层，可选的颜色就合上一层有关了，但是上一层颜色1和2都行，那么到底是选哪个呢？
    //画个表，dp[i][j]代表前i个房子，第i个选j颜色的最小花费（j是个变量，即这里是有一个list的dp[i][0],dp[i][1],dp[i][....k]），他可以通过上一个房子dp[i-1][j]推导，这个dp[i-1][j]代表上一层颜色的所有0到j颜色的结果，
    //其实就是把上一层图0到j种颜色的所有可能的值，再内嵌一个循环和当前层取0到j种颜色的所有的可能进行比较，选出来的数其实我就不需要直到他到底是选的什么颜色了，因为所有当前层和上一层可能的颜色我都列出来
    //比较过了

    //这题真没见过，可以通过上一层的第一小和第二小去判断当层的最小值如何选。。这个是优化的nk的复杂度
    //https://blog.csdn.net/qq508618087/article/details/50807874   看他nkk的分析
    //https://segmentfault.com/a/1190000005730655   nkk的代码看他

    //https://www.cnblogs.com/airwindow/p/4804011.html 看他nk的分析，但是代码有点乱，貌似他的最小和倒数第二小是只上一层的，别人都是只当前层的？
    //http://www.cnblogs.com/reboot329/p/6127979.html   nkk
    //https://www.jianshu.com/p/a8b4222a3782
    //https://www.cnblogs.com/Dylan-Java-NYC/p/5327633.html
    public int minCostII(int[][] costs) {
        if(costs.length==0){
            return 0;
        }

        int len=costs.length;
        int k=costs[0].length;
        int[][] dp=new int[len][k];
        for(int i=0;i<k;i++){
            dp[0][i]=costs[0][i];
        }
        for(int i=1;i<costs.length;i++){
            for(int j=0;j<k;j++){
                dp[i][j]=Integer.MAX_VALUE;
                for(int l=0;l<k;l++){
                    if(l==j){
                        continue;
                    }
                    dp[i][j]=Math.min(dp[i-1][l]+costs[i][j],dp[i][j]);
                }

            }

        }
        int rs=Integer.MAX_VALUE;
        for(int i=0;i<k;i++){//还第一次见最后答案还要再loop一遍dp的，真是灵活，之前卡住就是在想如果上一层有几个颜色都行，数字都是最小，那么到底选哪个呢，以为一定这个dp[i][j]要是一个确定的最小值，那样就不行了
            rs=Math.min(dp[len-1][i],rs);
        }
        return rs;

    }

    //7/19/2021和第一题一个思路的，改了一下过了。是nkk的复杂度，别人还有nk的复杂度的难想
    public int minCostII2(int[][] costs) {
        int[][]dp=new int[costs.length][costs[0].length];
        for (int i=0;i<costs[0].length;i++){
            dp[0][i]=costs[0][i];
        }
        for (int i=1;i<costs.length;i++){
            for (int j=0;j<costs[0].length;j++){
                dp[i][j]=Integer.MAX_VALUE;
                for (int k=0;k<costs[0].length;k++){
                    if (k==j){
                        continue;
                    }
                    dp[i][j]=Math.min(dp[i][j],dp[i-1][k]+costs[i][j]);//开始把costs【i】【j】写在Math。min外面就错了
                }
            }
        }
        int rs=Integer.MAX_VALUE;
        for (int i=0;i<costs[0].length;i++){
            rs=Math.min(rs,dp[dp.length-1][i]);
        }
        return rs;
    }
}
