import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
//����������ַ��������У������������ϸ��ַ�����anagram�Ļ��͵ü���������
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
          
        // ���������string��Ϊkey������anagram��ΪArrayList  
        Hashtable<String, ArrayList<String>> ht = new Hashtable<String, ArrayList<String>>();  
          
        for(int i=0; i<strs.length; i++){  
            String sorted = sorted(strs[i]);  
            ArrayList<String> val = ht.get(sorted); 
            if(val != null){  
                val.add(strs[i]);  //ע�⣬������val�ǻ�Ӱ�쵽map����val��
            }else{  
                val = new ArrayList<String>();  
                val.add(strs[i]);  
                ht.put(sorted, val);  
            }  
        }  
          
        // Hashtable��ѭ������ keySet   
        Set<String> set = ht.keySet();  
          
        // ������anagram��ӵ�ret��  
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
	//�ڶ���ûд��������һ�ε�˼·
	//�����֣�������д����������������ô����hashmap��key��
    public ArrayList<String> anagrams2(String[] strs) { 
    	ArrayList<String> a=new ArrayList<String>();
    	if(strs.length==0){
    		return a;
    	}
    	HashMap<String,ArrayList<String>> hm=new HashMap<String,ArrayList<String>>();
    	for(int i=0;i<strs.length;i++){
    		char[] c=strs[i].toCharArray();
    		Arrays.sort(c);
    		
    		String temp=new String(c);//ע�⣬����Ӧ������new String(c)��������toString������
    		if(hm.containsKey(temp)){//toString����ָ���ǰ����object��string����ʽ������Щ
    			 hm.get(temp).add(strs[i]);//��������ַ������
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
