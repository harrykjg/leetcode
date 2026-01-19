package 灵神.链表二叉树回溯.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII47 {
    static void main() {

    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        Arrays.sort(nums);
        boolean[] memo=new boolean[nums.length];
        dfs(al,nums,rs,memo);
        return rs;
    }
    void dfs(List<Integer> al,int[] nums,List<List<Integer>> rs,boolean[] memo){
        if(al.size()==nums.length){
            rs.add(new ArrayList<>(al));
        }
        for(int i=0;i<nums.length;i++){
            if(i>0&&nums[i]==nums[i-1]&&!memo[i-1]){
                continue;
            }
            if(!memo[i]){
                al.add(nums[i]);
                memo[i]=true;
                dfs(al,nums,rs,memo);
                memo[i]=false;
                al.removeLast();
            }
        }
    }
}
