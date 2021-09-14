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

    //9/23/2018,这样写一次超时，就是没有先找出所有factor，每次dfs中间取模去找factor。可能是因为23848713是质数所以找的慢？不管了
    public List<List<Integer>> getFactors2(int n) {
        List<List<Integer>> rs=new ArrayList<>();
        if(n<=1){
            return rs;
        }
        ArrayList<Integer> al=new ArrayList<>();
        dfs2(2,1,al,n,rs);
        return rs;
    }
    void dfs2(int b,int cur,ArrayList<Integer> al,int n,List<List<Integer>> rs){
        if(cur==n){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<=n/2;i++){
            if(n%i==0){
                if(cur*i<=n){
                    al.add(i);
                    dfs2(i,cur*i,al,n,rs);
                    al.remove(al.size()-1);
                }
            }
        }
    }
//7/17/2021,想的用除法,还改了几次，要设起点b，要检查能否整除
    public List<List<Integer>> getFactors3(int n) {
        List<List<Integer>> rs=new ArrayList<>();
        if (n<1){
            return rs;
        }
        List<Integer> factor=new ArrayList<>();
        for (int i=2;i<=n/2;i++){
            if (n%i==0){
                factor.add(i);
            }
        }
        dfs3(n,0,factor,new ArrayList<>(),rs);
        return rs;
    }
    void dfs3(int cur,int b,List<Integer> ls,List<Integer> al,List<List<Integer>> rs){
        if (cur==1){
            rs.add(new ArrayList<>(al));
            return;
        }
        for (int i=b;i<ls.size();i++){
            if (cur%ls.get(i)!=0){//忘了检查能否整除就错了
                continue;
            }
            al.add(ls.get(i));
            dfs3(cur/ls.get(i),i,ls,al,rs);
            al.remove(al.size()-1);
        }
    }

    //8/12/2021 一次超时。以前ac的现在都超时了，要用除法就不超时,因为除法那里可以检查某个candidate是否能被整除
    public List<List<Integer>> getFactors4(int n) {
        if(n==1){
            return new ArrayList<>();
        }
        List<Integer> can=new ArrayList<>();
        int a=2;
        while (a<=n/2){
            if (n%a==0){
                can.add(a);
            }
            a++;
        }
        List<List<Integer>> rs=new ArrayList<>();

        dfs4(0,new ArrayList<>(),can,n,rs);
        return rs;
    }
    void dfs4(int b,List<Integer> al,List<Integer> can,int target,List<List<Integer>> rs){
        if (target==1){
            rs.add(new ArrayList<>(al));
            return;
        }
        for (int i=b;i<can.size();i++){
            if(target%can.get(i)!=0){
                continue;
            }
            al.add(can.get(i));
            dfs4(i,al,can,target/can.get(i),rs);
            al.remove(al.size()-1);
        }
    }
}
