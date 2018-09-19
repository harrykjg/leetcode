import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 502575560 on 7/12/16.
 */
public class CombinationSumII {
//july2017改了一次,忘记了去重,去重这里还不熟
    public List<List<Integer>> combinationSum2(int[] can, int tar) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        if(can.length==0){

            rs.add(al);
            return rs;
        }
        Arrays.sort(can);
        helper(0,0,can,tar,al,rs);
        return rs;

    }
    public void helper(int cur,int b,int[] can,int tar,List<Integer> al,List<List<Integer>> rs){
        if(cur==tar){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<can.length;i++){
            if(i>b&&can[i]==can[i-1]){//这里之前写的i>0就不对了,这里还是不好理解
                continue;
            }
            if(cur+can[i]>tar){
                return;
            }
            al.add(can[i]);
            helper(can[i]+cur,i+1,can,tar,al,rs);
            al.remove(al.size()-1);

        }
    }
//9/9/2018,用code ganker那个去重不好想，还是写memo的吧,写的不好
    public List<List<Integer>> combinationSum22(int[] can, int tar) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        if(can.length==0){

            rs.add(al);
            return rs;
        }
        Arrays.sort(can);
        boolean[] memo=new boolean[can.length];
        dfs(0,0,can,al,rs,tar,memo);
        return rs;
    }
    void dfs(int b,int cur,int[] can,List<Integer> al,List<List<Integer>> rs,int tar,boolean[] memo){
        if(cur==tar){
            rs.add(new ArrayList<>(al));
            return;
        }

        for(int i=b;i<can.length;i++){
            if(i>0&&can[i-1]==can[i]&&!memo[i-1]){
                continue;
            }
            if(cur+can[i]<=tar){
                al.add(can[i]);
                memo[i]=true;
                dfs(i+1,cur+can[i],can,al,rs,tar,memo);
                al.remove(al.size()-1);
                memo[i]=false;
            }else{
                break;
            }
        }

    }
}
