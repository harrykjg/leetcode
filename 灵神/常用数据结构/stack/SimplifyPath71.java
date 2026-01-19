package 灵神.常用数据结构.stack;

import java.util.Stack;

public class SimplifyPath71 {
    static void main() {

    }
//11/28、2025，写不对，要用split就好写
    public String simplifyPath(String path) {
        Stack<String> st=new Stack<>();
        StringBuilder sb=new StringBuilder();
        String[] p=path.split("/");
        for(String s:p){
            if(s.length()==0||s.equals(".")){
                continue;
            }
            if (s.equals("..")){
                if (!st.isEmpty()){
                    st.pop();
                }
                continue;
            }
            st.push(s);
        }

        while (!st.isEmpty()){
            String s=st.pop();
            if(s.equals(".")){
                continue;
            }
            sb.insert(0,s);
            sb.insert(0,'/');
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
