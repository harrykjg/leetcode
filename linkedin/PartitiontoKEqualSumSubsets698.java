package linkedin;

import java.util.Arrays;

public class PartitiontoKEqualSumSubsets698 {
    static void main() {

    }
    //还是只能想到dfs，而dfs没想到为什么要用b，看以前的comment
    public boolean canPartitionKSubsets(int[] nums, int k) {
        long sum=0;
        for (int i:nums){
            sum+=i;
        }
        if(sum%k!=0){
            return false;
        }
        int target=(int)(sum/k);
        Arrays.sort(nums);
        boolean[] memo=new boolean[nums.length];
        return dfs(0,0,0,memo,nums,k,target);
    }
    boolean dfs(int b,int cur,int count,boolean[] memo,int[] nums,int k,int target){
        if(count==nums.length&&k==0){
            return true;
        }
        for (int i=b;i<nums.length;i++){
            if(memo[i]){
                continue;
            }
            int temp=cur+nums[i];
            if(cur+nums[i]>target){
                break;
            }
            memo[i]=true;
            if(temp==target){
                if(dfs(0,0,count+1,memo,nums,k-1,target)){
                    return true;
                }
            }
            if(dfs(i+1,temp,count+1,memo,nums,k,target)){
                return true;
            }
            memo[i]=false;
        }
        return false;

    }
}
