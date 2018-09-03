package DataStruct.Stack;

import java.util.Stack;

/**
 * Created by yufengzhu on 8/14/18.
 */
public class BasicCalculatorIII {
    public static void main(String[] args){
        BasicCalculatorIII bc=new BasicCalculatorIII();
        System.out.print(bc.calculate("2*(5+5*2)/3+(6/2+8)"));

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
}
