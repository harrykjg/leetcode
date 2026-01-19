package 灵神.链表二叉树回溯.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum39 {
    //12/27/2025
    static void main() {
        int[] a={2,3,6,7};
        System.out.println(combinationSum(a,7));
    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> rs= new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        Arrays.sort(candidates);
        dfs(0,0,candidates,rs,al,target);
        return rs;
    }
    static void dfs(int cur,int b,int[] can,List<List<Integer>> rs,List<Integer> al,int target){
        if(cur==target){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<can.length;i++){
            if(cur+can[i]<=target){
                al.add(can[i]);
                dfs(cur+can[i],i,can,rs,al,target);
                al.remove(al.size()-1);
            }else{
                break;
            }
        }
    }
}
