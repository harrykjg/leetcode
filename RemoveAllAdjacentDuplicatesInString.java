import javafx.util.Pair;

import java.util.Stack;

public class RemoveAllAdjacentDuplicatesInString {
    //8/24/2021 直接用第二题的解法做。其实不需要pair 的，stack里直接放char然后检测stack里的是不是当前的就行了
    public String removeDuplicates(String s) {
            Stack<Pair> st=new Stack<>();
            if (s.length()<=1){
                return s;
            }
            char[] ch=s.toCharArray();
            int i=0;
            while (i<ch.length){
                if (st.isEmpty()||st.peek().c!=ch[i]){
                    Pair p=new Pair(ch[i],1);
                    st.push(p);
                    i++;
                    continue;
                }
                st.pop();
                i++;
            }
            StringBuilder sb=new StringBuilder();
            while (!st.isEmpty()){
                sb.append(st.pop().c);
            }
            return sb.reverse().toString();

        }
        class Pair{
            char c;
            int count;
            public Pair(char c,int count){
                this.c=c;
                this.count=count;
            }
        }
}
