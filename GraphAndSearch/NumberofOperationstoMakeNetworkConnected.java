package GraphAndSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class NumberofOperationstoMakeNetworkConnected {

    //9/21/2021 直接dfs算几个单独的个体，比较有几条链接.然后答案就是这component数量-1。比如有5天电脑，然后component是2个，那么答案就是2-1，要举几个例子看看
    //https://leetcode.com/problems/number-of-operations-to-make-network-connected/discuss/477660/Java-Count-number-of-connected-components-Clean-code
    public int makeConnected(int n, int[][] connections) {
        if (connections.length+1<n){
            return -1;
        }
        HashMap<Integer, List<Integer>> map=new HashMap<>();
        for (int[] con:connections){
            int c1=con[0];
            int c2=con[1];
            if (!map.containsKey(c1)){
                map.put(c1,new ArrayList<>());
            }
            map.get(c1).add(c2);
            if (!map.containsKey(c2)){
                map.put(c2,new ArrayList<>());
            }
            map.get(c2).add(c1);
        }
        int count=n;
        boolean[] memo=new boolean[n];
        for (int i=0;i<n;i++){
            if (!memo[i]){
                memo[i]=true;
                dfs(i,map,memo);
                count--;
            }
        }
        return count-1;
    }
    void dfs(int cur,HashMap<Integer,List<Integer>> map,boolean[] memo){
        if(map.get(cur)!=null){
            for (int nei:map.get(cur)){
                if (!memo[nei]){
                    memo[nei]=true;
                    dfs(nei,map,memo);
                }
            }
        }
    }

}
