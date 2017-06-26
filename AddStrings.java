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
}
