//http://www.cnblogs.com/Marrybe/p/3847409.html 上面有好多罗马数字例子
//http://blog.csdn.net/wzy_1988/article/details/17057929
public class RomantoInteger {
	
	public static void main(String[] args) {
		
		RomantoInteger ri=new RomantoInteger();
		System.out.println(ri.romanToInt2("CMD"));
		
	}
	
//其实也是看了别人的在自己写的，但是变边角件太多了
	public int romanToInt(String s) {
		if(s.length()==0){
			return 0;
		}
		if(s.length()==1){
			return get(s.charAt(0));//如果只有一个数字的话，进入不了for如循环
		}
		int rs=0;
		//规则就是如果前面的比后面的大，那前面这个肯定是单独的
		for(int i=0;i<s.length()-1;i++){
			int pre=get(s.charAt(i));
			int post=get(s.charAt(i+1));
			if(pre>=post){//规则就是如果前面的比后面的大，那前面这个肯定是单独的
				rs+=pre;
			}else{//否则的话，说明是900,400,90之类的情况
				rs=rs+post-pre;
				i++;
			}
		}
		if(s.length()>=2&&get(s.charAt(s.length()-2))>=get(s.charAt(s.length()-1))){
			//这个的意义是，上面那个for循环里，如果最后倒数第二个是大于最后一个的话
			//则加上倒数第二个，然后就跳出for循环了，所以最后一个没法加，于是在这里补上
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
  
    public static int romanToInt2(String s) {  //别人的代码
        int i, total, pre, cur;  
  
        total = charToInt(s.charAt(0));  
  
        for (i = 1; i < s.length(); i++) {  
            pre = charToInt(s.charAt(i - 1));  
            cur = charToInt(s.charAt(i));  
  
            if (cur <= pre) {  //他这里是先把第一个数字加进去了，就算是比如CM这样，他也先把
                total += cur;  //C算成是100，然后再看第二位，如果第二位大于C的话，再更正：
            } else {       //减去2倍的第一个再加上第二个。 他这样写最后一位也会算上
                total = total - pre * 2 + cur;  
            }  
        }  
  
        return total;  
    }  


}
