//http://jixiangsanbao.http://jixiangsanbao.wordpress.com/2014/07/07/sqrtx/wordpress.com/2014/07/07/sqrtx/
//http://blog.csdn.net/linhuanmars/article/details/20089131
//�������Ĵ��붼����������Ĵ��룬��֪���Ǵ������ҵ�
public class Sqrtx {
	public static void main(String[] args) {

		Sqrtx sq=new Sqrtx();
		int b=sq.sqrt2(1);
		System.out.println(b);
		
		
	}

	public int sqrt(int x) {

		int begin=0;
		int end=x;
		
		while(begin<=end){
			double mid=(begin+end)/2;//��mid�ĳ�double����long�����ˣ�int��������Χֻ��2147483647 ��
			//���Լ���mid�Ļ��ᳬ�������Ի��ɸ��ģ����Ի���ѭ��
			if(mid*mid==x){
				return (int)mid;
			}
			if(mid*mid>x){
				end=(int) (mid-1);
			}else{
				begin=(int) (mid+1);
			}
		}
		
		return end;//����˵��50��ƽ����������Ե�7,7ƽ��Ϊ49����С��50����ʱbegin+1=8��whileѭ��
		//����ȥ���ͷ���end
	}

	  public int sqrt2(int x) {  
	        if(x<=1) {  
	            return x;  
	        }  
	          
	        int begin = 0;  
	        int end   = x;  
	        int middle = 0;  
	        while(begin<=end) {  
	            middle = begin + (end - begin)/2;//����͵��ڣ�begin+end��/2����
	            //��Ҫд��middle*middle==x�������   
	            if(middle==x/middle) {  //����˵50����������˶���int���͵ģ�middle����7,7�͵���
	                return middle;  //50����7�ͷ���7��
	            } else {  
	                if (middle<x/middle) {  
	                    begin = middle + 1;  
	                } else {  
	                    end = middle - 1;  
	                }  
	            }  
	              
	        }   
	        //��������endһ��<begin�����Է���end   
	        return end;  
	    }
	  //������֪��˼·������ûд��
//2015��11�����д����ʱ��,sqrt2�Ͳ���ʱ
	  public int sqrt3(int x) {
		  if(x==0||x<0){
			  return 0;
		  }
		  int b=0;
		  int e=x;//����д��2/x������͵üӶ�һ��x=1��ֱ�ӷ���1,
		  while(b<=e){//�����д�˸��Ⱥ�
			  int m=(b+e)/2;
			  if(m*m==x){
				  return m;
			  }
			  if(m*m>x){
				  e=m-1;
				  continue;
			  }
			  if(m*m<x){
				  b=m+1;
				  continue;
			  }
		  }
		  return e;//��ס����e������b�Ͷ���
	  }
}
