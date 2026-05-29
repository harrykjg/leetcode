package 灵神.常用数据结构.stack;

import java.util.Stack;

public class minstack155 {
    static void main() {

    }
    //3/12/2026 两分钟写出来
    Stack<Integer> st1;
    Stack<Integer> st2;
    public minstack155() {
        st1=new Stack<>();
        st2=new Stack<>();
    }
    public void push(int val) {
        st1.push(val);
        if(st2.isEmpty()||st2.peek()>=val){
            st2.push(val);
        }
    }

    public void pop() {
        int temp=st1.pop();
        if(st2.peek()==temp){
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
