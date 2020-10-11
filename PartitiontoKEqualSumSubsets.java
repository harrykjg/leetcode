import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yufengzhu on 9/13/18.
 */
public class PartitiontoKEqualSumSubsets {
    public static void main(String[] args){
        PartitiontoKEqualSumSubsets pk=new PartitiontoKEqualSumSubsets();
        int[] a={2,2,2,2,3,4,5};
        System.out.print(pk.canPartitionKSubsets2(a,4));
    }
    //不会
    //https://www.cnblogs.com/grandyang/p/7733098.html
    //https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/108730/JavaC++Straightforward-dfs-solution
    //https://blog.csdn.net/zjucor/article/details/78239446
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums.length==0||k>nums.length){
            return false;
        }
        int sum=0;

        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        if(sum%k!=0){
            return false;
        }
        Arrays.sort(nums);
        int subsum=sum/k;
        boolean[] memo=new boolean[nums.length];
        return dfs(0,0,0,nums,k,subsum,memo);//如何dfs是关键，其实就是要找到一个组合，这个组合由某些数字组成，这个组合的sum是sum/k，找到这个组合之后，再继续找别的组合，直到找到k个组合，并且把nums元素都用完

    }
    //还是超时了，之前以为不用begin，其实可以用来加速,然后还是超时了，把set变成boolean数组之后就过了
    boolean dfs(int b,int cur,int cursubsetcount,int[] nums,int k,int sum,boolean[] memo){
        if(cur==sum&&cursubsetcount+1==k){//这里容易写错成cursubsetcount==k
            return true;
        }
        if(cur==sum){
            return dfs(0,0,cursubsetcount+1,nums,k,sum,memo);
        }
        for(int i=b;i<nums.length;i++){

            if(cur+nums[i]>sum){
                break;
            }
            if(memo[i]){
                continue;
            }
            memo[i]=true;
            boolean flag=dfs(i+1,cur+nums[i],cursubsetcount,nums,k,sum,memo);
            memo[i]=false;
            if(flag){
                return true;
            }
        }

        return false;
    }
    //04/18/2020,只能想到dfs方法,写的不太好
    public boolean canPartitionKSubsets2(int[] nums, int k) {
        if(nums.length==0||k>nums.length){
            return false;
        }
        int sum=0;

        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        if(sum%k!=0){
            return false;
        }
        Arrays.sort(nums);
        int subsum=sum/k;
        ArrayList<Integer> al=new ArrayList<>();
        List<List<Integer>> rs=new ArrayList<>();
        boolean[] memo=new boolean[nums.length];
        return dfs2(0,0,0,subsum,k,nums,memo);

    }
    boolean dfs2(int b, int cur,int count,int sum,int k,int[] nums,boolean[] memo){
        if(cur==sum){
            return dfs2(0,0,count+1,sum,k,nums,memo);
        }
        if(count==k){
            return true;
        }
        for(int i=b;i<nums.length;i++){
            if(nums[i]+cur>sum){
                return false;
            }
            if(cur+nums[i]<=sum&&memo[i]==false){

                memo[i]=true;
                if(dfs2(i+1,cur+nums[i],count,sum,k,nums,memo)){
                    return true;
                }
                memo[i]=false;
            }
        }
        return false;
    }

}
