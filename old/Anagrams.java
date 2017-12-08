import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
//就是在这个字符串数组中，有两个或以上各字符串是anagram的话就得加入结果集，
//http://blog.csdn.net/linhuanmars/article/details/21664747
//http://jixiangsanbao.wordpress.com/2014/04/03/anagrams/
public class Anagrams {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] s={"cat","rye","aye","dog", "god","cud","cat","old","fop","bra","arb"};
		Anagrams an=new Anagrams();
		ArrayList<String> al=an.anagrams2(s);
		for(String ss:al){
			System.out.print(ss+" ");
		}
		
	}
	
//	ArrayList<String> al=new ArrayList<String>();
//	
//	public ArrayList<String> anagrams(String[] strs){
//		
//		if(strs.length<=1){
//			return al;
//		}
//		
//		for(int i=0;i<strs.length;i++){
//			for(int j=i+1;j<strs.length;j++){
//				boolean b=false;
//				b=compare(strs[i],strs[j]);
//				if(b=true){
//					al.add(strs[i]);
//					al.add(strs[j]);
//					
//				}
//			}
//		}
//		return al;
//	}
//	public boolean compare(String s1,String s2){
//		if(s1.length()!=s2.length()){
//			return false;
//		}
//		else{
//			
//			ArrayList<Character> a=new ArrayList<Character>();
//			
//			for(int i=0;i<s1.length();i++){
//				a.add(s1.charAt(i));
//				
//				
//			}
//			for(int i=0;i<s2.length();i++){
//				if(!a.contains(s2.charAt(i))){
//					return false;
//				}
//			}
//		}
//		return true;
//	}

	public ArrayList<String> anagrams(String[] strs) {  
        ArrayList<String> ret = new ArrayList<String>();  
          
        // 用排序过的string作为key，它的anagram作为ArrayList  
        Hashtable<String, ArrayList<String>> ht = new Hashtable<String, ArrayList<String>>();  
          
        for(int i=0; i<strs.length; i++){  
            String sorted = sorted(strs[i]);  
            ArrayList<String> val = ht.get(sorted); 
            if(val != null){  
                val.add(strs[i]);  //注意，这样改val是会影响到map里存的val的
            }else{  
                val = new ArrayList<String>();  
                val.add(strs[i]);  
                ht.put(sorted, val);  
            }  
        }  
          
        // Hashtable的循环方法 keySet   
        Set<String> set = ht.keySet();  
          
        // 把所有anagram添加到ret中  
        for(String s : set){  
            ArrayList<String> val = ht.get(s);  
            if(val.size() > 1){  
                ret.addAll(val);  
            }  
        }  
          
        return ret;  
    }  
      
    public String sorted(String a){  
        char[] achar = a.toCharArray();  
        Arrays.sort(achar);  
        return new String(achar);  
    }  
	//第二次没写，看看第一次的思路
	//第三轮，基本能写出来，就是忘了怎么遍历hashmap的key了
    public ArrayList<String> anagrams2(String[] strs) { 
    	ArrayList<String> a=new ArrayList<String>();
    	if(strs.length==0){
    		return a;
    	}
    	HashMap<String,ArrayList<String>> hm=new HashMap<String,ArrayList<String>>();
    	for(int i=0;i<strs.length;i++){
    		char[] c=strs[i].toCharArray();
    		Arrays.sort(c);
    		
    		String temp=new String(c);//注意，这里应该是用new String(c)而不是用toString方法，
    		if(hm.containsKey(temp)){//toString方法指的是把这个object用string的形式表达，即那些
    			 hm.get(temp).add(strs[i]);//无意义的字符的组合
    		}else{
    			ArrayList<String> aa=new ArrayList<String>();
    			aa.add(strs[i]);
    			hm.put(temp,aa );
    		}
    	}
    	Set<String> set= hm.keySet();
    	for(String s:set){
    		if(hm.get(s).size()>1){
    			for(int i=0;i<hm.get(s).size();i++){
    				a.add(hm.get(s).get(i));
    			}
    		}
    	}
    	return a;
    }
}
