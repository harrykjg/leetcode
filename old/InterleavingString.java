import java.util.ArrayList;
//�ݹ鳬ʱ���ǲο�����http://19542002.blog.163.com/blog/static/133932382201333002034138/

//http://blog.csdn.net/doc_sgl/article/details/11714793 �����ı�
//http://blog.csdn.net/linhuanmars/article/details/24683159

public class InterleavingString {
	public static void main(String[] args) {
		InterleavingString il=new  InterleavingString();
		System.out.println(il.isInterleave2("aabcc", "dbbca", "aadbbcbcac"));
	}

	//˼·����dfs���ݷ���һ��һ���ַ������ǲ�����a1����a2�У��ᳬʱ
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
		//���￪ʼ�е��벻���ף�֮ǰд�Ļ���û�����flag�����flag���жϵ�
		//�����������ˣ��������ÿһ���������������ֲ棬�����һ���ֲ���ˣ���falg=false���Ǿ�
		//����ν�������Եڶ����ֲ棬������ˣ���ֱ�ӷ���true���ڶ����ֲ�Ҳһ�������ø�flag��¼
		//��һ���Ƿ���ȷ����ȷ�Ļ���ֱ�ӷ���true�ˣ�����Ļ��ͼ���
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
	//dp���ǌţ�˼·����dp[i][j]����s1��ǰi����ĸ��s2��ǰj����ĸ���ܷ񹹳�s3��ǰi+j����ĸ
	//����ʽ����dp[i][j]=dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1)����
	//dp[i][j]=dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1)��һ����true����ôdp[i][j]����
	//true�������ǣ�s1��ǰi����ĸ��s2��ǰj-1����ĸ�����s3��ǰi+j-1����ĸ������s2�ĵ�j����ĸ
	//����s3�ĵ�i+j����ĸ�Ļ���˵��s1��ǰi����ĸ��s2��ǰj����ĸ�����s3��ǰi+j����ĸ��
	//��������dp����ʱ��һ�к͵�һ�пգ�����charAt���ǣ�j-1�����ߣ�i-1������i+j-1
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
					if(i==0){//�����������к��ж�����һ�еĻ�������
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
	 //�����֣����Ǽǲ��ö�̬�滮�����壬��ס�ͺ�
	 public boolean isInterleave3(String s1, String s2, String s3) {
	        
	 }

}
