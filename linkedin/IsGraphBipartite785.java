package linkedin;

import java.util.Arrays;

public class IsGraphBipartite785 {
    //2/1/2026还是写的不好,没想清楚，caller这边的访问过的就不用访问的。因为dfs应该能reach然后判断。
    public boolean isBipartite(int[][] graph) {
        int[] memo=new int[graph.length];
        Arrays.fill(memo,-1);
        for (int i=0;i<graph.length;i++){
                if(memo[i]!=-1){
                    continue;
                }
                boolean ok=dfs(i,memo,graph,1);
                if(!ok){
                    return false;
                }
        }
        return true;
    }
    boolean dfs(int i,int[] memo,int[][] graph,int color){
        if (memo[i]==-1){
            memo[i]=color;
        }else if(memo[i]!=color){//不用这个也对，因为caller一定是看到i没染色才进来了
            return false;
        }
        for(int next:graph[i]){
            boolean ok=true;
            if(memo[next]==-1){
                if(color==1){
                    ok=dfs(next,memo,graph,2);
                }else{
                    ok=dfs(next,memo,graph,1);
                }
                if(!ok){
                    return false;
                }
            }else{
                if(memo[next]==color){
                    return false;
                }
            }

        }
        return true;
    }
}
