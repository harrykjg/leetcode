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
	public String longestPalindrome(String s) {//���ϵĶ�̬�滮����
		//�ؼ��㣬�ҵ�״̬ת�Ʒ��̡�ʵ���Ͼ��ǰ�1��2��3��4.���������ȼ���ַ����������ƶ���window��������Ƿ��ǻ��ģ�
		//����ÿһ������¼��dp��i+1][j-1]������ֻ��Ҫ�ж�s��i���Ƿ����s��j��������
        boolean[][] dp;
        if(s.length() == 0)  
        {  
            return "";  
        }  
        if(s.length() == 1)  
        {  
            return s;  
        }  
 
        //��̬�滮��˼·�� dp[i][j] ��ʾ���� ��i �� j ���ִ����Ƿ��ǻ��Ĵ���
        dp = new boolean[s.length()][s.length()];  
 
        int i,j;  
 
        //��ʼ��dp[][]����  
        for( i = 0; i < s.length(); i++)  
        {  
            for( j = 0; j < s.length(); j++)  
            {  
                if(i >= j)  
                {  
                    //��i == j ��ʱ��ֻ��һ���ַ����ַ���; �� i > j ��Ϊ�ǿմ���Ҳ�ǻ���
                    dp[i][j] = true;  
 
                }  
                else  
                {  
                    //�����������ʼ���ɲ��ǻ���  
                    dp[i][j] = false;  
                }  
            }  
        }  
 
        //k��ʾ�Ӵ��������������±�-��ʼ�±�  
        int k;
 
        //���Ĵ�����󳤶�
        int maxLen = 1;
 
        //����Ĵ���s�е��±�
        int rf = 0, rt = 0;
 
        //��̬�滮�Ľ����ǰ����ַ����ĳ��ȴ�1��n�ƽ��ġ�ʵ���Ͼ��Ǽ���������sliding window��k����
        //��ĳ���
        for( k = 1; k < s.length(); k++)  
        {  
            //�Ӵ���ʼ�±�Ϊi
            for( i = 0;  k + i < s.length(); i++)  
            {  
                //�Ӵ������±�j,j=i+k
                j = i + k;  
 
                //���ַ��� s[i....j] ��� s[i] != s[j] ��ô���ǻ���  
                if(s.charAt(i) != s.charAt(j))
                {  
                    dp[i][j] = false;  
                }
 
                //���s[i] == s[j] ���������� s[i+1][j-1] ����
                else    
                {  
                    dp[i][j] = dp[i+1][j-1];  
                    if(dp[i][j])  
                    {  
                        //�Ӵ�����=�����±�-��ʼ�±�+1=j-i+1=(i+k)-i+1=k+1
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
 
        //rfΪ����Ĵ�����ʼ�±꣬rtΪ�����±ꡣ��Ϊʲô���صĽ����s.substring(rf,rt+1)�أ�
        //[�²ۿ�ʼ]Java��String���ṩ��substring(int beginIndex,int endIndex)
        //beginIndexΪ��ʼ����������ӦString�����еĿ�ʼλ��
        //endIndex�ǽ�ֹ������λ�ã���ӦString�еĽ���λ��
        //1.ȡ�õ��ַ�������Ϊ��endIndex - beginIndex;
        //2.��beginIndex��ʼȡ����endIndex��������0��ʼ��������[������endIndexλ�õ�]
        //���ˣ�ԭ������������صĽ���ǲ�����endInexλ���ϵ��ַ��ģ�������Ҫ+1
        //��˵beginIndex��endIndex��ȡֵ��Χ��[0,s.length()-1].
        //���������ͨ��substring��������������Լ��Ļ�����ȴ��s.substring(0,s.length())
        return s.substring(rf, rt+1);  
    }
	//����Ĵ��룬�о����ĳ�ʼ��dp���������һЩ
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

	 public String longestPalindrome3(String s) {//������չ��
			
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
		public String longestPalindrome4(String s) {  //code ganker��
		    if(s == null || s.length()==0)  
		        return "";  
		    boolean[][] palin = new boolean[s.length()][s.length()];  
		    String res = "";  
		    int maxLen = 0;  //����daabbaaa��������������ȿ����һ��a��Ȼ��ó�palin[i][j]=true
		    //Ȼ�󿴵����ڶ���a��Ȼ�󿴵����ڶ���a�����һ����ĸ�Ƿ���ȡ�Ȼ�󿴵���������a��Ȼ��
		    //j�ӵ�����������ʼ���������ڶ�����������һ��Ȼ�󿴵������ĸ���ĸ��Ȼ��j�ӵ������Ŀ�ʼ
		    //Ȼ�󵽵����������ڶ�����һ���������
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
		//�ڶ�����֪�����Ƕ�̬�滮֮��������Լ�д�ģ������������Ҳaccept������������Ż��ģ�1��
		//��Ҫ�����ַ�����Ӧ�ø��³��Ⱥ���ʼ�㣬���Ÿ�һ��substring��2��������forѭ������һ��
		//��������أ���ʼ��Ӧ����sliding window�����㣬�������
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
		//������,˼·�Ǽǵõģ�����Сë���߽���������˺ü��Ρ�
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

	//11/13/2018 ��Ȼһ�ι��ˣ�����Ҫע����±�
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
