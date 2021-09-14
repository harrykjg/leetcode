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
}
