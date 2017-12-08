public class AddBinary {

	public static void main(String[] args) {

		AddBinary add = new AddBinary();
		String a = "101111";
		String b = "10";

		String c = add.addBinary2(a, b);
		System.out.println(c);

	}

	char[] c;
	int longer;
	int shorter;
	int last;

	public String addBinary(String a, String b) {

		int len1 = a.length() - 1;
		int len2 = b.length() - 1;

		if (len1 > len2) {
			longer = len1;
			shorter = len2;
		} else {
			longer = len2;
			shorter = len1;
		}
		c = new char[longer + 1+1];
		if (len1 > len2) {// 这里相加只能运行短的那个的长度的次数
			add(len1, len2, 0, a, b, a.length() );
		} else {
			add(len2, len1, 0, b, a, b.length() );
		}

		if (longer - shorter >= 1) {// 不等长的话就要补一个数组与唱的那个未加的位再加
			char[] buqi = new char[longer - shorter];
			for (int i = 0; i < buqi.length; i++) {
				buqi[i] = '0';
			}
//			if(last==1){
//				buqi[buqi.length-1]='1';
//			}else{
//				buqi[buqi.length-1]='0';
//			}
			
			String sbuqi = new String(buqi);

			char[] buqi2 = new char[longer - shorter];
			if (a.length() > b.length()) {
				for (int i = 0; i < buqi2.length; i++) {
					buqi2[i] = a.charAt(i);
				}
			}else{
				for (int i = 0; i < buqi2.length; i++) {
					buqi2[i] = b.charAt(i);
				}
			}
			
			String s2=new String(buqi2);
			add(sbuqi.length()-1,s2.length()-1,last,sbuqi,s2,sbuqi.length());
			
		}
		if(last==1){
			c[0]='1';
		}
		String s = new String(c);
		if(s.charAt(0)=='\0'){//若第一位是空的话，就剪掉第一个，注意这空的话是'\0'这玩意，
			//而不是null
			s=new String(c,1,s.length()-1);
		}

		return s;

	}

	public void add(int first, int sec, int i, String a, String b, int num) {// 默认a比b长，或啊，b等长

		if (first < 0 || sec < 0) {
			last = i;
			return;
		}
		int ii = i;
		if (i == 1) {
			if (a.charAt(first) == '1' && b.charAt(sec) == '1') {
				c[num] = '1';
				ii = 1;
			} else if (a.charAt(first) == '0' && b.charAt(sec) == '0') {
				c[num] = '1';
				ii = 0;
			} else {
				c[num] = '0';
				ii = 1;
			}

		} else {// 没有进位
			if (a.charAt(first) == '1' && b.charAt(sec) == '1') {
				c[num] = '0';
				ii = 1;
			} else if (a.charAt(first) == '0' && b.charAt(sec) == '0') {
				c[num] = '0';
				ii = 0;
			} else {
				c[num] = '1';
				ii = 0;
			}
		}
		first--;
		sec--;
		num--;
		add(first, sec, ii, a, b, num);
	}
	
	//第二次写，思路就是模拟加法呗，改了好几次才accept，主要是长短不懂的字符串取字符的位置
	//有点麻烦
	public String addBinary2(String a, String b) {
		int len1=a.length();
		int len2=b.length();
		String longer=len1>=len2?a:b;
		String shorter=len1>=len2?b:a;
		String rs="";
		int k=0;
		int i=0;
		//i从短的字符串那个的最后一位开始，那么他对应的长的字符串的位置
		//就是i+onger.length()-shorter.length()
		for( i=shorter.length()-1;i>=0;i--){
			int temp=(longer.charAt(i+longer.length()-shorter.length())-'0')+(shorter.charAt(i)-'0')+k;
			if(temp>=2){
				k=1;
				rs=temp%2+rs;
			}else{
				k=0;
				rs=temp+rs;
			}
		}
		//短的遍历完了之后
		if(k!=0){//如果还有进位，则继续加
			for(i=longer.length()-shorter.length()-1;i>=0;i--){
			    int temp=(longer.charAt(i)-'0')+k;
			    if(temp==2){
			    	k=1;
			    	rs=0+rs;
			    }else{
			    	k=0;
			    	rs=temp+rs;
			    	
			    }
		    }
		}else{//没有进的位的话，就把长的那个剩余部分继续补上
			for(i=longer.length()-1-shorter.length();i>=0;i--){
				rs=longer.charAt(i)+rs;
			}
		}
		if(k!=0){//如果还是有进位，说明是结果比长的那个还长1位，第一位加上1
			rs=1+rs;
		}
		return rs;

	}
}
