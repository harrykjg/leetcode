//http://blog.csdn.net/linhuanmars/article/details/20092829
//http://alexjixiang.com/2014/04/04/powx-n/ 吉祥这个不行吧，直接递归2次pow（x，n/2），还不是重复
//计算了吗

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
//			 if(n==Integer.MIN_VALUE){ //由于是把n除了2的，所以不用管越界了，而吉祥那个那样搞的
			                           //应该要处理的。ps：int的范围是-2147483648到2147483647
			                           //所以-2147483648变成正的话会越界
//				
//			 }
			 if(n%2==0){//拿pow（2，-4）试一下，就知道how it works
				 return half*half;
			 }else{
				 return half*half/x;
			 }
		 }
	 }
	 //第三轮，基本思路还是记得
	 public double pow2(double x, int n) {
	        if(n==0){
	        	return 1;
	        }
	        if(x==0){
	        	return 0;
	        }
	        double a=pow2(x,n/2);//这里开始写成double a=pow2(x,n/2);double b=pow2(x,n/2);再a*b
	                           //这样的话就超时了
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
	 //9/29,记不清了，写错了
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
