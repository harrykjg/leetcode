package SomeInterviews;

import java.util.Stack;

/**
 * Created by 502575560 on 7/27/17.
 */
//http://www.geeksforgeeks.org/expression-evaluation/  题目和答案
    //自己想还是很难的,关键点就是用2个stack,一个装操作符号和左括号,另一个只装数字,遍历字符串时遇到某个操作符或者右括号都有机会trigger运算,因为
    //思路其实时从左到右按优先级计算,所谓的trigger运算意思就是先把前面可以算的算出来了.这里遇到右括号时,肯定是计算了,但遇到运算符则不一定,
    //比如2-1+3现在遇到+,此时2,1在栈里,'-'也在栈里,这里遇到+则要判断这个加好和栈里的减号谁优先,假如他们同等优先,则按从左到右的原则先要算2-1,
    //得出的数再入栈,再把加号入栈,假如是2-1*3,现在遇到*了,同样判断-和*谁优先,发现*优先,则我就继续把*入栈,3随后也入栈,最后表达式结束了,则挨个pop出来
    //装操作符的那个栈,每pop一个就pop两个数字栈的数字并且算出结果再入栈,知道操作符栈为空
// //https://leetcode.com/problems/evaluate-reverse-polish-notation/tabs/description
public class ExpressionEvaluation {
    public static void main(String[] args){
        ExpressionEvaluation ev=new ExpressionEvaluation();
        System.out.println(ev.evaluate("5*(6*(2-1+3))-12/4"));
    }
    public int evaluate(String s){
        Stack<Character> st1=new Stack<>();
        Stack<Integer> st2=new Stack<>();
        char[] ch=s.toCharArray();
        int i=0;
        while (i<s.length()){
            char c=ch[i];
            if(c>='0'&&c<='9'){
                int temp=c-'0';
                while (i+1<ch.length){
                    temp=temp*10+ch[i+1]-'0';
                    i++;
                }
                st2.push(c-'0');
                i++;
                continue;
            }
            if(c=='('){
                st1.push('(');
                i++;
                continue;
            }
            if(c==')'){
                while (st1.peek()!='('){
                    int temp=cal(st1.pop(),st2.pop(),st2.pop());
                    st2.push(temp);
                }
                st1.pop();
                i++;
                continue;
            }
            //现在c肯定是操作符了
            while(!st1.isEmpty()&&!isHigher(c,st1.peek())){//c比peek落后或相等则先算前面的
                int temp=cal(st1.pop(),st2.pop(),st2.pop());
                st2.push(temp);

            }
            st1.push(c);
            i++;
        }
        while (!st1.isEmpty()){
            int temp=cal(st1.pop(),st2.pop(),st2.pop());
            st2.push(temp);
        }
        return st2.pop();
    }
    boolean isHigher(char c1,char c2){//判断c1是否比c2优先
        if(c2=='('){
            return true;
        }
        if((c1=='+'||c1=='-')&&(c2=='*'||c2=='/')){
            return false;
        }
        return false;
    }
    int cal(char op,int i1,int i2){
        switch (op){
            case '+':
                return i2+i1;
            case  '-':
                return i2-i1;
            case '*':
                return i2*i1;
            case '/':
                return i2/i1;
        }
        return 0;
    }
}
