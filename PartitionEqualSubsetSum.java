import java.util.Arrays;

/**
 * Created by 502575560 on 6/25/17.
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args){
        System.out.print(canPartition3(new int[]{1,5,5,11}));
    }
    //砍了别人的思路,是np问题,就是找能不能放元素进背包里,能刚好使背包里sum/2重量,列个矩阵表试试就懂了
    //np问题好像大都是画个矩阵,横坐标是0,1,2,3,4,5...直到所要的重量,中坐标是nums数组,然后一行一行从右到左填,true代表当前值能被表示
    //列矩阵的话就是二维数组了,起始这里可以优化成一维数组因为所需要的信息都可以再这数组前面得到
//这一题的一个nums元素用了一次就不能再用了,和以前做过的一个元素可以重复用的不同,所以dp写的就不同,注意不到这个区别的话dp就很难写,
    //这里的dp就是从后面开始往前扫的
    //http://blog.csdn.net/mebiuw/article/details/52765840  这个用的二维dp但是启发了思路
    //http://blog.csdn.net/qq508618087/article/details/52774116
    //http://blog.csdn.net/Andy_Shan/article/details/52771233
    public static boolean canPartition(int[] nums) {
        int sum=0;
        int max=0;
        for(int n:nums){
            sum+=n;
            max=Math.max(max,n);
        }
        if(sum%2!=0){
            return false;
        }
        if(max>sum/2){
            return false;
        }
        boolean[] dp=new boolean[sum/2+1];//从列出的矩阵上看就是0,1,2,3,4...sum/2
        dp[0]=true;//初始化0为真
        for(int i=0;i<nums.length;i++){//dp写的很烂,改了好几次,原来是因该从后面开始往前找,否则不太好写,不像说了
            for(int j=sum/2;j>=nums[i];j--){//0,1,2,3,4,5,6,7,8,9,10,11,12
                dp[j]=dp[j]||dp[j-nums[i]];//dp[j]就两种情况,一个就是由nums[i]这单个元素就能到达,另一个就是由之前的元素加nums[i]到达
            }                              //这里我自己想的容易漏dp[j]这个条件,原因就是思路还是从左往右扫的,应该从右往左扫才行
        }
        return dp[dp.length-1];
    }
    //自己想的,dfs,超时了,起始就算是是暴力法加了一点枝减
    public boolean canPartition2(int[] nums) {
        int sum=0;
        int max=0;
        for(int n:nums){
            sum+=n;
            max=Math.max(max,n);
        }
        if(sum%2!=0){
            return false;
        }
        if(max>sum/2){
            return false;
        }
        Arrays.sort(nums);
        return helper(0,0,nums,sum);

    }
    boolean helper(int b,int cursum,int[] nums,int sum){
        for(int i=b;i<nums.length;i++){
            if(2*(cursum+nums[i])==sum){
                return true;
            }
            if(2*(cursum+nums[i])<sum){
                boolean bool=helper(i+1,cursum+nums[i],nums,sum);
                if(bool){
                    return true;
                }
            }else{
               return false;
            }
        }
        return false;

    }

    //9/14/2018,还是不会，看回之前的解释
    public static boolean canPartition3(int[] nums) {
        if(nums.length==0){
            return false;
        }
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        if(sum%2!=0){
            return false;
        }
        Arrays.sort(nums);
        int target=sum/2;
        boolean[][] dp=new boolean[nums.length+1][target+1];//还是画图，先是2维dp,dp的意义还不太好理解，要求的是能否用nums这个数组里的元素组成target，
                                    // 但是不一定要用完所有nums里的元素，并且可能不会用完所有nums里的元素，因为如果用完的话那么j肯定超过sum/2了。不好想
                                        // 貌似不能优化到一维，因为每个nums元素只能用一次,写的很烂，他们用一维的肯定是不同思路的
        dp[0][0]=true;
        for(int i=1;i<=nums.length;i++){
            for(int j=1;j<target+1;j++){
                if(nums[i-1]==j){//这个nums[i-1】直接就可以表示j
                    dp[i][j]=true;
                    continue;
                }
                if(dp[i-1][j]){//如果上一行可以表示那么这一行也就可以
                    dp[i][j]=true;
                    continue;
                }
                if(j-nums[i-1]>=0){//j减当前j大于等于0的话，如果上一行可以表示j-nums[i-1]那么这一行就能表示j
                    dp[i][j]=dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[dp.length-1][target];
    }
    //9/16/2018,这次写的dfs，一次超时，后来加上去重就accept了
    public static boolean canPartition4(int[] nums) {
        if(nums.length==0){
            return false;
        }
        int sum=0;
        for(int n:nums){
            sum+=n;
        }
        if(sum%2!=0){
            return false;
        }
        Arrays.sort(nums);
        boolean[] memo=new boolean[nums.length];
        return dfs(0,0,sum/2,nums,memo);
    }
    static  boolean dfs(int cur,int b,int target,int[] nums,boolean[] memo){//就dfs看能不能找到某几个数字等于sum／2的就完了，可能还要判断不能用光所有的元素
        if(cur==target){
            return true;
        }
        if(cur>target){
            return false;
        }
        for(int i=b;i<nums.length;i++){
            if(i>0&&nums[i-1]==nums[i]&&memo[i-1]==false){//后来加上这个去重就accept了
                continue;
            }
            if(memo[i]){
                continue;
            }
            memo[i]=true;
            if(dfs(cur+nums[i],i+1,target,nums,memo)){
                return true;
            }
            memo[i]=false;
        }
        return false;
    }
}
