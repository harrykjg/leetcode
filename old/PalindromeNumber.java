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
		 int temp=x/10;//这里temp先除以10 是因为怕int越界，所以使div等于x同样长度时就好了，如：
		 //x=1000000001时，div变成10000000000就会越界，因此div变成1000000000就行了
		 //而前面处理了个位数的可能，所以从那后面就是处理2位以上了，所以count=2
		 //应该不用这样，看吉祥博客说的
		 while(temp>=div){
			 count++;
			 div*=10;
		 }
	        
		 int count2=1;//前面的数字
		 int div2=1;//后面的数字用的分母
	
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
	//第二次写的爽多了，也不用考虑越界
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
			//注意这里x/(int)Math.pow(10, l)，如果math前不加int的话，由于Math.pow(10, l)是
			//得出一个double数，int和double数相运算会得出double的值，比如11,11除10=1.1，1.1模
			//10等于1.1，而11除1等于1等于11,11模10等于1，所以会错
			if(x/(int)Math.pow(10, l)%10!=x/(int)Math.pow(10, r)%10){
				return false;
			}
			l--;
			r++;
		}
		return true;
		
	}

	//1/10 写的判断右边的写成这样，也对，但是繁琐了，看回第二次的
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
