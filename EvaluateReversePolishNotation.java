import java.util.Stack;

public class EvaluateReversePolishNotation {
    //7/18/2021 就很简单stack 注意减法和除法的先后就行了
    public int evalRPN(String[] tokens) {
        Stack<Integer> st=new Stack<>();
        int rs=0;
        for (int i=0;i<tokens.length;i++){
            if (tokens[i].equals("+")){
                st.push(st.pop()+st.pop());
            } else if (tokens[i].equals("-")){
                int a=st.pop();
                int b=st.pop();
                st.push(b-a);
            }
            else if (tokens[i].equals("*")){

                st.push(st.pop()*st.pop());
            }
            else if (tokens[i].equals("/")){
                int divisor=st.pop();
                int dividend=st.pop();
                st.push(dividend/divisor);
            }else {
                st.push(Integer.valueOf(tokens[i]));
            }
        }
        return st.pop();
    }

    //linkedin面精要处理非法的情况
    public int evalRPN2(String[] tokens) {
        if (tokens.length==0){
            return 0;
        }
        int rs=0;
        int i=0;
        Stack<Integer> st=new Stack<>();
        while (i<tokens.length){
            String cur=tokens[i];
            if (cur.equals("+")){
                try{
                    int one=st.pop();
                    int two=st.pop();
                    st.push(one+two);
                }catch (Exception ex){
                    return -1;
                }
            }else if (cur.equals("-")){
                try{
                    int one=st.pop();
                    int two=st.pop();
                    st.push(two-one);
                }catch (Exception ex){
                    return -1;
                }
            }
            else if (cur.equals("*")){
                try{
                    int one=st.pop();
                    int two=st.pop();
                    st.push(two*one);
                }catch (Exception ex){
                    return -1;
                }
            }else if (cur.equals("/")){
                try{
                    int one=st.pop();
                    int two=st.pop();
                    st.push(two/one);
                }catch (Exception ex){
                    return -1;
                }
            }else {
                try {
                    int num=Integer.parseInt(cur);
                    st.push(num);
                }catch (Exception ex){
                    return -1;
                }
            }
            i++;
        }
        return st.pop();
    }
}
