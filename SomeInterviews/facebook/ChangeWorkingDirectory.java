package SomeInterviews.facebook;

import java.util.Stack;

public class ChangeWorkingDirectory {
    public static void main(String[] args){
        ChangeWorkingDirectory cw=new ChangeWorkingDirectory();
        String current1 = "/";
        String change1 = "/facebook";
        System.out.println(cw.simplifyPath(current1, change1)); //ans: /facebook

        String current2 = "/facebook/anin";
        String change2 = "../abc/def";
        System.out.println(cw.simplifyPath(current2, change2)); //ans: /facebook/abc/def

        String current3 = "/facebook/instagram";
        String change3 = "../../../../.";
        System.out.println(cw.simplifyPath(current3, change3)); //ans: /

        String current4 = "/facebook/instagram";
        String change4 = "../abc/../xy/./kk";
        System.out.println(cw.simplifyPath(current4, change4)); //ans: /facebook/xy/kk
    }
    //https://leetcode.com/discuss/interview-question/553454/facebook-phone-change-working-directory
    public String simplifyPath(String current, String change) {
        String[] cur=current.split("/");//注意这个split出来的东西第一个是empty的string。
        String[] ch=change.split("/");
        Stack<String> st=new Stack<>();
        for (String s:cur){
            if (s.isEmpty()){
                continue;
            }
            st.push(s);
        }
        for (String s:ch){
            if (s.equals("..")){
                if (!st.isEmpty()){
                    st.pop();
                }
            }else if (s.equals(".")||s.length()==0){
                continue;
            } else {
                st.push(s);
            }
        }
        StringBuilder sb=new StringBuilder();
        if (st.isEmpty()){
            return "/";
        }
        while (!st.isEmpty()){
            sb.insert(0,st.pop());
            sb.insert(0,"/");
        }
        return sb.toString();
    }
}
