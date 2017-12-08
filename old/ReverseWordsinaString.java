import java.util.ArrayList;

public class ReverseWordsinaString {
	public static void main(String[] args) {
		ReverseWordsinaString rw=new ReverseWordsinaString();
		System.out.println(rw.reverseWords("a"));
				
	}
//http://blog.csdn.net/worldwindjp/article/details/21073969
//自己写的，思路很简单啊就是分出来每个单词放进arraylist里，然后到了取出来加上空格就可以了
	public String reverseWords(String s) {
		if(s.length()==0){
			return "" ;
		}
		s=s+" ";//这里我把原单词先加了一个空格，否则例如 “a”这个例子，由于a后面没有空格，所以
		//a加不进arraylist里了
		ArrayList<String> al=new ArrayList<String>();
		int k=0;
		String rs="";
		while(k<s.length()&&s.charAt(k)==' '){//单词开始如果有空格的话，略过
			k++;
		}
		for(int i=k;i<s.length();i++){
			if((s.charAt(i)==' '&&rs!="")){
				al.add(rs);
				rs="";
				continue;
			}
			if(s.charAt(i)!=' '){
				rs=rs+s.charAt(i);
			}
		}
		rs="";
		for(int i=al.size()-1;i>=0;i--){
			if(i!=0){
				rs=rs+al.get(i)+" ";
			}else{
				rs=rs+al.get(i);
			}
		}
		return rs;

	}

}
