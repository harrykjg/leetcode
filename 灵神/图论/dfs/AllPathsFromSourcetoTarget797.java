package 灵神.图论.dfs;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourcetoTarget797 {
    public static void main(String[] args) {

    }
    //说了是DAG，貌似就是不会形成环既不会走回头路，所以不需要memo
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> rs=new ArrayList<>();

        dfs(0,graph,new ArrayList<>(),rs);
        return rs;
    }
    void dfs(int start,int[][] g,ArrayList<Integer> al,List<List<Integer>> rs){
        al.add(start);
        if(start==g.length-1){
            rs.add(new ArrayList<>(al));
        }
        for(int i=0;i<g[start].length;i++){
            dfs(g[start][i],g,al,rs);
        }
        al.remove(al.size()-1);
    }

    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        List<List<Integer>> rs=new ArrayList<>();
        boolean[] memo=new boolean[graph.length];
        List<Integer> al=new ArrayList<>();
        al.add(0);
        dfs2(0,graph,memo,al,rs);
        return rs;
    }
    void dfs2(int b,int[][] graph,boolean[] memo,List<Integer> al, List<List<Integer>> rs){
        if(b==graph.length-1){
            rs.add(new ArrayList<>(al));
            return;
        }
        memo[b]=true;
        for (int nei:graph[b]){
            if(!memo[nei]){
                al.add(nei);
                dfs2(nei,graph,memo,al,rs);
                al.remove(al.size()-1);
            }
        }
        memo[b]=false;
    }
}
