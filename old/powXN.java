//http://blog.csdn.net/linhuanmars/article/details/20092829
//http://alexjixiang.com/2014/04/04/powx-n/ ����������аɣ�ֱ�ӵݹ�2��pow��x��n/2�����������ظ�
//��������

public class powXN {
	
	 public double pow(double x, int n) {
		 
		 if(n==0){
			 return 1;
		 }
		 double half=pow(x,n/2);
		 if(n>0){
			 if(n%2==0){
				 return half*half;
		     } else{
		    	 return half*half*x;
		     }
		 }else{
//			 if(n==Integer.MIN_VALUE){ //�����ǰ�n����2�ģ����Բ��ù�Խ���ˣ��������Ǹ��������
			                           //Ӧ��Ҫ����ġ�ps��int�ķ�Χ��-2147483648��2147483647
			                           //����-2147483648������Ļ���Խ��
//				
//			 }
			 if(n%2==0){//��pow��2��-4����һ�£���֪��how it works
				 return half*half;
			 }else{
				 return half*half/x;
			 }
		 }
	 }
	 //�����֣�����˼·���Ǽǵ�
	 public double pow2(double x, int n) {
	        if(n==0){
	        	return 1;
	        }
	        if(x==0){
	        	return 0;
	        }
	        double a=pow2(x,n/2);//���￪ʼд��double a=pow2(x,n/2);double b=pow2(x,n/2);��a*b
	                           //�����Ļ��ͳ�ʱ��
	        double rs=a*a;
	        if(n>0){
	        	
		        if(n%2!=0){
		        	rs*=x;
		        }
		        return rs;
	        }else{
	        	
		        if(n%2!=0){
		        	rs=rs/x;
		        }
		        return rs;
	        }
	 }
	 //9/29,�ǲ����ˣ�д����
	 public double pow3(double x, int n) {
		 if(x==0){
			 return 0;
		 }
		 if(n==0){
			 return 1;
		 }
		
		 double half=pow(x,n/2);
		 if(n%2!=0){
			 if(n>0){
				 return half*half*x;
			 }
			 else{
				 return 1/(half*half*x);
			 }
		 }else{
			
				 return half*half;
			
		 }
		 
	 }
}
