import java.util.Stack;

public class ValidParentheses {
//04/20/2020，一次过
    public boolean isValid(String s) {
        if(s.length()==0){
            return true;
        }
        Stack<Character> st=new Stack<>();

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='{'||s.charAt(i)=='('||s.charAt(i)=='['){
                st.push(s.charAt(i));
            }else{
                if(st.isEmpty()){
                    return false;
                }
                char cur=st.pop();
                if((cur=='{'&&s.charAt(i)!='}')
                        ||(cur=='['&&s.charAt(i)!=']')
                        ||(cur=='(')&&s.charAt(i)!=')'){
                    return false;
                }
            }
        }
        return st.isEmpty();
    }
}
