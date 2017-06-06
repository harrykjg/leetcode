/**
 * Created by 502575560 on 6/2/17.
 */
public class IntegerReplacement {
    //自己想的dp好想可以做,想了一下想晕了,看了别人的思路,思路就是从i=1开始推到i=n,比如现有dp[1],算dp[2],2是偶数所以他只能除以2即1,即dp[2]=dp[1]+1,
    //有了2又可以算4,8,16...,其实有了1也就可以算2,4,8,16了,然后算dp[3],3是奇数所以可以+-1,即从2或4推出来,而dp[4]还不知道,但dp[4]=dp[2]+1,这样就可以算了
    //但是dp的算法会exceed memory
    //http://blog.csdn.net/u011934885/article/details/52510297
    //https://segmentfault.com/a/1190000007318944 位操作很神奇
    public int integerReplacement(int n) {
        if(n==1){
            return 0;
        }
       if(n%2==0){
           return integerReplacement(n/2)+1;
       }else{
           if(n==Integer.MAX_VALUE){//i等于max时,(i+1)/2会溢出,而且这个情况好像时特殊情况不符合规律,见第一个帖子说的
               return integerReplacement(n/2+1)+2;
           }else {
               return Math.min(integerReplacement(n-1),integerReplacement(n+1))+1;
           }
       }

    }

    public int integerReplacement2(int n) {
        if(n==1){
            return 0;
        }
        int[] dp=new int[n+1];
        dp[0]=0;
        dp[1]=0;
        for(int i=2;i<dp.length;i++){
            if(i%2==0){
                dp[i]=dp[i/2]+1;
            }else{
                if(i==Integer.MAX_VALUE){//i等于max时,(i+1)/2会溢出,而且这个情况好像时特殊情况不符合规律,见第一个帖子说的
                    dp[i]=dp[i/2+1]+2;
                }else {
                    dp[i]=Math.min(dp[i-1],dp[(i+1)/2]+1)+1;
                }

            }
        }
        return dp[dp.length-1];
    }
}
