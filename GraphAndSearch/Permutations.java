package GraphAndSearch;

import java.util.ArrayList;
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
}
