package 灵神.单调栈.最小字典顺序;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class RemoveKDigits402 {
    public static void main(String[] args) {
        System.out.println(removeKdigits("12023",4));
    }

    //基本是对的但是修修补补，参考他的如果还有多余的k那么则直接从后面开始删，如例子112，k=1
    //https://leetcode.cn/problems/remove-k-digits/solutions/484940/yi-diao-kwei-shu-zi-by-leetcode-solution/
    public static String removeKdigits(String num, int k) {
        char[] ch=num.toCharArray();
        Deque<Integer> st=new LinkedList<>();
        if(num.length()<=k){
            return "0";
        }
        for(int i=0;i<ch.length;i++){
            if(st.isEmpty()||k<=0){
                st.push(ch[i]-'0');
                continue;
            }
            while (!st.isEmpty()&&st.peek()>ch[i]-'0'&&k>0){
                st.pop();
                k--;
            }
            st.push(ch[i]-'0');
        }
        StringBuilder sb=new StringBuilder();
        while(!st.isEmpty()){
            if(sb.length()==0&&st.peekLast()==0){
                st.pollLast();
                continue;
            }
            sb.append(st.pollLast());
        }

        if(k>0){
            sb=new StringBuilder(sb.substring(0, Math.max(0,sb.length()-k)));//这里不如像答案那样之前在前面就先for循环搞掉多余的k
        }
        if(sb.length()==0){
            return "0";
        }
        return sb.toString();
    }
}
