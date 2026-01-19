package 灵神.常用数据结构.stack;

import java.util.Stack;

public class ScoreofParentheses856 {
    static void main() {
        System.out.println(scoreOfParentheses("(())"));
    }
    //11/29/2025 写不出
    //https://leetcode.cn/problems/score-of-parentheses/solutions/1878233/by-ac_oier-0mhz/
    public static int scoreOfParentheses(String s) {
        char[] ch=s.toCharArray();
        Stack<Integer> st=new Stack<>();
        st.push(0);//先加一个0不好想。//每次遇到 '('：说明开始了一个新的“内部得分区域”，所以压一个 0，表示“这个新
        // 区域目前还没有得分”.每次遇到 ')'：说明这个区域结束了，把它“内部的得分”计算出来，累加到“外层区域”
        // 如果整个字符串一开始就没有压一个 0，那么遇到第一个 ')' 时，外层区域不存在，无法累加
        for (int i=0;i<ch.length;i++){
            if(s.charAt(i)=='('){
                st.push(0);
            }else{
                int temp=st.pop();
                if(!st.isEmpty()){//有嵌套,其实由于外面加了0，而且本来是valid的括号，所以必然不是空
                    int temp2=st.pop()+Math.max(temp*2,1);
                    st.push(temp2);
                }else{
                    st.push(1);
                }
            }
        }

        return st.pop();
    }
}
