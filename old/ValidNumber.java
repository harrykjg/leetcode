//http://jixiangsanbao.wordpress.com/2014/07/26/valid-number/
//http://blog.csdn.net/linhuanmars/article/details/23809661
public class ValidNumber {
	public static void main(String[] args) {
		ValidNumber vn=new ValidNumber();
		//.7e23可以，7.e23也可以,+.7e34也行,.1e+23也行
		System.out.println(vn.isNumber("-.1E+23"));
	}
//吉祥的,他的E不行
	public boolean isNumber(String s) {
        //eliminate the space on both ends
        s = s.trim();
        if(s.length() == 0){
            return false;
        }
        boolean hasExp = false;
        boolean hasDigit = false;
        boolean hasDot = false;
        int i = 0;
        char[] chars = s.toCharArray();
        if(chars[0] == '+' || chars[0] == '-'){
            i++;
        }
        while(i < chars.length){
            char c = chars[i];
            //is a digit, set hasDigit to true
            if(c >= '0' && c <= '9'){
                hasDigit = true;
            }
            //for the number in format AeB: B can not be a dot number, and can not have 1+ e
            else if(c == '.'){
                if(hasDot || hasExp){
                    return false;
                }
                hasDot = true;
            }
            //for the number in format AeB, if there's 'e', (1)A can not be empty (2) B can not be empty (3)can not have 1+ e
            else if(c == 'e'){
                if(!hasDigit || hasExp){
                    return false;
                }
                hasExp = true;
                hasDigit = false;//到了e之后，要再看有没数字
            }
            //if not in beginning, + and - can only appear on the right of 'e'
            else if(c == '+' || c == '-'){
                if(chars[i-1] != 'e'){
                    return false;
                }
            }
            else{
                return false;
            }
            i++;
        }
        //to avoid the case like '.'
        return hasDigit;  
    }
//code ganker的
	public boolean isNumber2(String s) {  
	    if(s==null)  
	        return false;  
	    s = s.trim();  
	    if(s.length()==0)  
	        return false;  
	    boolean dotFlag = false;  
	    boolean eFlag = false;  
	    for(int i=0;i<s.length();i++)  
	    {  
	        switch(s.charAt(i))  
	        {  
	            case '.':  
	                if(dotFlag || eFlag   
	                || ((i==0||!(s.charAt(i-1)>='0'&&s.charAt(i-1)<='9'))   
	                    && (i==s.length()-1||!(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9'))))  
	                    return false;  
	                dotFlag = true;  
	                break;  
	            case '+':  
	            case '-':  
	                if((i>0 && (s.charAt(i-1)!='e' && s.charAt(i-1)!='E'))  
	                  || (i==s.length()-1 || !(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9'||s.charAt(i+1)=='.')))  
	                    return false;  
	                break;  
	            case 'e':  
	            case 'E':  
	                if(eFlag || i==s.length()-1 || i==0)  
	                    return false;  
	                eFlag = true;  
	                break;  
	            case '0':  
	            case '1':  
	            case '2':  
	            case '3':  
	            case '4':  
	            case '5':  
	            case '6':  
	            case '7':  
	            case '8':  
	            case '9':  
	                break;  
	            default:  
	                return false;  
	        }  
	    }  
	    return true;  
	}  


}
