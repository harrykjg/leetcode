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
		if (len1 > len2) {// �������ֻ�����ж̵��Ǹ��ĳ��ȵĴ���
			add(len1, len2, 0, a, b, a.length() );
		} else {
			add(len2, len1, 0, b, a, b.length() );
		}

		if (longer - shorter >= 1) {// ���ȳ��Ļ���Ҫ��һ�������볪���Ǹ�δ�ӵ�λ�ټ�
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
		if(s.charAt(0)=='\0'){//����һλ�ǿյĻ����ͼ�����һ����ע����յĻ���'\0'�����⣬
			//������null
			s=new String(c,1,s.length()-1);
		}

		return s;

	}

	public void add(int first, int sec, int i, String a, String b, int num) {// Ĭ��a��b�����򰡣�b�ȳ�

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

		} else {// û�н�λ
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
	
	//�ڶ���д��˼·����ģ��ӷ��£����˺ü��β�accept����Ҫ�ǳ��̲������ַ���ȡ�ַ���λ��
	//�е��鷳
	public String addBinary2(String a, String b) {
		int len1=a.length();
		int len2=b.length();
		String longer=len1>=len2?a:b;
		String shorter=len1>=len2?b:a;
		String rs="";
		int k=0;
		int i=0;
		//i�Ӷ̵��ַ����Ǹ������һλ��ʼ����ô����Ӧ�ĳ����ַ�����λ��
		//����i+onger.length()-shorter.length()
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
		//�̵ı�������֮��
		if(k!=0){//������н�λ���������
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
		}else{//û�н���λ�Ļ����Ͱѳ����Ǹ�ʣ�ಿ�ּ�������
			for(i=longer.length()-1-shorter.length();i>=0;i--){
				rs=longer.charAt(i)+rs;
			}
		}
		if(k!=0){//��������н�λ��˵���ǽ���ȳ����Ǹ�����1λ����һλ����1
			rs=1+rs;
		}
		return rs;

	}
}
