//http://blog.csdn.net/zhouworld16/article/details/16082765  �����ķ���
//�ܶ��˶��ǰ�Խ���������long �������棬��code ganker�Ĳ���
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
	        	flag=true;//���flag����������-+1��������ӵ�
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
	        	//ע������Ƚ�Ҫ����ת��long���ͣ�������������������ֲ���ת
	        	//���(long)(Integer.MAX_VALUE+1)����д���(long)Integer.MAX_VALUE+1�������
	        	//��֪���ˣ�(long)Integer.MAX_VALUE+1&&negʵ�������ǰ�1&&neg����һ�����ж���,
	        	//��֪��1&&neg�ó�����ɶ���⣬����rs>1&&neg����ǲ����﷨����ġ���
	        	//Integer.MAX_VALUE+1������int��ӣ��ĳ����ĳ���Maxֵ���ͱ�ɸ�����
	        	//�����Ȱ�����һ��ת��long���Ͷ���
	        	if(rs>(long)Integer.MAX_VALUE+1&&neg){
	        		return Integer.MIN_VALUE;
	        	}
	        }
	        return neg?0-(int)rs:(int)rs;
	    }

}
