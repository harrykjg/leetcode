package DataStruct;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackusingQueues2 {
    /** Initialize your data structure here. */
    //6/6/2021改了几次对了
    Queue<Integer> q1=new LinkedList<>();
    Queue<Integer> q2=new LinkedList<>();
    public ImplementStackusingQueues2() {

    }

    /** Push element x onto stack. */
    public void push(int x) {//想的和以前有点不一样，这个是q1和q2轮流空着，以前的是总是某一个q空着
        if(q1.isEmpty()&&q2.isEmpty()){
            q1.offer(x);
            return;
        }
        if(q2.isEmpty()){
            q2.offer(x);
            while(!q1.isEmpty()){
                q2.offer(q1.poll());
            }
        }else{
            q1.offer(x);
            while(!q2.isEmpty()){
                q1.offer(q2.poll());
            }
        }

    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(q1.isEmpty()){
            return q2.poll();
        }
        return q1.poll();
    }

    /** Get the top element. */
    public int top() {
        if(q1.isEmpty()){
            return q2.peek();
        }
        return q1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty()&&q2.isEmpty();
    }
}
