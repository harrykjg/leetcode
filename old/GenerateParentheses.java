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
		//Ҳ�ǿ�code ganker�Ż�ģ�˼·���ǵݹ�ÿ�μ������Ż��������ţ����ƾ���������ʣ��
		//���ܱ���������
		if(n<=0){
			return rs;
		}
		String s="(";
		go(s,n-1,n);
		return rs;

	}
	//��ʵ���Ǵ����Ҽ��ϡ��������ߡ���������˿����ǣ�����Ȼ���ټ��������ţ��������ǣ��������ټ���
	//������
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
	//�����֣�����һ��accept
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
