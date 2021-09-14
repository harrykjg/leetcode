package Advance3;

import com.sun.deploy.util.StringUtils;

import java.util.Stack;

/**
 * Created by 502575560 on 7/21/17.
 */
public class ExpressionExpand {
    public static void main(String[] args){
        System.out.println(expressionExpand4("10[lc]"));
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


//4/7/2018九章第二轮，还是很麻烦，想到是用2个stack，写起来还是不好写,"["之前肯定是有数字的，记上面的写法吧
    public static String expressionExpand3(String s) {
        String cur="";
        Stack<Integer> st1=new Stack<>();
        Stack<String> st2=new Stack<>();
        char[] ch=s.toCharArray();
        int i=0;
        while(i<s.length()){
            if(ch[i]>='0'&&ch[i]<='9'){
                int count=0;
                while (i<s.length()&&ch[i]>='0'&&ch[i]<='9'){
                    count=count*10+ch[i]-'0';
                    i++;
                }
                st1.push(count);
            }else if(ch[i]=='['){
                st2.push(cur);
                cur="";
                i++;
            }else if(ch[i]==']'){

                int count=st1.pop();
                String temp=st2.pop();
                for(int j=0;j<count;j++){
                    temp+=cur;
                }
                cur=temp;
                i++;
            }else{
                cur+=ch[i];
                i++;
            }

        }
        return cur;

    }

    //6/16/2021，还是写不出,就算知道用2个stack都想不出
    public static String expressionExpand4(String s) {
        int i=0;
        Stack<String> st1=new Stack<>();
        Stack<Integer> st2=new Stack<>();
        String rs="";
        while (i<s.length()){
            if (s.charAt(i)==']'){
                int num=st2.pop();
                String temp=st1.pop();//这里不想清楚谁加谁容易搞错
                while (num>0){
                    temp+=rs;
                    num--;
                }
                rs=temp;
                i++;
            }else {
                if (Character.isDigit(s.charAt(i))){
                    int num=0;
                    while (Character.isDigit(s.charAt(i))){
                        num=10*num+s.charAt(i)-'0';//开始写成+=就错了
                        i++;
                    }
                    st2.push(num);
                    continue;
                }else if (s.charAt(i)=='['){
                    i++;
                    st1.push(rs);
                    rs="";
                    continue;
                }else {
                    rs+=s.charAt(i);
                    i++;
                }
            }
        }
        return rs;
    }
}
