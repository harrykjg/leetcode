package 灵神.网格dfs;

public class MaxAreaofIsland695 {
    public static void main(String[] args) {
        int[][] grid={{0,1,0},
                      {0,1,0},
                      {0,1,0}};
        System.out.println(maxAreaOfIsland2(grid));

    }
    //不能像number of island那样设全局变量rs，再dfs用cur+1去做检测最大值
    //因为比如这个形状     1  1
    //                  1            当检测到第一个1时，他会先去右边的1，此时最大值是1+1=2，然后递归回来再到下面的1，此时还是cur+1=2，因此不能得出正确答案


    public static int maxAreaOfIsland(int[][] grid) {
        int rs=0;
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1&&!memo[i][j]){
                    memo[i][j]=true;
                    int count=dfs(i,j,grid,memo);
                    rs=Math.max(rs,count);
                }
            }
        }
        return rs;
    }
    static int dfs(int r,int c,int[][] grid,boolean[][] memo){
        int rs=1;
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        for(int i=0;i<dx.length;i++) {
            int row = r + dx[i];
            int col = c + dy[i];
            if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1 && !memo[row][col]) {
                memo[row][col]=true;
                rs+=dfs( row, col, grid, memo);
            }
        }
        return rs;
    }
    //1/23/2026写union find写的不好
    static int rs=1;
    public static int maxAreaOfIsland2(int[][] grid) {
        UF uf=new UF(grid);

        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    rs=Math.max(rs,uf.counts[uf.find(i*grid[0].length+j)]);//这个容易漏
                    helper(i,j,uf,grid);
                }
            }
        }
        return rs;
    }
    static void helper(int r,int c,UF uf,int[][] grid){
        int[] dx={0,-1,0,1};
        int[] dy={1,0,-1,0};
        int root1=uf.find(r*grid[0].length+c);
        for (int i=0;i<4;i++){
            int row=r+dx[i];
            int col=c+dy[i];

            if(row>=0&&row<grid.length&&col>=0&&col<grid[0].length&&grid[row][col]==1&&root1!= uf.find(row*grid[0].length+col)){
                uf.union(r*grid[0].length+c,row*grid[0].length+col);
                int ra=uf.find(r*grid[0].length+c);
                rs=Math.max(uf.counts[ra],rs);

            }
        }
    }

    static class UF{
        int[] ids;
        int[] counts;
        public UF(int[][] grid){
            ids=new int[grid.length*grid[0].length];
            counts=new int[grid.length*grid[0].length];
            for(int i=0;i<grid.length;i++){
                for (int j=0;j<grid[0].length;j++){
                    int id=i*grid[0].length+j;
                    ids[id]=id;
                    if (grid[i][j]==1){
                        counts[id]=1;
                    }
                }
            }
        }
        public int find(int id){
            if(ids[id]==id){
                return id;
            }
            ids[id]=find(ids[id]);
            return ids[id];
        }
        public void union(int a,int b){
            int ra=find(a);
            int rb=find(b);
            if(ra==rb){
                return;
            }
            if(ra<rb){//这样分大小有点别扭，其实不分也对，见以前的写法

                counts[ra]+=counts[rb];
                counts[rb]=counts[ra];
                ids[rb]=ra;
            }else{

                counts[rb]+=counts[ra];
                counts[ra]=counts[rb];
                ids[ra]=rb;
            }
        }
    }
}
