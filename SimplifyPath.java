import java.util.Stack;

public class SimplifyPath {
    //8/23/2021  用split之后就容易了，直接写，注意最后stack pop出来的时候是反着的。可以用dequeue也可以用stringbuilder.insert在开头
    public String simplifyPath(String path) {
        if (path.length()==0){
            return "";
        }
        String[] ss=path.split("/");
        Stack<String> st=new Stack<>();
        for (int i=0;i<ss.length;i++){
            if (ss[i].length()==0||ss[i].equals(".")){
                continue;
            }
            if (ss[i].equals("..")){
                if (!st.isEmpty()){
                    st.pop();
                }
                continue;
            }
            st.push(ss[i]);
        }
        StringBuilder sb=new StringBuilder();
        if(st.isEmpty()){
            return "/";
        }
        while (!st.isEmpty()){
            sb.insert(0,"/"+st.pop());
        }

        return sb.toString();
    }
}
