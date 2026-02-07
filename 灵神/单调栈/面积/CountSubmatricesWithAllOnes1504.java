package 灵神.单调栈.面积;

public class CountSubmatricesWithAllOnes1504 {
    public static void main(String[] args) {
        int[][] grid={{1,1,0},
                {0,0,0},
                {1,1,0},
                {1,1,0}};
        System.out.println(numSubmat(grid));
    }
    //很不好想，这个dp数组的意义是每一行的submatrix的数量，比如原数组是1,1，则dp的这一行就是1,2，然后rs+=1和rs+=2. dp[i][j]的意义就是第i行第j个地方如果是1的话，则他的submatrix要
    //取决于前面dp【i】【j-1】的值+1，意义就是本来这里就是一个1的方格，并且和左边的1连上了，因此是2.
    //有了一行一行的dp代表从左往右的连续的1之后，还要算上下之间的连续的1，既从i行往上看，遇到上面是0的则中断，否则就是上面有连续的1，注意这里是看上面的dp【k】【j】的值，这个值要和
    //dp【i】【j】的值取最小的才能加进结果集，而且注意不是dp【i】【j】增加，而是直接加到结果集。这个很不好想。因为dp【i】【j】的意义就是第i行j位置从左往右有几个连续的1，而dp【i-1】【j】的意义
    //也是一样，那么现在上下行连在一起看
    //https://leetcode.com/problems/count-submatrices-with-all-ones/solutions/7102398/count-submatrices-with-all-ones/
    public static int numSubmat(int[][] mat) {
        int[][] dp=new int[mat.length][mat[0].length];
        int rs=0;
        for(int i=0;i<mat.length;i++){
            for (int j=0;j<mat[0].length;j++){
                if(mat[i][j]==0){
                    dp[i][j]=0;
                    continue;
                }else{
                    if(j>0){
                        dp[i][j]=dp[i][j-1]+1;
                    }else{
                        dp[i][j]=1;
                    }
                }
                int cur=dp[i][j];
                rs+=cur;
                for(int k=i-1;k>=0;k--){
                    cur=Math.min(cur,dp[k][j]);//这里很难想， 刚好就是要加最小值，看图理解
                    if(dp[k][j]==0){
                        break;
                    }
                    rs+=cur;
                }
            }
        }
        return rs;
    }
    //1/21/2026 还是很不好想，画图理解
    /*
     0 1 1 0
     0 1 1 0
     1 1 1 0
     对应dp就是
     0 1 2 0
     0 1 2 0
     1 2 3 0
     */

}
