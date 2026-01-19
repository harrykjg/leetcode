package 灵神.常用数据结构.stack;

import java.util.Stack;

public class DecodeString394 {
    static void main() {
        System.out.println(decodeString("3[a]2[bc]"));
    }
    //由856启发也先加入一个空string进st2里，但是这样处理不了这种情况
    //2[a2[b]c] 的结构里：
    //a2[b]c 整体是被 2[...] 包起来的
    //但我的算法在外层 ] 时，只拿了 最后那个 "c" 当成要重复的子串 cur
    //而之前已经变成 "abb" 的那一段，被当成外层已有的 temp，结果只重复了 "c"，而不是整个 "abbc"
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
    这个是gpt的写法更好
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
            cur = prev; // 回到上一层
        } else { // 字母
            cur.append(c);
        }
    }
    return cur.toString();
}
     */
}
