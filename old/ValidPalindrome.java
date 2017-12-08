//http://blog.csdn.net/linhuanmars/article/details/22775045
//http://jixiangsanbao.wordpress.com/2014/05/20/valid-palindrome/
public class ValidPalindrome {
	public static void main(String[] args) {
		ValidPalindrome vp=new ValidPalindrome();
		String s="aa";
		System.out.println(vp.isPalindrome(s));
	}

	public boolean isPalindrome(String s) {
		if (s == null) {
			return true;
		}
		int len=s.length()-1;
		int b=0;
		while(b<len){
			if(!valid(s.charAt(b))){
				b++;
				continue;
			}
			if(!valid(s.charAt(len))){
				len--;
				continue;
			}
			if(!same(s.charAt(b),s.charAt(len))){
				return false;
			}
			b++;
			len--;
		}
		return true;

	}
	public boolean valid(char c){
		if((c>='a'&&c<='z')||(c>='0'&&c<='9')||(c>='A'&&c<='Z')){
			return true;
		}
		return false;
	}
	public boolean same(char c1,char c2){//注意这样的方法把大写转为小写
		if(c1>='A'&&c1<='Z'){
			c1=(char) (c1-'A'+'a');//注意，小写的ascii码是大于大写的
		}
		if(c2>='A'&&c2<='Z'){
			c2=(char) (c2-'A'+'a');
		}
		return c1==c2;
	}
	//第二次也没写，思路挺简单的

}
