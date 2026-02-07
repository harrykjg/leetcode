package linkedin;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DesignBoundedBlockingQueue1188 {

    //lock不会用了，怎么blocking
    //https://leetcode.com/problems/design-bounded-blocking-queue/solutions/380140/java-reentrantlock-condition-solution-by-6szr/?envType=company&envId=linkedin&favoriteSlug=linkedin-six-months
    Deque<Integer> q=new LinkedList<>();
    int cap;
    Lock lock=new ReentrantLock();
    Condition full=lock.newCondition();
    Condition empty=lock.newCondition();

    public BoundedBlockingQueue(int capacity) {
        cap=capacity;
    }

    public void enqueue(int element) throws InterruptedException {

        try{
            lock.lock();//注意lock是blocking的，如果是trylock的话是立马返回，如果拿不到就是返回false，这里lock放在try外面也行
            while (q.size()==cap){
                full.await();
            }
            q.offerFirst(element);
            empty.signal();
        }finally {
            lock.unlock();;
        }
    }



    public int dequeue() throws InterruptedException {
        try{
            lock.lock();
            int rs=0;
            while (q.size()==0){
                empty.await();
            }
            rs=q.pollLast();
            full.signal();//注意不是notify,notify是object的方法，signal是condition的方法，做的东西是一样的
            return rs;
        }finally {
            lock.unlock();;
        }

    }

    public int size() {
        lock.lock();
        try{
            return q.size();
        } finally{
            lock.unlock();
        }
    }
}
