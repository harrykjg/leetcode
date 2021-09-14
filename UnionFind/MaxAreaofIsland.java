package UnionFind;

import java.util.Arrays;

public class MaxAreaofIsland {
    public static void main(String[] args){
        int[][] m={{0,0,1,0,0,0,0,1,0,0,0,0,0},
                  {0,0,0,0,0,0,0,1,1,1,0,0,0},
                  {0,1,1,0,1,0,0,0,0,0,0,0,0},
                  {0,1,0,0,1,1,0,0,1,0,1,0,0},
                  {0,1,0,0,1,1,0,0,1,1,1,0,0},//这里有分叉
                  {0,0,0,0,0,0,0,0,0,0,1,0,0},
                  {0,0,0,0,0,0,0,1,1,1,0,0,0},
                  {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        MaxAreaofIsland ma=new MaxAreaofIsland();
        System.out.println(ma.maxAreaOfIsland2(m));
    }
    //7/9/2021。明显并查集，但是写起来不顺，这种还是直接dfs容易，时间和空间复杂度都是grid的长*宽
    // 思路就是维护一个count数组，联合的时候把他们的count也加起来。主要是怎么避开把已经联合的节点再联合，那么就只能看他们id是否相同。而number of island不需要管，因为他不存在把count加起来的操作
    public int maxAreaOfIsland(int[][] grid) {
        int rs=0;
        uf uf=new uf(grid.length*grid[0].length);
        int col=grid[0].length;
        int row=grid.length;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    uf.ids[i*col+j]=i*col+j;//开始想着不初始化，直接后面遇到1再设id,结果不对
                    uf.count[i*col+j]=1;
                }
            }
        }
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    if (i>0&&grid[i-1][j]==1&&(uf.findId((i-1)*col+j)!=uf.findId(i*col+j))){//没连起来过的才能联合
                        uf.union(i*col+j,(i-1)*col+j);
                    }
                    if (j+1<col&&grid[i][j+1]==1&&(uf.findId(i*col+j+1)!=uf.findId(i*col+j))){
                        uf.union(i*col+j,i*col+j+1);
                    }
                    if (i+1<row&&grid[i+1][j]==1&&(uf.findId((i+1)*col+j)!=uf.findId(i*col+j))){
                        uf.union(i*col+j,(i+1)*col+j);
                    }
                    if (j>0&&grid[i][j-1]==1&&(uf.findId(i*col+j)!=uf.findId(i*col+j-1))){
                        uf.union(i*col+j,i*col+j-1);
                    }
                    rs=Math.max(rs,uf.count[uf.findId(i*col+j)]);//还有这里容易错，count[uf.findId(i*col+j)]而不是count[i*col+j])，只有看根结点的count才能保证是对的count
                }
            }
        }
        return rs;
    }
    //8/15/2021写个dfs的,可以visit之后设成0，就不用memo数组了.原来我这个dfs的写法是不对的！比如例子那个最大的岛，他在上面标注那里，当走到那个cell时，
    //他有分叉是向上走还是向下走，所以说dfs是不用带一个当前的数的，而是默认进入一个dfs他本身就是1，然后再加上四个方向各自dfs返回的count
    //https://leetcode.com/problems/max-area-of-island/discuss/108533/JavaC%2B%2B-Straightforward-dfs-solution
    //https://leetcode.com/problems/max-area-of-island/discuss/108529/Very-simple-DFS-Java-solution 带了一个count但是也可以不带
    //https://blog.csdn.net/whitesun123/article/details/79654002
    int rsx=0;
    public int maxAreaOfIsland2(int[][] grid) {
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        int rs=0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1&&!memo[i][j]){
                    memo[i][j]=true;
                    int count=dfs2(i,j,grid,memo);
                    rs=Math.max(rs,count);
                }
            }
        }
        return rs;
    }

    int dfs2(int i,int j,int[][] grid,boolean[][] memo){
        int rs=1;

        if (i>0&&grid[i-1][j]==1&&memo[i-1][j]==false){
            memo[i-1][j]=true;
            rs+=dfs2(i-1,j,grid,memo);
        }
        if (j+1<grid[0].length&&grid[i][j+1]==1&&memo[i][j+1]==false){
            memo[i][j+1]=true;
            rs+=dfs2(i,j+1,grid,memo);
        }
        if (i+1<grid.length&&grid[i+1][j]==1&&memo[i+1][j]==false){
            memo[i+1][j]=true;
            rs+=dfs2(i+1,j,grid,memo);
        }
        if (j>0&&grid[i][j-1]==1&&memo[i][j-1]==false){
            memo[i][j-1]=true;
            rs+=dfs2(i,j-1,grid,memo);
        }
        return rs;
    }

    void dfs(int cur,int i,int j,int[][] grid,boolean[][] memo){//这个是错误的写法！
        rsx=Math.max(rsx,cur);

        if (i>0&&grid[i-1][j]==1&&memo[i-1][j]==false){
            memo[i-1][j]=true;
            dfs(cur+1,i-1,j,grid,memo);
        }
        if (j+1<grid[0].length&&grid[i][j+1]==1&&memo[i][j+1]==false){
            memo[i][j+1]=true;
            dfs(cur+1,i,j+1,grid,memo);
        }
        if (i+1<grid.length&&grid[i+1][j]==1&&memo[i+1][j]==false){
            memo[i+1][j]=true;
            dfs(cur+1,i+1,j,grid,memo);
        }
        if (j>0&&grid[i][j-1]==1&&memo[i][j-1]==false){
            memo[i][j-1]=true;
            dfs(cur+1,i,j-1,grid,memo);
        }
    }
    //8/29/2021
    public int maxAreaOfIsland3(int[][] grid) {
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        int rs=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1&&memo[i][j]==false){
                    int temp=dfs3(i,j,grid,memo);
                    rs=Math.max(rs,temp);
                }
            }
        }
        return rs;
    }
    int dfs3(int row,int col,int[][] grid,boolean[][] memo){
        memo[row][col]=true;
        int rs=1;
        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};
        for(int i=0;i<4;i++){
            int r=row+dx[i];
            int c=col+dy[i];
            if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length&&memo[r][c]==false&&grid[r][c]==1){
                rs+=dfs(r,c,grid,memo);

            }
        }
        return rs;
    }

}
class uf{
    int[] count;
    int[] ids;
    public uf(int size){
        count=new int[size];
        ids=new int[size];
        Arrays.fill(ids,-1);
    }
    int findId(int id){
        if (ids[id]==id){
            return id;
        }
        ids[id]=findId(ids[id]);
        return ids[id];
    }

    void union(int a,int b){
        int id1=findId(a);
        int id2=findId(b);
        if (id1==id2){
            return;
        }
        int total=count[id1]+count[id2];
        count[id1]=total;
        count[id2]=total;
        ids[id2]=id1;
    }


}
