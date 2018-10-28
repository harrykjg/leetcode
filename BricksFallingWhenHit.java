import java.util.*;

/**
 * Created by yufengzhu on 7/4/18.
 */
//图算法
public class BricksFallingWhenHit {
    public static void main(String[] args){
        BricksFallingWhenHit bf=new BricksFallingWhenHit();
//        int[][] grid={{0,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1},{0,0,0,1,0,0,1,1,1},{0,0,1,1,0,1,1,1,0},{0,0,0,0,0,1,1,1,1},{0,0,0,0,0,0,0,1,0}};
//        int[][] hit={{1,8},{2,1},{1,4},{3,0},{3,4},{0,7},{1,6}};

        int[][] grid={{1,0,1},{1,1,1}};
        int[][] hit={{0,0},{0,2},{1,1}};
       System.out.print( bf.hitBricks2(grid,hit));
    }

    //题意就是一堵墙，删掉一个砖头，如果和他挨边的砖头没有连上第一行的砖头的话就会掉
    //自己想的会超时,就是对每次hit，做上下左右四个bfs，每个bfs返回一个int代表几个brick被删掉，要想清楚bfs是单独的，所以memo也是分开的，比如第一个bfs和第二个bfs都会遇到某个点，如果这个点被会被删除的话
    //第一个bfs已经把它删除了，那么第二个bfs也不会去这个点了（只去为1的点）。再想，如果一个点bfs走过一条向上的路径到达第一行说明安全了，另一个点bfs也要路过第一个路径到达第一行，如果说共用memo的话就不行了
    //下面是别人的解法
    //https://blog.csdn.net/brazy/article/details/79678332
    //https://leetcode.com/problems/bricks-falling-when-hit/discuss/119829/Python-Solution-by-reversely-adding-hits-bricks-back
    //https://leetcode.com/problems/bricks-falling-when-hit/solution/ 答案思路是反过来，先复制出一个矩阵和原来的举证相同，然后把hit的点设成空，然后再从最后一个hit的位置开始，加上一个brick到这个位置，
    //看能连上几点brick
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int[] rs=new int[hits.length];

