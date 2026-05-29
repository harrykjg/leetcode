package 灵神.常用数据结构.stack;

import java.util.Stack;

public class DecodeString394 {
    static void main() {
        System.out.println(decodeString2("3[a2[c]]"));
    }

    public static String decodeString(String s) {
        Stack<Integer> st=new Stack<>();
        Stack<String> st2=new Stack<>();
        st2.push("");
        char[] ch=s.toCharArray();
        int i=0;
        int num=0;
        while (i<ch.length){
            if(ch[i]=='['){//原来的错误，在 [ 那一刻就把后面的“连续字母块”截出来，等于提前截断结构了，导致后面 ]
                // 时只会重复「最后那一块」，而不是整个括号里的内容，这就是你在 2[a2[b]c] 里只重复了 c 的本质原因。
                i++;
               st.push(num);
               st2.push("");//这里容易漏
                continue;
            }
            if(Character.isDigit(ch[i])){
                num=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    num=num*10+ch[i]-'0';
                    i++;
                }
//                st.push(num); 先不入栈，等遇到【时再入
                continue;
            }
            if(ch[i]==']'){
                int count=st.pop();
                String cur=st2.pop();
                String temp=st2.pop();
                for(int j=0;j<count;j++){
                    temp+=cur;
                }
                st2.push(temp);
                i++;
                continue;
            }
            if(Character.isAlphabetic(ch[i])){
                String temp="";
                while (i<ch.length&&Character.isAlphabetic(ch[i])){
                    temp+=ch[i];
                    i++;
                }
                String top=st2.pop();
                st2.push(top+temp);
                continue;
            }
        }

        return st2.pop();
    }
    /*
    这个是gpt的写法更好，但是他这个用cur并且cur需要重置回上一层的不好想，过一段时间写起来还是会按照以前的模拟法写
    public static String decodeString(String s) {
    Deque<Integer> countStack = new ArrayDeque<>();
    Deque<StringBuilder> strStack = new ArrayDeque<>();
    StringBuilder cur = new StringBuilder();
    int k = 0;

    for (char c : s.toCharArray()) {
        if (Character.isDigit(c)) {
            k = k * 10 + (c - '0'); // 支持多位数
        } else if (c == '[') {
            countStack.push(k);     // 这一层的次数
            strStack.push(cur);     // 这一层外面的字符串
            k = 0;
            cur = new StringBuilder(); // 开始新的子串
        } else if (c == ']') {
            int count = countStack.pop();
            StringBuilder prev = strStack.pop();
            for (int i = 0; i < count; i++) {
                prev.append(cur);
            }
            cur = prev; // 回到上一层，这个不好想
        } else { // 字母
            cur.append(c);
        }
    }
    return cur.toString();
}
     */

    //3/9/2026 还是不好写，得一点点想，还有有初始化cur=“”的写法也有不用的写法，basic calculator1有cur=0；
    public static String decodeString2(String s) {
        Stack<Integer> st1=new Stack<>();
        Stack<String> st2=new Stack<>();
        st2.push("");

        char[] ch=s.toCharArray();
        int i=0;
        String cur="";

        String rs="";
        while (i<ch.length){
            if(ch[i]==']'){
                int count=st1.pop();
                String temp=st2.pop();
                String temp2=st2.pop();
                for (int j=0;j<count;j++){
                    temp2+=temp;
                }

                st2.push(temp2);
                cur="";
                i++;
            }
            else if(Character.isDigit(ch[i])){
                st2.push(st2.pop()+cur);//加进rs还是加进st2，应该不是加进rs吧，因为nested的情况如3[cd4[f]]，现在cur是cd遇到4，那肯定不是加进rs
                cur="";
                int count=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    count=count*10+ch[i]-'0';
                    i++;
                }
                st1.push(count);//现在i就必然是‘[’
                st2.push("");//这个真的很难想
                i++;
            }else if(Character.isAlphabetic(ch[i])){
                while (i<ch.length&&Character.isAlphabetic(ch[i])){
                    cur+=ch[i];
                    i++;
                }
                st2.push(st2.pop()+cur);
                cur="";
            }
        }
        if(!st2.isEmpty()){
            rs+=st2.pop();
        }
        return rs;

    }
}
