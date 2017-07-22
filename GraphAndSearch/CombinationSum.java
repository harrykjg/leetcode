package GraphAndSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 502575560 on 7/14/17.
 */
public class CombinationSum {
    //还是写错了一次,以为helper不用b,其实是要的
    List<List<Integer>> rs=new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        if(candidates.length==0){
            rs.add(new ArrayList<>());
            return rs;
        }
        Arrays.sort(candidates);
        ArrayList<Integer> al=new ArrayList<>();
        helper(0,0,candidates,target,al);
        return rs;
    }
    void helper(int b,int cur,int[] nums,int target,ArrayList<Integer> al){
        if(cur==target){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<nums.length;i++){
            if(cur+nums[i]<=target){
                al.add(nums[i]);
                helper(i,cur+nums[i],nums,target,al);
                al.remove(al.size()-1);
            }
        }
    }
}
