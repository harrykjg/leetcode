package old;
//http://blog.163.com/gjx_12358@126/blog/static/895363452014232191498/ 这个说的好
//解释一下：做的添加删除等操作是作用在word1身上，认真看他的解释。
//他说的word1添加的话，即word1添加一个字母，该字母与word2对应的字母相等，如abc要变成de，现在
//我添加e则word1变成abce，word还是de，即我现在只要把abc变成d就行了，abc还是3个，而de从2个变成1个了
//因此是dp[i][j-1]+1，注意这里对dp[i][j]的理解，其意思是把word1的前i个字符变成word2的前j个字符。
//     d e b a
//   a
//   b
//   c   x
//   a
//要画图理解下，如在x的位置那，如果把abc加个e成了abce，那么只要把abc变成d就行了，所以x处值为
//dp[i][j-1]+1,即x左边那个值+1。这个理解之后删除也好理解了

public class EditDistance {
	public static void main(String[] args) {

		String s1 = "ab";
		String s2 = "acb";
		EditDistance ed = new EditDistance();
		System.out.println(ed.minDistance2(s1, s2));
	}

	public int minDistance(String word1, String word2) {
		if(word1.length()==0){
			return word2.length();
		}
		if(word2.length()==0){
			return word1.length();
		}
		int[][] dp=new int[word1.length()+1][word2.length()+1];//word1是竖着的，word2是横着的
		//当时我是反过来了，word1是行，但是如果是这样的话，理解dp[i][j-1]和dp[i-1][j]就
		//会正好相反了，但是得出的结果还是一样，可能是因为替换的权值也是1的缘故
		for(int i=0;i<dp[0].length;i++){
			dp[0][i]=i;//即一个空的字符串变到word1的第i个字母的距离就是i
		}
		for(int i=0;i<dp.length;i++){
			dp[i][0]=i;//同上
		}
		for(int i=1;i<dp.length;i++){//按行开始填
			for(int j=1;j<dp[0].length;j++){
				int f=word1.charAt(j-1)==word2.charAt(i-1)?0:1;//行是word2，所以是j，列是word1
				//所以是i。注意这里减1是因为dp的长度时字符串的长度加了1的，等于是把一行word1和
				//一列word2插在了二维数组的第一行和第一列
				//他们是如果当前字符相等的话，就直接dp[i][j]等于dp[[i-1][j-1]了，这样能保证它就是
				//最小了？
				int min=Math.min(dp[i-1][j]+1, dp[i][j-1]+1);
				min=Math.min(min, dp[i-1][j-1]+f);
				dp[i][j]=min;
			}
		}

		return dp[word2.length()][word1.length()];
	}

	public int minDistance2(String word1, String word2) {
		if(word1.length()==0){
			return word2.length();
		}
		if(word2.length()==0){
			return word1.length();
		}
		int[][] dp=new int[word1.length()+1][word2.length()+1];
		for(int i=0;i<dp.length;i++){
			dp[0][i]=i;
		}
		for(int i=0;i<dp[0].length;i++){
			dp[i][0]=i;
		}
		for(int i=1;i<dp.length;i++){
			for(int j=1;j<dp[0].length;j++){
				if(word1.charAt(i-1)==word2.charAt(j-1)){
					dp[i][j]=dp[i-1][j-1];
				}else{
					dp[i][j]=Math.min(dp[i][j-1], Math.min(dp[i-1][j-1], dp[i-1][j]))+1;
					
				}
			}
		}
		return dp[dp.length-1][dp[0].length-1];
		
	}
}
