/**
 * Created by 502575560 on 6/25/17.
 */
public class AddStrings {
    public static void main(String[]args){
        System.out.println(addStrings("9","99"));
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
}
