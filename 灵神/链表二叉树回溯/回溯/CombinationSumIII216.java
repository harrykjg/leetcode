package 灵神.链表二叉树回溯.回溯;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII216 {
    static void main() {

    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        dfs(0,1,al,rs,k,n);
        return rs;
    }
    void dfs(int cur,int b,List<Integer> al,List<List<Integer>> rs,int count,int n){
        if(cur==n&&al.size()==count){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<=9;i++){
            if(cur+i<=n&&al.size()+1<=count){
                al.add(i);
                dfs(cur+i,i+1,al,rs,count,n);
                al.remove(al.size()-1);
            }else {
                break;
            }
        }
    }
}
