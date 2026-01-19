package 灵神.链表二叉树回溯.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII90 {
    static void main() {

    }
    //12/30/2025 如果caller不用for loop，那么dfs要怎么写？就是这届来一个dfs的al就直接加到结果集，由于要去重所以要设置begin
    //其实不需要memo，因为有了begin
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        Arrays.sort(nums);
        dfs(0,nums,al,rs);//
        return rs;
    }
    //
    void dfs(int b,int[] nums,List<Integer> al,List<List<Integer>> rs){
        rs.add(new ArrayList<>(al));
        for(int i=b;i<nums.length;i++){
            if(i>b&&nums[i-1]==nums[i]){
                continue;
            }
            al.add(nums[i]);
            dfs(i+1,nums,al,rs);
            al.removeLast();
        }
    }
}
