//http://www.2cto.com/kf/201311/257732.html 先看他解释题目的意思，否则看不懂

public class CountAndSay {
	
	public static void main(String[] args) {
		CountAndSay ca=new CountAndSay();
		System.out.println(ca.countAndSay(6));
	}
	//1
	//11
	//21
	//1211
	//111221
	//312211
	public String countAndSay(int n) {  
	    if(n<=0)  
	        {return "";}
	    if(n==1){
	    	return "1";
	    }
	    String last="1";
	    int count=0;
	    String s="";
	    for(int i=2;i<=n;i++){//就是一个一个推下去看得到的是啥了
	    	char temp=last.charAt(0);//得到第一个字符
	    	count=1;//有了第一个字符，所以其数量是1
	    	for(int j=0;j<last.length();j++){
	    		if(j+1<last.length()&&temp==last.charAt(j+1)){//如果下一个数还是一样的话，count++
	    			count++;
	    			temp=last.charAt(j+1);
	    		}else{//否则就先把前面这几个相同的凑成字符串
	    			s=s+(char)(count+'0')+temp;//注意这样的字符串操作
	    			if(j+1<last.length()){
	    				temp=last.charAt(j+1);
	    				count=1;  //注意这里temp变成了新的字符，所以count要重新变成1，容易漏
	    			}

	    		}
	    	}
	    	last=s;
	    	s="";  //每推出一个字符串，s都清零
	    }
	   return last;
	} 

}
