//http://alexjixiang.com/2014/02/28/reverse-integer/ 吉祥的越界是抛出异常
//http://www.2cto.com/kf/201310/253435.html 这个写的简单
public class ReverseInteger {
//其实正数负数都是一样的，但是题目说的负数如果反转会溢出的，我没有处理
	
	public static void main(String[] args) {
		ReverseInteger ri=new ReverseInteger();
		System.out.println(ri.reverse2(-123));
		System.out.println(1%10);
	}
	
	public int reverse(int x) {
		if(x==0){
			return 0;
		}
		if(x/10==0){//除以10如果是0的话说明就是一个数字，直接返回就行了
			return x;
		}
		
		int div=10;
		int rs=0;
		int len=1;
		while(x/div!=0){//先弄出这个数字有几位
			len++;
			div*=10;
		}
		div=1;
		int k=len-1;
		while(k>=0){//例如9342，我们有 9342/1 mod 10=2, 而2*10的3次方=2000；
			       //  9342/10 mod 10=4,而4*10的2次方=400；
			       //9342/100 mod 10=3,而4*10的1次方=30；
			       //9342/1000 mod 10=9,而4*10的0次方=9；
			       //9+30+400+2000=2439即反过来了
			rs+=x/div%10*Math.pow(10, k);
			div*=10;
			k--;
		}
		return rs;
	}

	//第二次写
	public int reverse2(int x) {
		long rs=0;
		int div=10;
		boolean flag=false;
		
		while(x!=0){
			rs=rs*10+x%10;
			x/=10;
					
		}
		
			if(rs>Integer.MAX_VALUE){//int和long类型应该是可以比较的
				//见http://bbs.csdn.net/topics/340145230
				return Integer.MIN_VALUE;
			}
			if(rs<Integer.MIN_VALUE){
				return Integer.MIN_VALUE;
			}
		
		return (int)rs;
	}
	//1/10
	public int reverse3(int x) {
		if(x==0){
			return 0;
		}
		
		double rs=0;
		
		while(x!=0){
			rs=rs*10+x%10;
			x/=10;
		}
		if(rs>Integer.MAX_VALUE){ 
			 
			return Integer.MIN_VALUE;
		}
		if(rs<Integer.MIN_VALUE){
			return Integer.MIN_VALUE;
		}
	
	return (int)rs;
	}
}
