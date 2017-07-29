package Advance3;

import java.util.Stack;

/**
 * Created by 502575560 on 7/21/17.
 */
public class ExpressionExpand {
    public static void main(String[] args){
        System.out.println(expressionExpand("3[a]2[bc]i"));
    }
    //就是leetcode的Decode String,再写还是很麻烦,学链接的解法二吧,用2个stack,也不用先加1进stack
    //http://www.cnblogs.com/grandyang/p/5849037.html
    public static String expressionExpand(String s) {
        // Write your code here
        String cur="";
        int i=0;
        int count=0;
        Stack<Integer> st1=new Stack<>();
        Stack<String> st2=new Stack<>();
        char[] ch=s.toCharArray();
        while (i<s.length()){
            char c=ch[i];
            if(c>='0'&&c<='9'){
                count=count*10+(c-'0');
                i++;
                continue;
            }
            if(c=='['){//括号前没有数字的话这个括号里的东西就不显示了
                st1.push(count);
                st2.push(cur);
                cur="";
                count=0;
                i++;
                continue;
            }
            if(c==']'){//这一个if是关键,st2里装的东西直觉上应该是括号里的东西,其实不是,st2装的是括号前的字符串比如ab3[c],st2pop出来是ab,
                int temp=st1.pop();//而cur才是括号里的东西,这样通过for循环ab加上三个cur就对了,完事之后cur就变成到现在为止的result了,不太好想
                String ss=st2.pop();
                for(int j=0;j<temp;j++){
                    ss=ss+cur;
                }
                cur=ss;
                i++;
                continue;
            }
            cur=cur+c;
            i++;
        }
        return cur;
    }

    public static String expressionExpand2(String s) {
        // Write your code here
        String cur="";
        int i=0;
        int count=0;
        Stack<Integer> st1=new Stack<>();
        Stack<String> st2=new Stack<>();
        char[] ch=s.toCharArray();
        while (i<s.length()){
            char c=ch[i];
            if(c>='0'&&c<='9'){
                count=count*10+(c-'0');
                i++;
                continue;
            }
            if(c=='['){//括号前没有数字的话这个括号里的东西就不显示了
                st1.push(count);
                st2.push(cur);
                cur="";
                count=0;
                i++;
                continue;
            }
            if(c==']'){//这一个if是关键,st2里装的东西直觉上应该是括号里的东西,其实不是,st2装的是括号前的字符串比如ab3[c],st2pop出来是ab,
                int temp=st1.pop();//而cur才是括号里的东西,这样通过for循环ab加上三个cur就对了,完事之后cur就变成到现在为止的result了,不太好想
                String ss=st2.pop();
                for(int j=0;j<temp;j++){
                    ss=ss+cur;
                }
                cur=ss;
                i++;
                continue;
            }
            cur=cur+c;
            i++;
        }
        return cur;
    }
}
