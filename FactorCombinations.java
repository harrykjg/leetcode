import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 8/8/18.
 */
public class FactorCombinations {
    //dfs一次过
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> rs=new ArrayList<>();
        if(n<=3){
            return rs;
        }
        List<Integer> factors=factors(n);
        if(factors.size()==0){
            return rs;
        }
        ArrayList<Integer> al=new ArrayList<>();
        dfs(0,1,al,factors,rs,n);
        return rs;

    }
    void dfs(int b,int cur,ArrayList<Integer> al,List<Integer> factors,List<List<Integer>> rs,int n){
        if(cur==n){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<factors.size();i++){
            int temp=cur*factors.get(i);
            if(temp>n){
                return;
            }
            al.add(factors.get(i));
            dfs(i,temp,al,factors,rs,n);
            al.remove(al.size()-1);
        }

    }

    List<Integer> factors(int n){
        int a=2;
        List<Integer> ls=new ArrayList<>();
        while (a<=n/2){
            if(n%a==0){
                ls.add(a);
            }
            a++;
        }
        return ls;
    }
}
