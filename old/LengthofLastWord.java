//http://jixiangsanbao.wordpress.com/2014/02/17/length-of-last-word/
//http://blog.csdn.net/linhuanmars/article/details/21858067
public class LengthofLastWord {
	 public int lengthOfLastWord1(String s) {//��һ��д
		 
			if(s==null||s==" "||s.length()==0){
				return 0;
			}
			String last=s;
			for(int i=0;i<s.length();i++){
				if(s.charAt(i)==32&&i+1<s.length()&&s.charAt(i+1)!=32){
					last=s.substring(i+1);
				}
			}
			while(last.length()-1>=0&&last.charAt(last.length()-1)==32){
				last=last.substring(0,last.length()-1);
			}
			return last.length();
		}
	//���ǵڶ���д������trim�������ͼ����
	public int lengthOfLastWord(String s) {
		if(s.length()==0){
			return 0;
		}
		s=s.trim();
		int f=0;
		boolean flag=false;
		for(int i=0;i<s.length()-1;i++){
			if(s.charAt(i)==' '&&s.charAt(i+1)!=' '){//��¼�������Ǹ��ո��λ�ã���������ǵ�����
				f=i;
			}
		}
		if(f==0){//f=0��2�������1��s�ǡ� ������ôf����0���ַ���ҲӦ�ñ���ǡ����ɣ����Գ�����0��
			//2���ǡ�asdew������������Ǵ��ĵ��ʵ���������Ƿ���s�ĳ���
			
			return s.length();
		}else{//����ͷ������һ�����ʵĳ���
			s=s.substring(f+1);
			return s.length();
		}
		
	}

}
