public class UniquePathsII {
	//就是比uniquepath多了一个判断那格是不是有障碍就行了
	
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if(obstacleGrid.length==0||obstacleGrid[0].length==0){
			return 0;
		}
		if(obstacleGrid[0][0]==1){
			return 0;
		}
		int[][] dp=new int[obstacleGrid.length][obstacleGrid[0].length];
		for(int i=0;i<dp.length;i++){
			for(int j=0;j<dp[0].length;j++){
				if(i==0&&j==0){
					dp[i][j]=1;
				}
				else if(i-1<0&&obstacleGrid[i][j]!=1){
					dp[i][j]=dp[i][j-1];
				}
				else if(j-1<0&&obstacleGrid[i][j]!=1){
					dp[i][j]=dp[i-1][j];
				}
				else if(obstacleGrid[i][j]==1){
					dp[i][j]=0;
				}
				else{
					dp[i][j]=dp[i][j-1]+dp[i-1][j];
				}
			}
		}
		return dp[dp.length-1][dp[0].length];

	}
	

}
