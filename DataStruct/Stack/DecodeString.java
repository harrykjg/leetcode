package DataStruct.Stack;

import java.util.Stack;

/**
 * Created by 502575560 on 5/25/17.
 */
public class DecodeString {

    public static void main(String[] args){
//        String s="3[a]3[g2[bc2[d]2[kj]]]";
        System.out.println(DecodeString.decodeString("2[2[2[b]]]"));//注意 这样的字母前面没有数字的例子
    }

    //看这个3[a]3[g2[bc4[d]2[kj]]]例子想,tag用stack,自己想的,改了很久终于accept了,
    //http://www.cnblogs.com/grandyang/p/5849037.html
    //http://blog.csdn.net/mebiuw/article/details/52448807
    //http://blog.csdn.net/qq508618087/article/details/52439114
    public static String decodeString(String s) {
        int i=0;
        String rs="";
        Stack<numString> st=new Stack<>();

        while (i<s.length()){
            char c=s.charAt(i);
            if(c==']'){
                i++;
                numString ns=st.peek();
                int num=ns.num;
                String l=ns.s;

                if(num!=-1){
                    String temp="";
                    for(int j=0;j<num;j++){
                        temp+=l;
                    }
                    st.pop();
                    if(st.size()==0){
                        rs+=temp;
                    }else{
                        st.push(new numString(-1,temp));
                    }
                } else {
                    String temp="";
                    while (num==-1){
                        temp=l+temp;
                        st.pop();
                        if(st.size()>0){
                            num=st.peek().num;
                            l=st.peek().s;
                        }else {
                            break;
                        }
                    }
                    temp=l+temp;
                    String temp2="";
                    for(int j=0;j<num;j++){
                        temp2+=temp;
                    }
                    st.pop();
                    if(st.size()==0){
                        rs+=temp2;
                    }else{
                        st.push(new numString(-1,temp2));
                    }
                }
            }else {
                String num="";
                while (c-'0'>=0&&c-'9'<=0){
                    num+=c;
                    i++;
                    c=s.charAt(i);
                }
                if(num==""){//说明是没有数字的
                    StringBuilder letter=new StringBuilder();
                    int count=0;
                    while (i<s.length()){
                        c=s.charAt(i);
                        if(c==']'||c=='['||(c-'0'>0&&c-'9'<=0)){
                            break;
                        }
                        letter.append(c);
                        i++;
                        count++;
                    }
                    //把原字符串加上[]
                    String s2=s.substring(i);

                    s=s.substring(0,i)+"]"+s2;
                    s2=s.substring(i-count);
                    s=s.substring(0,i-count)+"["+s2;
                    st.push(new numString(1,letter.toString()));
                    i+=1;
                }else {
                    //数组读完之后下一个肯定是'['
                    i++;
                    StringBuilder letter=new StringBuilder();
                    while (true){
                        c=s.charAt(i);
                        if(c==']'||c=='['||(c-'0'>0&&c-'9'<=0)){
                            break;
                        }
                        letter.append(c);
                        i++;
                    }
                    st.push(new numString(Integer.parseInt(num),letter.toString()));
                }
            }
        }

        return rs;
    }
    static class numString{
        int num;
        String s;
        public numString(int num, String s){
            this.num=num;
            this.s=s;
        }
    }
}
