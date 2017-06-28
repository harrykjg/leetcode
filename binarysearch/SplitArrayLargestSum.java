package binarysearch;

/**
 * Created by 502575560 on 6/21/17.
 */
public class SplitArrayLargestSum {
    public static void main(String[] args){
        System.out.println(splitArray(new int[]{7,2,5,10,8},3));
    }
    //目测只能想到暴力法,nums长度减1就是数组可插的空隙,m-1就是要插几个隔板,所以是就暴力回溯法咯,传递一个长度为m的数组,存的数就是第1,2,3..段
    //nums数组的元素的和,然后超时了
    //手机app上的二分法写法不一样, 思路得看 太神奇了 很难理解
    //http://www.cnblogs.com/grandyang/p/5933787.html
    //http://blog.csdn.net/mebiuw/article/details/52724293

//这个是第二个帖子的人的code,我加了注释
    public static int splitArray(int[] nums, int m) {
        long sum = 0;
        int max = 0;
        for(int num: nums){
            max = Math.max(max, num);
            sum += num;
        }
        return (int)binarySearch(nums, m, sum, max);
    }
    //二分查找,首先要看看手机app的那个解法1的思路,二分查找又带了greedy的思想,由于题目要找的是最小值,所以要么是从小到大一个一个找,要么就是二分找,
    //所以就要调整二分中的mid,找到mid最小而且满足条件(能分割成m个子数组)的时候就好了(二分法其实就是看求出的mid值),那么现在问题是怎么知道能不能分成m个子数组呢?
    //假如我现在找到一个mid,假设他符合条件,即他是各个subarray的和里最大的,那么说明别的subarray的和都是小于等于mid的,因此,我从左往右尽量使每个subarray
    //多装元素但也要使subarray的和小于等于mid,每发现当前subarray的和要大于mid了,那就砍一刀(停止往这个subarray里加元素),这样弄下来如果进行到分了比m多的subarray
    //说明这个这个mid的值取得太小了,使得分的m段太多,因此low=mid+1;那么假如算下来分成的段数少于m段的话,就说明这个mid可能正是结果,或者这个mid有点大了因此要high=mid
    //缩小一下.还有一个地方要想清楚,为啥二分法得出来的值肯定是可以产生于某个子数组呢?举个例子:3,2,3,2,3,2,3,2,3,3,2 m=3,假如现在mid=12,发现数组可以分成3+2+3+2,
    //3+2+3+2,3+3+2三段,按那个valid方法判断是返回true,所以mid继续减小,下一个二分法mid是(3+12)/2=7,发现得增大mid,再下一个mid是(8+12)/2=10,
    //发现valid返回true,mid再试着减小,(8+10)/2=9,小了,再mid变成(9+10)/2=9,也小了,lwo变成10,high也是10,退出循环,答案就是10,其实可以看出mid其实是不断试着
    //减小去靠近最小得那个符合条件得值的
    private static long binarySearch(int[] nums, int m, long high, long low){
        long mid = 0;
        while(low < high){
            mid = (high + low)/2;
            //验证是否满足,也就是这么大的值有可能出现么
            if(valid(nums, m, mid)){
                high = mid;
            }else{
                low = mid + 1;
            }
        }
        return high;//low,因为当推出while循环是low和high肯定是相等的
    }

    private static boolean valid(int[] nums, int m, long max){
        int cur = 0;
        int count = 1;
        //是否有多余m个片段or区间，大于给定值的max的，如果有了，那么就不合法了，因为这样划分就不止m个，及max太小
        for(int num: nums){
            cur += num;
            if(cur > max){//如果把当前num值加进来发现大于max了,说明这个值不能加了,要在加之前砍一刀,而这个当前值就变成下一个段新的subarray的起始值
                cur = num;
                count++;//count本来等于1,说明本来就有1段,能进入这个if说明砍了一刀,变成了count+1段了
                if(count > m){//count>m代表能把数组分成多于m个子数组,所以错了,否则就没事
                    return false;
                }
            }
        }
        return true;
    }

    //自己写的暴力法
    static int rs=Integer.MAX_VALUE;
    static int all=0;
    public static int splitArray2(int[] nums, int m) {
        int[] dp=new int[m];
        for(int i=nums.length-1;i>=0;i--){
            all+=nums[i];
        }
        helper(0,0,nums,dp,m-1,0);//我这里想的m的意义时要插几个隔离,分成m段就是要插m-1个隔离
        return rs;
    }
    public static void helper(int b,int cut,int[] nums,int[] dp,int m,int curMax){
        if(cut==m){
            int remain=all;
            for(int i=0;i<dp.length-1;i++){//最后一段就数组的总和减去前面的dp的值
                remain-=dp[i];
            }
            curMax=Math.max(curMax,remain);
            rs=Math.min(rs,curMax);
            return;
        }
        for(int i=b;i<nums.length&&nums.length-i>m-cut;i++){//nums.length-b就是现在数组还剩几个元素(在这一刀还没砍时),它要大于还要砍几刀(m-1-cut),举个例子算算
            int n=nums[i];
            dp[cut]+=n;
            curMax=Math.max(curMax,dp[cut]);
            helper(i+1,cut+1,nums,dp,m,curMax);
            dp[cut+1]=0;//这里有点难想,自己试了一下还对了,就是回溯之后把下一段清零
        }

    }
}
