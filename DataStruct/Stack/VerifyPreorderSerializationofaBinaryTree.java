package DataStruct.Stack;

import java.util.Stack;

/**
 * Created by yufengzhu on 7/8/18.
 */
public class VerifyPreorderSerializationofaBinaryTree {
    public boolean isValidSerialization(String preorder) {
        if(preorder==null||preorder.length()==0){
            return false;
        }
        String[] s=preorder.split(",");
        Stack<String> st=new Stack();
        for(int i=0;i<s.length;i++){
            while(s[i].equals("#")&&st.size()>=2&&st.peek().equals("#")){
                st.pop();
                st.pop();
            }

            st.push(s[i]);
            if(st.size()==1&&st.peek().equals("#")&&i!=s.length-1){
                return false;
            }
        }
        return st.size()==1&&st.peek().equals("#");

    }
}
