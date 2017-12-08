import java.util.ArrayList;
import java.util.HashMap;
//http://jixiangsanbao.wordpress.com/2014/03/29/substring-with-concatenation-of-all-words/
//吉祥这个和我的运行时间基本相同，要1千多ms
//http://blog.csdn.net/linhuanmars/article/details/20342851 code ganker的只要500多
//
public class SubstringwithConcatenationofAllWords {
	
	public static void main(String[] args) {
		SubstringwithConcatenationofAllWords sc=new SubstringwithConcatenationofAllWords();
		String[] L={"ab","ba","ab","ba"};
		ArrayList<Integer> al=sc.findSubstring3("aaaaaabaababbaba", L);
	}

	ArrayList<Integer> al=new ArrayList<Integer>();
	public ArrayList<Integer> findSubstring(String S, String[] L) {
		if(L.length==0){
			for(int i=0;i<S.length();i++){
				al.add(i);
			}
			return al;
		}
		if(S.length()==0){
			return al;
		}
		boolean[] memo=new boolean[L.length];
		for(int i=0;i<=S.length()-L[0].length()*L.length;i++){
			
				check(S,i,L,memo,0);	
		}
		return al;
	}
	public void check(String s,int b1,String[] l,boolean[] memo,int cur){
		if(cur==l[0].length()){
			al.add(b1-l[0].length()*l.length);
			return;
		}
		for(int i=0;i<l.length;i++){
			if(memo[i]){
				continue;
			}
			else{
				String ss=l[i];
				int j=0;
				for(j=0;j<l[0].length();j++){
					if(s.charAt(b1+j)!=ss.charAt(j)){
						break;
					}
				}
				if(j==l.length){
					memo[i]=true;
					check(s,b1+j,l,memo,cur+1);
					memo[i]=false;
				}				
			}
		}
	}
	//这个是看了他们的思想之后自己写的，思路就是用2个hashmap，第一个村L里的所有字符串
	//然后对每个原字符串S的字母，得出他的一个长度为L中所有字符串长度的和的substring，
	//然后用第二个hashmap记录他每个长度为L[0]的substring，记录其出现次数，看是否和hashmap1一样，
	//这里其实只要比较他是否大于hashmap1的就行了，超出即错，不存在该substring也错，就break了
	public ArrayList<Integer> findSubstring2(String S, String[] L) {
		ArrayList<Integer> al=new ArrayList<Integer>();
		if(L.length==0){
			for(int i=0;i<S.length();i++){
				al.add(i);
			}
			return al;
		}
		if(S.length()==0){
			return al;
		}
		HashMap<String,Integer> hm1=new HashMap<String,Integer>();
		HashMap<String,Integer> hm2=new HashMap<String,Integer>();
		for(int i=0;i<L.length;i++){//hm1记录整个L
			if(hm1.containsKey(L[i])){
				hm1.put(L[i], hm1.get(L[i])+1);
			}else{
				hm1.put(L[i], 1);
			}
		}
		int window=L[0].length()*L.length;
		for(int i=0;i<=S.length()-window;i++){
			hm2.clear();
			String ss=S.substring(i, i+window);
			int j=0;
			for(j=0;j<=ss.length()-L[0].length();j+=L[0].length()){
				String sss=ss.substring(j,j+L[0].length());
				if(hm1.containsKey(sss)){
					
					if(hm2.containsKey(sss)){
						hm2.put(sss, hm2.get(sss)+1);
					}else{
						hm2.put(sss, 1);
					}
					if(hm2.get(sss)>hm1.get(sss)){
						break;
					}
				}else{
					break;
				}
			}
			if(j==ss.length()){//j==ss.length说明这个for循环遍历了都没有报错，说明全部符合
				al.add(i);
				i+=L[0].length()-1;
			}
		}
		return al;
	}
	//code ganker的,还是思路不太清晰
	public ArrayList<Integer> findSubstring3(String S, String[] L) {  
	    ArrayList<Integer> res = new ArrayList<Integer>();  
	    if(S==null || S.length()==0 || L==null || L.length==0)  
	        return res;  
	    HashMap<String,Integer> map = new HashMap<String,Integer>();  
	    for(int i=0;i<L.length;i++)  
	    {  
	        if(map.containsKey(L[i]))  
	        {  
	            map.put(L[i],map.get(L[i])+1);  
	        }  
	        else  
	        {  
	            map.put(L[i],1);  
	        }  
	    }  
	    //他这个外层循环只循环字典中字符串的长度的次数就能保证把所有的substring都找对，因为
	    //内循环是一直延续到最后的，比如“aaaaaabaababbaba”，{"ab","ba","ab","ba"}这个例子，
	    //只需要试从第一个a开始，一直往后搜，和第二个a开始，一直往后搜，就能保证所有substring的
	    //组合都找得到，比较神奇
	    for(int i=0;i<L[0].length();i++)  
	    {  
	        HashMap<String,Integer> curMap = new HashMap<String,Integer>();  
	        int count = 0;  
	        int left = i;  
	        //他这个j其实就是一直可以延续到倒数第二个单词的
	        for(int j=i;j<=S.length()-L[0].length();j+=L[0].length())  
	        {  
	            String str = S.substring(j,j+L[0].length());  
	            if(map.containsKey(str))  
	            {  
	                if(curMap.containsKey(str))  
	                    curMap.put(str,curMap.get(str)+1);  
	                else  
	                    curMap.put(str,1);  
	                if(curMap.get(str)<=map.get(str))  
	                    count++;  
	                else  
	                {  
	                    while(curMap.get(str)>map.get(str))  
	                    {  
	                        String temp = S.substring(left,left+L[0].length());  
	                        curMap.put(temp,curMap.get(temp)-1);  
	                        left += L[0].length();  
	                    }  
	                }  
	                if(count == L.length)  
	                {  //找到了完整的匹配之后
	                    res.add(left);  
	                    String temp = S.substring(left,left+L[0].length());  //把第一个移除
	                    curMap.put(temp,curMap.get(temp)-1);  
	                    count--;  
	                    left += L[0].length();  
	                }  
	            }  
	            else  
	            {  //他这个比较巧妙的就是，如果完全不存在于字典的单词，就清空curmap重来，否则如果
	            	//存在于字典，但是大于字典中的数量，那就先去掉第一个，再看后一个是否符合条件
	                curMap.clear();  
	                count = 0;  
	                left = j+L[0].length();  
	            }  
	        }  
	    }  
	    return res;  
	}  
//第二次思路不太清晰，就没写
	
