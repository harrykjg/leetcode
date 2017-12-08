//http://alexjixiang.com/2014/05/16/unique-binary-search-trees/ ����������������������T0Ӧ����1
//http://blog.csdn.net/linhuanmars/article/details/24761459
public class BinaryUniqueSearchTree {
	public static void main(String[] args) {
		BinaryUniqueSearchTree bu=new BinaryUniqueSearchTree();
		System.out.println(bu.numTrees(3));
	}

	public int numTrees(int n) {
		if(n==0) return 0;
		if(n==1) return 1;
		int [] dp=new int[n+1];
		dp[0]=1;
		dp[1]=1;
		for(int i=2;i<=n;i++){     //һ��һ���������������
			dp[i]=create(dp,i);    
			
		}
		return dp[n];

	}
	public int create(int[] dp, int n){
		int rs=0;
		for(int i=1;i<=n;i++){    //����n=3����ֳɸ���1����������T0,��������T2������2����������
			//T1����������T1������3����������T2����������T0 �����֣�Ȼ�����������T3��ֵ
			
			rs+=dp[i-1]*dp[n-i];
		}
		return rs;
	}

}
