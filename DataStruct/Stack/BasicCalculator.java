package DataStruct.Stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by 502575560 on 7/8/16.
 */
public class BasicCalculator {
    //容易和expandexpression搞混
    public static void main(String[] a){
        BasicCalculator bc=new BasicCalculator();
        bc.calculate2("2-1+1");
    }

    //2016年7月,两星期前才看了思路但是还是实现不出来,就是没弄清楚其实. stack里装的是1或-1,是按括号是"("还是")"来决定是push还是pop的,
    //stack最顶层的是当前括号的符号
    public int calculate(String s) {
        if(s.length()==0){
            return 0;
        }
        int rs=0;
        s=s.replace(" ","");
        Stack<Integer> st=new Stack<>();
        st.push(1);
        int i=0;
        int sign=1;
        while(i<s.length()){
            char c=s.charAt(i);
            if(c=='+'){
                sign=1;
                i++;
                continue;
            }
            if(c=='-'){
                sign=-1;
                i++;
                continue;
            }
            if(c=='('){
                st.push(st.peek()*sign);
                sign=1;//这里要重设sign
                i++;
                continue;
            }
            if(c==')'){
                st.pop();//这里开始写了sign=1,应该不要重设sign的
                i++;
                continue;
            }
            int temp=0;
            while(i<s.length()&&s.charAt(i)-'0'<=26&&s.charAt(i)-'0'>=0){
                temp=10*temp+s.charAt(i)-'0';
                i++;
            }
            rs+=st.peek()*sign*temp;
        }
        return rs;
    }
//8/13/2018,还是很不好写
    //https://segmentfault.com/a/1190000003796804
    //https://www.cnblogs.com/grandyang/p/4570699.html
    //http://www.cnblogs.com/ganganloveu/p/4640589.html
    public int calculate2(String s) {
        if(s.length()==0){
            return 0;
        }
        s=s.replace(" ","");
        int sign=1;
        Stack<Integer> st=new Stack<>();
        st.push(1);
        int i=0;
        char[] ch=s.toCharArray();
        int rs=0;
        while (i<ch.length){
            if(ch[i]=='('){
                st.push(sign*st.peek());
                sign=1;//这个要重新设sign很容易漏
                i++;
                continue;
            }
            if(ch[i]=='+'){
                sign=1;
                i++;
                continue;
            }
            if(ch[i]=='-'){
                sign=-1;
                i++;
                continue;
            }
            if(ch[i]==')'){
                st.pop();
                sign=1;//写不写都对
                i++;
                continue;
            }
            int temp=0;

            while (i<ch.length&&ch[i]-'0'>=0&&ch[i]-'0'<=9){
                temp=temp*10+ch[i]-'0';
                i++;
            }
            rs+=st.peek()*sign*temp;

        }
        return rs;

    }
}
