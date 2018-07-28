import java.util.HashMap;
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
        map.put(0,-1);//这里应该放一个0，-1，比较难想，导致下面i-map.get(mod)>1可以满足，奇怪的是我不put0，-1的话，下面改成i-map.get(mod)>=1也能accept,应该是lc的test case不完整，{9, 2, 6},6)这个就不行的
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
}
