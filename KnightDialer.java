import java.util.Arrays;

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
    //10/9/2021 把memo啥的都改成是long才行，
    public int knightDialer2(int n) {
        int[][] g=new int[4][3];
        long[][][] memo=new long[4][3][n+1];
        for(int i=0;i<4;i++){
            for(int j=0;j<3;j++){
                Arrays.fill(memo[i][j],-1);
            }
        }
        long rs=0;
        for(int i=0;i<g.length;i++){
            for(int j=0;j<g[0].length;j++){
                if(i==3&&(j==0||j==2)){
                    continue;
                }
                rs+=dfs2(i,j,g,n,memo)%mod;
            }
        }
        return (int)(rs%mod);//这里不加括号也错了
    }
    long dfs2(int row,int col,int[][] g,int step,long[][][] memo){
        if(memo[row][col][step]!=-1){
            return memo[row][col][step]%mod;
        }
        if(step==1){
            return 1;
        }
        long local=0;//这里还是容易错写成1
        for(int i=0;i<8;i++){
            int r=row+dx[i];
            int c=col+dy[i];
            if(r==3&&(c==0||c==2)){
                continue;
            }
            if(r>=0&&r<g.length&&c>=0&&c<g[0].length){
                local+=dfs2(r,c,g,step-1,memo)%mod;
            }
        }
        memo[row][col][step]=local%mod;
        return local%mod;
    }
}
