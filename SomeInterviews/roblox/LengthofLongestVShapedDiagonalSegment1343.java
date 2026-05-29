package SomeInterviews.roblox;

public class LengthofLongestVShapedDiagonalSegment1343 {
    static void main() {
        int[][] g={{1,1,1,2,0,0},{0,0,0,0,1,2}};
        LengthofLongestVShapedDiagonalSegment1343 lo=new LengthofLongestVShapedDiagonalSegment1343();
        System.out.println(lo.lenOfVDiagonal(g));
    }
    //memo设成三维的，第三维就是长度是2，代表turn了0次和一次的状态。而且这个memo不能通用的，每个dfs自己建得有一个memo，画图理解一下，比如一个是从左上走右下，
    //后面又遇到一个1，可以走左下方向，就有可能和之前那条相交，但是不应该受影响.后来又想由于是走对角线，那么除非走反方向不然不会重复，并且只能转90度所以也不能走
    //反方向.所以dfs的时候是不用去重的，但是这样写超时，加memo，加memo又不太好想， memo[r][c][k]的意义是从这点出发能走多远的,是按每个方向分的！我没写完
    int rs=0;
    public int lenOfVDiagonal(int[][] grid) {
        int[][][] memo=new int[grid.length][grid[0].length][4];
        int[] dx={1,1,-1,-1};
        int[] dy={1,-1,-1,1};
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    for(int k=0;k<4;k++){
                        int[] dir={dx[k],dy[k]};
                        dfs(1,i,j,k,grid,memo,false);

                    }
                }
            }
        }
        return rs;
    }
    //上面dfs已经走四个方向了，这里就不用走四个方向了，无非就是顺着走或者左拐或右拐，右拐90度就是dir的（index+1）%4，自己列下四个方向就看得出来
    int dfs(int cur,int row,int col,int dir,int[][] grid,int[][][] memo,boolean used){
        rs=Math.max(cur,rs);
        int[] dx={1,1,-1,-1};
        int[] dy={1,-1,-1,1};
        int now=grid[row][col];
        int r=row+dx[dir];
        int c=col+dy[dir];//同方向
        int curRs=cur;
        if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length){
            if(now==0&&grid[r][c]==2){
                curRs= dfs(cur+1,r,c,dir,grid,memo,used);
            }else if(now==2&&grid[r][c]==0){
                curRs= dfs(cur+1,r,c,dir,grid,memo,used);
            }else if(now==1&&grid[r][c]==2){
                curRs= dfs(cur+1,r,c,dir,grid,memo,used);
            }
        }
        int turnRs=cur;
        if(!used){//可以转
            dir=(dir+1)%4;
            r=row+dx[dir];
            c=col+dy[dir];
            if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length){
                if(now==0&&grid[r][c]==2){
                    turnRs=dfs(cur+1,r,c,dir,grid,memo,true);
                }else if(now==2&&grid[r][c]==0){
                    turnRs=dfs(cur+1,r,c,dir,grid,memo,true);
                }else if(now==1&&grid[r][c]==2){
                    turnRs=dfs(cur+1,r,c,dir,grid,memo,true);
                }
            }

        }
        curRs=Math.max(curRs,turnRs);

        memo[row][col][dir]=curRs;
        return curRs
    }
}
