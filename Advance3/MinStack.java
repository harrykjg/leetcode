package Advance3;

import java.util.Stack;

/**
 * Created by yufengzhu on 4/7/18.
 */
//九章第二轮，不记得方法，写的不好的算法，重写一遍对的算法
public class MinStack {
    Stack<Integer> st1;
    Stack<Integer> st2;
    public MinStack() {
        st1=new Stack<>();
        st2=new Stack<>();
    }

    public void push(int x) {
        st1.push(x);
        if(st2.isEmpty()||x<=st2.peek()){
            st2.push(x);
        }

    }

    public void pop() {
        int temp=st1.pop();
        if(temp==st2.peek()){
           st2.pop();
        }
    }

    public int top() {
        return st1.peek();
    }

    public int getMin() {
        return st2.peek();
    }
}