        int index=0;
        for(int[] hit:hits){
            int row=hit[0];
            int col=hit[1];

            grid[row][col]=0;
            int cur=0;
            if(row>0&&grid[row-1][col]==1){
                boolean[] memo=new boolean[grid.length*grid[0].length];
                cur+=check(grid,row-1,col,memo);
            }
            if(col+1<grid[0].length&&grid[row][col+1]==1){
                boolean[] memo=new boolean[grid.length*grid[0].length];
                cur+=check(grid,row,col+1,memo);
            }
            if(row+1<grid.length&&grid[row+1][col]==1){
                boolean[] memo=new boolean[grid.length*grid[0].length];
                cur+=check(grid,row+1,col,memo);
            }
            if(col-1>=0&&grid[row][col-1]==1){
                boolean[] memo=new boolean[grid.length*grid[0].length];
                cur+=check(grid,row,col-1,memo);
            }
            rs[index]=cur;
            index++;
        }
        return rs;

    }
    int check(int[][] grid,int row,int col,boolean[] memo){
        int cord=row*grid[0].length+col;
        if(memo[cord]==true){
            return 0;
        }
        ArrayList<int[]> dispear=new ArrayList<>();//还有一个难点就是bfs到最后才发现这一个路径的点都要删除，怎么处理？我就想的一个办法就是维持一个count，用来记录
        //路径的长度，和一个arraylist记录整个路径，如果这个路径能达到第一行则count就是0返回了，如果不能达到第一行那么bfs所走过的所有路径都已经被记录下来，再遍历把他们都删除

//        memo[cord]=true;
        int count=0;
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{row,col});
        while (!q.isEmpty()){
            int[] cur=q.poll();
            int r=cur[0];
            int c=cur[1];
            if(memo[r*grid[0].length+c]==true){
                continue;
            }
            memo[r*grid[0].length+c]=true;
            if(r==0&&grid[r][c]==1){
                return 0;
            }
            dispear.add(new int[]{r,c});
            count++;
            if(r>0&&grid[r-1][c]==1&&memo[(r-1)*grid[0].length+c]==false){
                q.offer(new int[]{r-1,c});
            }
            if(c+1<grid[0].length&&grid[r][c+1]==1&&memo[r*grid[0].length+c+1]==false){
                q.offer(new int[]{r,c+1});
            }
            if(r+1<grid.length&&grid[r+1][c]==1&&memo[(r+1)*grid[0].length+c]==false){
                q.offer(new int[]{r+1,c});
            }
            if(c-1>=0&&grid[r][c-1]==1&&memo[r*grid[0].length+c-1]==false){
                q.offer(new int[]{r,c-1});
            }

        }
        if(count!=0){
            for(int[] d:dispear){
                grid[d[0]][d[1]]=0;
            }
        }

        return count;

    }

    int[] ids;
    UnionFind uf;
    //10／21／2018,试着顺着用unionfind,貌似不行，关键是在hit之前就union了所有值为1的点，然后再hit的话，比如原先这个分支是连着roof的，现在不连了，没法快速判断因为删掉了这个hit的点而不连roof了
    //还是看回以前第一个帖子,debug了一天
    public int[] hitBricks2(int[][] grid, int[][] hits) {
        int[] rs=new int[hits.length];
        ids=new int[grid.length*grid[0].length];
        Arrays.fill(ids,-1);
        uf=new UnionFind(grid.length*grid[0].length);
        int[][] copy=new int[grid.length][grid[0].length];
        for(int i=0;i<copy.length;i++){
            for(int j=0;j<copy[0].length;j++){
                copy[i][j]=grid[i][j];
            }
        }
        for(int i=0;i<hits.length;i++){
            copy[hits[i][0]][hits[i][1]]=0;
        }
        for(int i=0;i<copy.length;i++){
            for(int j=0;j<copy[0].length;j++){
                if(copy[i][j]==0){
                    continue;
                }
                if(i>0&&copy[i-1][j]==1){//只和值为1的东西联通
                    uf.union(i*copy[0].length+j,(i-1)*copy[0].length+j);
                }
                if(j+1<copy[0].length&&copy[i][j+1]==1){
                    uf.union(i*copy[0].length+j,(i)*copy[0].length+j+1);
                }
                if(i+1<copy.length&&copy[i+1][j]==1){
                    uf.union(i*copy[0].length+j,(i+1)*copy[0].length+j);
                }
                if(j>0&&copy[i][j-1]==1){
                    uf.union(i*copy[0].length+j,(i)*copy[0].length+j-1);
                }
            }
        }

        int index=hits.length-1;
        for(int i=hits.length-1;i>=0;i--){//想清楚为啥要倒着来，因为先把所有brick打掉了，如果再从头开始填上砖，那么这个砖联系的其他转按理来说是还没打掉的，所以说不行，倒着来就对了
            int row=hits[i][0];
            int col=hits[i][1];
            boolean flag=false;
            if(row==0){
                flag=true;
            }else{
               flag =canLink(copy,row,col);
            }
            if(grid[row][col]==0){//这里也容易漏，如果原来的grid的这个位置本来就是0，那么就不存在把这个点变成1这个行为，也不存在把这个1和周围的点union这个行为，因为如果union的话，这个点是0，但是count会加上他，会错
                index--;
                continue;
            }

            int id=uf.find(row*copy[0].length+col);

            int temp=0;
            if(row>0&&copy[row-1][col]==1){//现在检查hit的砖的四周的砖，如果四周的砖和roof不联系，并且要通过当前hit的砖才能联系上roof的话才能加上，即当前hit的砖得连得上roof才行，这个很容易漏。举个例子
                int idnow=uf.find((row-1)*copy[0].length+col);  //{1,0,1}{1,1,1},hit{0,0},{0,2},{1,1}。那怎么检查当前hit的点是否和roof相连呢？我这里设了个flag，如果row=0那么就是第一行，否则
                if(flag&&idnow>=copy[0].length&&id!=idnow){    //就用一个canLink方法检查，canLink无非就是检测row，col的周围四个点的id能不能连上roof（开始思维有误区，以为一定要union才能检查连通性）
                    temp+=uf.counts[idnow];
                }
                uf.union(id,idnow);
            }
            if(col+1<copy[0].length&&copy[row][col+1]==1){
                int idnow=uf.find((row)*copy[0].length+col+1);
                if(flag&&idnow>=copy[0].length&&id!=idnow){
                    temp+=uf.counts[idnow];
                }
                uf.union(id,idnow);
            }
            if(row+1<copy.length&&copy[row+1][col]==1){
                int idnow=uf.find((row+1)*copy[0].length+col);
                if(flag&&idnow>=copy[0].length&&id!=idnow){
                    temp+=uf.counts[idnow];
                }
                uf.union(id,idnow);
            }
            if(col>0&&copy[row][col-1]==1){
                int idnow=uf.find((row)*copy[0].length+col-1);
                if(flag&&idnow>=copy[0].length&&id!=idnow){
                    temp+=uf.counts[idnow];
                }
                uf.union(id,idnow);
            }
            rs[index--]=temp;
            copy[row][col]=1;
        }
        return rs;
    }
    boolean canLink(int[][] grid,int r,int c){
        if(grid[r][c]==1&&r==0){
            return true;
        }

        if(r>0&&grid[r-1][c]==1){
            int idtemp=uf.find((r-1)*grid[0].length+c);
            if(idtemp<grid[0].length){
                return true;
            }
        }
        if(c+1<grid[0].length&&grid[r][c+1]==1){
            int idtemp=uf.find((r)*grid[0].length+c+1);
            if(idtemp<grid[0].length){
                return true;
            }
        }
        if(c>0&&grid[r][c-1]==1){
            int idtemp=uf.find((r)*grid[0].length+c-1);
            if(idtemp<grid[0].length){
                return true;
            }
        }
        if(r+1<grid.length&&grid[r+1][c]==1){
            int idtemp=uf.find((r+1)*grid[0].length+c);
            if(idtemp<grid[0].length){
                return true;
            }
        }
        return false;
    }

    class UnionFind {
        int count;
        int[] counts;
        public UnionFind(int a){
            counts=new int[a];
            Arrays.fill(counts,1);
        }
        int find(int a) {
            if(ids[a]==-1){
                ids[a]=a;
                return a;
            }
            if (ids[a] == a) {
                return a;
            }
            int rs = find(ids[a]);
            ids[a] = rs;
            return rs;
        }

        void union(int a, int b) {
            int ida = find(a);
            int idb = find(b);
            if (ida!=idb){
                if(ida<idb){//把id大的那个并到id小的那个，这样可以用id是否小于grid【0】。length来看不断是不是连第一行
                    counts[ida]+=counts[idb];
                    counts[idb]=0;
                    ids[idb]=ida;
                }else{
                    counts[idb]+=counts[ida];
                    counts[ida]=0;
                    ids[ida]=idb;
                }
            }
        }
    }
}
