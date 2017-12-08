//http://jixiangsanbao.wordpress.com/2014/07/20/wildcard-matching/
//http://blog.csdn.net/shiquxinkong/article/details/28104547 看他的最后一个例子
//https://github.com/yuanx/leetcode/blob/master/WildcardMatching.java 也有递归的例子
//http://www.cnblogs.com/reynold-lei/p/3921872.html 这个非递归非dp的可以accept
//http://www.cnblogs.com/jasonC/archive/2013/11/20/3433571.html
public class WildcardMatching {
	
	public static void main(String[] args) {
		WildcardMatching wm=new WildcardMatching();
		System.out.println(wm.isMatch3("", "*"));
	}
	public boolean isMatch(String s, String p) {//这个版本的recursion比吉祥的快
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
        if (p.charAt(0) == '*') {//他这个居然还有和或，因为他不是一个while去尝试一个*能对付i
        	//个s的字母的，而是递归着去试，比较难想
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

	
	
	//code ganker的,是动态规划
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
	//吉祥的 ,我改了一部分
	public boolean isMatch3(String s, String p) {
        if(s == null || p == null){
            return false;
        }
        if(p.length() == 0){//如果p长度是0的话，如果s长度也是0，则为true，否则就false
            return s.length() == 0;
        }
        //case 1: if the first character is not *
        if(p.charAt(0) != '*'){
        	//这个s.length>0的条件是用来保证s至少有1位，否则用a，aa这个例子就会报错了
            if(s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?')){
                return isMatch3(s.substring(1), p.substring(1));
            }
            else{
                return false;
            }
        }
        //case 2: if the first character is *
        else{
//            int i = 0;//他这个while循环就会导致有多个***的情况会很快超时，用不到最后一个test case就
//            while(i <= s.length()){//超了
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

	 public boolean isMatch4(String s, String p) {//这个是贪心法可以accept的
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

//隔一天背的,大概这样可以把，不确定所有corner case都对，但肯定超时
	 public boolean isMatch6(String s, String p) {
		 if(s==null||p==null){
			 return false;
		 }
		 if(p.length()==0){
			 return s.length()==0;
		 }
		 if(p.charAt(0)!='*'){//这里少背了一个条件，就是s.length()>0,而且不能把
			 if(s.length()>0&&(p.charAt(0)==s.charAt(0)||p.charAt(0)=='?')){
				 return isMatch5(s.substring(1),p.substring(1));
			 }else{
				 return false;
			 }
		 }else{
			 for(int i=0;i<=s.length();i++){//注意这个i可以=s.length
				 if(isMatch5(s.substring(i),p.substring(1))){
					 return true;
				 }
			 }
			 return false;
		 }
	 }
	 //1/10，这种写法肯定是超时反正
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
					int k=1;//这样用个k可以把****当成一个*，应该会好点
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
