package Advance6;

import java.util.Arrays;

/**
 * Created by yufengzhu on 6/17/18.
 */
public class BackpackIV {
    //lintcode的
    //https://www.cnblogs.com/whaochen205/p/5851340.html 他的分析是对的把但是代码是错的
    //https://segmentfault.com/a/1190000006325321
    //不太会，感觉不用dp用dfs做,应该是可以的但是超时，而且容易和permutation2搞混，区别是那里nums里有重复的，而这里是无重复的但是可以重复使用
    // 后来看了答案，用的一维dp,还是可以用二维的dp来理解，就是行改成用nums表示，列用1，2，3，4，5，6，7。。。target表示,列个表可以理解，写出来有点恶心
    //再和combinationsum2比较一下
 public static void main(String[] args){
     BackpackIV bp=new BackpackIV();
     int[] nums={2,3,6,7};
     System.out.print(bp.backPackIV2(nums,7));
 }
    //用二维数组
    public int backPackIV(int[] nums, int target) {
        Arrays.sort(nums);
        int dp[][]=new int[target+1][nums.length+1];

        for(int i=0;i<=target;i++){
            for(int j=0;j<dp[0].length;j++){
                if(i==0&&j==0){
                    dp[i][j]=1;
                    continue;
                }
                if(i==0){
                    dp[i][j]+=dp[i][j-1];
                    continue;
                }
                if(j>0){
                    if(i>=nums[j-1]){//开始忘了加上dp[i][j-1]
                        dp[i][j]+=dp[i-nums[j-1]][j]+dp[i][j-1];
                    }else{
                        dp[i][j]+=dp[i][j-1];
                    }
                }
            }
        }

        return dp[dp.length-1][dp[0].length-1];
    }
//用一维数组,从二维数组优化成一维数组很容易错，不好写，//https://segmentfault.com/a/1190000006325321他这个两层循环和二维数组的不一样，就对了，真神奇
    public int backPackIV2(int[] nums, int target) {
//        Arrays.sort(nums);
//        int[] dp=new int[target+1];
//        dp[0]=1;
//        for(int i=1;i<=target;i++){
//            for(int j=0;j<nums.length;j++){
//                if(i>=nums[j]){
//                    dp[i]+=dp[i-nums[j]];
//                }
//
//            }
//        }
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {//内外层顺序调过来就不对了！
            for (int j = 1; j <= target; j++) {

                if (nums[i] <= j) {
                    dp[j] += dp[j-nums[i]];
                }
            }
        }
        return dp[target];
    }

    //6/21/2021不会,画图都挺难理解
    //https://www.liangzl.com/get-article-detail-127410.html，这个好理解一些，图画出来找到规律，用dp[][]=new int[nums.length][target+1]，第一列初始化
    //为1
    public int backPackIV3(int[] nums, int target) {

    }

}
