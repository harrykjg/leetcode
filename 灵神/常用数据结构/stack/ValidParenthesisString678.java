package 灵神.常用数据结构.stack;

import java.util.Stack;

public class ValidParenthesisString678 {
    static void main() {
        System.out.println(checkValidString("(*))"));
    }
    //11/29/2025
    //想的是记录左边和右边括号的数量和star的数量去判断，结果不好写，因为star出现的位置也有影响，不能说把左边出现的星号补给右边，比如“*（”这个例子
    //gpt说了我这种统计数量的写法很难写，因为没有星号的位置信息，要么就是用stack写。而且用栈的话装的是下标，才能有先后顺序
    // 贪心法很难想
    //https://leetcode.cn/problems/valid-parenthesis-string/solutions/992347/you-xiao-de-gua-hao-zi-fu-chuan-by-leetc-osi3/
    public static boolean checkValidString(String s) {
        Stack<Integer> st=new Stack<>();
        Stack<Integer> st2=new Stack<>();
        char[] ch=s.toCharArray();
        for (int i=0;i<ch.length;i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else if (s.charAt(i) == '*') {
                st2.push(i);
            } else {
                if (st.isEmpty() && st2.isEmpty()) {
                    return false;
                }
                if (!st.isEmpty()) {
                    st.pop();
                } else {
                    st2.pop();
                }
            }
        }
        while (!st.isEmpty()){//这个就是来对付*（这种情况的
            if(st2.isEmpty()){
                return false;
            }
            if(st2.peek()<st.peek()){
                return false;
            }
            st2.pop();
            st.pop();
        }
        return true;
    }

}
