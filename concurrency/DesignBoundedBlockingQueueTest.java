package concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class DesignBoundedBlockingQueue implements Runnable {
    DesignBoundedBlockingQueueTest db = new DesignBoundedBlockingQueueTest(2);

    public static void main(String[] args) {
        Thread t1=new Thread()
    }


    @Override
    public void run() {
        db.enqueue(1);
    }
}
class DesignBoundedBlockingQueueTest {

    Queue<Integer> q;
    int cap;

    public DesignBoundedBlockingQueueTest(int capacity) {
        cap = capacity;
        q = new LinkedList<>();
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized (q) {
            try {
                while (q.size() >= cap) {
                    wait();
                }
            } catch (Exception ex) {
                notifyAll();
                throw ex;
            }
            q.offer(element);
            notifyAll();
        }
    }

    public int dequeue() throws InterruptedException {
        synchronized (q) {
            try {
                while (q.isEmpty()) {
                    wait();
                }
            } catch (Exception ex) {
                notifyAll();
                throw ex;
            }

            int rs = q.poll();
            notifyAll();
            return rs;
        }
    }

    public int size() {
        synchronized (q) {
            return cap;
        }
    }
}