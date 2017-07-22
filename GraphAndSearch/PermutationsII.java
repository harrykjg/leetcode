package GraphAndSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 502575560 on 7/13/17.
 */
public class PermutationsII {
    //居然写错了,就是略去重复那个条件,忘了原来还要memo[i-1]==false这个条件
    List<List<Integer>> rs=new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        // Write your code here
        if(nums.length==0){
            rs.add(new ArrayList<>());
            return rs;
        }
        Arrays.sort(nums);
        ArrayList<Integer> al=new ArrayList<>();
        boolean[] memo=new boolean[nums.length];
        dfs(nums,al,memo);
        return rs;
    }
    void dfs(int[] nums,ArrayList<Integer> al, boolean[] memo){
        if(al.size()==nums.length){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(i>0&&nums[i]==nums[i-1]&&memo[i-1]==false){//注意这个条件别忘
                continue;
            }
            if(!memo[i]){
                al.add(nums[i]);
                memo[i]=true;
                dfs(nums,al,memo);
                memo[i]=false;
                al.remove(al.size()-1);
            }
        }
    }

}
