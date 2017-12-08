//http://blog.csdn.net/liuweiran900217/article/details/19204117 ��������ſ���
//http://jixiangsanbao.wordpress.com/2014/06/13/word-break-ii/
//http://blog.csdn.net/linhuanmars/article/details/22452163 ��˵��dfs�Ͷ�̬�滮���ӶȲ��
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WordBreakII {
	public static void main(String[] args) {
		WordBreakII wb=new WordBreakII();
		Set<String> dict=new HashSet<String>();
		dict.add("a");
		dict.add("b");
		System.out.println(wb.wordBreak3("ab", dict));
		
	}
//˼��������հ�word break�ģ��ó�һ��dp���飬��ʼ�һ���ΪҪ��parlindrome partition2����Ҫ��2ά
	//dp�����ֻҪһά�����ˡ�Ȼ�������һάdpȥdfsȡ���п��Ե��ַ�����
	public ArrayList<String> wordBreak(String s, Set<String> dict) {
		
		ArrayList<String> al=new ArrayList<String>();
		if(s.length()==0||dict.size()==0){
			return al;
		}
		boolean[] dp=new boolean[s.length()+1];
		dp[0]=true;
		for(int i=1;i<=s.length();i++){
			for(int j=0;j<i;j++){
				if(dp[j]&&dict.contains(s.substring(j, i))){
					dp[i]=true;
				}
			}
		}
		String ss="";
		if(dp[dp.length-1]==false){
			return al;
		}
		dfs(s,dp,0,ss,al,dict);
		return al;
	}
	public void dfs(String s,boolean[] dp,int b,String ss,ArrayList<String> al, Set<String> dict){
		if(b==s.length()){
			ss=ss.substring(0,ss.length()-1);//�����һ�����ʺ�Ŀո�ȥ��
			al.add(ss);
			return;
		}
		for(int i=b+1;i<=s.length();i++){
			if(dp[b]&&dict.contains(s.substring(b,i))){
				dfs(s,dp,i,new String(ss)+s.substring(b,i)+" ",al,dict);
			}
		}
	}
//�ڶ�����Ļ�����ֻ���뵽dfs�ģ��ᳬʱ
	
//������,�µ�������word break1�ķ���Ū����dp���飬��dfs�����ǲ�̫���complexity��ȴ�dfs��ߣ�
	//����ǿ϶��ã�����123456789�������1��ʼ��⵽6,7,8������3��ʼҲ��⵽6,7,8�ˣ���dp��¼
	//�Ļ��Ͳ����ڼ����
	//���Ǹ����һ���ط���go�������ҿ�ʼд����2��ѭ��,Ҫ�����.��ʵdp����Ӧ��Ҳ����ˣ�
	//Ӧ������dp[b]��ȷ�����0��b���substring�ܷ񱻷ָ����dict��ȷ��b+1��b+1+i���substring�Ƿ�
	//������dict��
	public ArrayList<String> wordBreak2(String s, Set<String> dict) {
		ArrayList<String> a=new ArrayList<String>();
		boolean[] dp=new boolean[s.length()+1];
		dp[0]=true;
		for(int i=1;i<=s.length();i++){

			for(int j=0;j<=s.length()-i;j++){
				String temp=s.substring(i-1,i+j);
				if(dict.contains(temp)&&dp[i-1]){
					dp[i+j]=true;
				}
			}
		}
		if(dp[dp.length-1]==false){
			return a;
		}
		go(0,"",s,dict,a,dp);
		return a;
	}
	private void go(int b,String cur,String s,Set<String> dict,ArrayList<String> a,boolean[] dp){
		if(b==s.length()){
			cur=cur.substring(0,cur.length()-1);
			a.add(new String(cur));
			return;
		}//�����ҿ�ʼ�������2��ѭ��ȷ���ӽ�ȥ�ĵ��ʵĿ�ʼ�ͽ�β����ʵ��һ������ˣ���bȷ����ʼ����
		//forѭ��ȷ����β.��������±�ͦ���ĵ�
		for(int i=1;i<=s.length()-b;i++){
			if(dp[b]&&dict.contains(s.subSequence(b,b+i))){
				go(b+i,cur+s.substring(b,b+i)+" ",s,dict,a,dp);
			}
		}
	}
	
	// 30/9
	public ArrayList<String> wordBreak3(String s, Set<String> dict) {
		ArrayList<String> a=new ArrayList<String>();
		if(s==null||s.length()==0||dict==null){
			return a;
		}
	    boolean[] memo=new boolean[s.length()+1];
	    memo[0]=true;
	    //��memo��������,����catsanddog�������i��0��Ȼ�󿴴�0����һ���ڶ������������Ƿ���
	    //dict��ڵĻ���0��i+j����ַ����Ա��ָ��������substring��i+j��ͻ��һλ��Ӧ�ü�1
	    //���պ�dp����߶���һλ��Ӧ�ü�1�����Ըպõ�����memo[i+j]=true
	    for(int i=0;i<s.length();i++){
	    	for(int j=1;j<=s.length()-i;j++){
	    		String temp=s.substring(i,j+i);
	    		if(dict.contains(temp)&&memo[i]){
	    			memo[i+j]=true;
	    		}
	    	}
	    }
	    if(memo[memo.length-1]==false){
			return a;
		}
	    go2(0,"",memo,s,dict,a);
	    return a;
		
	}
	private void go2(int b,String cur,boolean[] memo,String s,Set<String> dict,ArrayList<String> a){
		
		if(b==s.length()){
			String temp=cur.substring(0,cur.length()-1);
			a.add(temp);
			return;
		}
		//���Ǹ����������2��ѭ���ʹ��Ǻ���1�㣡
		
			for(int j=1;j<=s.length()-b;j++){
				String temp=s.substring(b,j+b);
				if(memo[b]&&dict.contains(temp)){
					go2(b+j,cur+temp+" ",memo,s,dict,a);
				}
			}
		
	}
}
