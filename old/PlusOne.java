
public class PlusOne {
	
	public static void main(String[] args) {
		PlusOne po=new PlusOne();
		int[] a=new int[]{9,9};
		int[] b=po.plusOne(a);
		for(int i:b){
			System.out.println(i);
		}
		
	}
	
	
	
	public static int[] plusone(int[] digits){
		int l=digits.length;
		int i=l-1;
		while(i>=0){
			if(digits[i]+1==10){//����10�Ļ���˵����һλ��Ҫ��1�������ڵĻ���ֱ��break��
				digits[i]=0;
				
				if(i==0){
					int[] d=new int[l+1];
					d[0]=1;
					return d;
				}
				i--;
			}
			else{
				digits[i]+=1;
				break;
			}
		}
		return digits;
	}
	//�ڶ���,���ˣ��������ǽ�һλ�������鳤�ȼ�һ�Ļ�����ô�϶���10000��������ʽ���Ǿͺô�����
	//�ڶ�����Ļ�Ҫnewһ���µ����飬��ʵ���á����һ�д���е��ơ����벻�ã���Ҫ��
	public int[] plusOne(int[] digits) {
       if(digits.length==0){
    	   return digits;
       }
      
       int k=0;
       boolean flag=false;
       for(int i=digits.length-1;i>=0;i--){
    	   if(digits[i]+1+k==10&&flag==false){//����д�Ĳ��ã������ÿһλ��Ҫ��1�ˣ���ʵֻ��
    		   k=1;         //��Ҫ��һλʱ��Ҫ��1
    		   flag=true;
    		   digits[i]=0;
    		   if(i==0){
    			   int[] p=new int[digits.length+1];
    			   p[0]=1;
    			   return p;
    		
    		   }
    	   }else if(digits[i]+k==10){
    		   digits[i]=0;
    		   if(i==0){
    			   int[] p=new int[digits.length+1];
    			   p[0]=1;
    			   return p;
    		
    		   }
    	   }
    	   else{
    		   digits[i]=digits[i]+1;
    		   k=0;
    		   break;
    	   }
       }
    
       return digits;
	}
	//�����λ��ǲ��ǵ���������һλ����������ÿ���һ�ε�,������һ��
	public  int[] plusOne3(int[] digits){
		
		if(digits.length==0){
			return digits;
		}
		int k=0;
		for(int i=digits.length-1;i>=0;i--){
			
			if(i==digits.length-1){
				if(digits[i]+k+1==10){
				   digits[i]=0;
				   k=1;
				}else{
					digits[i]+=1;
					break;
				}
			}else if(digits[i]+k==10){
				digits[i]=0;
				k=1;
			}else {
				digits[i]+=1;
				k=0;
				break;
			}
			if(i==0&&k==1){
				int[] dig=new int[digits.length+1];
				dig[0]=1;
				return dig;
			}
		}
		return digits;
		
	}

}
