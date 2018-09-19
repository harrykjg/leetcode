package GraphAndSearch;

import java.util.Arrays;

/**
 * Created by yufengzhu on 9/13/18.
 */
public class LongestIncreasingPathinaMatrix {
    public static void main(String[] args){
        LongestIncreasingPathinaMatrix li=new LongestIncreasingPathinaMatrix();
        int[][] m={{9,9,4},{6,6,8},{2,1,1}};
        System.out.print(li.longestIncreasingPath2(m));
    }

    //以前的代码
    int rs = 1;

    //后来看了别人说可以记录某个点以他自己为起点的最长上升数列的长度，这样就不用下次遇到他再算一次了，
    //然后自己写的，这题可以这样原因应该是他只要长度而不用路径吧，要路径的话可能只能纯dfs了
    public int longestIncreasingPath(int[][] m) {
        if (m.length == 0) {
            return 0;
        }
        int[][] memo = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                helper2(i, j, m, memo);//helper2方法不用cur了，每一点的cur肯定是1
            }
        }
        return rs;
    }

    public void helper2(int i, int j, int[][] m, int[][] memo){
        if(memo[i][j]!=0){//如果memo不为0即以这点为起点的最长上升数列的长度已经计算过了
            rs=Math.max(rs, memo[i][j]);
            return;
        }
        if (i > 0&& m[i - 1][j] > m[i][j]) {
            if(memo[i-1][j]==0){//如果上米这点没计算过的话，计算之，否则啥也不干，然后比较当前i，j点加上这点的值，
                helper2(i-1,j,m,memo); //作为memo[i][j]的值
            }
            memo[i][j]=Math.max(memo[i][j],1+memo[i-1][j]);
            rs=Math.max(rs, memo[i][j]);

        }
        if(j+1<m[0].length&&m[i][j+1]>m[i][j]){
            if(memo[i][j+1]==0){
                helper2(i,j+1,m,memo);
            }
            memo[i][j]=Math.max(memo[i][j],1+memo[i][j+1]);
            rs=Math.max(rs, memo[i][j]);
        }
        if (i +1< m.length&& m[i +1][j] > m[i][j]) {
            if(memo[i+1][j]==0){
                helper2(i+1,j,m,memo);
            }
            memo[i][j]=Math.max(memo[i][j],1+memo[i+1][j]);
            rs=Math.max(rs, memo[i][j]);

        }
        if(j-1>=0&&m[i][j-1]>m[i][j]){
            if(memo[i][j-1]==0){
                helper2(i,j-1,m,memo);
            }
            memo[i][j]=Math.max(memo[i][j],1+memo[i][j-1]);
            rs=Math.max(rs, memo[i][j]);
        }
        if(memo[i][j]==0){//这个容易漏，如果这个i，j点都不进入上面四个if，则把他值设为1
            memo[i][j]=1;
        }

    }
    //9／12／2018，还是只能想到暴力，答案说可以用memorization,但是还是写不出来，只能写出来暴力法的,要练！
    int rs2=0;
    public int longestIncreasingPath2(int[][] m) {
        if(m.length==0){
            return rs2;
        }
        int[] dp=new int[m.length*m[0].length];
        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[0].length;j++){
                dfs(i,j,m,dp);

            }
        }
        return Math.max(1,rs2);
    }
    void dfs(int row,int col,int[][] m,int[] dp){

        if(row-1>=0&&m[row-1][col]>m[row][col]){
            if(dp[(row-1)*m[0].length+col]!=0){
                dp[row*m[0].length+col]=Math.max(dp[row*m[0].length+col],dp[(row-1)*m[0].length+col]+1);
            }else{
                dfs(row-1,col,m,dp);//这里有点难想，如果row-1，col这个点没有计算过的话，那么就dfs进去计算，出来的时候dp上已经有记录了，再同样的加到现在的row，col上面就行了
                dp[row*m[0].length+col]=Math.max(dp[row*m[0].length+col],dp[(row-1)*m[0].length+col]+1);
            }
        }
        if(col+1<m[0].length&&m[row][col+1]>m[row][col]){

            if(dp[(row)*m[0].length+col+1]!=0){
                dp[row*m[0].length+col]=Math.max(dp[row*m[0].length+col],dp[(row)*m[0].length+col+1]+1);
            }else{
                dfs(row,col+1,m,dp);
                dp[row*m[0].length+col]=Math.max(dp[row*m[0].length+col],dp[(row)*m[0].length+col+1]+1);
            }
        }
        if(row+1<m.length&&m[row+1][col]>m[row][col]){
            if(dp[(row+1)*m[0].length+col]!=0){
                dp[row*m[0].length+col]=Math.max(dp[row*m[0].length+col],dp[(row+1)*m[0].length+col]+1);
            }else{
                dfs(row+1,col,m,dp);
                dp[row*m[0].length+col]=Math.max(dp[row*m[0].length+col],dp[(row+1)*m[0].length+col]+1);
            }
        }
        if(col-1>=0&&m[row][col-1]>m[row][col]){
            if(dp[(row)*m[0].length+col-1]!=0){
                dp[row*m[0].length+col]=Math.max(dp[row*m[0].length+col],dp[(row)*m[0].length+col-1]+1);
            }else{
                dfs(row,col-1,m,dp);
                dp[row*m[0].length+col]=Math.max(dp[row*m[0].length+col],dp[(row)*m[0].length+col-1]+1);
            }

        }
        dp[row*m[0].length+col]=Math.max(dp[row*m[0].length+col],1);
        rs2=Math.max(rs2,dp[row*m[0].length+col]);
    }

    //9/16/2018 还是写不好，要再写
    int rs3=0;
    public int longestIncreasingPath3(int[][] m) {
        if(m.length==0){
            return rs3;
        }
        int[][] memo=new int[m.length][m[0].length];

        for(int i=0;i<m.length;i++){
            for(int j=0;j<m[0].length;j++){
                dfs2(i,j,memo,m);
            }
        }
        return rs3;
    }
    void dfs2(int row,int col,int[][] memo,int[][] m){
        int[] x={-1,0,1,0};
        int[] y={0,1,0,-1};

        for(int i=0;i<4;i++){
            if(row+x[i]>=m.length||row+x[i]<0||col+y[i]>=m[0].length||col+y[i]<0){
                continue;
            }
            if(m[row+x[i]][col+y[i]]>m[row][col]){
                if(memo[row+x[i]][col+y[i]]==0){
                    dfs2(row+x[i],col+y[i],memo,m);
                }
                memo[row][col]=Math.max(memo[row][col],memo[row+x[i]][col+y[i]]+1);
            }
        }
        memo[row][col]=Math.max(memo[row][col],1);
       rs3=Math.max(memo[row][col],rs3);
    }

}
