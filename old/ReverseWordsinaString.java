import java.util.ArrayList;

public class ReverseWordsinaString {
	public static void main(String[] args) {
		ReverseWordsinaString rw=new ReverseWordsinaString();
		System.out.println(rw.reverseWords("a"));
				
	}
//http://blog.csdn.net/worldwindjp/article/details/21073969
//�Լ�д�ģ�˼·�ܼ򵥰����Ƿֳ���ÿ�����ʷŽ�arraylist�Ȼ����ȡ�������Ͽո�Ϳ�����
	public String reverseWords(String s) {
		if(s.length()==0){
			return "" ;
		}
		s=s+" ";//�����Ұ�ԭ�����ȼ���һ���ո񣬷������� ��a��������ӣ�����a����û�пո�����
		//a�Ӳ���arraylist����
		ArrayList<String> al=new ArrayList<String>();
		int k=0;
		String rs="";
		while(k<s.length()&&s.charAt(k)==' '){//���ʿ�ʼ����пո�Ļ����Թ�
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
