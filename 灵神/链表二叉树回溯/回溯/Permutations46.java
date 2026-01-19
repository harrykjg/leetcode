package 灵神.链表二叉树回溯.回溯;

import java.util.ArrayList;
import java.util.List;

public class Permutations46 {
    static void main() {

    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        boolean[] memo=new boolean[nums.length];
        dfs(al,memo,nums,rs);
        return rs;
    }
    void dfs(List<Integer> al,boolean[] memo,int[] nums,List<List<Integer>> rs){
        if(al.size()==nums.length){
            rs.add(new ArrayList<>(al));
        }
        for(int i=0;i<nums.length;i++){
            if(!memo[i]){
                al.add(nums[i]);
                memo[i]=true;
                dfs(al,memo,nums,rs);
                memo[i]=false;
                al.removeLast();
            }
        }
    }
}
