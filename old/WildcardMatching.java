//http://jixiangsanbao.wordpress.com/2014/07/20/wildcard-matching/
//http://blog.csdn.net/shiquxinkong/article/details/28104547 ���������һ������
//https://github.com/yuanx/leetcode/blob/master/WildcardMatching.java Ҳ�еݹ������
//http://www.cnblogs.com/reynold-lei/p/3921872.html ����ǵݹ��dp�Ŀ���accept
//http://www.cnblogs.com/jasonC/archive/2013/11/20/3433571.html
public class WildcardMatching {
	
	public static void main(String[] args) {
		WildcardMatching wm=new WildcardMatching();
		System.out.println(wm.isMatch3("", "*"));
	}
	public boolean isMatch(String s, String p) {//����汾��recursion�ȼ���Ŀ�
        // Start typing your Java solution below
        // DO NOT write main() function
 
        int i = 0, j = 0;
 
        //the following is a recursive one, it timed out on large input
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (s.length() == 0) {
            return p.equals("") || allStar(p);
        }
        if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?') {
            return isMatch(s.substring(1), p.substring(1));
        }
        if (p.charAt(0) == '*') {//�������Ȼ���кͻ���Ϊ������һ��whileȥ����һ��*�ܶԸ�i
        	//��s����ĸ�ģ����ǵݹ���ȥ�ԣ��Ƚ�����
            return isMatch(s.substring(1), p) || isMatch(s, p.substring(1));
        }
        return false;
    }
 
    public boolean allStar (String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '*') {
                return false;
            }
        }
 
        return true;
    }

	
	
	//code ganker��,�Ƕ�̬�滮
	public boolean isMatch2(String s, String p) {  
	    if(p.length()==0)  
	        return s.length()==0;  
	    boolean[] res = new boolean[s.length()+1];  
	    res[0] = true;  
	    for(int j=0;j<p.length();j++)  
	    {  
	        if(p.charAt(j)!='*')  
	        {  
	            for(int i=s.length()-1;i>=0;i--)  
	            {  
	                res[i+1] = res[i]&&(p.charAt(j)=='?'||s.charAt(i)==p.charAt(j));  
	            }  
	        }  
	        else  
	        {  
	            int i = 0;  
	            while(i<=s.length() && !res[i])  
	                i++;  
	            for(;i<=s.length();i++)  
	            {  
	                res[i] = true;  
	            }  
	        }  
	        res[0] = res[0]&&p.charAt(j)=='*';  
	    }  
	    return res[s.length()];  
	}  
	//����� ,�Ҹ���һ����
	public boolean isMatch3(String s, String p) {
        if(s == null || p == null){
            return false;
        }
        if(p.length() == 0){//���p������0�Ļ������s����Ҳ��0����Ϊtrue�������false
            return s.length() == 0;
        }
        //case 1: if the first character is not *
        if(p.charAt(0) != '*'){
        	//���s.length>0��������������֤s������1λ��������a��aa������Ӿͻᱨ����
            if(s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?')){
                return isMatch3(s.substring(1), p.substring(1));
            }
            else{
                return false;
            }
        }
        //case 2: if the first character is *
        else{
//            int i = 0;//�����whileѭ���ͻᵼ���ж��***�������ܿ쳬ʱ���ò������һ��test case��
//            while(i <= s.length()){//����
//                if(isMatch3(s.substring(i), p.substring(1))){
//                    return true;
//                }
//                i++;
//            }
//            return false;
        	for(int i=0;i<=s.length();i++){
        		if(isMatch3(s.substring(i),p.substring(1))){
        			return true;
        		}
        	}
        	return false;
        	
        }    
    }

	 public boolean isMatch4(String s, String p) {//�����̰�ķ�����accept��
	        int i = 0;
	        int j = 0;
	        int star = -1;
	        int mark = -1;
	        while (i < s.length()){
	            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
	                ++i;
	                ++j;
	            } else if (j < p.length() && p.charAt(j) == '*') {
	                star = j++;
	                mark = i;
	            } else if (star != -1) {
	                j = star + 1;
	                i = ++mark;
	            } else {
	                return false;
	            }
	        }
	        while (j < p.length() && p.charAt(j) == '*') {// i == s.length()
	            ++j;
	        }
	        return j == p.length();
	    }

//��һ�챳��,����������԰ѣ���ȷ������corner case���ԣ����϶���ʱ
	 public boolean isMatch6(String s, String p) {
		 if(s==null||p==null){
			 return false;
		 }
		 if(p.length()==0){
			 return s.length()==0;
		 }
		 if(p.charAt(0)!='*'){//�����ٱ���һ������������s.length()>0,���Ҳ��ܰ�
			 if(s.length()>0&&(p.charAt(0)==s.charAt(0)||p.charAt(0)=='?')){
				 return isMatch5(s.substring(1),p.substring(1));
			 }else{
				 return false;
			 }
		 }else{
			 for(int i=0;i<=s.length();i++){//ע�����i����=s.length
				 if(isMatch5(s.substring(i),p.substring(1))){
					 return true;
				 }
			 }
			 return false;
		 }
	 }
	 //1/10������д���϶��ǳ�ʱ����
	 public boolean isMatch5(String s, String p) {
		 if(s==null||p==null){
				return false;
			}
			if(p.length()==0){
				return s.length()==0;
			}
			
			if(p.charAt(0)!='*'){
				if(s.length()>0&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='?')){
					return isMatch5(s.substring(1),p.substring(1));
				}else{
					return false;
				}
			}else{
				int i=0;
				while(i<=s.length()){
					int k=1;//�����ø�k���԰�****����һ��*��Ӧ�û�õ�
					while(k<p.length()&&p.charAt(k)==p.charAt(k-1)){
					        k++;
					}
					if( isMatch5(s.substring(i),p.substring(k))){
						return true;
					}
					i++;
				}
				return false;
			}
			
	 }

}