	//第三轮,有之前的思路，然后自己写的，基本没什么错，调试一次啊就过了，但是运行时间要1500多.
	//然后改进一下就是用temp记录substring，不要每次substring，就1200多ms
	public ArrayList<Integer> findSubstring4(String S, String[] L) {
		ArrayList<Integer> a=new ArrayList<Integer>();
		if(S.length()==0||L.length==0){
			return a;
		}
		int len=L[0].length();
		int len2=L.length*len;
		HashMap<String,Integer> hm=new HashMap<String,Integer>();
		for(int i=0;i<L.length;i++){
			if(hm.containsKey(L[i])){
				hm.put(L[i], hm.get(L[i])+1);
			}else{
				hm.put(L[i], 1);
			}
		}
		HashMap<String,Integer> hm2=null;
		for(int i=0;i<=S.length()-len2;i++){
			hm2=new HashMap<String,Integer>(hm);
			go(i,i+len2,S,L,hm2,a);
		}
		return a;
	}
	private void go(int b,int end,String S,String[] L,HashMap<String,Integer> hm,ArrayList<Integer> a){
		if(b==end){
			a.add(end-L[0].length()*L.length);
			return;
		}
		String temp=S.substring(b,b+L[0].length());
		if(!hm.containsKey(temp)){
			return;
		}else{
			hm.put(temp, hm.get(temp)-1);
			if(hm.get(temp)<0){
				return;
			}
			go(b+L[0].length(),end,S,L,hm,a);
		}
	   
	}
}
