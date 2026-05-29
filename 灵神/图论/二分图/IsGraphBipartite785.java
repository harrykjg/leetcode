package 灵神.图论.二分图;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IsGraphBipartite785 {
    public static void main(String[] args) {
        int[][] g={{4},{},{4},{4},{0,2,3}};
        System.out.println(isBipartite2(g));

    }
    //比以前的写的好些
    //直接用memo数组0,1,2表示没访问，染色1，染色2.每个点都要便利，因为假如是两坨分开的节点群，只要染色没有冲突，也是bipartite
    //https://leetcode.cn/problems/is-graph-bipartite/solutions/3803670/tu-jie-jiao-ti-ran-se-fa-pythonjavaccgoj-ov27/
    public static boolean isBipartite(int[][] graph) {
        int[] memo=new int[graph.length];
        for (int i=0;i<graph.length;i++){
            if(memo[i]!=0){
                continue;
            }
            if(!dfs(i,1,graph,memo)){
                return false;
            }
        }
        return true;
    }

    static boolean dfs(int cur,int v,int[][] g,int[] memo){
        if(memo[cur]==v){
            return true;
        }else if(memo[cur]==0){
            memo[cur]=v;
        }else{
            return false;
        }

        for (int i=0;i<g[cur].length;i++){
            if(memo[g[cur][i]]==0){
                if(v==1){
                    if(!dfs(g[cur][i],2,g,memo)){
                        return false;
                    }
                }else{
                    if (!dfs(g[cur][i],1,g,memo)){
                        return false;
                    }
                }
            }else{//开始漏了这里
                if(memo[g[cur][i]]==v){
                    return false;
                }
            }
        }
        return true;
    }
    public  static boolean isBipartite2(int[][] graph) {
        Map<Integer, Set<Integer>> map=new HashMap<>();
        for (int i=0;i<graph.length;i++){
            map.putIfAbsent(i,new HashSet<>());
            for (int j=0;j<graph[i].length;j++){
                map.get(i).add(graph[i][j]);
            }
        }
        int[] color=new int[graph.length];

        for (int i=0;i<graph.length;i++){
            if(color[i]!=0){
                 continue;
            }
            if(!dfs2(i,map,color)){
                return false;
            }
        }

        return true;

    }
    //2/28/2026还是写错，我觉得是所有点都要连一起才算biparty，其实不是，题目是说所有存在的边。
    static boolean dfs2(int begin,Map<Integer,Set<Integer>> map,int[] color){
        if(color[begin]==0){
            color[begin]=1;
        }
        Set<Integer> neighbour=map.get(begin);
        if(neighbour!=null){
            for (int nei:neighbour){
                if(color[nei]==0){
                    int nc=color[begin]==1?2:1;
                    color[nei]=nc;
                    if(!dfs2(nei,map,color)){
                        return false;
                    }
                }else{
                    if(color[nei]==color[begin]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
