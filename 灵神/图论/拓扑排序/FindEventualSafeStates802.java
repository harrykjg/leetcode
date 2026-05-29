package 灵神.图论.拓扑排序;

import java.util.*;

public class FindEventualSafeStates802 {
    public static void main(String[] args) {
        int[][] g={{1,2},{2,3},{5},{0},{5},{},{}};
        FindEventualSafeStates802 fe=new FindEventualSafeStates802();
        List<Integer> rs=fe.eventualSafeNodes2(g);
        for(int i:rs){
            System.out.println(i);
        }
    }
    //这个灵神是拓扑排序的归类但是不好想，因为是反着的。自己写的dfs，就是一个节点如果有环就不行了。好像和答案的三色标记法差不多
    //https://leetcode.cn/problems/find-eventual-safe-states/solutions/916155/zhao-dao-zui-zhong-de-an-quan-zhuang-tai-yzfz/
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> rs=new ArrayList<>();
        Map<Integer,Boolean> map=new HashMap<Integer,Boolean>();
        for (int i=0;i<graph.length;i++){
            if(map.containsKey(i)&&map.get(i)){
                rs.add(i);
                continue;
            }
            if(valid(i,graph,map)){
                rs.add(i);
            }
        }
        return rs;
    }
    static boolean valid(int cur,int[][] graph,Map<Integer,Boolean> map){
        map.put(cur,false);//先假设不行，然后有一个邻居不行就返回false
        for (int i=0;i<graph[cur].length;i++){
            if(map.containsKey(graph[cur][i])){//邻居已经访问过了
                if (map.get(graph[cur][i])){
                    continue;
                }else {//有环
                    return false;
                }
            }else{//邻居没访问过，去访问
                if(!valid(graph[cur][i],graph,map)){
                    map.put(cur,false);
                    return false;
                }
            }

        }
        map.put(cur,true);
        return true;
    }

    //3/1/2026 gpt说这不能用dfs加memo找环，那是用在无向图的，这个是有向的因此要三色法，这不是二分图的染色法。可以再练练
    public  List<Integer> eventualSafeNodes2(int[][] graph) {
        Map<Integer,Boolean> map=new HashMap<>();//用来记录这个点是不是有环
        int[] memo=new int[graph.length];//三种状态0,1,2代表没访问，正在dfs，已经访问
        List<Integer> rs=new ArrayList<>();
        for (int i=0;i<graph.length;i++){
            if(!dfs2(i,graph,map,memo)){
                rs.add(i);
            }
        }
        return rs;
    }
    //dfs2意义是看有没环,true就是有环
    boolean dfs2(int begin,int[][] graph,Map<Integer,Boolean> map, int[] memo){
        if(memo[begin]==2){
            return map.get(begin);//
        }
        memo[begin]=1;
        boolean rs=false;
        for (int i=0;i<graph[begin].length;i++){
            if(memo[graph[begin][i]]==1){
                rs=true;
                break;
            }else {
                if(dfs2(graph[begin][i],graph,map,memo)){
                    rs= true;
                    break;
                }
            }
        }
        memo[begin]=2;
        map.put(begin,rs);
        return rs;
    }
}
