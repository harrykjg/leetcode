import java.util.HashSet;
//http://jixiangsanbao.wordpress.com/2014/07/28/decode-ways/

public class DecodeWay {
	
	public static void main(String[] args) {
		DecodeWay dw=new DecodeWay();
		System.out.println(dw.numDecodings2("026"));
	}
	//dfs�����ǶԵģ����ǳ�ʱ.ע�⣬��12026���֣���ȷ�ķַ�ֻ��2�֣���֮ǰд������map��integer
	//�����Ļ��õ�substring��ת��integer��֤map���Ƿ����ʱ�ͻ�����⣬���硰02��Ӧ���ǲ��Ϸ���
	//��ת��integer֮������2�������ͺϷ��ˣ����Գ���Ӧ����map��ֱ�Ӵ��ַ������͵ľͶ���
	int max=0;
	public int numDecodings(String s){
		if(s.length()==0){
			return 0;
		}
		HashSet<String> set=new HashSet<String>();
		set.add("1");
		set.add("2");
		set.add("3");
		set.add("4");
		set.add("5");
		set.add("6");
		set.add("7");
		set.add("8");
		set.add("9");
		set.add("10");
		set.add("11");
		set.add("12");
		set.add("13");
		set.add("14");
		set.add("15");
		set.add("16");
		set.add("17");
		set.add("18");
		set.add("19");
		set.add("20");
		set.add("21");
		set.add("22");
		set.add("23");
		set.add("24");
		set.add("25");
		set.add("26");
		
//		check(0,s,set);
		check(0,s,set);
		return  max;
		
	}
	public void check(int b,String s,HashSet<String> set){
		if(b==s.length()){
			max++;
			return;
		}
		String s1=s.substring(b,b+1);
		if(set.contains(s1)){
			check(b+1,s,set);
		}
		if(b+2<=s.length()){
			String s2=s.substring(b,b+2);
			if(set.contains(s2))
				{
				    check(b+2,s,set);
				}
		}
	}
	

	//dp�ķ�����˼·����0����ǰ��ĸ�ķָ������0����һ����ĸ�ķָ��������0�����ϸ���ĸ�ķָ�
	//����
	public int numDecodings2(String s) {  
		if(s.length()==0){
			return 0;
		}
		int[] dp=new int[s.length()+1];
		dp[0]=1;//dp[0]Ӧ����Ϊ1����Ϊ����ǵ�һ�����ֵĻ�����ô����dp[i-1]ҲӦ����1�Ŷԣ�����
		//��һ�����־͵Ļ��ַ�������0��
		for(int i=1;i<=s.length();i++){//ע��i��substring�����Ǹ�����������������<=s.length
			String s1=s.substring(i-1,i);
			if(valid(s1)){
				dp[i]+=dp[i-1];
			}
			if(i-2>=0){
				String s2=s.substring(i-2,i);
				if(valid(s2)){
					dp[i]+=dp[i-2];
				}
			}
		}
		return dp[dp.length-1];
	}
	public boolean valid(String s){
		if(s.charAt(0)=='0'){
			return false;
		}
		int temp=Integer.parseInt(s);
		if(temp<27&&temp>0){
			return true;
		}
		return false;
	}
//�ڶ�����Ҳ�����dfs�ķ������벻��dp�ķ���
	
	//������,Ҳ�벻��dp��dp���ǌ�.Ҫ�ǳ�ʼ���͸�ֵ��д��
	public int numDecodings3(String s){
		if(s.length()==0){
			return 0;
		}
		int[] dp=new int[s.length()+1];
		dp[0]=1;
		for(int i=1;i<=s.length();i++){//������ں�����©
			if(valid2(s.substring(i-1, i))){
				dp[i]+=dp[i-1];
			}
			if(i-2>=0){
				if(valid2(s.substring(i-2,i))){
					dp[i]+=dp[i-2];
				}
			}
		}
		return dp[dp.length-1];
	}
	private boolean valid2(String s){
		if(s.charAt(0)=='0'){
			return false;
		}
		if(Integer.parseInt(s)>26||Integer.parseInt(s)<0){
			return false;
		}
		return true;
	}
	//8/4/2018,����decode
	public int numDecodings4(String s){
		if(s.length()==0){
			return 0;
		}
		int[] dp=new int[s.length()+1];
		dp[0]=1;
		char[] ch=s.toCharArray();
		for(int i=1;i<=ch.length;i++){
			if(i==1){
				dp[i]=helper(s.substring(i-1,i))*dp[i-1];
				continue;
			}
			dp[i]=helper(s.substring(i-2,i))*dp[i-2]+helper(s.substring(i-1,i))*dp[i-1];
		}
		return dp[dp.length-1];
	}
	int helper(String s){
		char[] ch=s.toCharArray();

		if(ch.length==1){
			if(ch[0]-'1'>=0&&ch[0]-'1'<=8){
				return 1;
			}
		}
		if(ch.length==2){

			if(ch[0]=='1'&&ch[1]-'0'>=0&&ch[1]-'0'<=9){
				return 1;
			}
			if(ch[0]=='2'&&ch[1]-'0'>=0&&ch[1]-'0'<=6){
				return 1;
			}
		}
		return 0;
	}

}
