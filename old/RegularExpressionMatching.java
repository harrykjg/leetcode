//http://blog.csdn.net/doc_sgl/article/details/12719761 看了这个才知道为什么最后一个例子是true
//http://blog.csdn.net/lsdtc1225/article/details/39201663 
//http://blog.csdn.net/amuseme_lu/article/details/1898500 这是一堆算法总结，英文的
public class RegularExpressionMatching {
	public static void main(String[] args) {
		RegularExpressionMatching re=new RegularExpressionMatching();
		//test case "baccbbcbcacacbbc", "c*.*b*c*ba*b*b*.a*"
		//"aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*a*a*b"
		System.out.println(re.isMatch4("aaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*a*a*b"));
	}
	
	//递归的思路自己大概能想出个大半，就是实现起来比较多细节要想，而且要调试很久，参考他们的再写的
	public boolean isMatch(String s, String p) {
		if(s==null||p==null){
			return false;
		}
		if(s.equals(p)){
			return true;
		}
		if(p.length()==0){
			return s.length()==0;
		}//这些条件得记，不然现场想要很久，p.length==1的情况就是避免p.charAt（1）越界报错的情况
		//即比如“aa”，“a”的情况，p.charAt（1）会报错，wildcard matching就没有这个情况，要记
		if(p.length()==1||p.charAt(1)!='*'){
			if(s.length()>0&&(p.charAt(0)=='.'||p.charAt(0)==s.charAt(0))){
				return isMatch(s.substring(1),p.substring(1));
			}else{
				return false;
			}
		}else{
			int i=0;//这里的写法是和吉祥的不同，借鉴了code ganker的，吉祥那个i初始位-1,
//他那里s.substring是i+1，因此和s.charAt的i其实指的是不同的，而且他哪里while的条件
//还有一个i<0，即处理初始的情况，要拿aa，a*,和"baccbbcbcacacbbc", "c*.*b*c*ba*b*b*.a*"试验一下才
//说得清。如果我这里像吉祥那样写（即while之后是return false而不是return isMatch(s.substring(i),
//p.substring(2)的话,比如"baccbbcbcacacbbc", "c*.*b*c*ba*b*b*.a*"这个例子，s的第一个字母和p
//的第一个字母是不同的，那么就进入不了while循环，就会直接return false了，显然是错的，因为我们看到
//p的前两位是c*,说明c可以出现0次，即直接去掉c*这两位而检测s和p.substirng(2)了，对于这种情况，我们
//就应该是过了while循环后就直接检测s和p的除去前两位后的substring。理解一下。如果能进入while的话，
//说明是把c*去替s的空字符（i=0的情况），第一个，二个，3个字母（如果第二，第三个字母都和第一个字母相同的话）.其实这里
//说白了就是while去对付把p前2个去掉，然后s去掉0到倒数第二个字母（只剩倒数第一个，因为这里是i<s.length）
//而这个while不能覆盖的右一种情况，就是把s的所有字母去光的情况，即s=“”而p=“x*”的情况，因为i初始为0，i要小于s的长度，所以进入
//不了while循环，这个情况就只能用return isMatch(s.substring(i),p.substring(2))去处理了。而这里
//i不能等于s.length的原因，不是因为substing(s.length)会报错（不会报错，返回的是“”），而是因为当
//i++之后等于s的长度了，再去判断s.charAt（i）会报错。所以说这里不能像wildcard matching那样i=s的
//长度并且后面return false的原因就是：substring（s.length)不会报错而s.charAt(s.length)会报错！
//用aa，a*这个例子debug一次就知道了
			while(i<s.length()&&(s.charAt(i)==p.charAt(0)||p.charAt(0)=='.')){
				if(isMatch(s.substring(i),p.substring(2))){
					return true;
				}
				i++;
			}
			return isMatch(s.substring(i),p.substring(2));
		}	
	}
	 public boolean isMatch2(String s, String p) {
	        if(s == null || p == null){
	            return false;
	        }
	        if (p.length() == 0) {
	            return (s.length() == 0);
	        }
	        //if the second char is not *
	        if (p.length() == 1 || p.charAt(1) != '*') {
	            //System.out.println(s.length() + "||" + p.length());
	            if (s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
	                return isMatch2(s.substring(1), p.substring(1));
	            } else {
	                return false;
	            }
	        }
	        //if the second char is *
	        else {
	            //s.substring(0) is the case that * in p matches with 0 characters
	            int i = -1;
	            int len = s.length();
	            while (i < len && (i < 0 || s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
	                if (isMatch2(s.substring(i + 1), p.substring(2))) {
	                    return true;
	                }
	                i++;
	            }
	            return false;
	        }
	    }

//code ganker 的
	public boolean isMatch3(String s, String p) {  
	    return helper(s,p,0,0);  
	}  
	private boolean helper(String s, String p, int i, int j)  
	{  
	    if(j==p.length())  
	        return i==s.length();  
	      
	    if(j==p.length()-1 || p.charAt(j+1)!='*')  
	    {  
	        if(i==s.length()|| s.charAt(i)!=p.charAt(j) && p.charAt(j)!='.')  
	            return false;  
	        else  
	            return helper(s,p,i+1,j+1);  
	    }  
	    //p.charAt(j+1)=='*'   
	    while(i<s.length() && (p.charAt(j)=='.' || s.charAt(i)==p.charAt(j)))  
	    {  
	        if(helper(s,p,i,j+2))  
	            return true;  
	        i++;  
	    }  
	    return helper(s,p,i,j+2);  
	}  
//1/10
	public boolean isMatch4(String s, String p) {
		if(s==null||p==null){
			return false;
		}
		if(p.length()==0){
			return s.length()==0;
		}
		if(p.length()==1){//这个可以与下面的if放在一起
			if(s.length()==1&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.')){
				return true;
			}else{
				return false;
			}
		}
		if(p.charAt(1)!='*'){
			if(s.length()>0&&(s.charAt(0)==p.charAt(0)||p.charAt(0)=='.')){
				return isMatch4(s.substring(1),p.substring(1));
			}else{
				return false;
			}
		}else{
			int i=0;
			while(i<s.length()&&(s.charAt(i)==p.charAt(0)||p.charAt(0)=='.')){
				
				if( isMatch4(s.substring(i),p.substring(2))){
					return true;
				}
				i++;
			}
			//为什么这里要s.substring（i）而不是0呢，没太想明白
//2016年一月，这里确实应该是substring(i)，比如s=aa,p=a*这个例子，i可以等于0,1，此时
//while循环里判断aa和“”，a和“”是否match，显然不行，因此最后还有一线生机就是检测
//s.substring(s.length)和“”是否match.还是不太好理解，记着就行了
			return isMatch4(s.substring(i),p.substring(2));
		}
		
	}

}
