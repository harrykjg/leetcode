/**
 * Created by 502575560 on 6/25/17.
 */
public class AddStrings {
    public static void main(String[]args){
        System.out.println(addStrings5("12.678","1.5"));
    }
    //和以前的题一样吧,自己写了,改了一两次才对
    //https://discuss.leetcode.com/topic/62310/straightforward-java-8-main-lines-25ms/2  看看别人的简洁
    public static String addStrings(String num1, String num2) {
        if(num1==null){
            return num2;
        }
        int count=0;
        //先把短的那个前面加上0
        if(num1.length()!=num2.length()){
            if(num1.length()>num2.length()){
                int gap=num1.length()-num2.length();
                for(int i=0;i<gap;i++){
                    num2="0"+num2;
                }
            }else{
                int gap=num2.length()-num1.length();
                for(int i=0;i<gap;i++){
                    num1="0"+num1;
                }
            }
        }
        StringBuilder sb=new StringBuilder();
        int i=num1.length()-1;
        while (i>=0){
            int a=Integer.parseInt(num1.substring(i,i+1));
            int b=Integer.parseInt(num2.substring(i,i+1));

            int cursum=a+b+count;
            if(cursum>=10){
                count=1;
                cursum-=10;
            }else {
                count=0;
            }
            sb.append(cursum+"");
            i--;
        }
        if(count==1){
            sb.append(1+"");
        }
        return sb.reverse().toString();
    }

    //8/24/2021 直接写的改了1次过了。没有把短的那个补齐，这样后面要多两个while循环，重复代码多一些，别忘了把最后的carry加上
    public static String addStrings2(String num1, String num2) {
        char[] ch1=num1.toCharArray();
        char[] ch2=num2.toCharArray();
        int i1=ch1.length-1;
        int i2=ch2.length-1;
        int carry=0;
        StringBuilder sb=new StringBuilder();
        while (i1>=0&&i2>=0){
            int temp=ch1[i1]-'0'+ch2[i2]-'0'+carry;
            if (temp>=10){
                carry=1;
                sb.append(temp-10);
            }else {
                sb.append(temp);
                carry=0;
            }
            i1--;
            i2--;
        }
        while (i1>=0){
            int temp=ch1[i1]-'0'+carry;
            if (temp>=10){
                carry=1;
                sb.append(temp-10);
            }else {
                sb.append(temp);
                carry=0;
            }
            i1--;
        }
        while (i2>=0){
            int temp=ch2[i2]-'0'+carry;
            if (temp>=10){
                carry=1;
                sb.append(temp-10);
            }else {
                sb.append(temp);
                carry=0;
            }
            i2--;
        }
        if (carry!=0){//这个开始忘了
          sb.append(1);
        }
        return sb.reverse().toString();
    }
//10/17/2021 合并了3个while成1个
    public static String addStrings3(String num1, String num2) {
        int[] a=new int[Math.max(num1.length(),num2.length())+1];
        int carry=0;
        StringBuilder sb=new StringBuilder();
        int i1=num1.length()-1;
        int i2=num2.length()-1;
        int index=a.length-1;
        while(i1>=0||i2>=0){
            int x=i1>=0?num1.charAt(i1)-'0':0;
            int y=i2>=0?num2.charAt(i2)-'0':0;
            int temp=carry+x+y;
            if(temp>=10){
                carry=1;
                a[index--]=temp-10;
            }else{
                carry=0;
                a[index--]=temp;
            }
            i1--;
            i2--;
        }

        if(carry!=0){
            a[index]=1;
        }
        int i=0;
        if(a[0]==0){
            i=1;
        }
        for(;i<a.length;i++){
            sb.append(a[i]);
        }
        return sb.toString();

    }

    //有小数点,12.678 +1.5 变成12678和1500 加 14178，小数点就在14.178
    public static String addStrings4(String num1, String num2) {
        int i1=num1.indexOf('.');
        int i2=num2.indexOf('.');
        int big=Math.max(i1,i2);
        int decimalCount=num1.length()-i1-1;//如12.123 i1=2, 6-2-1=3
        int decimalCount2=num2.length()-i2-1;//如1.1  3-1-1=1
        int gap=Math.abs(decimalCount-decimalCount2);


        StringBuilder sb1=new StringBuilder();
        StringBuilder sb2=new StringBuilder();
        int c1=0;
        for (;c1<num1.length();c1++){
            if(c1==i1){
                continue;
            }
            sb1.append(num1.charAt(c1));
        }
        if (decimalCount<decimalCount2){
            for (int i=0;i<gap;i++){
                sb1.append('0');
            }
        }
        int c2=0;
        for (;c2<num2.length();c2++){
            if(c2==i2){
                continue;
            }
            sb2.append(num2.charAt(c2));
        }
        if (decimalCount2<decimalCount){
            for (int i=0;i<gap;i++){
                sb2.append('0');
            }
        }

        i1=sb1.length()-1;
        i2=sb2.length()-1;
        int carry=0;
        StringBuilder sb=new StringBuilder();
        while (i1>=0||i2>=0){
            int x=i1>=0?sb1.charAt(i1)-'0':0;
            int y=i2>=0?sb2.charAt(i2)-'0':0;
            int temp=x+y+carry;
            if (temp>=10){
                carry=1;
                sb.append(temp-10);
            }else {
                sb.append(temp);
                carry=0;
            }
            i1--;
            i2--;
        }
        if (carry!=0){//这个开始忘了
            sb.append(1);
        }
        int dotIndex=Math.max(decimalCount,decimalCount2);//从后往前第几位是'.'
        return sb.reverse().insert(sb.length()-dotIndex,'.').toString();
    }

    public  static String addStrings5(String num1, String num2) {
        int i1=num1.indexOf('.');
        int i2=num2.indexOf('.');
        String n1=num1.substring(0,i1);
        String n2=num2.substring(0,i2);

        String f1=num1.substring(i1+1);
        String f2=num2.substring(i2+1);
        String rs1=addStrings3(n1,n2);
        String rs2=helper(f1,f2);//helper返回的string是.123或者1.123这样的

    }
}
