//http://blog.csdn.net/liuweiran900217/article/details/19204117 看了这个才看懂
//http://jixiangsanbao.wordpress.com/2014/06/13/word-break-ii/
//http://blog.csdn.net/linhuanmars/article/details/22452163 他说了dfs和动态规划复杂度差不多
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
//思想就是先照搬word break的，得出一个dp数组，开始我还以为要像parlindrome partition2那样要个2维
	//dp，结果只要一维就行了。然后用这个一维dp去dfs取所有可以的字符串。
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
			ss=ss.substring(0,ss.length()-1);//把最后一个单词后的空格去掉
			al.add(ss);
			return;
		}
		for(int i=b+1;i<=s.length();i++){
			if(dp[b]&&dict.contains(s.substring(b,i))){
				dfs(s,dp,i,new String(ss)+s.substring(b,i)+" ",al,dict);
			}
		}
	}
//第二次想的话还是只能想到dfs的，会超时
	
//第三轮,猜到了先用word break1的方法弄出个dp数组，再dfs，但是不太清楚complexity会比纯dfs提高？
	//提高是肯定得，想想123456789，计入从1开始检测到6,7,8，而从3开始也检测到6,7,8了，用dp记录
	//的话就不用在检测了
	//还是搞错了一个地方，go方法里我开始写的是2重循环,要想清楚.其实dp理解和应用也搞错了，
	//应该是用dp[b]来确定这个0到b这个substring能否被分割，再用dict来确定b+1到b+1+i这个substring是否
	//存在于dict中
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
		}//这里我开始想的是用2层循环确定加进去的单词的开始和结尾，其实用一层就行了，用b确定开始，用
		//for循环确定结尾.想清楚，下标挺恶心的
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
	    //对memo的理解错了,比如catsanddog，先起点i是0，然后看从0到第一，第二，第三。。是否在
	    //dict里，在的话即0到i+j这段字符可以被分割，这里由于substring的i+j是突了一位，应该减1
	    //而刚好dp是左边多了一位，应该加1，所以刚好抵消，memo[i+j]=true
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
		//还是搞错，还是用了2层循环就错！记好是1层！
		
			for(int j=1;j<=s.length()-b;j++){
				String temp=s.substring(b,j+b);
				if(memo[b]&&dict.contains(temp)){
					go2(b+j,cur+temp+" ",memo,s,dict,a);
				}
			}
		
	}
}
