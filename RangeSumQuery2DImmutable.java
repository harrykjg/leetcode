/**
 * Created by 502575560 on 2/18/17.
 */
public class RangeSumQuery2DImmutable {
    public static void main(String[] args){
        int[][] m={{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        RangeSumQuery2DImmutable rs=new RangeSumQuery2DImmutable(m);
        rs.RangeSumQuery2DImmutable2(m);
        System.out.println(rs.sumRegion2(2,1,4,3));
    }


    //这题就是要在构造对象的时候就设计好如何快速的取得的矩形(可以不是正方形)的体积
    //开始以为的是正方形所以想的dp都是三位数组的,后来发现是可以是长方形的,做不下去了,看帖子
    //http://blog.csdn.net/hpingwu/article/details/49822379
    int[][] dp;
    public RangeSumQuery2DImmutable(int[][] m) {
        if(m.length==0){
            return;
        }
        dp=new int[m.length][m[0].length];
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[0].length;j++) {
                if(i==0&&j==0){
                    dp[i][j]=m[i][j];
                    continue;
                }
                if(j==0){
                    dp[i][j]=dp[i-1][j]+m[i][j];
                    continue;
                }
                int colsum=0;
                for(int k=0;k<=i;k++){
                    colsum+=m[k][j];
                }
                dp[i][j]=dp[i][j-1]+colsum;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {

        if(row1>0&&col1>0){
            return dp[row2][col2]-dp[row1-1][col2]-dp[row2][col1-1]+dp[row1-1][col1-1];
        }
        if(row1==0&&col1>0){
            return dp[row2][col2]-dp[row2][col1-1];
        }
        if(row1==0){//这里的if else可以改一下顺序理顺一点
            return dp[row2][col2];
        }
        if(col1==0&&row1>0){
            return dp[row2][col2]-dp[row1-1][col2];
        }
        return dp[row2][col2];

    }
    //6/21/2021 不会
    //https://blog.csdn.net/qq_32142535/article/details/78777669

    //8/22/2021 知道公式怎么算二维前缀和，关键是怎么初始化那个prefix sum矩阵。是要把所有大小的正方形在初始化的时候都算出来吗？不是的，应该就是建一个prefix sum
    //矩阵，做法是先初始化第一行的前缀和，然后第二行的前缀和就可以由第一行得出吧；其实不需要单独初始化第一行前缀和，一起搞就行吧。
    //然后得到二维前缀和之后怎么算正方形又是另一个方程
    //https://oi-wiki.org/basic/prefix-sum/
    int[][] sums;
    public void RangeSumQuery2DImmutable2(int[][] m) {
        sums=new int[m.length+1][m[0].length+1];
        for (int i=1;i<sums.length;i++){
            for (int j=1;j<sums[0].length;j++){
                if(i==1&&j==1){
                    sums[i][j]=m[i-1][j-1];
                }else if (i==1){
                    sums[i][j]=sums[i][j-1]+m[i-1][j-1];
                }else if (j==1){
                    sums[i][j]=sums[i-1][j]+m[i-1][j-1];
                }else {
                    sums[i][j]=sums[i][j-1]+sums[i-1][j]-sums[i-1][j-1]+m[i-1][j-1];
                }
            }
        }
    }
    public int sumRegion2(int row1, int col1, int row2, int col2) {
        return sums[row2+1][col2+1]-sums[row1][col2+1]-sums[row2+1][col1]+sums[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */