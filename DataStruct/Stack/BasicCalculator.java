package DataStruct.Stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by 502575560 on 7/8/16.
 */
public class BasicCalculator {

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
}
