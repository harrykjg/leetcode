//http://cozilla.iteye.com/blog/1950242
//http://blog.csdn.net/lanxu_yy/article/details/17715633
//http://blog.csdn.net/linhuanmars/article/details/24506703
public class ScrambleString {
	public static void main(String[] args) {
		
	}
	 public boolean isScramble(String s1, String s2) {
	        if(s1.length()!=s2.length()){
	        	return false;
	        }
	        if(s1.length()==1&&s1.charAt(0)==s2.charAt(0)){
	            return true;
	        }
	        if(!check(s1,s2)){
	            return false;
	        }
	        for(int i=1;i<s1.length();i++){
	        	String ss1=s1.substring(0,i);
	        	String ss2=s1.substring(i);
	        	String ss3=s2.substring(0,i);
	        	String ss4=s2.substring(i);
	        	
	        	if(isScramble(ss1,ss3)&&isScramble(ss2,ss4)){
	        		return true;
	        	}
	        	ss3=s2.substring(0,s1.length()-i);
	        	ss4=s2.substring(s1.length()-i);
	        	if(isScramble(ss1,ss4)&&isScramble(ss2,ss3)){
	        		return true;
	        	}
	        	
	        }
	        
	        return false;
	    }
		public boolean check(String s1,String s2){
			int[] a1=new int[26];
			int[] a2=new int[26];
			for(int i=0;i<s1.length();i++){
				a1[s1.charAt(i)-'a']++;
				a2[s2.charAt(i)-'a']++;
				
			}
			for(int i=0;i<a1.length;i++){
				if(a1[i]!=a2[i]){
					return false;
				}
			}
			return true;
		}


}
