//http://www.cnblogs.com/Marrybe/p/3847409.html �����кö�������������
//http://blog.csdn.net/wzy_1988/article/details/17057929
public class RomantoInteger {
	
	public static void main(String[] args) {
		
		RomantoInteger ri=new RomantoInteger();
		System.out.println(ri.romanToInt2("CMD"));
		
	}
	
//��ʵҲ�ǿ��˱��˵����Լ�д�ģ����Ǳ�߽Ǽ�̫����
	public int romanToInt(String s) {
		if(s.length()==0){
			return 0;
		}
		if(s.length()==1){
			return get(s.charAt(0));//���ֻ��һ�����ֵĻ������벻��for��ѭ��
		}
		int rs=0;
		//����������ǰ��ıȺ���Ĵ���ǰ������϶��ǵ�����
		for(int i=0;i<s.length()-1;i++){
			int pre=get(s.charAt(i));
			int post=get(s.charAt(i+1));
			if(pre>=post){//����������ǰ��ıȺ���Ĵ���ǰ������϶��ǵ�����
				rs+=pre;
			}else{//����Ļ���˵����900,400,90֮������
				rs=rs+post-pre;
				i++;
			}
		}
		if(s.length()>=2&&get(s.charAt(s.length()-2))>=get(s.charAt(s.length()-1))){
			//����������ǣ������Ǹ�forѭ������������ڶ����Ǵ������һ���Ļ�
			//����ϵ����ڶ�����Ȼ�������forѭ���ˣ��������һ��û���ӣ����������ﲹ��
			rs+=get(s.charAt(s.length()-1));
		}

		return rs;

	}
	
	public int get(char c){
		int val=0;
		switch(c){
		case 'M':
			val=1000;
			break;
		case 'D':
			val=500;
			break;
		case 'C':
			val=100;
			break;
		case 'L':
			val=50;
			break;
		
		case 'X':
			val=10;
			break;
		case 'V':
			val=5;
			break;
		case 'I':
			val=1;
			break;
				
		}
		return val;
	}
	
	public static int charToInt(char c) {  
        int data = 0;  
  
        switch (c) {  
            case 'I':  
                data = 1;  
                break;  
            case 'V':  
                data = 5;  
                break;  
            case 'X':  
                data = 10;  
                break;  
            case 'L':  
                data = 50;  
                break;  
            case 'C':  
                data = 100;  
                break;  
            case 'D':  
                data = 500;  
                break;  
            case 'M':  
                data = 1000;  
                break;  
        }  
  
        return data;  
    }  
  
    public static int romanToInt2(String s) {  //���˵Ĵ���
        int i, total, pre, cur;  
  
        total = charToInt(s.charAt(0));  
  
        for (i = 1; i < s.length(); i++) {  
            pre = charToInt(s.charAt(i - 1));  
            cur = charToInt(s.charAt(i));  
  
            if (cur <= pre) {  //���������Ȱѵ�һ�����ּӽ�ȥ�ˣ������Ǳ���CM��������Ҳ�Ȱ�
                total += cur;  //C�����100��Ȼ���ٿ��ڶ�λ������ڶ�λ����C�Ļ����ٸ�����
            } else {       //��ȥ2���ĵ�һ���ټ��ϵڶ����� ������д���һλҲ������
                total = total - pre * 2 + cur;  
            }  
        }  
  
        return total;  
    }  


}
