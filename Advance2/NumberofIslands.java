package Advance2;

/**
 * Created by yufengzhu on 3/31/18.
 */
//3/31/2018九章第二轮
public class NumberofIslands {
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

    //union find
    public int numIslands2(char[][] grid) {

    }
}
