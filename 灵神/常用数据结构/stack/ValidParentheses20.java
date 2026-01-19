package 灵神.常用数据结构.stack;

import java.util.Stack;

public class ValidParentheses20 {
    static void main() {
        System.out.println(isValid("()[]{}"));
    }

    public static boolean isValid(String s) {
        char[] ch=s.toCharArray();
        Stack<Character> st=new Stack<>();
        for (int i=0;i<ch.length;i++){
            if(ch[i]=='('){
                st.push('(');
            }
            if(ch[i]=='['){
                st.push(ch[i]);
            }
            if(ch[i]=='{'){
                st.push(ch[i]);
            }
            if(ch[i]==')'){
                if(st.isEmpty()||st.pop()!='('){
                    return false;
                }
            }
            if(ch[i]==']'){
                if(st.isEmpty()||st.pop()!='['){
                    return false;
                }
            }
            if(ch[i]=='}'){
                if(st.isEmpty()||st.pop()!='['){
                    return false;
                }
            }
        }
        return st.isEmpty();
    }
}
