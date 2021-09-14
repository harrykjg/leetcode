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
//3/12/2018九章第二轮，还是以为不用b，其实是要的，例如[2,3,6,7] target=7的例子，不用b的话就会有2，2，3和2，3，2这样的结果
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates.length==0){
            rs.add(new ArrayList<>());
            return rs;
        }
        Arrays.sort(candidates);
        ArrayList<Integer> a=new ArrayList<>();
        helper2(0,0,a,candidates,target);
        return rs;
    }

    void helper2(int cur,int b,ArrayList a,int[] nums,int target){
        if(cur==target){
            rs.add(new ArrayList<>(a));
            return;
        }
        for(int i=b;i<nums.length;i++){
            if(nums[i]+cur>target){
                return;
            }
            a.add(nums[i]);
            helper2(cur+nums[i],i,a,nums,target);
            a.remove(a.size()-1);
        }
    }

    //6/12/2021 一次过
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        Arrays.sort(candidates);
        helper3(candidates,0,0,target,al,rs);
        return rs;
    }
    void helper3(int[] nums,int b,int cur,int target,List<Integer> al,List<List<Integer>> rs){
        if (cur==target){
            rs.add(new ArrayList<>(al));
            return;
        }
        for (int i=b;i<nums.length;i++){
            if (cur + nums[b]>target) {
                return;
            }
            al.add(nums[i]);
            helper3(nums,i,cur+nums[i],target,al,rs);
            al.remove(al.size()-1);
        }
    }
}
