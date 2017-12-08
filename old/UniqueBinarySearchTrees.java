//http://jixiangsanbao.wordpress.com/2014/05/16/unique-binary-search-trees/
public class UniqueBinarySearchTrees {

	public int numTrees(int n) {
		if(n==0) return 0;
		if(n==1) return 1;
		int [] dp=new int[n+1];
		dp[0]=1;
		dp[1]=1;
		for(int i=2;i<=n;i++){
			dp[i]=create(dp,i);
			
		}
		return dp[n];

	}
	public int create(int[] dp, int n){
		int rs=0;
		for(int i=1;i<=n;i++){//����n=4��������1Ϊ�������0���ұ�3����������2Ϊ�������1���ұ�2��
			//����3Ϊ�������2���ұ�һ����������4Ϊ�������3���ұ�0��
			rs+=dp[i-1]*dp[n-i];
		}
		return rs;
	}
	
	//������
	public int numTrees2(int n) {
		if(n==0) return 0;
		if(n==1) return 1;
		int [] dp=new int[n+1];
		dp[0]=1;//ע������1
		dp[1]=1;
		gen(dp,n);
		return dp[n];
	
	}
	private int gen(int[] dp,int n){
		if(n==1){
			return 1; 
		}
		
		dp[n-1]=gen(dp,n-1);
		int i=1;
		while(i<=n){
			dp[n]+=dp[i-1]*dp[n-i];//ע�����ǳ˷�
			i++;
		}
		
		return dp[n];
	}
	
}
