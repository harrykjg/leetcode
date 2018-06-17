public class DistinctSubsequence {
	//http://jixiangsanbao.wordpress.com/2014/06/30/distinct-subsequences/
//应该看吉祥这个表比较合理，要其实只要先初始化第一行，第一列不用动。关键是状态方程的理解
//从上到下，从左到右赋值，所以说其实是固定了T的前i个字符，然后不断加入S的字符，进行比较
//如果如果新加入的S的第j个字符和T的第i个字符不同的话,那么dp[i][j]的值等于dp[i][j-1]，即
//这个第j个字符加不加进来都没影响，反正不相同
//如果相同的话，那么他们的他的值由2部分组成：1，和不加他们两也是一样的即dp[i-1][j-1]。
//2，dp[i][j-1],即不加第j个字符之前他原来的值。还是挺难理解的
//http://blog.csdn.net/linhuanmars/article/details/23589057
//http://www.blogjava.net/menglee/archive/2013/12/31/408231.html
	public static void main(String[] args) {


		DistinctSubsequence ds=new DistinctSubsequence();
		String S="abccde";
		String T ="ace";
		int k=ds.numDistinct2(S, T);
		System.out.println(k);


	}

	public int numDistinct(String S, String T) {

		if(T.length()>S.length()){
			return 0;
		}
		if(S.length()!=0&&T.length()==0){
			return 1;
		}
		int[][] dp=new int[T.length()][S.length()];
		for(int i=0;i<dp.length;i++){
			for(int j=0;j<dp[0].length;j++){
				if(S.charAt(j)!=T.charAt(i)){
					if(j==0&&i==0){
						dp[i][j]=0;
					}else if(j==0){
						dp[i][j]=0;
					}
					else{
						dp[i][j]=dp[i][j-1];
					}
				}
				else{
					if(i==0&&j==0){
						dp[i][j]=1;
					}
					else if(i==0){
						dp[i][j]=dp[i][j-1]+1;
					}else if(j==0&&i>j){

						dp[i][j]=0;

					}
					else{
						dp[i][j]=dp[i-1][j-1]+dp[i][j-1];
					}
				}
			}
		}
		return dp[T.length()-1][S.length()-1];
	}
	//第二次自己没写
	//第三轮,对方程的理解没有对，要看回原来的
	public int numDistinct2(String S, String T) {
		if(T.length()>S.length()){
			return 0;
		}
		int[][] dp=new int[T.length()+1][S.length()+1];
		for(int i=0;i<=S.length();i++){//这段初始化可以理解为t为空时即长度为0时，那s中只能找到1个t，而不是s长度个t
			dp[0][i]=1;

		}

		for(int i=1;i<=T.length();i++){
			for(int j=1;j<=S.length();j++){
				if(T.charAt(i-1)==S.charAt(j-1)){
					dp[i][j]=dp[i-1][j-1]+dp[i][j-1];
				}else{
					dp[i][j]=dp[i][j-1];
				}
			}
		}
		return dp[dp.length-1][dp[0].length-1];
	}

}