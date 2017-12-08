//http://blog.csdn.net/HarryHuang1990/article/details/25793079
//http://jixiangsanbao.wordpress.com/2014/03/03/palindrome-number/
public class PalindromeNumber {
	public static void main(String[] args) {
		PalindromeNumber pn=new PalindromeNumber();
		System.out.println(pn.isPalindrome2(11));
	}
	
	public boolean isPalindrome(int x) {
	     
		 if(x<0){
			 return false;
		 }
		 if(x/10==0){
			 return true;
		 }
		 
		 int div=10;
		 int count=2;
		 int temp=x/10;//����temp�ȳ���10 ����Ϊ��intԽ�磬����ʹdiv����xͬ������ʱ�ͺ��ˣ��磺
		 //x=1000000001ʱ��div���10000000000�ͻ�Խ�磬���div���1000000000������
		 //��ǰ�洦���˸�λ���Ŀ��ܣ����Դ��Ǻ�����Ǵ���2λ�����ˣ�����count=2
		 //Ӧ�ò��������������鲩��˵��
		 while(temp>=div){
			 count++;
			 div*=10;
		 }
	        
		 int count2=1;//ǰ�������
		 int div2=1;//����������õķ�ĸ
	
		 while(count2<count){
			 if((x/div%10)!=(x/div2%10)){
				 return false;
			 }
			 div/=10;
			 div2*=10;
			 count2++;
			 count--;
		 }
		 
		 return true;
	    }
	//�ڶ���д��ˬ���ˣ�Ҳ���ÿ���Խ��
	public boolean isPalindrome2(int x) {
		if(x<0){
			return false;
		}
		if(x/10==0){
			return true;
		}
		int temp=x;
		int len=0;
		while(temp/10!=0){
			temp/=10;
			len++;
		}
		int l=len;
		int r=0;
		while(l>r){
			//ע������x/(int)Math.pow(10, l)�����mathǰ����int�Ļ�������Math.pow(10, l)��
			//�ó�һ��double����int��double���������ó�double��ֵ������11,11��10=1.1��1.1ģ
			//10����1.1����11��1����1����11,11ģ10����1�����Ի��
			if(x/(int)Math.pow(10, l)%10!=x/(int)Math.pow(10, r)%10){
				return false;
			}
			l--;
			r++;
		}
		return true;
		
	}

	//1/10 д���ж��ұߵ�д��������Ҳ�ԣ����Ƿ����ˣ����صڶ��ε�
	 public boolean isPalindrome3(int x) {
	        if(x<0){
	            return false;
	        }
	        int len=1;
	        int copy=x;
	        while(copy/10!=0){
	            copy/=10;
	            len++;
	        }
	        int b=0;
	        int e=len-1;
	        while(b<len){
	            int left=(x/(int)(Math.pow(10,e))%10);
	            int right=(x%(int)(Math.pow(10,b+1))/(int)Math.pow(10,b));
	            if(left!=right){
	                return false;
	            }
	            b++;
	            e--;
	        }
	        return true;
	        
	        
	    }
}
