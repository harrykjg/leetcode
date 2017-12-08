//http://jixiangsanbao.http://jixiangsanbao.wordpress.com/2014/07/07/sqrtx/wordpress.com/2014/07/07/sqrtx/
//http://blog.csdn.net/linhuanmars/article/details/20089131
//这两个的代码都不是我这里的代码，不知道是从哪里找的
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
			double mid=(begin+end)/2;//把mid改成double或者long就行了，int的正数范围只有2147483647 ，
			//所以计算mid的话会超过，所以会变成负的，所以会死循环
			if(mid*mid==x){
				return (int)mid;
			}
			if(mid*mid>x){
				end=(int) (mid-1);
			}else{
				begin=(int) (mid+1);
			}
		}
		
		return end;//比如说找50的平方根，最后试到7,7平方为49还是小于50，此时begin+1=8，while循环
		//进不去，就返回end
	}

	  public int sqrt2(int x) {  
	        if(x<=1) {  
	            return x;  
	        }  
	          
	        int begin = 0;  
	        int end   = x;  
	        int middle = 0;  
	        while(begin<=end) {  
	            middle = begin + (end - begin)/2;//这个就等于（begin+end）/2。。
	            //不要写成middle*middle==x，会溢出   
	            if(middle==x/middle) {  //比如说50，他这个到了都是int类型的，middle到了7,7就等于
	                return middle;  //50除以7就返回7了
	            } else {  
	                if (middle<x/middle) {  
	                    begin = middle + 1;  
	                } else {  
	                    end = middle - 1;  
	                }  
	            }  
	              
	        }   
	        //结束条件end一定<begin，所以返回end   
	        return end;  
	    }
	  //第三次知道思路，但是没写对
//2015年11月这个写法超时了,sqrt2就不超时
	  public int sqrt3(int x) {
		  if(x==0||x<0){
			  return 0;
		  }
		  int b=0;
		  int e=x;//不能写成2/x，否则就得加多一个x=1的直接返回1,
		  while(b<=e){//妈的少写了个等号
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
		  return e;//记住返回e而不是b就对了
	  }
}
