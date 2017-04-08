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
            if(i>b&&can[i]==can[i-1]){//这里之前写的i>0就不对了
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
}
