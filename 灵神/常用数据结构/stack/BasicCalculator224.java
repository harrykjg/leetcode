package 灵神.常用数据结构.stack;

import java.util.Stack;

public class BasicCalculator224 {
    static void main() {
        System.out.println(calculate2("(1+(4+5+2)-3)+(6+8)"));
    }
    //11/30、2025
    //居然一次过，和394的好的做法也像，就是位置一个cur作为当前层的数，遇到（则cur入栈，signal入栈，同时
    // 重置cur（相当于内层是一个新的开始）和signal，遇到）则pop出来st1和st2，计算出的东西作为cur
    //不需要预先加入任何东西入栈，而以前的写法是要先加入1的。由于这几题都差不多所以这个写法更有连贯性
    public static int calculate(String s) {
        char[] ch=s.toCharArray();
        int i=0;
        Stack<Integer> st=new Stack<>();
        Stack<Integer> st2=new Stack<>();//存正负号
        int cur=0;
        int signal=1;
        while (i<ch.length){
            if(Character.isDigit(ch[i])){
                int temp=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    temp=temp*10+(ch[i]-'0');
                    i++;
                }
                cur+=signal*temp;
            }else if(ch[i]=='+'){
                signal=1;
                i++;
            }else if(ch[i]=='-'){
                signal=-1;
                i++;
            }else if(ch[i]=='('){
                st.push(cur);
                cur=0;
                st2.push(signal);
                signal=1;
                i++;
            }else if(ch[i]==')'){
                int sig=st2.pop();
                int outer=st.pop();
                cur=outer+sig*cur;
                i++;
            }else{//别的东西都ignore
                i++;
            }

        }
        return cur;
    }

    //3/10/2026 又不会了，记住是不需要先push东西进st的，只要维护cur和signal，而且signal不是说一遇到就要入栈，而只是当遇到括号的时候
    //才把signal入栈，并且cur入栈. 例子-3+（1+2-（4-5）-（3））+（3+4）
    public static int calculate2(String s) {
        Stack<Integer> st1=new Stack<>();
        Stack<Integer> st2=new Stack<>();
        int signal=1;
        int cur=0;
        int i=0;
        s=s.trim();
        char[] ch=s.toCharArray();
        while (i<ch.length){
            if(ch[i]=='-'){
                signal=-1;
                i++;
            }else if(ch[i]=='+'){
                signal=1;
                i++;
            }else if(ch[i]=='('){
                st1.push(signal);
                st2.push(cur);
                signal=1;
                cur=0;
                i++;
            }else if(ch[i]==')'){
                int temp=cur*st1.pop();
                cur=st2.pop()+temp;//之前还想错了再把这个数放回进stack2然后没有更新cur。其实看见右括号就意味着这里就该结束，值再更新回cur
                i++;
            }else if(Character.isDigit(ch[i])){
                int temp=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    temp=temp*10+(ch[i]-'0');
                    i++;
                }
                cur+=signal*temp;
            }else{
                i++;
            }
        }

        return cur;
    }
}
