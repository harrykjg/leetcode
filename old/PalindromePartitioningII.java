import java.util.HashSet;
//http://blog.csdn.net/yutianzuijin/article/details/16850031  ��������ĺ�
//http://jixiangsanbao.wordpress.com/2014/05/22/palindrome-partitioning-ii/ ���뼪������׶�Щ
//http://www.cnblogs.com/sunshineatnoon/p/3870222.html
//http://blog.csdn.net/linhuanmars/article/details/22837047
public class PalindromePartitioningII {
	
	public static void main(String[] args) {
		PalindromePartitioningII pp=new PalindromePartitioningII();
		System.out.println(pp.minCut2("dde"));
	}

	//����ͦ�ѵģ��������ǵĴ���ܾ�֮��Ŷ���ע���palindromepartition1�Ĳ�֮ͬ���ǣ����������
	//dfs��accept��ԭ���ǣ�����Ҫ���������и�������Ա���ȫ�����ܶ��ѳ�����������Ҫ�������ٵ�
	//�и��������ԡ��������������һ������
	
	//˼·�ǣ��ȵ�Ū��longestpalindromicsubstirng���õ�һ����ά���飬������Ƕ����ַ���s��
	//����λ��i��j�Ƿ��ǻ��ĵ�booleanֵ��Ȼ�����������Ϣ����ȥ��̬�滮��С�и��
	//��С�и����˼·�ǣ�dp[i]����0��i���ַ�����С�и���,dp[i]��ʼ��Ϊi���������£�i����ĸ
	//Ҫ1�����ķּ�i���з���dp[i]=min��dp[i],dp[j-1]+1)������j��0��i֮���һ��������˼��j��i֮��
	//����ǻ��ĵĻ�����ôdp[i]�͵���dp[j-1]����j��i֮��Ļ��ģ����jҪȫ���Թ����ó���С�����
	//����aabcbk����һ����ĸ�ǻ��ģ�����dp[0]=0���ٿ�ǰ������ĸҲ�ǻ��ģ�����dp[1]Ҳ��0���ٿ���
	//������ĸ������aab���ǻ��ģ����Դ�aab�����һ����ĸ����b�ǻ��ģ�����dp[2]=dp[1]+1��
	//Ȼ��ab�����ǻ��ģ�Ȼ��aabҲ���ǣ�done��Ȼ��aabc�����ǻ��ģ�Ȼ��c���ǻ��ģ�
	//����dp[3]=dp[2]+1��Ȼ��bc�����ǻ��ģ�Ȼ��abcҲ���ǣ�Ȼ��aabcҲ���ǣ�done��Ȼ��
	//aabcb�����ǻ��ģ�Ȼ��b���ǻ��ģ�dp[]=dp[j-1]+1��Ȼ��cb�����ǣ�Ȼ��bcb�ǻ��ģ�����
	//dp[]=dp[j-1]+1���ͻ�õ���С����
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
		   for(int j=i;j>=0;j--){//��i��ʼ��ǰ��
			   if(dict[j][i]){
				   dp[i]=Math.min(dp[i], dp[j-1]+1);
			   }
		   }
	   }
	   return dp[dp.length-1];
	}  
	private boolean[][] getDict(String s)  //��longestpalindromicsubstirng��һ����
	{  
	    boolean[][] dp=new boolean[s.length()][s.length()];
	    for(int i=0;i<s.length();i++){//���i����sliding window�ĳ���
	    	for(int j=0;j<s.length()-i;j++){
	    		int end=j+i;
	    		if(s.charAt(j)==s.charAt(end)&&(end-j<=2||dp[j+1][end-1])){
	    			dp[j][end]=true;
	    		}
	    	}
	    }
	    return dp;
	}  

	//30/9,����ͦ�ѵģ��߽�����Ҫע�⣬���ж�����dp�����
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
			for(int j=i;j>=0;j--){//��ʼд��j=i-1�ˣ������Ļ�dp[j][i]���ǿ�2����ĸ�ˣ���©����
				if(dp[0][i]){      //һ����ĸ�����
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
