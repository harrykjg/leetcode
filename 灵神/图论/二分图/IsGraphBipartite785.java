package 灵神.图论.二分图;

public class IsGraphBipartite785 {
    public static void main(String[] args) {
        int[][] g={{1,2,3},{0,2},{0,1,3},{0,2}};
        System.out.println(isBipartite(g));

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
}
