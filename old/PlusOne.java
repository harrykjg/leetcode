
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
			if(digits[i]+1==10){//等于10的话，说明下一位还要加1，不等于的话就直接break了
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
	//第二次,忘了，如果最后是进一位导致数组长度加一的话，那么肯定是10000这样的形式，那就好处理了
	//第二次想的还要new一个新的数组，其实不用。而且还写的有点绕。代码不好，不要看
	public int[] plusOne(int[] digits) {
       if(digits.length==0){
    	   return digits;
       }
      
       int k=0;
       boolean flag=false;
       for(int i=digits.length-1;i>=0;i--){
    	   if(digits[i]+1+k==10&&flag==false){//这样写的不好，想成是每一位都要加1了，其实只有
    		   k=1;         //当要进一位时才要加1
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
	//第三次还是不记得如果处理进一位的情况，还得看第一次的,调试了一次
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
