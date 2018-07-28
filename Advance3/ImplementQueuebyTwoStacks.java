package Advance3;

import java.util.Stack;

/**
 * Created by 502575560 on 7/21/17.
 */
public class ImplementQueuebyTwoStacks {
    public static void main(String[] args){
        ImplementQueuebyTwoStacks iq=new ImplementQueuebyTwoStacks();
        iq.push(1);
        iq.push(2);
        iq.push(3);
        iq.peek();
        iq.push(4);
        iq.peek();
    }

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
//其实这次我想的方法不太好,需要来回倒腾,看回以前的方法其实不用来回倒腾,只是push进来的数字可能同时存在2个stack里,只有当st2为空时才从st1中倒过来,
    //看回下面commneted的code就是以前的code
    /** Initialize your data structure here. */
    public ImplementQueuebyTwoStacks() {
        stack1=new Stack<>();
        stack2=new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if(stack1.isEmpty()&&!stack2.isEmpty()){//我这里假先push1,2,3,再pop,则st1为空,st2剩下2,3,那我再push4,5那咋办呢,我就先把2,3
            while (!stack2.isEmpty()){   //再push进st1,再push4,5,后面peek或者pop的时候再倒回st2里,虽然也accept了但是其实不好,其实没必要
                stack1.push(stack2.pop()); //倒回st1,就让4,5先存在st1,等st2pop完了之后再把st1倒进st2也不迟
            }
        }
        stack1.push(x);

    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    /** Get the front element. */
    public int peek() {
        while (!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty()&&stack2.isEmpty();
    }

//    public void push(int x) {
//        st1.push(x);
//    }
//
//    // Removes the element from in front of queue.
//    public int pop() {
//        if(st2.isEmpty()){
//            while(!st1.isEmpty()){
//                st2.push(st1.pop());
//            }
//            return st2.pop();
//        }else{
//            return st2.pop();
//        }
//    }
//
//    // Get the front element.
//    public int peek() {
//        if(st2.isEmpty()){
//            while(!st1.isEmpty()){
//                st2.push(st1.pop());
//            }
//        }
//        return st2.peek();
//    }
//
//    // Return whether the queue is empty.
//    public boolean empty() {
//        return st1.isEmpty()&&st2.isEmpty();
//    }


}
//4/7/2018九章第二轮，还是不会写，用的是唇方法pop和peek都要全部倒腾一遍,这个是好的方法
class ImplementQueuebyTwoStacks2 {
    private Stack<Integer> st1;
    private Stack<Integer> st2;
    public ImplementQueuebyTwoStacks2() {
        st1=new Stack<>();
        st2=new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {

        st1.push(x);

    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (st2.isEmpty()){
            while (!st1.isEmpty()){
                st2.push(st1.pop());
            }
        }
        int rs=st2.pop();
        return rs;
    }

    /** Get the front element. */
    public int peek() {
        if (st2.isEmpty()){
            while (!st1.isEmpty()){
                st2.push(st1.pop());
            }
        }
        return st2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return st1.isEmpty()&&st2.isEmpty();
    }
}

//7/14/2018,还是会的
class ImplementQueuebyTwoStacks3 {
    private Stack<Integer> st1;
    private Stack<Integer> st2;
    public ImplementQueuebyTwoStacks3() {
        st1=new Stack<>();
        st2=new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        st1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
       if(st2.isEmpty()){
           while (!st1.isEmpty()){
               st2.push(st1.pop());
           }
       }
        return st2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(st2.isEmpty()){
            while (!st1.isEmpty()){
                st2.push(st1.pop());
            }
        }
        return st2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return st1.isEmpty()&&st2.isEmpty();
    }
}