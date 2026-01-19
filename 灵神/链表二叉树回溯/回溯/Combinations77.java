package 灵神.链表二叉树回溯.回溯;

import dp.ClimbingStairs;

import java.util.ArrayList;
import java.util.List;

public class Combinations77 {
    static void main() {

    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        dfs(1,n,k,al,rs);
        return rs;
    }
    void dfs(int b,int n,int k,List<Integer> al,List<List<Integer>> rs){
        if(al.size()==k){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<=n;i++){
            al.add(i);
            dfs(i+1,n,k,al,rs);
            al.removeLast();
        }
    }
}
