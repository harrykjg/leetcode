//http://www.2cto.com/kf/201311/257732.html �ȿ���������Ŀ����˼�����򿴲���

public class CountAndSay {
	
	public static void main(String[] args) {
		CountAndSay ca=new CountAndSay();
		System.out.println(ca.countAndSay(6));
	}
	//1
	//11
	//21
	//1211
	//111221
	//312211
	public String countAndSay(int n) {  
	    if(n<=0)  
	        {return "";}
	    if(n==1){
	    	return "1";
	    }
	    String last="1";
	    int count=0;
	    String s="";
	    for(int i=2;i<=n;i++){//����һ��һ������ȥ���õ�����ɶ��
	    	char temp=last.charAt(0);//�õ���һ���ַ�
	    	count=1;//���˵�һ���ַ���������������1
	    	for(int j=0;j<last.length();j++){
	    		if(j+1<last.length()&&temp==last.charAt(j+1)){//�����һ��������һ���Ļ���count++
	    			count++;
	    			temp=last.charAt(j+1);
	    		}else{//������Ȱ�ǰ���⼸����ͬ�Ĵճ��ַ���
	    			s=s+(char)(count+'0')+temp;//ע���������ַ�������
	    			if(j+1<last.length()){
	    				temp=last.charAt(j+1);
	    				count=1;  //ע������temp������µ��ַ�������countҪ���±��1������©
	    			}

	    		}
	    	}
	    	last=s;
	    	s="";  //ÿ�Ƴ�һ���ַ�����s������
	    }
	   return last;
	} 

}
