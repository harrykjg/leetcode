package 灵神.二分法.二分答案.最小化最大值;

import java.util.*;

public class MinimizetheMaximumEdgeWeightofGraph3419 {
    public static void main(String[] args) {
        int[][] edges={{1,0,1},{2,0,2},{3,0,1},{4,3,1},{2,1,1}};
        System.out.println(minMaxWeight(5,edges,1));
    }
    //不会，实际上就是枚举一个数，然后验证从0出发是否能到每一个node，他是等价于每个node都可以到0（union find也行，看评论说是不行的，关键是想不到把edge都反向来解读）
    //https://leetcode.cn/problems/minimize-the-maximum-edge-weight-of-graph/solutions/3045060/liang-chong-fang-fa-er-fen-da-an-dijkstr-eb7d/
    public static int minMaxWeight(int n, int[][] edges, int threshold) {
        int b=0;
        int e=0;
        Map<Integer, List<int[]>> map=new HashMap<>();
        for(int i=0;i<edges.length;i++){//建图，存edge[i][1]的邻居
            e=Math.max(e,edges[i][2]);
            List<int[]> al=map.getOrDefault(edges[i][1],new ArrayList<>());
            int[] a={edges[i][0],edges[i][2]};
            al.add(a);
            map.put(edges[i][1],al);
        }
        while (b+1<e){
            int m=e-(e-b)/2;
            boolean good=dfs(0,edges,m,n,new HashSet<Integer>(),map);//就是看从0出发能否到所有的点，并且路径权限不能大于m
            if(good){
                e=m;
            }else {
                b=m;
            }
        }
        if(dfs(0,edges,b,n,new HashSet<Integer>(),map)){
            return b;
        }
        if(dfs(0,edges,e,n,new HashSet<Integer>(),map)){
            return e;
        }
        return -1;
    }
    static boolean dfs(int begin, int[][] edges,int m,int n, HashSet<Integer> set,Map<Integer, List<int[]>> map){
        set.add(begin);
        if(set.size()==n){
            return true;
        }
        if(!map.containsKey(begin)){

            return false;
        }
        for(int i=0;i<map.get(begin).size();i++){
            int next=map.get(begin).get(i)[0];
            int weight=map.get(begin).get(i)[1];
            if(weight>m){//应该是大于的就是要删掉的边，所以略过他
                continue;
            }
            if(!set.contains(next)){
                boolean good=dfs(next,edges,m,n,set,map);
                if (good){
                    return true;
                }
            }
        }
        return false;
    }
}
