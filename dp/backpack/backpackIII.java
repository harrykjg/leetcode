package dp.backpack;

public class backpackIII {
    public static void main(String[]args){
        backpackIII bp=new backpackIII();
        int[] a={2,3,5,7};
        int[] v={1,5,2,4};
        System.out.println( bp.backPackIII(a,v,10));
    }
//6/21/2021
    public int backPackIII(int[] A, int[] V, int m) {
        // write your code here
        int[][] dp=new int[A.length+1][m+1];
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(j>=A[i-1]){
                    dp[i][j]=Math.max(dp[i][j-A[i-1]]+V[i-1],Math.max(dp[i][j],dp[i-1][j]));//开始少写了dp[i-1][j]就错了
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
