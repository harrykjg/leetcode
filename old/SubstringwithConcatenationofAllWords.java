import java.util.ArrayList;
import java.util.HashMap;
//http://jixiangsanbao.wordpress.com/2014/03/29/substring-with-concatenation-of-all-words/
//����������ҵ�����ʱ�������ͬ��Ҫ1ǧ��ms
//http://blog.csdn.net/linhuanmars/article/details/20342851 code ganker��ֻҪ500��
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
	//����ǿ������ǵ�˼��֮���Լ�д�ģ�˼·������2��hashmap����һ����L��������ַ���
	//Ȼ���ÿ��ԭ�ַ���S����ĸ���ó�����һ������ΪL�������ַ������ȵĺ͵�substring��
	//Ȼ���õڶ���hashmap��¼��ÿ������ΪL[0]��substring����¼����ִ��������Ƿ��hashmap1һ����
	//������ʵֻҪ�Ƚ����Ƿ����hashmap1�ľ����ˣ��������������ڸ�substringҲ����break��
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
		for(int i=0;i<L.length;i++){//hm1��¼����L
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
			if(j==ss.length()){//j==ss.length˵�����forѭ�������˶�û�б���˵��ȫ������
				al.add(i);
				i+=L[0].length()-1;
			}
		}
		return al;
	}
	//code ganker��,����˼·��̫����
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
	    //��������ѭ��ֻѭ���ֵ����ַ����ĳ��ȵĴ������ܱ�֤�����е�substring���Ҷԣ���Ϊ
	    //��ѭ����һֱ���������ģ����硰aaaaaabaababbaba����{"ab","ba","ab","ba"}������ӣ�
	    //ֻ��Ҫ�Դӵ�һ��a��ʼ��һֱ�����ѣ��͵ڶ���a��ʼ��һֱ�����ѣ����ܱ�֤����substring��
	    //��϶��ҵõ����Ƚ�����
	    for(int i=0;i<L[0].length();i++)  
	    {  
	        HashMap<String,Integer> curMap = new HashMap<String,Integer>();  
	        int count = 0;  
	        int left = i;  
	        //�����j��ʵ����һֱ���������������ڶ������ʵ�
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
	                {  //�ҵ���������ƥ��֮��
	                    res.add(left);  
	                    String temp = S.substring(left,left+L[0].length());  //�ѵ�һ���Ƴ�
	                    curMap.put(temp,curMap.get(temp)-1);  
	                    count--;  
	                    left += L[0].length();  
	                }  
	            }  
	            else  
	            {  //������Ƚ�����ľ��ǣ������ȫ���������ֵ�ĵ��ʣ������curmap�������������
	            	//�������ֵ䣬���Ǵ����ֵ��е��������Ǿ���ȥ����һ�����ٿ���һ���Ƿ��������
	                curMap.clear();  
	                count = 0;  
	                left = j+L[0].length();  
	            }  
	        }  
	    }  
	    return res;  
	}  
//�ڶ���˼·��̫��������ûд
	
	//������,��֮ǰ��˼·��Ȼ���Լ�д�ģ�����ûʲô������һ�ΰ��͹��ˣ���������ʱ��Ҫ1500��.
	//Ȼ��Ľ�һ�¾�����temp��¼substring����Ҫÿ��substring����1200��ms
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
