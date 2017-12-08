//http://www.tuicool.com/articles/Iv2QJf
//http://blog.csdn.net/feliciafay/article/details/18999903
//http://blog.csdn.net/loverooney/article/details/37359477
//http://blog.csdn.net/a83610312/article/details/12870501
//http://jixiangsanbao.wordpress.com/2014/06/12/word-break/ 吉祥还有dfs超时的解法
import java.util.HashSet;
import java.util.Set;

public class WordBreak {
	
	public static void main(String[] args) {
		WordBreak wb=new WordBreak();
		Set<String> dict=new HashSet<String>();
		dict.add("a");
		wb.wordBreak("a", dict);
	}
//题意就是能否把字符串分割，使得每个部分都能在dict里找到。只能说动态规划太牛逼

	public boolean wordBreak(String s, Set<String> dict) {
		if(s.length()==0||s.length()==0){
			return false;
		}
		boolean[] memo=new boolean[s.length()+1];//思路就是，一个boolean数组，记录原字符串的0到i
		                                  //的subtring是否能分割最后memo[memo.length-1]的值即
		memo[0]=true;                     //整个字符串能否被分割。从左到右一个个的把boolean数组
		for(int i=1;i<=s.length();i++){  //填上。
			for(int j=0;j<i;j++){
				                 //这个if的意义是，比如abcdefg，此时i到6了，然后里层for循环遍历
				                 //abcdefg，bcdefg，cdefg，defg，efg，fg，g，这里如果abcdefg
				                 //在字典里找得到的话，就直接memo[6]=true就break了，如果bcdefg
				                //能在字典里找到，那么同时还要a在字典里能找到，memo[6]才是true，
				                //同理，如果cdefg能在字典里找到，同时ab在字典里也有，memo[6]才是
				                //true，只要有其中一种可以，就可以break了
//15年更新
//比如abc，这个memo第一位是true，要看abc能否break，就先看a能否被break，再看ab，再看abc，而a能break的条件是：
//a的前面（这里是空）能被拆分并且a在字典里，则a是可被break的，即memo[1]=true，然后看ab，ab能被break的条件是：
//ab的前面能被拆分且ab在字典里，a能被拆分且b在字典里，则memo[2]=true，再看abc，abc能被break的条件是：
//abc的前面能被拆分且abc在字典里，或a能被拆分且bc在字典里，或ab能被拆分且c在字典里
				if(memo[j]&&dict.contains(s.substring(j,i))){
					memo[i]=true;
					break;
				}
			}
		}
		return memo[memo.length-1];

	}
	//第三轮,居然一次过了。。但还是要注意两层循环的index问题，得想清楚，易出错
	public boolean wordBreak2(String s, Set<String> dict) {
		if(s.length()==0){
			return false;
		}
		boolean[] dp=new boolean[s.length()+1];
		dp[0]=true;
		for(int i=1;i<=s.length();i++){//我这个i，j和第一次的那个意义不一样，这里是先看0到1个字母
			//被dict包含与否，再看0到2，再看0到3一直到最后，然后再看1到2,1到3,1到4，再2到3,2到4.。
			//也行。。
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
		//妈的这个index很难想，虽然也一次accept了，想的又和之前的不一样
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
