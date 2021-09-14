public class MultiplyStrings {
    public static void main(String[] args){
        MultiplyStrings ms=new MultiplyStrings();
        System.out.println(ms.multiply("9","99"));
    }
    //8/20/2021 还是很恶心，记要用一个array来记录算出来的数字。还是得模拟.看以前的index，是怎么设的，但是后面填数那怎么取carry啥的我这写的比较直观
    public String multiply(String num1, String num2) {
        String rs="";
        int[] x=new int[num1.length()+num2.length()];

        for (int i=num2.length()-1;i>=0;i--){
            int index=x.length-1-(num2.length()-1-i);
            int carry=0;
            for (int j=num1.length()-1;j>=0;j--){
                int n2=num2.charAt(i)-'0';
                int n1=num1.charAt(j)-'0';
                int temp=n1*n2+carry;
                if (x[index]+temp>=10){
                    carry=(x[index]+temp)/10;
                    x[index]=(x[index]+temp)%10;
                }else {
                    carry=0;
                    x[index]=x[index]+temp;
                }
                index--;
            }
            if (carry!=0){
                x[index]+=carry;
            }
        }
        int i=0;
        if (x[0]==0){
            i=1;
        }
        for (;i<x.length;i++){
            rs+=x[i];
        }
        if(rs.charAt(0)=='0'){
            return "0";
        }
        return rs;
    }
}
