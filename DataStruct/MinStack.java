package DataStruct;

import java.util.Stack;

/**
 * Created by 502575560 on 7/11/17.
 */
public class MinStack {
    //还不太会做,看了要用2个stack才自己想的
    Stack<Integer> st;
    Stack<Integer> minst;
    public MinStack() {
        // do initialize if necessary
        st=new Stack<>();
        minst=new Stack<>();
    }

    public void push(int number) {
        // write your code here
        st.push(number);
        if(minst.isEmpty()||number<=minst.peek()){
            minst.push(number);
        }

    }

    public int pop() {
        // write your code here
        int temp=st.pop();
        if(temp==minst.peek()){
            minst.pop();
        }
        return temp;
    }

    public int min() {
        // write your code here
        return minst.peek();
    }
}
