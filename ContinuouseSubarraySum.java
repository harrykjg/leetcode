import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yufengzhu on 7/20/18.
 */
//array，前缀和
public class ContinuouseSubarraySum {
    public static void main(String[] a){
        ContinuouseSubarraySum cs=new ContinuouseSubarraySum();
        cs.checkSubarraySum2(new int[]{9, 2, 6},6);
    }
//和SubarraySum像，都有hashmap的解法
    //7/20/2018,写不出,写的这个用二维数组存i到j的sum的，结果还是超时，为啥不是n方啊？
    //https://leetcode.com/problems/continuous-subarray-sum/solution/  第二个解法应该是标准的sliding window的改进，第三个解法用hashmap的也有点屌，和以前见过
    public boolean checkSubarraySum(int[] nums, int k) {

        int[] dp=new int [nums.length];
        dp[0]=nums[0];

        for(int i=1;i<nums.length;i++){
            dp[i]=dp[i-1]+nums[i];//z

            for(int j=0;j<i;j++){
                int temp=dp[i]-dp[j]+nums[j];//这里加上了nums[j]就是包括i到j的两个端点了，所以长度肯定是大于等于2的，就不用写i-j>=1这个条件了
                if(k==0){
                    if(temp==0){
                        return true;
                    }
                }else{
                    if(temp%k==0){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //hashmap,不知道的话真不好想，也算前缀和吧,看答案的图解
    public boolean checkSubarraySum2(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int sum=0;
        map.put(0,-1);//这里应该放一个0，-1，比较难想，导致下面i-map.get(mod)>1可以满足
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(i>=1){
                if (sum==k){
                    return true;
                }
            }
            if(k!=0){
                int mod=sum%k;
                if(!map.containsKey(mod)){
                    map.put(mod,i);
                }else{
                    if(i-map.get(mod)>1){//想清楚，sum之间的差是不包扩前面的那个数的
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //8/25/2021感觉肯定是前缀和，加上map，但是还是不会做。关键是要知道这个式子 （sum2-sum1）%k=0代表sum2%k==sum1%k，因此用map的key来记录余数，value记index
    //来判断subarray的长度。前缀和只要一个sum而不是数组，只要当前sum%k的值存在map，就说明（sum2-sum1）%k=0，真不好想
    public boolean checkSubarraySum3(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,-1);
        int sum=0;
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
            int mod=sum%k;
            if (map.containsKey(mod)){
                if (i-map.get(mod)>1){
                    return true;
                }
            }else {//开始这里没放在else里，而是直接更新map就错了，比如5，0，0，0 k=3的例子，其中2个0的和就是true，由于持续更新0的位置导致subarray长度一直不能大于1
                map.put(mod,i);
            }
        }
        return false;
    }
}
