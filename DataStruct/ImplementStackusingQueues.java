package DataStruct;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackusingQueues {

  //自己还想不出来
  //http://www.cnblogs.com/grandyang/p/4568796.html 第二个写起来方便一点，而且pop和top都是O（1）
  Queue<Integer> q1;
  Queue<Integer> q2;

  public ImplementStackusingQueues() {
      q1=new LinkedList<>();
      q2=new LinkedList<>();
  }

  /** Push element x onto stack. */
  public void push(int x) {
      while (!q1.isEmpty()){
        q2.offer(q1.poll());
      }
      q1.offer(x);
      while (!q2.isEmpty()){
        q1.offer(q2.poll());
      }
  }

  /** Removes the element on top of the stack and returns that element. */
  public int pop() {
    return q1.poll();
  }

  /** Get the top element. */
  public int top() {
    return q1.peek();
  }

  /** Returns whether the stack is empty. */
  public boolean empty() {
    return q1.isEmpty();
  }
}
