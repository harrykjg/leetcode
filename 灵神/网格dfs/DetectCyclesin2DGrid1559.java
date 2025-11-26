package 灵神.网格dfs;

public class DetectCyclesin2DGrid1559 {
    public static void main(String[] args) {
        char[][] g={{'c','a','d'},
                    {'a','a','a'},
                    {'a','a','d'},
                    };
        System.out.println(containsCycle(g));

    }

    //我想的是记录路径，最后会不会到达起始的点，结果不行，
    //                      如  x a x
    //                         a a a
    //                         a a x  这种上面凸起来的a开始，走到最后是【1,0】这个a往右看是【1，1】，不是起始点【0,1】，因此找不到环，实际上是有环的
    //
    //https://leetcode.cn/problems/detect-cycles-in-2d-grid/solutions/385356/java-bing-cha-ji-by-xiaoxi666/ 并查集写法，但是思路还是从一个点开始只能
    //访问右边和下面，如果这样走下来应该是不会走到左边和上方向的点的，但初始的便利grid的方法继续走如果最后发现merge的时候他们已经是相同的parent就说明是有环的了
    //https://leetcode.com/problems/detect-cycles-in-2d-grid/solutions/805677/dfs-simple-explanation/ dfs写法，就是dfs的时候不能访问来的时候的那个点，但是我写的和他不一样
    public static boolean containsCycle(char[][] grid) {
        boolean[][] memo=new boolean[grid.length][grid[0].length];

        for(int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(!memo[i][j]){
                    if(dfs(-1,-1,i,j,grid,memo)){
                        return true;
                    }
                }
            }
        }

        return false;

    }
    static boolean dfs(int lastr,int lastc, int r, int c, char[][] grid, boolean[][] memo){

        memo[r][c]=true;
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        for(int i=0;i<4;i++){
            int row=r+dx[i];
            int col=c+dy[i];
            if(row>=0&&row<grid.length&&col>=0&&col<grid[0].length&&grid[row][col]==grid[r][c]){
                if(row!=lastr||col!=lastc){//注意这里不是&&，只要一个不等就可以！
                    if(!memo[row][col]){
                        if(dfs(r,c,row,col,grid,memo)){//这里看起来比较奇怪但是是对的，少了if判断会错，就是开头那个例子会错
                            return true;
                        }

                    }else{//如果还是遇到了已经访问的地方说明有环
                        return true;
                    }
                }
            }
        }
        return false;

    }
}
