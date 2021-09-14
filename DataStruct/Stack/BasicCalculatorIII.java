package DataStruct.Stack;

import java.util.SplittableRandom;
import java.util.Stack;

/**
 * Created by yufengzhu on 8/14/18.
 */
public class BasicCalculatorIII {
    public static void main(String[] args){
        BasicCalculatorIII bc=new BasicCalculatorIII();
//        System.out.print(bc.calculate3("(2+6*3+5-(3*14/7+2)*5)+3"));
        System.out.println(bc.calculate3("(1-(3-4))"));
    }


    //自己的写法，就是遇到括号的话，就找这个括号的对应的结束的括号，然后对这个substring递归调用calculate方法，这样解决了括号的计算之后就是basic calculator2一样的了
    //https://www.cnblogs.com/grandyang/p/8873471.html 这个和我的想法是一样的
    //lc不会出现2(5+5*2)这样的，2和括号之间肯定有符号，但是却有（5+3）这样的
    public int calculate(String s) {
        if(s.length()==0){
            return 0;
        }
        s=s.replace(" ","");
        char[] ch=s.toCharArray();
        char sign='+';
        Stack<Integer> st=new Stack<>();
        int i=0;
        if(ch[0]=='-'){
            sign='-';
            i=1;
        }
        int first=0;
        if(ch[0]=='('){//检查第一个就是括号的情况，那么就先把第一个括号内的东西算出来，所谓第一个first，push进stack里
            int e=findPranthesis(s);
            first=calculate(s.substring(i+1,e));
            i=e+1;
        }else{
            while (i<s.length()&&Character.isDigit(ch[i])){
                first=first*10+ch[i]-'0';
                i++;
            }
        }

        if(sign=='-'){
            st.push(-first);
        }else{
            st.push(first);
        }

        while (i<ch.length){
            char c=ch[i];
            i++;
            char c2=ch[i];//这里c应该是运算符，但是由于会有5*（2+3）这样的，所以还看了c后一位是不是括号，是的话就处理括号里的得出temp，写法不是很elegant
            int temp=0;
            if(i<s.length()&&c2=='('){
                String ss=s.substring(i);
                int e=findPranthesis(ss)+i;
                temp=calculate(s.substring(i+1,e));
                i=e+1;
            }else{
                while (i<s.length()&&Character.isDigit(ch[i])){
                    temp=temp*10+ch[i]-'0';
                    i++;
                }
            }
            if(c=='+'){
                st.push(temp);
            }
            else if(c=='-'){

                st.push(-temp);

            }
            else if(c=='*'){
                st.push(st.pop()*temp);

            }
            else if(c=='/'){
                st.push(st.pop()/temp);

            }
        }
        int rs=0;
        while (!st.isEmpty()){
            rs+=st.pop();
        }
        return rs;
    }
    int findPranthesis(String s){
        int i=1;
        int count=1;
        char[] ch=s.toCharArray();
        while (i<s.length()){
            if(ch[i]=='('){
                count++;
                i++;
                continue;
            }
            if(ch[i]==')'){
                count--;
                if(count==0){
                    return i;
                }
                i++;
                continue;
            }
            i++;
        }
        return -1;
    }
//9／16／2018,背的,比较顺
    public int calculate2(String s) {
        if(s.length()==0){
            return 0;
        }
        s=s.replace(" ","");
        Stack<Integer> st=new Stack<>();
        char sign='+';
        int rs=0;
        char[] ch=s.toCharArray();
        int i=0;
        int first=0;
        boolean neg=false;
        if(ch[0]=='-'){
            neg=true;
            i++;
        }
        if(ch[i]=='('){
            int end=findPranthesis2(i+1,s);
            first=calculate2(s.substring(i+1,end));
            i=end+1;
        }else{
            while (i<ch.length&&Character.isDigit(ch[i])){
                first=first*10+ch[i]-'0';
                i++;
            }
        }

        if(neg){
            st.push(-1*first);
        }else{
            st.push(first);
        }
        while (i<ch.length){
            sign=ch[i];
            i++;
            int a=0;
//            if (i>=ch.length){//不写也对
//                break;
//            }
            if(ch[i]=='('){
                int end=findPranthesis2(i+1,s);
                a=calculate2(s.substring(i+1,end));
                i=end+1;
            }else{
                while (i<ch.length&&Character.isDigit(ch[i])){
                    a=a*10+ch[i]-'0';
                    i++;
                }
            }
            if(sign=='*'){
                st.push(st.pop()*a);
                //这里容易多写i++；
                continue;
            }
            if(sign=='/'){
                st.push(st.pop()/a);
                continue;
            }
            if(sign=='+'){
                st.push(a);
                continue;
            }
            if(sign=='-'){
                st.push(-1*a);
                continue;
            }

        }

        while (!st.isEmpty()){
            rs+=st.pop();
        }

        return rs;
    }
    //找括号这个方法开始想错了，要找到对应的，而不是第一个遇到的'）'
    int findPranthesis2(int b,String s){
        char[] ch=s.toCharArray();
        int left=1;
        int i=b;
        while (i<ch.length){
            if(ch[i]=='('){
                left++;
                i++;
                continue;
            }
            if(ch[i]==')'){
                left--;
            }
            if(left==0){
                return i;
            }
            i++;
        }
        return -1;
    }

