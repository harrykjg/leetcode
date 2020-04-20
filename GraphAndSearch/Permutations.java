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

    //9/13/2017,没有重复的,typo之后一次过
    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> rs=new ArrayList<>();
        if(nums.length==0){
            rs.add(new ArrayList<>());
            return rs;
        }

        List<Integer> a=new ArrayList<>();
        HashSet<Integer> set=new HashSet<>();
        dfs2(0,nums,set,a,rs);
        return rs;
    }
    void dfs2(int cur,int[] nums,HashSet<Integer> set,List<Integer> al,List<List<Integer>> rs){

        if(cur==nums.length){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
            al.add(nums[i]);
            dfs2(cur+1,nums,set,al,rs);
            al.remove(al.size()-1);
            set.remove(nums[i]);
        }
    }
//04/16/2020,dfs的一次过,iterative的不会，看这个https://leetcode.com/problems/permutations/discuss/18237/My-AC-simple-iterative-javapython-solution
    public List<List<Integer>> permute4(int[] nums) {
        List<List<Integer>> rs=new ArrayList<>();
        if(nums.length==0){
            rs.add(new ArrayList<>());
            return rs;
        }
//        ArrayList<Integer> al=new ArrayList<>();
//        boolean[] memo=new boolean[nums.length];
//        dfs4(al,nums,rs,memo);
        for(int i=0;i<nums.length;i++){

        }

        return rs;
    }
    void dfs4(ArrayList<Integer> al,int[] nums,List<List<Integer>> rs,boolean[] memo){
        if(al.size()==nums.length){
            rs.add(new ArrayList<>(al));
        }
        for(int i=0;i<nums.length;i++){
            if(!memo[i]){
                al.add(nums[i]);
                memo[i]=true;
                dfs4(al,nums,rs,memo);
                al.remove(al.size()-1);
                memo[i]=false;
            }
        }
    }

}
