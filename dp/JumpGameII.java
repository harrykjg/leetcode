package dp;

import java.util.Arrays;

/**
 * Created by 502575560 on 9/10/17.
 */
public class JumpGameII {

    //九章第二轮,还是没写对,只用2个变量貌似写不出来,而且,for循环从1开始好像也写不出来
    //[3,4,1,1,2,2,1,1],看这个例子,首先curScope为0,意味着i超过0的话step就要++,开始i在0,没超curScope,所以是0step没毛病,同时发现新的scope是0+3=3,
    //然后i=1,超过了curScope所以step+1,现在走了一步了,即进入了一个新的scope,此事更新curScope,意味着在着curScope期间step不用++了,目测可知i在1,2,3
    //时都只需要1步,没毛病,且有i在1时已经更新furtherScope变成5,现在i到4了,超了3的scope,step++,进入了scope为5的范围,更新curScope为5,
    public int jump(int[] A) {
        if(A.length<=1){
            return 0;
        }
        int step=0;
        int furtherScope=A[0];//furtherScope开始为0或者A[0]其实都一样,关键是curScope不能是A[0]
        int curScope=0;//curScope应该理解为当前scope,
        for(int i=0;i<A.length;i++){
           if(i>curScope){
               step++;
               curScope=furtherScope;//更新scope,之前的循环中就应该更新了scope的值
           }
            furtherScope=Math.max(furtherScope,i+A[i]);//更新furtherscope在这里,就保证了如果能到达数组末尾的话肯定过是当前step+1
            if(furtherScope>=A.length-1){
                return step+1;
            }
        }
        return -1;
    }
    //05/25/2020,写的dp貌似还可以，就是最后一个case超时
    public int jump2(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int[] dp=new int[nums.length];
        int local=nums[0];
        for(int i=1;i<dp.length;i++){
            dp[i]=i;
            for(int j=0;j<i;j++){
                local=j+nums[j];
                if(local>=i){
                    dp[i]=Math.min(dp[j]+1,dp[i]);
                }
            }
        }
        return dp[dp.length-1];
    }
//非dp的,写不出来
    public int jump3(int[] nums) {
        if(nums.length<=1){
            return 0;
        }
        int step=0;
        int scope=0;
        int maxscope=0;
        int i=0;
        while (i<nums.length){
            if(i>scope){
                step++;
                scope=maxscope;
            }
            maxscope=Math.max(maxscope,i+nums[i]);
            if(maxscope>=nums.length-1){
                return step+1;
            }
            i++;
        }
        return step;
    }

    //6/4/2021,贪心法不容易写对，面试时还是写dp吧
    public int jump4(int[] nums) {
        if(nums.length==1){
            return 0;
        }
        int step=0;
        int max=nums[0];
        int scope=0;
        for(int i=1;i<nums.length&&i<=max;i++){
            if(i>scope){
                step++;
                scope=max;
                if(scope>=nums.length-1){
                    return step;
                }
            }
            if(i+nums[i]>max){
                max=i+nums[i];
                if(max>=nums.length-1){
                    return step+1;
                }
            }

        }
        return -1;
    }
    //dp写法,居然一次过
    public int jump5(int[] nums) {
        if(nums.length==1){
            return 0;
        }
        int[] dp=new int[nums.length];
        for(int i=1;i<nums.length;i++){
            dp[i]=i;
            for(int j=0;j<i;j++){
                if(j+nums[j]>=i){//能从j到达i的点才拿来比较
                    dp[i]=Math.min(dp[j]+1,dp[i]);
                }
            }
        }
        return dp[dp.length-1];

    }
}