    //9/8/2021 思路是有的，写出来不太好，关键是判断"（"之后我这写的是直接加入栈，那么2*（xxx）这种就处理不了，要处理的话就要遇到*和/的时候看后面是数字还是括号
    //那样又不知道咋的写不对，还是看回以前要先弄出first的写法。，看到括号先把括号里的计算出来，否则就是得出第一个数字，再在同一个while里看下一个字符，加减的话
    // 就直接push第一个数字入栈，乘除
    public int calculate3(String s) {
        s=s.replace(" ","");
        Stack<Integer> st=new Stack<>();
        int i=0;
        int sign=1;
        while (i<s.length()){
            if (s.charAt(i)=='('){
                int end=findPranthesis3(s,i);
                int num=calculate3(s.substring(i+1,end));
                st.push(sign*num);
                sign=1;//因为没括号了，sign只会作用一次，比如-3*4，比如2-1
                i=end+1;
                continue;
            }
            if (s.charAt(i)=='+'){
                sign=1;
                i++;
                int temp=0;
                while (i<s.length()&&Character.isDigit(s.charAt(i))){
                    temp=temp*10+s.charAt(i)-'0';
                    i++;
                }
                if (temp!=0){
                    st.push(temp);
                }else {
                    int end=findPranthesis3(s,i);
                    int num=calculate3(s.substring(i+1,end));
                    st.push(num);
                    i=end+1;
                }
                continue;
            }
            if (s.charAt(i)=='-'){
                sign=-1;
                i++;
                int temp=0;
                while (i<s.length()&&Character.isDigit(s.charAt(i))){
                    temp=temp*10+s.charAt(i)-'0';
                    i++;
                }
                if (temp!=0){
                    st.push(-temp);
                    sign=1;
                }else {
                    int end=findPranthesis3(s,i);
                    int num=calculate3(s.substring(i+1,end));
                    st.push(sign*num);
                    sign=1;
                    i=end+1;
                }
                continue;
            }
            if (s.charAt(i)=='*'){
                int temp=0;
                i++;
                while (i<s.length()&&Character.isDigit(s.charAt(i))){
                    temp=temp*10+s.charAt(i)-'0';
                    i++;
                }
                if (temp!=0){
                    st.push(st.pop()*temp);
                }else {
                    int end=findPranthesis3(s,i);
                    int num=calculate3(s.substring(i+1,end));
                    st.push(st.pop()*num);
                    i=end+1;
                }
                continue;
            }
            if (s.charAt(i)=='/'){
                int temp=0;
                i++;
                while (i<s.length()&&Character.isDigit(s.charAt(i))){
                    temp=temp*10+s.charAt(i)-'0';
                    i++;
                }
                if (temp!=0){
                    st.push(st.pop()/temp);
                }else {
                    int end=findPranthesis3(s,i);
                    int num=calculate3(s.substring(i+1,end));
                    st.push(st.pop()/num);
                    i=end+1;
                }
                continue;
            }
            int temp=0;
            while (i<s.length()&&Character.isDigit(s.charAt(i))){
                temp=temp*10+s.charAt(i)-'0';
                i++;
            }
            st.push(temp);
        }
        int rs=0;
        while (!st.isEmpty()){
            rs+=st.pop();
        }
        return rs;
    }
    int findPranthesis3(String s,int i){
        int left=1;
        i++;
        while (i<s.length()){
            if (s.charAt(i)=='('){
                left++;
                i++;
                continue;
            }
            if (s.charAt(i)==')'){
                left--;
                if (left==0){
                    return i;
                }
                i++;
                continue;
            }
            i++;
        }
        return -1;
    }
}
