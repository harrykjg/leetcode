public class DistinceSequence {
//http://jixiangsanbao.wordpress.com/2014/06/30/distinct-subsequences/
//Ӧ�ÿ����������ȽϺ���Ҫ��ʵֻҪ�ȳ�ʼ����һ�У���һ�в��ö����ؼ���״̬���̵����
//���ϵ��£������Ҹ�ֵ������˵��ʵ�ǹ̶���T��ǰi���ַ���Ȼ�󲻶ϼ���S���ַ������бȽ�
//�������¼����S�ĵ�j���ַ���T�ĵ�i���ַ���ͬ�Ļ�,��ôdp[i][j]��ֵ����dp[i][j-1]����
//�����j���ַ��Ӳ��ӽ�����ûӰ�죬��������ͬ
//�����ͬ�Ļ�����ô���ǵ�����ֵ��2������ɣ�1���Ͳ���������Ҳ��һ���ļ�dp[i-1][j-1]��
//2��dp[i][j-1],�����ӵ�j���ַ�֮ǰ��ԭ����ֵ������ͦ������
//http://blog.csdn.net/linhuanmars/article/details/23589057
//http://www.blogjava.net/menglee/archive/2013/12/31/408231.html
	public static void main(String[] args) {
		
		
		DistinceSequence ds=new DistinceSequence();
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
	//�ڶ����Լ�ûд
	//������,�Է��̵����û�жԣ�Ҫ����ԭ����
	public int numDistinct2(String S, String T) {
		if(T.length()>S.length()){
			return 0;
		}
		int[][] dp=new int[T.length()+1][S.length()+1];
		for(int i=0;i<=S.length();i++){//��γ�ʼ���������ΪtΪ��ʱ������Ϊ0ʱ����s��ֻ���ҵ�1��t��������s���ȸ�t
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

