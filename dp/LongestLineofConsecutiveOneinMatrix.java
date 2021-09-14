package dp;

public class LongestLineofConsecutiveOneinMatrix {
    //9/9/2021 看了提示说要三维dp存每个位置的4个方向的和,然后一次过
    public int longestLine(int[][] mat) {
        int[][][] dp=new int[mat.length][mat[0].length][4];
        int rs=0;
        for (int i=0;i<dp.length;i++){
            for (int j=0;j<dp[0].length;j++){
                if (mat[i][j]==1){
                    if (j>0){
                        dp[i][j][0]=dp[i][j-1][0]+1;
                    }else {
                        dp[i][j][0]=1;
                    }
                    if (i>0){
                        dp[i][j][1]=dp[i-1][j][1]+1;
                    }else {
                        dp[i][j][1]=1;
                    }
                    if (i>0&&j>0){
                        dp[i][j][2]=dp[i-1][j-1][2]+1;
                    }else {
                        dp[i][j][2]=1;
                    }
                    if (i>0&&j<mat[0].length-1){
                        dp[i][j][3]=dp[i-1][j+1][3]+1;
                    }else {
                        dp[i][j][3]=1;
                    }
                    rs=Math.max(rs,Math.max(dp[i][j][1],Math.max(dp[i][j][2],Math.max(dp[i][j][3],dp[i][j][0]))));
                }
            }
        }

        return rs;
    }
}
