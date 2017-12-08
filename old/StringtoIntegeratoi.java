//http://blog.csdn.net/zhouworld16/article/details/16082765  看他的分析
//很多人都是把越界问题的用long 类型来存，而code ganker的不是
//http://blog.csdn.net/linhuanmars/article/details/21145129
public class StringtoIntegeratoi {
	
	public static void main(String[] args) {
		StringtoIntegeratoi st=new StringtoIntegeratoi();
		System.out.println(st.atoi("-1"));
	}
	
	 public int atoi(String str) {
	        if(str.length()==0){
	        	return 0;
	        }
	        str=str.trim();
	        long rs=0;
	        if(str.charAt(0)!='+'&&str.charAt(0)!='-'&&str.charAt(0)-'0'<0&&
	        		str.charAt(0)-'9'>0){
	        	return 0;
	        }
	        boolean neg=false;
	        boolean flag=false;
	        if(str.charAt(0)=='-'){
	        	neg=true;
	        	str=str.substring(1,str.length());
	        	flag=true;//这个flag就是来防“-+1”这个例子的
	        }
	        if(str.charAt(0)=='+'&&flag==false){
	        	str=str.substring(1,str.length());
	        }
	        for(int i=0;i<str.length();i++){
	        	if(str.charAt(i)-'0'<0||str.charAt(i)-'9'>0){
	        		return neg?0-(int)rs:(int)rs;
	        	}
	        	rs=rs*10+str.charAt(i)-'0';
	        	if(rs>Integer.MAX_VALUE&&neg==false){
	        		return Integer.MAX_VALUE;  
	        	}
	        	//注意这里比较要把其转成long类型，否则会错，但是上面这个又不用转
	        	//奇怪(long)(Integer.MAX_VALUE+1)这样写会错，(long)Integer.MAX_VALUE+1这样会对
	        	//我知道了，(long)Integer.MAX_VALUE+1&&neg实际上他是把1&&neg当成一个来判断了,
	        	//不知道1&&neg得出的是啥玩意，但是rs>1&&neg这个是不会语法报错的。而
	        	//Integer.MAX_VALUE+1是两个int相加，的出来的抄书Max值，就变成负数了
	        	//所以先把其中一个转成long，就对了
	        	if(rs>(long)Integer.MAX_VALUE+1&&neg){
	        		return Integer.MIN_VALUE;
	        	}
	        }
	        return neg?0-(int)rs:(int)rs;
	    }

}
