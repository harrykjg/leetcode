import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 9/9/18.
 */
public class CombinationSumIII {
    //还一定要选k个
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        dfs(1,0,0,al,rs,n,k);
        return rs;
    }
    void dfs(int begin,int cur,int count,List<Integer> al,List<List<Integer>> rs,int n,int k){
        if(cur==n&&al.size()==k){
            rs.add(new ArrayList<>(al));
            return ;
        }
        if(count>=k){
            return;
        }
        for(int i=begin;i<=9;i++){
            if(cur+i>n){
                return;
            }
            al.add(i);
            dfs(i+1,cur+i,count+1,al,rs,n,k);
            al.remove(al.size()-1);
        }
    }
}
