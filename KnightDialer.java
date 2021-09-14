public class KnightDialer {
    public static void main(String[] args){
        KnightDialer kd=new KnightDialer();
        System.out.println(kd.knightDialer(2));
    }
    //8/19/2021直接暴力dfs改了一下超时.这个有点类似那个Max Area of Island，我写的dfs是void然后去更新全局变量rs，如果要用memo的话就不好写了，应该把
    //dfs写成是返回int的，一个点的8个方向都加上本层
    //https://leetcode.com/problems/knight-dialer/discuss/190787/How-to-solve-this-problem-explained-for-noobs!!! 奇怪为啥我的就还是timeout
    //他的和我的写法几乎是一样的,还是没搞出来
    int[] dx={-2,-2,-1,-1,2,2,1,1};
    int[] dy={-1,1,-2,2,-1,1,-2,2};
    int mod=(int)Math.pow(10,9)+7;
    public int knightDialer(int n) {
        int[][] grid=new int[4][3];//其实根本就不用一个grid，因为不care数字，每次n个数走过就是unique的
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                grid[i][j]=i*3+j+1;
            }
        }
        int rs=0;
        grid[3][0]=-1;
        grid[3][2]=-1;
        int[][][] memo=new int[grid.length][grid[0].length][n+1];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==-1){//这里开始忘了
                    continue;
                }
                int x=dfs(n,i,j,grid,memo)%mod;
                rs+=x;
                rs%=mod;
            }
        }
        return rs;
    }
    int dfs(int n,int i,int j,int[][] grid,int[][][] memo){
        if (1==n){
            return 1;
        }
        if (memo[i][j][n]>0){
            return memo[i][j][n];
        }
        int rs=0;//这个不能写1，应该要一开始n==1的时候就要返回1。想想写1也是不对，当前点和后面的每个方向的dfs是属于同一个路径，比如说当前点只能再跳2个方向，
        for (int k=0;k<dx.length;k++){  //那么当前点再跳一步的结果就是2条路径，如果rs初始化是1则变成了3了
            int r=i+dx[k];
            int c=j+dy[k];
            if (r<0||r>=grid.length||c<0||c>=grid[0].length||grid[r][c]==-1){
                continue;
            }
            rs+=dfs(n-1,r,c,grid,memo)%mod;
        }
        memo[i][j][n]=rs%mod;
        return rs%mod;
    }
}
