package linkedin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MakingALargeIsland827 {
    static void main() {
        int[][] g={{1,1},{1,0}};
        MakingALargeIsland827 ma=new MakingALargeIsland827();
        System.out.println(ma.largestIsland(g));
    }
    //2/7/2026改了几次ac，就是union find先找出所有岛。再遍历grid，找每一个0的点，看他的四周的岛的count加在一起找最大的数，而看四周岛
    //的时候可能有两个方向的岛已经是联通的，因此还要去重
    int rs=0;
    public int largestIsland(int[][] grid) {
        UF uf=new UF(grid.length,grid[0].length);
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1&&!memo[i][j]){
                    dfs(i,j,memo,grid,uf);
                    rs=Math.max(rs,1);
                }
            }
        }
        for (int i=0;i<grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j]==0){
                    int[] dx={0,1,0,-1};
                    int[] dy={1,0,-1,0};
                    int temp=1;
                    //难点在这，从这个0点看四个方向，不能直接把每个方向的count加上来，因为可能有两个方向本来就是连通的。因此需要记录四个方向是否
                    //已经联通过，就是找parent id
                    Set<Integer> set=new HashSet<>();
                    for (int k=0;k<4;k++){
                        int row=dx[k]+i;
                        int col=dy[k]+j;
                        if(row>=0&&row<grid.length&&col>=0&&col<grid[0].length&&grid[row][col]==1){
                            int root=uf.find(row*grid[0].length+col);
                            if(!set.contains(root)){
                                set.add(root);
                                temp+=uf.count[uf.find(row*grid[0].length+col)];
                            }

                        }
                    }
                    rs=Math.max(rs,temp);
                }
            }
        }
        return rs;
    }

    void dfs(int r, int c,boolean[][] memo,int[][] grid,UF uf){
        int n=grid[0].length;
        memo[r][c]=true;
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        for (int i=0;i<4;i++){
            int row=dx[i]+r;
            int col=dy[i]+c;
            if(row>=0&&row<grid.length&&col>=0&&col<grid[0].length&&!memo[row][col]&&grid[row][col]==1){
                int fa=uf.find(r*n+c);
                uf.union(r*n+c,row*n+col);
                memo[row][col]=true;
                rs=Math.max(rs,uf.count[fa]);
                dfs(row,col,memo,grid,uf);
            }
        }

    }

    class UF{
        int[] ids;
        int[] count;
        public UF(int m,int n){
            ids=new int[m*n];
            count=new int[m*n];
            Arrays.fill(count,1);
            for (int i=0;i<m;i++){
                for (int j=0;j<n;j++){
                    ids[i*n+j]=i*n+j;
                }
            }
        }
        int find(int a){
            if(ids[a]==a){
                return a;
            }
            ids[a]=find(ids[a]);
            return ids[a];
        }
        void union(int a,int b){
            int ra=find(a);
            int rb=find(b);
            int count1=count[ra];
            int count2=count[rb];
            if(ra!=rb){
                ids[rb]=ra;
            }
            count[ra]+=count2;
            count[rb]+=count2;//这个不对吧？
        }

    }

    //5/20/2026 想法是有得，懒得写完了
    public int largestIsland2(int[][] grid) {
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        int[] ids=new int[grid.length*grid[0].length];
        int m=grid.length;
        int n=grid[0].length;
        for (int i=0;i<grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int x=i*n+j;
                ids[x]=x;
            }
        }
        UF2 uf=new UF2(ids);
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1&&!memo[i][j]){
                    dfs2(grid,i,j,memo,uf);
                    rs=Math.max(rs,1);
                }
            }
        }

        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){

            }
        }


    }
    class UF2{
        int[] count;
        int[] ids;
        public UF2(int[] ids){
            this.ids=ids;
            Arrays.fill(count,1);
        }
        int find(int x){
            if(ids[x]==x){
                return x;
            }
            ids[x]=find(ids[x]);
            return ids[x];
        }
        void union(int a,int b){
            int root1=find(a);
            int root2=find(b);
            if(root2!=root1){
                ids[root1]=root2;
                count[root2]+=count[root1];
                count[root1]=count[root2];//不写也行吧
            }
        }

    }
}
