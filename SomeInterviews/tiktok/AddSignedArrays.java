package SomeInterviews.tiktok;

public class AddSignedArrays {
    //简单题
    public int addSignedArrays(char[] num1, char[] num2) {
        int a=0;
        int b=0;
        boolean isNegative=false;
        for (int i=0;i<num1.length;i++){
            if(Character.isDigit(num1[i])){
                a=a*10+num1[i]-'0';
            }else{
                isNegative=true;
            }
        }
        if(isNegative){
            a=-a;
        }
        isNegative=false;
        for (int i=0;i<num2.length;i++){
            if(Character.isDigit(num2[i])){
                b=b*10+num2[i]-'0';
            }else{
                isNegative=true;
            }
        }
        if(isNegative){
            b=-b;
        }
        return a+b;
    }
}
