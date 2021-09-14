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
        int[] a={4,3,2,3,5,2,1};
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
//7/24/2021.思路有，dfs写的其实还行，就是b要不要，怎么用，没想清楚.写出来还超时。没搞懂和上面的有啥大区别，
    public boolean canPartitionKSubsets3(int[] nums, int k) {
        long sum=0;
        for (int i:nums){
            sum+=i;
        }
        int s=(int)(sum/k);
        if(sum%k!=0){
            return false;
        }
        Arrays.sort(nums);
        boolean[] memo=new boolean[nums.length];
        return dfs3(0,0,0,s,nums,k,memo);
    }
    boolean dfs3(int b,int cur,int count,int target,int[] nums,int k,boolean[] memo){
        if (count==k&&cur==0){
            return true;
        }
        for (int i=b;i<nums.length;i++){
            if (i>0&&nums[i-1]==nums[i]&&memo[i-1]==false){
                continue;
            }
            if (memo[i]==true){
                continue;
            }
            int temp=cur+nums[i];
            if (temp>target){
                break;
            }
            if (temp==target){
                memo[i]=true;
                if (dfs3(0,0,count+1,target,nums,k,memo)){//找到一个集合之后，在开始就得从头开始。
                    return true;
                }
                memo[i]=false;
            }else {//如果还在找
                memo[i]=true;
                if (dfs3(i+1,temp,count,target,nums,k,memo)){//这个写成b就超时，写成b+1就ac但是还是比以前的慢,写成i+1就快了。为啥i+1可以得想清楚，
                    return true;                    //既然当前temp小于target，那么他下一个试的数一定是在当前i后面吗？比如1，2，2，3，4，target=10，现在
                }                               //取了1和3，还小于target，还真是得从4开始试而不是再回去从2开始试，因为既然已经到了3了，说明以前已经试过2了
                memo[i]=false;
            }
        }
        return false;
    }
//8/12/2021
    public boolean canPartitionKSubsets4(int[] nums, int k) {
        long sum=0;
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        int target=(int)sum/k;
        if(sum%k!=0){
            return false;
        }
        Arrays.sort(nums);
        boolean[] memo=new boolean[nums.length];
        return dfs4(0,0,0,target,nums,k,memo);
    }
    boolean dfs4(int b,int cur,int count,int target,int[] nums,int k,boolean[] memo){
        if (count>=k&&cur==0){
            return true;
        }
        for (int i=b;i<nums.length;i++){
            if(memo[i]){
                continue;
            }
            if(i>0&&nums[i-1]==nums[i]&&memo[i-1]==false){
                continue;
            }
            int temp=nums[i]+cur;
            if (temp>target){
                break;
            }
            memo[i]=true;
            if (temp==target){
                if (dfs4(0,0,count+1,target,nums,k,memo)){
                    return true;
                }
            }
            if(dfs4(i+1,temp,count,target,nums,k,memo)){
                return true;
            }
            memo[i]=false;
        }
        return false;
    }

}
