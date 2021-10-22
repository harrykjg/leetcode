public class StringtoIntegeratoi {
    //8/15/2021就是暴力写，改了好几次过了
    public int myAtoi(String s) {
        if (s.length()==0){
            return 0;
        }
        long rs=0;
        s=s.trim();
        char[] ch=s.toCharArray();
        int i=0;
        boolean found=false;
        boolean sign=false;
        boolean negative=false;
        while (i<ch.length){
            if (s.charAt(i)=='+'||s.charAt(i)=='-'){
                if (!sign&&!found){
                    sign=true;
                    negative=s.charAt(i)=='-'?true:false;
                    i++;
                    continue;
                }
            }
            if (!Character.isDigit(s.charAt(i))){
                if (found){
                    break;
                }else {
                    return 0;
                }
            }
            if(!found){
                found=true;
            }else{
                return 0;
            }
            while (i<ch.length&&Character.isDigit(s.charAt(i))){
                rs=rs*10+s.charAt(i)-'0';
                i++;
                if(!negative&&rs>Integer.MAX_VALUE){
                    return Integer.MAX_VALUE;
                }
                if(negative&&-rs<Integer.MIN_VALUE){
                    return Integer.MIN_VALUE;
                }
            }
        }
        if(negative){
            rs=-rs;
        }
        return (int)rs;
    }
    //10/11/2021, 比上面简化一些，但是如果不能用long的话就写
    // if (result > Integer.MAX_VALUE/10||(result==Integer.MAX_VALUE/10&&str.charAt(i)-'0'>Integer.MAX_VALUE % 10))，
    //https://leetcode.com/problems/string-to-integer-atoi/discuss/4654/My-simple-solution
    public int myAtoi2(String s) {
        if(s.length()==0){
            return 0;
        }
        s=s.trim();
        int sign=1;
        int i=0;
        if(s.length()>0&&s.charAt(0)=='+'){
            i++;
        }
        if(s.length()>0&&s.charAt(0)=='-'){
            i++;
            sign=-1;
        }

        long rs=0;
        while(i<s.length()&&Character.isDigit(s.charAt(i))){
            rs=rs*10+s.charAt(i)-'0';
            if(sign==1&&rs>=Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
            if(sign==-1&&rs>=(long)Integer.MAX_VALUE+1l){
                return Integer.MIN_VALUE;
            }
            i++;
        }
        return (int)(sign*rs);
    }

}
