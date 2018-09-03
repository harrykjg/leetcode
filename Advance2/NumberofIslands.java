package Advance2;

import java.util.Arrays;

/**
 * Created by yufengzhu on 3/31/18.
 */
//3/31/2018九章第二轮
public class NumberofIslands {
    public static void main(String[] args) {
        NumberofIslands ni=new NumberofIslands();
        char[][] g = {{'1', '1', '1','0'}, { '0','0', '1', '0'}, {'1', '1', '0','0'},{'1','1','1','0'}};
        System.out.print(ni.numIslands3(g));
        for(int i=0;i<g.length;i++){
            for(int j=0;j<g[0].length;j++){
                System.out.print(g[i][j]+" ");
            }
            System.out.println();
        }
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
    //Uber变形，找到最大的那个岛，并且把1变成0，想的是用并查集，但是如何统计集合大小不知道，参考这个的思路，代码没看他的https://blog.csdn.net/wangwei6125/article/details/68954171
    //思路就是用一个count数组去记录这个节点有几个联通的节点的数量.可以找到count里最大的数就是最大的那个岛，但是怎么再找到这个最大的岛的所有节点呢,就要再找到这个最大岛的根结点，再遍历整个matrix的
    //所有id，看谁的id是这个最大岛的根结点的id，再把他们的id通过除法和取余找到x和y的坐标
    public int numIslands3(char[][] grid) {
        int max=0;

        Union un=new Union(grid[0].length*grid.length);
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    int indexa=i*grid[0].length+j;
//                    grid[i][j]='0';//先不把他射程'0'也行
                    if(i>0&&grid[i-1][j]=='1'){
                        un.union((i-1)*grid[0].length+j,i*grid[0].length+j);
                        max=Math.max(max,un.count[un.find((i-1)*grid[0].length+j)]);
                    }
                    if(j+1<grid[0].length&&grid[i][j+1]=='1'){
                        un.union(i*grid[0].length+j+1,i*grid[0].length+j);
                        max=Math.max(max,un.count[un.find(i*grid[0].length+j+1)]);
                    }
                    if(i+1<grid.length&&grid[i+1][j]=='1'){
                        un.union((i+1)*grid[0].length+j,i*grid[0].length+j);
                        max=Math.max(max,un.count[un.find((i+1)*grid[0].length+j)]);
                    }
                    if(j-1>=0&&grid[i][j-1]=='1'){
                        un.union(i*grid[0].length+j-1,i*grid[0].length+j);
                        max=Math.max(max,un.count[un.find(i*grid[0].length+j-1)]);
                    }
                }
            }
        }
        int index=-1;
        for(int i=0;i<un.count.length;i++){
            if(un.count[i]==max){
                index=i;//找到最大的岛的根id,其实也可以省掉这个for loop，之前判断最大max的时候就记录下来，那样的话就只能记录一个，如果存在多个最大的话那就可能用一个list存最大的，再说吧
            }
        }
        for(int i=0;i<un.ids.length;i++){
            if(un.find(i)==index){
                int xx=i/grid[0].length;
                int yy=i%grid[0].length;
                grid[xx][yy]='0';
            }
        }
        return max;
    }
    class Union{
        int[] ids;
        int[] count;

        public Union(int size){
            ids=new int[size];
            count=new int[size];
            Arrays.fill(count,1);//count初始化为1
            for(int i=0;i<ids.length;i++){
                ids[i]=i;
            }
        }
        int find(int a){
            if(ids[a]==a){
                return a;
            }
            ids[a]=find(ids[a]);//这一步记错了！
            return ids[a];
        }
        void union(int a,int b){
            int ra=find(a);
            int rb=find(b);
            if(ra!=rb){

                ids[rb]=ra;

                count[ra]+=count[rb];//b节点的数量加到a上面去

            }
        }

    }
}
