package old;
//http://jixiangsanbao.wordpress.com/2014/04/21/longest-palindromic-substring/
//http://blog.csdn.net/linhuanmars/article/details/20888595
public class LongestPalindromicSubstring {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LongestPalindromicSubstring lp=new LongestPalindromicSubstring();
		System.out.println(lp.longestPalindrome6("aba"));

	}
	public String longestPalindrome(String s) {//网上的动态规划方法
		//关键点，找到状态转移方程。实际上就是按1，2，3，4.。。个长度检测字符串，等于移动的window，检测其是否是回文，
		//而且每一步都记录了dp【i+1][j-1]，所以只需要判断s【i】是否等于s【j】就行了
        boolean[][] dp;
        if(s.length() == 0)  
        {  
            return "";  
        }  
        if(s.length() == 1)  
        {  
            return s;  
        }  
 
        //动态规划的思路是 dp[i][j] 表示的是 从i 到 j 的字串，是否是回文串。
        dp = new boolean[s.length()][s.length()];  
 
        int i,j;  
 
        //初始化dp[][]数组  
        for( i = 0; i < s.length(); i++)  
        {  
            for( j = 0; j < s.length(); j++)  
            {  
                if(i >= j)  
                {  
                    //当i == j 的时候，只有一个字符的字符串; 当 i > j 认为是空串，也是回文
                    dp[i][j] = true;  
 
                }  
                else  
                {  
                    //其他情况都初始化成不是回文  
                    dp[i][j] = false;  
                }  
            }  
        }  
 
        //k表示子串步幅，即结束下标-起始下标  
        int k;
 
        //回文串的最大长度
        int maxLen = 1;
 
        //最长回文串在s中的下标
        int rf = 0, rt = 0;
 
        //动态规划的进行是按照字符串的长度从1到n推进的。实际上就是吉祥那样，sliding window，k就是
        //框的长度
        for( k = 1; k < s.length(); k++)  
        {  
            //子串起始下标为i
            for( i = 0;  k + i < s.length(); i++)  
            {  
                //子串结束下标j,j=i+k
                j = i + k;  
 
                //对字符串 s[i....j] 如果 s[i] != s[j] 那么不是回文  
                if(s.charAt(i) != s.charAt(j))
                {  
                    dp[i][j] = false;  
                }
 
                //如果s[i] == s[j] 回文性质由 s[i+1][j-1] 决定
                else    
                {  
                    dp[i][j] = dp[i+1][j-1];  
                    if(dp[i][j])  
                    {  
                        //子串长度=结束下标-起始下标+1=j-i+1=(i+k)-i+1=k+1
                        if(k + 1 > maxLen)  
                        {  
                            maxLen = k + 1;  
                            rf = i;  
                            rt = j;  
                        }  
                    }  
                }  
            }  
        }  
 
        //rf为最长回文串的起始下标，rt为结束下标。那为什么返回的结果是s.substring(rf,rt+1)呢？
        //[吐槽开始]Java的String类提供的substring(int beginIndex,int endIndex)
        //beginIndex为开始的索引，对应String数字中的开始位置
        //endIndex是截止的索引位置，对应String中的结束位置
        //1.取得的字符串长度为：endIndex - beginIndex;
        //2.从beginIndex开始取，到endIndex结束，从0开始数，其中[不包括endIndex位置的]
        //懂了，原来这个方法返回的结果是不包括endInex位置上的字符的，所以需要+1
        //按说beginIndex和endIndex的取值范围是[0,s.length()-1].
        //可如果你想通过substring这个方法返回它自己的话代码却是s.substring(0,s.length())
        return s.substring(rf, rt+1);  
    }
	//吉祥的代码，感觉他的初始化dp数组更合理一些
	public String longestPalindrome2(String s){  
		      int n = s.length();  
		      if(n == 0){  
		        return new String();  
		      }  
		      int longestStart = 0;  
		      int maxLen = 0;  
		      boolean dp[][] = new boolean[n][n];  
		      //initialize base case  
		      for(int i = 0; i < n; i++){  
		        dp[i][i] = true;  
		        maxLen = 1;  
		      }  
		      for(int i = 0; i < n-1; i++){  
	              if(s.charAt(i) == s.charAt(i+1)){  
		              dp[i][i+1] = true;  
		              longestStart = i;  
		              maxLen = 2;  
		        }  
		      }  
		      //expand the base case, on the condition that s(i,j) is palindrome iff s(i+1,j-1) is palindrom and s[i] = s[j]  
		      for(int len = 3; len <= n; len++){  
		        for(int i = 0; i <= n - len; i++){  
		          int j = i + len - 1;  
		          if(dp[i+1][j-1] && s.charAt(i) == s.charAt(j)){  
		            dp[i][j] = true;  
		            longestStart = i;  
		            maxLen = len;  
		          }  
		        }  
		      }  
		      return s.substring(longestStart, longestStart + maxLen);  
		    } 

	 public String longestPalindrome3(String s) {//中心扩展法
			
			if(s.length()==1||s==null){
				return s;
			}
			if(s.length()==2&&s.charAt(0)==s.charAt(1)){
				return s;
			}
			int len=s.length();
			String s1=s.substring(0,1);
		
			for(int i=0;i<len-1;i++){
				String temp1=check1(i,s);
				if(temp1.length()>s1.length()){
					s1=temp1;
				}
				String temp2=check2(i,s);
				if(temp2.length()>s1.length()){
					s1=temp2;
				}
			}		
		return s1;
		}
		
		public String check1(int i,String s){
			
			int left=i-1;
			int right=i+1;
			while(left>=0&&right<=s.length()-1&&s.charAt(left)==s.charAt(right)){
			
				left--;
				right++;
			}
			return s.substring(left+1,right);
		}
		
		public String check2(int i,String s){
			if(s.charAt(i)!=s.charAt(i+1)){
				return s.substring(i,i+1);
			}
			
			int left=i-1;
			int right=i+2;
			while(left>=0&&right<=s.length()-1&&s.charAt(left)==s.charAt(right)){
				
				left--;
				right++;
			}
			return s.substring(left+1,right);
		}
		public String longestPalindrome4(String s) {  //code ganker的
		    if(s == null || s.length()==0)  
		        return "";  
		    boolean[][] palin = new boolean[s.length()][s.length()];  
		    String res = "";  
		    int maxLen = 0;  //例如daabbaaa，他这个方法是先看最后一个a，然后得出palin[i][j]=true
		    //然后看倒数第二个a，然后看倒数第二个a到最后一个字母是否相等。然后看倒数第三个a，然后
		    //j从倒数第三个开始，到倒数第二，到倒数第一。然后看倒数第四个字母，然后j从倒数第四开始
		    //然后到倒数第三，第二，第一。。好奇怪
		    for(int i=s.length()-1;i>=0;i--)  
		    {  
		        for(int j=i;j<s.length();j++)  
		        {  
		            if(s.charAt(i)==s.charAt(j) && (j-i<=2 || palin[i+1][j-1]))  
		            {  
		                palin[i][j] = true;  
		                if(maxLen<j-i+1)  
		                {  
		                    maxLen=j-i+1;  
		                    res = s.substring(i,j+1);  
		                }  
		            }  
		        }  
		    }  
		    return res;  
		}  
		//第二次是知道了是动态规划之后基本按自己写的，会很慢，但是也accept。有两点可以优化的，1，
		//不要更新字符串，应该更新长度和起始点，最后才搞一个substring。2，把两个for循环融入一个
		//如何融入呢？起始就应该是sliding window那样搞，看吉祥的
		public String longestPalindrome5(String s) {
			 boolean[][] dp=new boolean[s.length()][s.length()];
			 if(s.length()==0||s.length()==1){
				 return s;
			 }
			 for(int i=0;i<dp.length;i++){
				 dp[i][i]=true;
			 }
			 String rs=""+s.charAt(0);
			 for(int i=0;i<dp.length;i++){
				 for(int j=1;j<=dp.length/2;j++){
					 if(i-j>=0&&i+j<dp.length&&s.charAt(i-j)==s.charAt(i+j)&&dp[i-j+1][i+j-1]){
						 dp[i-j][i+j]=true;
						 if(2*j>rs.length()){
							 rs=s.substring(i-j,i+j+1);
						 }
					 }
				 }
			 }
			 for(int i=0;i<dp.length-1;i++){
				 for(int j=0;j<dp.length/2;j++){
					 if(s.charAt(i)==s.charAt(i+1)){
					     dp[i][i+1]=true;
					     if(i+1-i+1>rs.length()){
							 rs=s.substring(i,i+2);
						 }
						 if(i-j>=0&&i+1+j<dp.length&&s.charAt(i-j)==s.charAt(i+1+j)&&dp[i-j+1][i+j]){
							 dp[i-j][i+1+j]=true;
							  if(2*j+2>rs.length()){
							 rs=s.substring(i-j,i+j+2);
						 }
						 }
						
					 }
				 }
			 }
			 return rs;        
	    }	
		//第三轮,思路是记得的，就是小毛病边界问题调试了好几次。
		int begin;
		int end;
		int gap=begin-end;
		public String longestPalindrome6(String s) {
			if(s.length()==0){
				return "";
			}
			if(s.length()==1){
			    return s;
			}
			boolean[][] memo=new boolean[s.length()][s.length()];
			for(int i=0;i<memo.length;i++){
				memo[i][i]=true;
				if(i>0&&s.charAt(i)==s.charAt(i-1)){
					memo[i-1][i]=true;
					
						begin=i-1;
						end=i;
				}
			}
			for(int i=3;i<=s.length();i++){
				check(0,i,memo,s);
			}
			String rs=s.substring(begin,end);
			return rs;
			
		}
		private void check(int b,int len,boolean[][] memo,String s){
			
			for(int i=b;i<=s.length()-len;i++){
				int left=i;
				int right=i+len;
				if(s.charAt(left)==s.charAt(right-1)&&memo[left+1][right-1-1]){
					memo[left][right-1]=true;
					if(len>gap){
						begin=left;
						end=right;
					}
				}
			}
		}

	//11/13/2018 居然一次过了，就是要注意好下标
	public String longestPalindrome7(String s) {
		String rs="";
		if(s.length()==0){
			return rs;
		}
		boolean[][] dp=new boolean[s.length()][s.length()];
		int max=Integer.MIN_VALUE;
		int left=0;
		int right=0;
		for(int i=0;i<dp.length;i++){
			dp[i][i]=true;
			if(1>max){
				max=1;
				left=i;
				right=i;
			}
			if(i+1<s.length()&&s.charAt(i)==s.charAt(i+1)){
				dp[i][i+1]=true;
				if(2>max){
					max=2;
					left=i;
					right=i+1;
				}
			}
		}
		for(int k=2;k<s.length();k++){
			for(int i=0;i+k<s.length();i++){
				int end=i+k;
				if(s.charAt(i)==s.charAt(end)&&dp[i+1][end-1]){
					dp[i][end]=true;
					if(k+1>max){
						max=k+1;
						left=i;
						right=end;
					}
				}
			}
		}
		return s.substring(left,right+1);
	}
}
