//http://blog.csdn.net/linhuanmars/article/details/20276833 说了复杂度,吉祥和他的一样的
// http://www.cnblogs.com/yjiyjige/p/3263858.html 介绍kmp的
public class ImplementstrStr {
	
	public String strStr(String haystack, String needle) {
		
		if(haystack==null){
			return null;
		}
		if(needle==null||needle.length()==0){
			return haystack;
		}
		
		for(int i=0;i<haystack.length()-needle.length()+1;i++){
			int j=0;
			
			boolean flag=true;
			while(j<needle.length()){
				if(haystack.charAt(j+i)!=needle.charAt(j)){
					flag=false;
					break;
				}
				j++;
			}
			if(flag){
				return haystack.substring(i);
			}
		}
		return null;

	}

}
