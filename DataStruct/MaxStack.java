package DataStruct;

import java.util.*;

public class MaxStack {
    public static void main(String[] args){
        MaxStack ms=new MaxStack();
        ms.push2(5);
        ms.push2(1);
        ms.push2(5);
        ms.popMax2();
        ms.top2();
        ms.peekMax2();
        ms.pop2();
        ms.peekMax2();
    }
    //8/6/2021比minstack要难，那里不需要pop min而这里需要。所以更难
    //https://leetcode.com/problems/max-stack/discuss/108938/Java-AC-solution 2 stack的
    Stack<Integer> stack1=new Stack<>();
    Stack<Integer> stack2=new Stack<>();//开始多写了个max变量，其实就check stack2的就行了
    public MaxStack() {

    }

    public void push(int x) {
        if (stack1.isEmpty()){
            stack1.push(x);
            stack2.push(x);
            return;
        }
        int max=stack2.peek();
        if (x>=max){
            stack2.push(x);
        }

        stack1.push(x);

    }

    public int pop() {
        int temp=stack1.pop();
        if (temp==stack2.peek()){
            stack2.pop();
        }
        return temp;
    }

    public int top() {
        return stack1.peek();
    }

    public int peekMax() {
        return stack2.peek();
    }

    public int popMax() {
        int temp=stack2.pop();
        Stack<Integer> help=new Stack<>();
        while (stack1.peek()!=temp){//如果pop出来的max是在原来stack1里中间的位置，则一直pop找到第一个这样的点，pop掉，再把pop出来的东西加回去
            help.push(stack1.pop());
        }
        stack1.pop();
        while (!help.isEmpty()){//这里回调上面的push方法
            push(help.pop());
        }
        return temp;
    }
//写个用treemap的,node的操作很难写对
    //https://leetcode.com/problems/max-stack/discuss/129922/Java-simple-solution-with-strict-O(logN)-push()popMax()pop() 他这个其实是先设一个
    //dummy head，然后入栈的node一直插入到head的前一个，所以pop的时候就是拿head。pre。而不是向list那样append到后面，如果是像list那样append的话应该需要
    //维护last才行.不写了
    TreeMap<Integer, List<DoublyNode>> map=new TreeMap<>();
    DoublyNode head;
    DoublyNode last;
    public void MaxStack() {
    }
    public void push2(int x) {
        if (head ==null){
            head =new DoublyNode(x,x);
            head.next=head;
            head.pre=head;
            List<DoublyNode> list=new ArrayList<>();
            list.add(head);
            map.put(x,list);
            return;
        }
        if (!map.containsKey(x)){
            List<DoublyNode> list=new ArrayList<>();
            map.put(x,list);
        }
        DoublyNode n=new DoublyNode(x,x);


        map.get(x).add(n);
    }
    public int pop2() {
        DoublyNode temp=head;
        if (head.next==head){
            head=null;
        }else {
            head.pre.next=head.next;
            head.next.pre=head.pre;
            head=head.next;
        }
        List<DoublyNode> list=map.get(temp.key);
        list.remove(list.size()-1);
        if (list.size()==0){
            map.remove(temp.key);
        }
        return temp.key;
    }

    public int top2() {
        return head.key;
    }

    public int peekMax2() {
        return map.lastKey();
    }

    public int popMax2() {
        int temp=map.lastKey();
        List<DoublyNode> list=map.get(temp);
        DoublyNode n=list.remove(list.size()-1);
        if (list.size()==0){
            map.remove(temp);
        }
        //这个值可能在stack的头上或者中间。那么无论是头还是中间，直接砍掉应该是一样的。如果只有一个值的话，
        if (n.next==n){
            n=null;
        }else {
            n.pre.next=n.next;
            n.next.pre=n.pre;
            if (n==head){
                head=n.pre;
            }
        }

        return temp;
    }
}
