import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//他们有他们的写法，思路都一样就是dfs每个候选字符串取一个字母，再去拼下一个候选字符串的一个字母


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
	
	    ArrayList<String> p=new ArrayList<String>();//我这里是又用了一个arraylist存实际输入了
	    for(int i=0;i<digits.length();i++){//的数字所对应的字母库，比如输入233，那么这个p里
		    p.add(pool.get(digits.charAt(i)-'2'));//就存了abc，def，def。然后在find方法里
	    }                                  //就直接试这个p里的字符就好了。其实也可以不用这个p，
	    String s="";                     //可以在find里每次找到字符所对应在pool里的字符，也一样
	    find(p,0,s);
	    return al;
	}

	public void find(ArrayList<String> p,int begin,String s){
		
		String pp=p.get(begin);//获得当前的候选字符串
		for(int  i=0;i<pp.length();i++){
			String ss=new String(s);
			char c=pp.charAt(i);
			ss=ss.concat(Character.toString(c));//取出一个字符，加到string上，就是注意一下
			                                   //如何把字符转成字符串，这个concat直接用加号也行
			if(ss.length()==p.size()){
				al.add(ss);
				continue;
			}
			find(p,begin+1,ss);//再取下一个待选字符串
		}
	}

	//9/13/2018,写的有点卡，基本还是一次过
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

