import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//���������ǵ�д����˼·��һ������dfsÿ����ѡ�ַ���ȡһ����ĸ����ȥƴ��һ����ѡ�ַ�����һ����ĸ


public class LetterCombinationsofaPhoneNumber {
	
	public static void main(String[] args) {
		LetterCombinationsofaPhoneNumber lc=new LetterCombinationsofaPhoneNumber();
		String s="24";
		lc.letterCombinations(s);
		
	}
	
	
	ArrayList<String> al=new ArrayList<String>();
	public ArrayList<String> letterCombinations(String digits) {
		
		if(digits.length()==0){
			al.add("");
			return al;
		}
		ArrayList<String> pool=new ArrayList<String>();
		pool.add("abc");
		pool.add("def");
		pool.add("ghi");
		pool.add("jkl");
		pool.add("mno");
		pool.add("pqrs");
		pool.add("tuv");
		pool.add("wxyz");
	
	    ArrayList<String> p=new ArrayList<String>();//��������������һ��arraylist��ʵ��������
	    for(int i=0;i<digits.length();i++){//����������Ӧ����ĸ�⣬��������233����ô���p��
		    p.add(pool.get(digits.charAt(i)-'2'));//�ʹ���abc��def��def��Ȼ����find������
	    }                                  //��ֱ�������p����ַ��ͺ��ˡ���ʵҲ���Բ������p��
	    String s="";                     //������find��ÿ���ҵ��ַ�����Ӧ��pool����ַ���Ҳһ��
	    find(p,0,s);
	    return al;
	}

	public void find(ArrayList<String> p,int begin,String s){
		
		String pp=p.get(begin);//��õ�ǰ�ĺ�ѡ�ַ���
		for(int  i=0;i<pp.length();i++){
			String ss=new String(s);
			char c=pp.charAt(i);
			ss=ss.concat(Character.toString(c));//ȡ��һ���ַ����ӵ�string�ϣ�����ע��һ��
			                                   //��ΰ��ַ�ת���ַ��������concatֱ���üӺ�Ҳ��
			if(ss.length()==p.size()){
				al.add(ss);
				continue;
			}
			find(p,begin+1,ss);//��ȡ��һ����ѡ�ַ���
		}
	}

	//9/13/2018,д���е㿨����������һ�ι�
	public List<String> letterCombinations2(String digits) {
		List<String> rs=new ArrayList<>();

		String[] pool=new String[10];

		pool[2]="abc";
		pool[3]="def";
		pool[4]="ghi";
		pool[5]="jkl";
		pool[6]="mno";
		pool[7]="pqrs";
		pool[8]="tuv";
		pool[9]="wxyz";
		if(digits.length()==0){
			return rs;
		}
		char[] ch=digits.toCharArray();
		dfs("",0,ch,pool,rs);
		return rs;

	}
	void dfs(String cur,int b, char[] digits, String[] pool,List<String> rs){
		if(cur.length()==digits.length){
			rs.add(cur);
			return;
		}
		if(b>=digits.length){
			return;
		}
		int index=digits[b]-'0';
		char[] ch=pool[index].toCharArray();
		for(int i=0;i<ch.length;i++){
			dfs(cur+ch[i],b+1,digits,pool,rs);
		}
	}
}

