//http://alexjixiang.com/2014/02/28/reverse-integer/ �����Խ�����׳��쳣
//http://www.2cto.com/kf/201310/253435.html ���д�ļ�
public class ReverseInteger {
//��ʵ������������һ���ģ�������Ŀ˵�ĸ��������ת������ģ���û�д���
	
	public static void main(String[] args) {
		ReverseInteger ri=new ReverseInteger();
		System.out.println(ri.reverse2(-123));
		System.out.println(1%10);
	}
	
	public int reverse(int x) {
		if(x==0){
			return 0;
		}
		if(x/10==0){//����10�����0�Ļ�˵������һ�����֣�ֱ�ӷ��ؾ�����
			return x;
		}
		
		int div=10;
		int rs=0;
		int len=1;
		while(x/div!=0){//��Ū����������м�λ
			len++;
			div*=10;
		}
		div=1;
		int k=len-1;
		while(k>=0){//����9342�������� 9342/1 mod 10=2, ��2*10��3�η�=2000��
			       //  9342/10 mod 10=4,��4*10��2�η�=400��
			       //9342/100 mod 10=3,��4*10��1�η�=30��
			       //9342/1000 mod 10=9,��4*10��0�η�=9��
			       //9+30+400+2000=2439����������
			rs+=x/div%10*Math.pow(10, k);
			div*=10;
			k--;
		}
		return rs;
	}

	//�ڶ���д
	public int reverse2(int x) {
		long rs=0;
		int div=10;
		boolean flag=false;
		
		while(x!=0){
			rs=rs*10+x%10;
			x/=10;
					
		}
		
			if(rs>Integer.MAX_VALUE){//int��long����Ӧ���ǿ��ԱȽϵ�
				//��http://bbs.csdn.net/topics/340145230
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
