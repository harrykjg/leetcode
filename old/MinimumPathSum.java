public class MinimumPathSum {

//http://blog.csdn.net/linhuanmars/article/details/22126357 ����unique path����Ľ��ͣ���˵��ΰѶ�ά����һάdp
	//���⿪ʼ�����ˣ�����ֻ��1�����ӵĻ�������Ϊ������0����ʵӦ����������ӵ�ֵ
	//�Լ���ϸ����Ӧ����д��
	public int minPathSum(int[][] grid) {//�ö�ά�ռ�
		if(grid.length==0){
			return 0;
		}
		int[][] dp=new int[grid.length][grid[0].length];
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[0].length;j++){
				if(i==0&&j==0){
					dp[i][j]=grid[i][j];
				}else if(i==0){
					dp[i][j]=dp[i][j-1]+grid[i][j];
				}else if(j==0){
					dp[i][j]=dp[i-1][j]+grid[i][j];
				}else{
					dp[i][j]=Math.min(dp[i-1][j]+grid[i][j],dp[i][j-1]+grid[i][j]);
				}
				
			}
		}
		return dp[dp.length-1][dp[0].length-1];

	}
	
	public int minPathSum2(int[][] grid) {//��һά�ռ�
		if(grid.length==0){
			return 0;
		}
		int[] dp=new int[grid[0].length];//dp�ĳ��Ⱥ��������
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[0].length;j++){
				if(i==0&&j==0){
					dp[j]=grid[0][0];
				}else if(i==0){
					dp[j]=dp[j-1]+grid[i][j];
				}
				else if(j==0){
					dp[j]=dp[j]+grid[i][j];
				}else{
					dp[j]=Math.min(dp[j-1]+grid[i][j],dp[j]+grid[i][j]);
				}
				
			}
		}
		return dp[dp.length-1];

	}
	
	//1/10 ��һά
	public int minPathSum3(int[][] grid) {
        int[] dp=new int[grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(i==0&&j==0){
                    dp[j]=grid[0][0];
                    continue;
                }
                if(i==0){
                    dp[j]=dp[j-1]+grid[i][j];
                    continue;
                }
                if(j==0){
                    dp[j]=dp[j]+grid[i][j];
                    continue;
                }
                dp[j]=Math.min(dp[j]+grid[i][j],dp[j-1]+grid[i][j]);
            }
        }
        return dp[dp.length-1];
    }

}
