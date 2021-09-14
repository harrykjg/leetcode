import java.util.Deque;
import java.util.LinkedList;

public class MovingAveragefromDataStream {
    //7/11/2021,用deque很简单，还有circular queue的解法
    //https://leetcode.com/problems/moving-average-from-data-stream/solution/
    Deque<Integer> deque=new LinkedList<>();
    int cap;
    double sum=0;
    public MovingAveragefromDataStream(int size) {
        cap=size;
    }

    public double next(int val) {
        if (deque.size()<cap){
            deque.addLast(val);
        }
        sum+=val;
        double rs=sum/deque.size();
        if (deque.size()==cap){
            sum-=deque.removeFirst();
        }
        return rs;
    }
    //circular queue的解法。不好想
    int size, head = 0, windowSum = 0, count = 0;
    int[] queue;
    public MovingAveragefromDataStream2(int size) {
        this.size = size;
        queue = new int[size];
    }

    public double next2(int val) {
        ++count;
        // calculate the new sum by shifting the window
        int tail = head;
        windowSum = windowSum - queue[tail] + val;
        // move on to the next head
        queue[head] = val;
        head = (head + 1) % size;
        //他这里其实很巧妙.第一个进来的数其实是先经过计算，再放到index为1这来，
        //这样第三个数来的时候，他的tail就正好指向空的位置，然后算出三个数的和，再把第三个数放到index
        //0这来，那么下一个来数字时，tail也正好指向1，确实是该删的元素。直觉上是第一个来的数先放到queue的第一个位置
        return windowSum * 1.0 / Math.min(size, count);
    }
}
