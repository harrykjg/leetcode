//http://blog.csdn.net/linhuanmars/article/details/20092829
//http://alexjixiang.com/2014/04/04/powx-n/ ????????????????????????2??pow??x??n/2??????????????
//????????

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
//			 if(n==Integer.MIN_VALUE){ //????????n????2????????????????????????????????????????
			                           //??????????????ps??int????????-2147483648??2147483647
			                           //????-2147483648????????????????
//				
//			 }
			 if(n%2==0){//??pow??2??-4????????????????how it works
				 return half*half;
			 }else{
				 return half*half/x;
			 }
		 }
	 }
	 //????????????????????????
	 public double pow2(double x, int n) {
	        if(n==0){
	        	return 1;
	        }
	        if(x==0){
	        	return 0;
	        }
	        double a=pow2(x,n/2);//????????????double a=pow2(x,n/2);double b=pow2(x,n/2);??a*b
	                           //????????????????
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

}
