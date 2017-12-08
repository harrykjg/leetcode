//http://www.tuicool.com/articles/Iv2QJf
//http://blog.csdn.net/feliciafay/article/details/18999903
//http://blog.csdn.net/loverooney/article/details/37359477
//http://blog.csdn.net/a83610312/article/details/12870501
//http://jixiangsanbao.wordpress.com/2014/06/12/word-break/ ���黹��dfs��ʱ�Ľⷨ
import java.util.HashSet;
import java.util.Set;

public class WordBreak {
	
	public static void main(String[] args) {
		WordBreak wb=new WordBreak();
		Set<String> dict=new HashSet<String>();
		dict.add("a");
		wb.wordBreak("a", dict);
	}
//��������ܷ���ַ����ָʹ��ÿ�����ֶ�����dict���ҵ���ֻ��˵��̬�滮̫ţ��

	public boolean wordBreak(String s, Set<String> dict) {
		if(s.length()==0||s.length()==0){
			return false;
		}
		boolean[] memo=new boolean[s.length()+1];//˼·���ǣ�һ��boolean���飬��¼ԭ�ַ�����0��i
		                                  //��subtring�Ƿ��ָܷ����memo[memo.length-1]��ֵ��
		memo[0]=true;                     //�����ַ����ܷ񱻷ָ������һ�����İ�boolean����
		for(int i=1;i<=s.length();i++){  //���ϡ�
			for(int j=0;j<i;j++){
				                 //���if�������ǣ�����abcdefg����ʱi��6�ˣ�Ȼ�����forѭ������
				                 //abcdefg��bcdefg��cdefg��defg��efg��fg��g���������abcdefg
				                 //���ֵ����ҵõ��Ļ�����ֱ��memo[6]=true��break�ˣ����bcdefg
				                //�����ֵ����ҵ�����ôͬʱ��Ҫa���ֵ������ҵ���memo[6]����true��
				                //ͬ�����cdefg�����ֵ����ҵ���ͬʱab���ֵ���Ҳ�У�memo[6]����
				                //true��ֻҪ������һ�ֿ��ԣ��Ϳ���break��
//15�����
//����abc�����memo��һλ��true��Ҫ��abc�ܷ�break�����ȿ�a�ܷ�break���ٿ�ab���ٿ�abc����a��break�������ǣ�
//a��ǰ�棨�����ǿգ��ܱ���ֲ���a���ֵ����a�ǿɱ�break�ģ���memo[1]=true��Ȼ��ab��ab�ܱ�break�������ǣ�
//ab��ǰ���ܱ������ab���ֵ��a�ܱ������b���ֵ����memo[2]=true���ٿ�abc��abc�ܱ�break�������ǣ�
//abc��ǰ���ܱ������abc���ֵ����a�ܱ������bc���ֵ����ab�ܱ������c���ֵ���
				if(memo[j]&&dict.contains(s.substring(j,i))){
					memo[i]=true;
					break;
				}
			}
		}
		return memo[memo.length-1];

	}
	//������,��Ȼһ�ι��ˡ���������Ҫע������ѭ����index���⣬����������׳���
	public boolean wordBreak2(String s, Set<String> dict) {
		if(s.length()==0){
			return false;
		}
		boolean[] dp=new boolean[s.length()+1];
		dp[0]=true;
		for(int i=1;i<=s.length();i++){//�����i��j�͵�һ�ε��Ǹ����岻һ�����������ȿ�0��1����ĸ
			//��dict��������ٿ�0��2���ٿ�0��3һֱ�����Ȼ���ٿ�1��2,1��3,1��4����2��3,2��4.��
			//Ҳ�С���
			for(int j=0;j<=s.length()-i;j++){
				String temp=s.substring(i-1,i+j);
				if(dict.contains(temp)&&dp[i-1]){
					dp[i+j]=true;
				}
			}
		}
		return dp[dp.length-1];
	}
	//30/9
	public boolean wordBreak3(String s, Set<String> dict) {
		if(s.length()==0){
			return false;
		}
		boolean[] dp=new boolean[s.length()+1];
		dp[0]=true;
		//������index�����룬��ȻҲһ��accept�ˣ�����ֺ�֮ǰ�Ĳ�һ��
		for(int i=1;i<=s.length();i++){
			for(int j=1;j<=i;j++){
				String temp=s.substring(i-j,i);
				if(dict.contains(temp)&&dp[i-j]){
					dp[i]=true;
					break;
				}
			}
		}
		return dp[dp.length-1];
	}

}
