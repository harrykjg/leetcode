package 灵神.常用数据结构.stack;

import java.util.Stack;

public class BasicCalculatorII227 {
    static void main() {
        System.out.println(calculate2("1+1+1"));
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

    //3/11/2026 方法不好，看回旧的最后一个写法，只用一个stack，不用signal，遇到符号直接把后面得数字搞出来然后直接算，如遇到加减就是
    //stack里放正负数，遇到乘除就直接算出来再push进去，最后再遍历stack 把所有元素加起来就是答案
    public static int calculate2(String s) {
        Stack<Character> st1=new Stack<>();
        Stack<Integer> st2=new Stack<>();
        s=s.trim();
        char[] ch=s.toCharArray();
        int i=0;
        while (i<ch.length){
            if(ch[i]=='+'){
                st1.push(ch[i]);
                i++;
            }else if(ch[i]=='-'){
                st1.push(ch[i]);
                i++;
            }else if(ch[i]=='*'){
                st1.push(ch[i]);
                i++;
            }else if(ch[i]=='/'){
                st1.push(ch[i]);
                i++;
            }
            else if(Character.isDigit(ch[i])){
                int temp=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    temp=temp*10+(ch[i]-'0');
                    i++;
                }
                if(!st1.isEmpty()){
                    char c=st1.pop();
                    if(c=='*'){
                        st2.push(st2.pop()*temp);
                    }else if(c=='/'){
                        st2.push(st2.pop()/temp);
                    }else{
                        st1.push(c);
                        st2.push(temp);
                    }
                }else{
                    st2.push(temp);
                }
            }else{
                i++;
            }
        }
        if(st1.isEmpty()){
            return st2.peek();
        }
        int rs=0;
        while (!st1.isEmpty()){
            char c=st1.pop();
            int temp=st2.pop();
            if(c=='-'){
               rs-=temp;
            }else{
                rs+=temp;
            }
        }
        return rs+st2.peek();
    }

}
