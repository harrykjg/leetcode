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
        if(st.isEmpty()){
            return "/";
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

    //3/12/2026
    public String simplifyPath2(String path) {
        String[] p=path.split("/");
        Stack<String> stack=new Stack<>();
        int i=0;
        while (i<p.length){
            if(p[i].length()==0){
                i++;
                continue;
            }
            if(p[i].equals(".")){
            }else if(p[i].equals("..")){
                if(stack.isEmpty()){
                    stack.pop();
                }
            }else {
                stack.push(p[i]);
            }
            i++;
        }
        String rs="/";
        if(stack.isEmpty()){
            return rs;
        }
        while (!stack.isEmpty()){
            rs= "/"+stack.pop()+rs;
        }
        return rs.substring(0,rs.length()-1);
     }
}
