package Advance2;

/**
 * Created by yufengzhu on 3/31/18.
 */
//3/31/2018九章第二轮
public class NumberofIslands {
    public static void main(String[] args) {
        NumberofIslands ni=new NumberofIslands();
        char[][] g = {{'1', '1', '1'}, { '0', '1', '0'}, {'1', '1', '1'}};
        System.out.print(ni.numIslands2(g));
    }
    //自己的话还是只能想到dfs找岛数量的算法一次过，想不到union find的做法
    public int numIslands(char[][] grid) {
        if(grid.length==0){
            return 0;
        }
        int rs=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);

                    rs++;
                }
            }
        }
        return  rs;
    }
    void dfs(char[][] g,int i,int j){
        g[i][j]='0';
        if(i>0&&g[i-1][j]=='1'){
            g[i-1][j]='0';//这里dfs和g[i-1][j]='0'哪个放前面都对
            dfs(g,i-1,j);
        }
        if(j+1<g[0].length&&g[i][j+1]=='1'){
            g[i][j+1]='0';
            dfs(g,i,j+1);
        }
        if(i+1<g.length&&g[i+1][j]=='1'){
            g[i+1][j]='0';
            dfs(g,i+1,j);
        }
        if(j-1>=0&&g[i][j-1]=='1'){
            g[i][j-1]='0';
            dfs(g,i,j-1);
        }
    }

    //7/11/2018,union find,通过union所有联通点之后，由于unionfind初始化时认为各个点都不联通，所以有n个模块，所有该联通的点联通后就剩下正确的count 了
    public int numIslands2(char[][] grid) {
        if(grid.length==0){
            return 0;
        }
        UnionFind uf=new UnionFind(grid);
        int rs=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){

                    if(i>0&&grid[i-1][j]=='1'){
                        uf.union((i-1)*grid[0].length+j,i*grid[0].length+j);
                    }
                    if(j+1<grid[0].length&&grid[i][j+1]=='1'){
                        uf.union(i*grid[0].length+j+1,i*grid[0].length+j);
                    }
                    if(i+1<grid.length&&grid[i+1][j]=='1'){
                        uf.union((i+1)*grid[0].length+j,i*grid[0].length+j);
                    }
                    if(j-1>=0&&grid[i][j-1]=='1'){
                        uf.union(i*grid[0].length+j-1,i*grid[0].length+j);
                    }
                }
            }
        }
        return  uf.count;
    }
    class UnionFind{
        int count=0;
        int[] parent;
        public UnionFind(char[][] g){
            parent=new int[g.length*g[0].length];
            for(int i=0;i<g.length;i++){
                for(int j=0;j<g[0].length;j++){
                    if(g[i][j]=='1'){
                        parent[i*g[0].length+j]=i*g[0].length+j;
                        count++;
                    }
                }
            }
        }
        int find(int i){
            if(parent[i]==i){
               return i;
            }
            parent[i]=find(parent[i]);
            return parent[i];
        }
        void union(int a,int b){
            int pa=find(a);
            int pb=find(b);
            if(pa!=pb){
                parent[pa]=pb;//这里容易写错，之前写成parent[a]就错了
                count--;
            }
        }
    }
}
