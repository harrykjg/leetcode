package 灵神.常用数据结构.stack;

import java.util.Stack;

public class BasicCalculatorII227 {
    static void main() {
        System.out.println(calculate("3*2*2"));
    }
    //还是有点延续了basic calculator1的思想，比如用cur，用signal，遇到符号才push进去，改了好几次过了

    public static int calculate(String s) {
        int signal=1;
        int i=0;
        s=s.replace(" ","");
        int cur=0;
        char[] ch=s.toCharArray();
        Stack<Integer> st=new Stack<>();
        while (i<ch.length){
            if(Character.isDigit(ch[i])){
                int temp=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    temp=temp*10+(ch[i]-'0');
                    i++;
                }
                cur+=signal*temp;
            }else if(ch[i]=='+'){
                st.push(cur);
                signal=1;
                cur=0;
                i++;
            }else if(ch[i]=='-'){
                st.push(cur);
                signal=-1;
                cur=0;
                i++;
            }else if(ch[i]=='*'){//不存在1*-2这种
                signal=1;//此时前面的那个数还是cur，而且计算了正负，因此直接乘以temp，并且也不用入栈，否则2*2*3这种连乘的到了第二个乘的时候就没有cur了
                i++;
                int temp=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    temp=temp*10+(ch[i]-'0');
                    i++;
                }
                cur=cur*temp;
            }else if(ch[i]=='/'){
                signal=1;
                i++;
                int temp=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    temp=temp*10+(ch[i]-'0');
                    i++;
                }
                cur=cur/temp;
            }else{
                i++;
            }
        }
        while (!st.isEmpty()){
            cur+=st.pop();
        }
        return cur;
    }

}
