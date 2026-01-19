package 灵神.链表二叉树回溯.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII40 {
    static void main() {

    }
    //12/29/2025 他们都没用memo，那是怎么去重的？比如【1，1，1】 target=2, 下面这位是用了
    // permutation2是要用memo的
    //https://leetcode.cn/problems/combination-sum-ii/solutions/14753/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        boolean[] memo=new boolean[candidates.length];
        dfs(0,0,candidates,al,rs,memo,target);
        return rs;
    }
    void dfs(int cur,int b,int[] candidates,List<Integer> al,List<List<Integer>> rs,boolean[] memo,int target){
        if(cur==target){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<candidates.length;i++){
            if(i>0&&candidates[i-1]==candidates[i]&&!memo[i-1]){//如上面那个贴的的人那样，不用memo，
                // 但是要保证i>b&&candidates[i-1]==candidates[i],这样就不需要memo的
                continue;
            }
            if(memo[i]){
                continue;
            }
            if(cur+candidates[i]>target){
                break;
            }
            al.add(candidates[i]);
            memo[i]=true;
            dfs(cur+candidates[i],i+1,candidates,al,rs,memo,target);
            memo[i]=false;
            al.removeLast();
        }
    }
}
