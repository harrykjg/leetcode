import java.util.ArrayList;
//递归超时的是参考他的http://19542002.blog.163.com/blog/static/133932382201333002034138/

//http://blog.csdn.net/doc_sgl/article/details/11714793 看他的表
//http://blog.csdn.net/linhuanmars/article/details/24683159

public class InterleavingString {
	public static void main(String[] args) {
		InterleavingString il=new  InterleavingString();
		System.out.println(il.isInterleave2("aabcc", "dbbca", "aadbbcbcac"));
	}

	//思路就是dfs回溯法，一个一个字符看他是不是在a1或者a2中，会超时
	public boolean isInterleave(String s1, String s2, String s3) {
		if(s3.length()==0){
			return true;
		}
		if(s1.length()+s2.length()!=s3.length()){
			return false;
		}
		ArrayList<Character> a1=new ArrayList<Character>();
		ArrayList<Character> a2=new ArrayList<Character>();
		for(int i=0;i<s1.length();i++){
			a1.add(s1.charAt(i));
		}
		for(int i=0;i<s2.length();i++){
			a2.add(s2.charAt(i));
		}
		return check(a1,0,a2,0,s3,0);

	}
	
	public boolean check(ArrayList<Character> a1,int i,ArrayList<Character> a2,int j,
			String s,int k){
		if(i==a1.size()&&j==a2.size()&&k==s.length()){
			return true;
		}
		//这里开始有点想不明白，之前写的话就没有这个flag和这个flag的判断的
		//现在想明白了，这里就是每一步都可以走两个分叉，如果第一个分叉错了，即falg=false，那就
		//无所谓，继续试第二个分叉，如果对了，就直接返回true。第二个分叉也一样。即拿个flag记录
		//这一步是否正确，正确的话就直接返回true了，错误的话就继续
		boolean flag=false;
		if(i<a1.size()&&s.charAt(k)==a1.get(i)){
			flag= check(a1,i+1,a2,j,s,k+1);
		}
		if(flag){
			return true;
		}
		if(j<a2.size()&&s.charAt(k)==a2.get(j)){
			 flag= check(a1,i,a2,j+1,s,k+1);
		}
		if(flag){
			return true;
		}
		return false;
	}
	//dp还是屌，思路就是dp[i][j]代表s1的前i个字母和s2的前j个字母，能否构成s3的前i+j个字母
	//递推式就是dp[i][j]=dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1)或者
	//dp[i][j]=dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1)有一个是true，那么dp[i][j]就是
	//true。含义是：s1的前i个字母和s2的前j-1个字母能组成s3的前i+j-1个字母，并且s2的第j个字母
	//等于s3的第i+j个字母的话，说明s1的前i个字母和s2的前j个字母能组成s3的前i+j个字母。
	//这里由于dp数组时第一行和第一列空，所以charAt里是（j-1）或者（i-1）或者i+j-1
	 public boolean isInterleave2(String s1, String s2, String s3) {
		 if(s3.length()==0){
				return true;
			}
			if(s1.length()+s2.length()!=s3.length()){
				return false;
			}
			boolean[][] dp=new boolean[s1.length()+1][s2.length()+1];
			for(int i=0;i<dp.length;i++){
				for(int j=0;j<dp[0].length;j++){
					if(i==0&&j==0){
						dp[i][j]=true;
						continue;
					}
					if(i==0){//这里，如果不是行和列都多了一行的话，就是
						//dp[i][j]=dp[i][j-1]&&s2.charAt(j))==s3.charAt(i+j-1)?true:false;
						dp[i][j]=dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1)?true:false;
						continue;
					}
					if(j==0){
						dp[i][j]=dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1)?true:false;
						continue;
					}
					boolean flag1=dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1);
					boolean flag2=dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1);
					if(flag1||flag2){
						dp[i][j]=true;
					}
					else{
						dp[i][j]=false;
					}
				}
			}
			return dp[dp.length-1][dp[0].length-1];
	 }
	 //第三轮，还是记不得动态规划的意义，记住就好
	 public boolean isInterleave3(String s1, String s2, String s3) {
	        
	 }

}
