import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/19873463
//http://jixiangsanbao.wordpress.com/2014/03/27/generate-parentheses/

public class GenerateParentheses {
	
	public static void main(String[] args) {
		GenerateParentheses gp =new GenerateParentheses();
		gp.generateParenthesis(1);
	}
	
	
	ArrayList<String> rs=new ArrayList<String>();
	public ArrayList<String> generateParenthesis(int n) {
		//也是看code ganker才会的，思路就是递归每次加左括号还是右括号，限制就是右括号剩的
		//不能比左括号少
		if(n<=0){
			return rs;
		}
		String s="(";
		go(s,n-1,n);
		return rs;

	}
	//其实就是从左到右加上“（”或者“）”，因此可以是（（（然后再加上右括号，而不能是（）））再加上
	//左括号
	public void go(String s,int left, int right){
		if(left==0&&right==0){
			rs.add(s);
			return;
		}	
		if(left-1>=0){
			String s1=new String(s);
			s1=s+"(";
			go(s1,left-1,right);
		}
		if(right-1>=left&&right-1>=0){
			String s2=new String(s);
			s2=s2+")";
			go(s2,left,right-1);
		}
	}
	//第三轮，基本一次accept
	public ArrayList<String> generateParenthesis2(int n) {
		ArrayList<String> al=new ArrayList<String>();
		if(n==0){
			return al;
		}
		String s="";
		go2(s,n,n,al);
		return al;
	}
	private void go2(String s,int left,int right,ArrayList<String> al){
		if(left==0&&right==0){
			al.add(new String(s));
			return;
		}
		if(left>0){
			s=s+"(";
			
			go2(new String(s),left-1,right,al);
			s=s.substring(0,s.length()-1);
		}
		if(left<right){
			s=s+")";
			
			go2(new String(s),left,right-1,al);
			s=s.substring(0,s.length()-1);
		}
	}

}
