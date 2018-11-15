

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 502575560 on 6/27/16.
 */
public class NumberofIslands {
    public static void main(String[] args) {
        char[][] g = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.print(numIslands(g));
    }
//    2016年6月26 dfs一次过,bfs 改了2下过了?
    static int count=0;
    public static int numIslands(char[][] grid) {
        if(grid.length==0){
            return 0;
        }
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    bfs(grid,i,j);
                    count++;
                    continue;
                }
            }
        }
        return count;

    }
    public static void dfs(char[][] g,int i,int j){
        g[i][j]='0';
        if(i>0&&g[i-1][j]=='1'){
            dfs(g,i-1,j);
        }
        if(j+1<g[0].length&&g[i][j+1]=='1'){
            dfs(g,i,j+1);
        }
        if(i+1<g.length&&g[i+1][j]=='1'){
            dfs(g,i+1,j);
        }
        if(j-1>=0&&g[i][j-1]=='1'){
            dfs(g,i,j-1);
        }

    }
    public static void bfs(char[][] g, int i,int j){
        Queue<ilandPair> q=new LinkedList<>();
        q.add(new ilandPair(i,j));
        while(!q.isEmpty()){
            ilandPair iland=q.poll();
            g[iland.i][iland.j]='0';
            if(iland.i>0&&g[iland.i-1][iland.j]=='1'){
                g[iland.i-1][iland.j]='0';//注意这里开始漏了就会导致超时,应该在这里就先把他改成0的
                q.add(new ilandPair(iland.i-1,iland.j));
            }
            if(iland.j+1<g[0].length&&g[iland.i][iland.j+1]=='1'){
                g[iland.i][iland.j+1]='0';
                q.add(new ilandPair(iland.i,iland.j+1));
            }
            if(iland.i+1<g.length&&g[iland.i+1][iland.j]=='1'){
                g[iland.i+1][iland.j]='0';
                q.add(new ilandPair(iland.i+1,iland.j));
            }
            if(iland.j-1>=0&&g[iland.i][iland.j-1]=='1'){
                g[iland.i][iland.j-1]='0';
                q.add(new ilandPair(iland.i,iland.j-1));
            }
        }
    }
    //11/8/2018
    public static int numIslands2(char[][] grid) {
        if(grid.length==0){
            return count;
        }
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    dfs2(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    static void dfs2(char[][] grid,int row,int col){
        grid[row][col]='0';
        if(row>0&&grid[row-1][col]=='1'){
            dfs2(grid,row-1,col);
        }
        if(col+1<grid[0].length&&grid[row][col+1]=='1'){
            dfs2(grid,row,col+1);
        }
        if(row+1<grid.length&&grid[row+1][col]=='1'){
            dfs2(grid,row+1,col);
        }
        if(col-1>=0&&grid[row][col-1]=='1'){
            dfs2(grid,row,col-1);
        }

    }
}




class ilandPair{
    int i;
    int j;
    public ilandPair(int a, int b){
        i=a;
        j=b;
    }
}


