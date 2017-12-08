//http://alexjixiang.com/2014/05/16/unique-binary-search-trees/ 吉祥这个分析的清楚，但是T0应该是1
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
		for(int i=2;i<=n;i++){     //一个一个的填上这个数组
			dp[i]=create(dp,i);    
			
		}
		return dp[n];

	}
	public int create(int[] dp, int n){
		int rs=0;
		for(int i=1;i<=n;i++){    //例如n=3，则分成根是1，左子树是T0,右子树是T2；根是2，左子树是
			//T1，右子树是T1；根是3，左子树是T2，右子树是T0 这三种，然后加起来就是T3的值
			
			rs+=dp[i-1]*dp[n-i];
		}
		return rs;
	}

}
