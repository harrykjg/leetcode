package GraphAndSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 502575560 on 7/13/17.
 */
public class Permutations {
    List<List<Integer>> rs=new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        if(nums.length==0){
            rs.add(new ArrayList<>());
            return rs;
        }
        ArrayList<Integer> al=new ArrayList<>();
        HashSet<Integer> set=new HashSet<>();
        dfs(nums,0,al,set);
        return rs;
    }
    void dfs(int[] nums,int cur,ArrayList<Integer> al,HashSet<Integer> set){
        if(cur==nums.length){
            rs.add(new ArrayList<>(al));
        }
        for(int i=0;i<nums.length;i++){
            if(set.add(nums[i])){
                al.add(nums[i]);
                dfs(nums,cur+1,al,set);
                al.remove(al.size()-1);
                set.remove(nums[i]);
            }

        }
    }
    //23/2/2018,居然还写的不顺,还没以前写的好
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> rs=new ArrayList<>();
        if(nums.length==0){
            rs.add(new ArrayList<>());
            return rs;
        }

        List<Integer> a=new ArrayList<>();
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<nums.length;i++){
            a.add(nums[i]);
            set.add(nums[i]);
            helper(a,nums,rs,set);
            set.remove(nums[i]);
            a.remove(a.size()-1);
        }
        return rs;

    }
    void helper(List<Integer> a,int[] nums,List<List<Integer>> rs,HashSet<Integer> set){
        if(a.size()==nums.length){
            rs.add(new ArrayList<Integer>(a));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])){
                continue;
            }else{
                a.add(nums[i]);
                set.add(nums[i]);
                helper(a,nums,rs,set);
                a.remove(a.size()-1);
                set.remove(nums[i]);
            }
        }
    }

}
