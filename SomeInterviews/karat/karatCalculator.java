package SomeInterviews.karat;

public class karatCalculator {
    public static void main(String[] args){
        karatCalculator kc=new karatCalculator();
        System.out.println(kc.calculate("3+4-2+2-1"));
    }
    //9/29没有符号的加减法,直接stack都不用
    public int calculate(String s){
        int rs=0;
        s=s.replace(" ","");
        s=s.trim();
        char[] ch=s.toCharArray();
        int i=0;
        int sign=1;
        while (i<ch.length){
            if (ch[i]=='+'){
                sign=1;
                i++;
            }else if (ch[i]=='-'){
                sign=-1;
                i++;
            }else {
                int temp=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    temp=temp*10+ch[i]-'0';
                    i++;
                }
                rs+=temp*sign;
            }
        }
        return rs;
    }
}
