import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 502575560 on 7/12/16.
 */
public class CombinationSum {
    public static void main(String[] args){
        int[] a={2,3,6,7};
        System.out.print(combinationSum2(a,7));
    }
//july2017 改了一次过
    public static List<List<Integer>> combinationSum(int[] can, int tar) {
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
    public static void helper(int cur,int b,int[] can,int tar,List<Integer> al,List<List<Integer>> rs){
        if(cur==tar){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<can.length;i++){

            if(cur+can[i]>tar){
                return;
            }
            al.add(can[i]);
            helper(cur+can[i],i,can,tar,al,rs);
            al.remove(al.size()-1);
        }
    }

    //04/13/2020,还是改了一次，忘记设begin去重了。
    public  List<List<Integer>> combinationSum2(int[] can, int tar) {
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        helper2(0,0,tar,can,al,rs);
        return rs;
    }
    void helper2(int b,int cur,int tar,int[] can, List<Integer> al,List<List<Integer>> rs){
        if(cur==tar){
            rs.add(new ArrayList<>(al));
            return;
        }
        for(int i=b;i<can.length;i++){
            if(cur+can[i]<=tar){
                al.add(can[i]);
                helper2(i,cur+can[i],tar,can,al,rs);
                al.remove(al.size()-1);
            }
        }
    }

}
