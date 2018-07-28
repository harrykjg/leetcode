import java.util.Stack;

/**
 * Created by yufengzhu on 7/9/18.
 */
public class BackspaceStringCompare {
    //typo之后一次过，但是是O(n）空间的，还有O(1）空间的方法想不出来 https://leetcode.com/problems/backspace-string-compare/solution/
    public boolean backspaceCompare(String S, String T) {

        String s1="";
        String s2="";
        Stack<Character> st=new Stack<>();
        char[] ch1=S.toCharArray();
        char[] ch2=T.toCharArray();
        int i=0;
        while (i<ch1.length){
            if(ch1[i]=='#'){
                if(!st.isEmpty()){
                    st.pop();
                }
                i++;
                continue;
            }
            st.push(ch1[i]);
            i++;
        }
        while (!st.isEmpty()){
            s1+=st.pop();
        }
        i=0;
        while (i<ch2.length){
            if(ch2[i]=='#'){
                if(!st.isEmpty()){
                    st.pop();
                }
                i++;
                continue;
            }
            st.push(ch2[i]);
            i++;
        }
        while (!st.isEmpty()){
            s2+=st.pop();
        }
        return s1.equals(s2);

    }
}
