package 灵神.图论.拓扑排序;

import java.util.*;

public class FindEventualSafeStates802 {
    public static void main(String[] args) {
        int[][] g={{1,2},{2,3},{5},{0},{5},{},{}};
        List<Integer> rs=eventualSafeNodes(g);
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
}
