package contest;

import java.util.*;

public class MaximalNetworkRank {
    //貌似就是在n里面，找2个city，找到和这每一个city直接相连的路的数量加起来最大的那两个,
    //那其实就是找出来每个点的链接数，然后再用2冲循环找出两个最大的组合
    //https://leetcode.com/problems/maximal-network-rank/discuss/888916/Java-simple-O(n2)
    public static void main(String[] args){
        MaximalNetworkRank mn=new MaximalNetworkRank();
        int[][] a={{2,3},{0,3},{0,4},{4,1}};
        System.out.println(mn.maximalNetworkRank(5,a));
    }
    public int maximalNetworkRank(int n, int[][] roads) {
        Map<Integer, Set<Integer>> map=new HashMap<>();
        int[] rank=new int[n];
        for(int[] road:roads){
            int a=road[0];
            int b=road[1];
            map.putIfAbsent(a,new HashSet<>());
            map.putIfAbsent(b,new HashSet<>());
            map.get(a).add(b);
            map.get(b).add(a);
            rank[a]++;
            rank[b]++;
        }
        int max=0;
        for(int i=0;i<rank.length;i++){
            for(int j=0;j<rank.length;j++){
                if(i==j){
                    continue;
                }
                int cur=rank[i]+rank[j];
                if(map.get(i)!=null&&map.get(i).contains(j)){
                    cur--;
                }
                max=Math.max(max,cur);
            }
        }
        return max;
    }
}
