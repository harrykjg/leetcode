import java.util.HashSet;
//http://blog.csdn.net/yutianzuijin/article/details/16850031  这个分析的好
//http://jixiangsanbao.wordpress.com/2014/05/22/palindrome-partitioning-ii/ 代码吉祥的容易懂些
//http://www.cnblogs.com/sunshineatnoon/p/3870222.html
//http://blog.csdn.net/linhuanmars/article/details/22837047
public class PalindromePartitioningII {
	
	public static void main(String[] args) {
		PalindromePartitioningII pp=new PalindromePartitioningII();
		System.out.println(pp.minCut2("dde"));
	}

	//这题挺难的，看了他们的代码很久之后才懂。注意和palindromepartition1的不同之处是，那题可以用
	//dfs且accept的原因是，那题要的是所有切割方法，所以必须全部可能都搜出来，而这题要的是最少的
	//切割数，所以。。具体分析看第一个链接
	
	//思路是：先得弄懂longestpalindromicsubstirng，得到一个二维数组，保存的是对于字符串s的
	//任意位置i到j是否是回文的boolean值。然后利用这个信息，再去动态规划最小切割方法
	//最小切割方法的思路是：dp[i]代表0到i个字符的最小切割数,dp[i]初始化为i，即最坏情况下，i个字母
	//要1个个的分即i个切法。dp[i]=min（dp[i],dp[j-1]+1)，其中j是0到i之间的一个数，意思是j到i之间
	//如果是回文的话，那么dp[i]就等于dp[j-1]加上j到i之间的回文，这个j要全部试过，得出最小的组合
	//比如aabcbk，第一个字母是回文，所以dp[0]=0，再看前两个字母也是回文，所以dp[1]也是0，再看第
	//三个字母，由于aab不是回文，所以从aab的最后一个字母看起，b是回文，所以dp[2]=dp[1]+1，
	//然后看ab，不是回文，然后看aab也不是，done，然后看aabc，不是回文，然后看c，是回文，
	//所以dp[3]=dp[2]+1，然后看bc，不是回文，然后看abc也不是，然后看aabc也不是，done。然后看
	//aabcb，不是回文，然后看b，是回文，dp[]=dp[j-1]+1，然后看cb，不是，然后看bcb是回文，所以
	//dp[]=dp[j-1]+1，就会得到最小切数
	public int minCut(String s) {  
	   if(s.length()==0){
		   return 0;
	   }
	   boolean[][] dict=getDict(s);
	   int[] dp=new int[s.length()];
	   for(int i=0;i<s.length();i++){
		   dp[i]=i;
		   if(dict[0][i]){
			   dp[i]=0;
			   continue;
		   }
		   for(int j=i;j>=0;j--){//从i开始往前看
			   if(dict[j][i]){
				   dp[i]=Math.min(dp[i], dp[j-1]+1);
			   }
		   }
	   }
	   return dp[dp.length-1];
	}  
	private boolean[][] getDict(String s)  //和longestpalindromicsubstirng是一样的
	{  
	    boolean[][] dp=new boolean[s.length()][s.length()];
	    for(int i=0;i<s.length();i++){//这个i就是sliding window的长度
	    	for(int j=0;j<s.length()-i;j++){
	    		int end=j+i;
	    		if(s.charAt(j)==s.charAt(end)&&(end-j<=2||dp[j+1][end-1])){
	    			dp[j][end]=true;
	    		}
	    	}
	    }
	    return dp;
	}  

	//30/9,还是挺难的，边角条件要注意，还有对两个dp的理解
	public int minCut2(String s) { 
		if(s.length()==0||s==null){
			return 0;
		}
		boolean[][] dp=new boolean[s.length()][s.length()];
		for(int i=0;i<s.length();i++){
			if(i>0&&s.charAt(i)==s.charAt(i-1)){
				dp[i-1][i]=true;
			}
			dp[i][i]=true;
		}
		for(int i=3;i<=s.length();i++){
			for(int j=0;j<=s.length()-i;j++){
				int k=j+i;
				if(s.charAt(j)==s.charAt(k-1)&&dp[j+1][k-1-1]){
					dp[j][k-1]=true;
				}
			}
		}
		int[] min=new int[s.length()];
		for(int i=0;i<min.length;i++){
			min[i]=i;
			for(int j=i;j>=0;j--){//开始写成j=i-1了，那样的话dp[j][i]就是看2个字母了，就漏掉了
				if(dp[0][i]){      //一个字母的情况
					min[i]=0;
					break;
				}
				if(dp[j][i]){
					min[i]=Math.min(min[i],min[j-1]+1);
				}
				
			}
		}
		return min[min.length-1];
		
		
	}
	

}
