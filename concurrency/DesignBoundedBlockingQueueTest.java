package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class DesignBoundedBlockingQueueTest {

    public static void main(String[] args) {
        DesignBoundedBlockingQueue q = new DesignBoundedBlockingQueue(3);
        deqThread t1=new deqThread(q);
        enqThread t2=new enqThread(q,1);
        enqThread t3=new enqThread(q,2);
        enqThread t4=new enqThread(q,3);
        deqThread t5=new deqThread(q);
        deqThread t6=new deqThread(q);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

    }

}
//https://leetcode.com/problems/design-bounded-blocking-queue/discuss/787067/3-different-imps-JAVA-monitor-lock-reentrant-lock-semaphore
class deqThread extends Thread{
    DesignBoundedBlockingQueue q;
    public deqThread(DesignBoundedBlockingQueue q){
        this.q=q;
    }
    @Override
    public void run(){
        try {
            q.dequeue();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class enqThread extends Thread{
    DesignBoundedBlockingQueue q;
    int n;
    public enqThread(DesignBoundedBlockingQueue q, int n){
        this.q=q;
        this.n=n;
    }
    @Override
    public void run(){
        try {
            q.enqueue(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DesignBoundedBlockingQueue {

//    Queue<Integer> q;
//    int cap;
//
//    public DesignBoundedBlockingQueue(int capacity) {
//        cap = capacity;
//        q = new LinkedList<>();
//    }
//
//    public void enqueue(int element) throws InterruptedException {
//        synchronized (q) {
//
//                while (q.size() >= cap) {
//                    System.out.println("full");
//                    q.wait();
//                }
//
//            q.offer(element);
//            System.out.println("ppppp"+element);
//            q.notifyAll();
//        }
//    }
//
//    public int dequeue() throws InterruptedException {
//        synchronized (q) {//为什么这里写q就不对？
//            while (q.isEmpty()) {
//                System.out.println("empty");
//
//                q.wait();
//            }
//
//
//            int rs = q.poll();
//            System.out.println("ddddd"+rs);
//            q.notifyAll();
//            return rs;
//        }
//    }
//
//    public int size() {
//        synchronized (this) {
//            return q.size();
//        }
//    }

    //----------------------------------------------------------------------------------------------------------
//    ReentrantLock lock=new ReentrantLock();
//    Condition producer=lock.newCondition();//写成producer比写成什么full empty的好理解
//    Condition consumer=lock.newCondition();
//    Queue<Integer> q;
//    int cap;
//    public DesignBoundedBlockingQueue(int capacity) {
//        q=new LinkedList<>();
//        cap=capacity;
//    }
//
//    public void enqueue(int element) throws InterruptedException {
////        lock.lockInterruptibly();//ReentrantLock.lockInterruptibly允许在等待时由其它线程调用等待线程的Thread.interrupt方法来中断等待线程的
////        等待而直接返回，这时不用获取锁，而会抛出一个InterruptedException。 ReentrantLock.lock方法不允许Thread.interrupt中断,即使检测
////        到Thread.isInterrupted,一样会继续尝试获取锁，失败则继续休眠。只是在最后获取锁成功后再把当前线程置为interrupted状态,然后再中断线程。
//        try {
//            lock.lock();// lock拿不到锁会一直等待。tryLock是去尝试，拿不到就返回false，拿到返回true。
//            while (q.size()==cap){
//                producer.await();//await是condition的方法而wait是object的方法，两者差不多。
//            }
//            q.offer(element);
//
//        }catch (Exception ex){
//
//        }
//        finally {
//            consumer.signal();
//            lock.unlock();
//        }
//
//    }
//
//    public int dequeue() throws InterruptedException {
//        try {
//            lock.lock();
//            while (q.isEmpty()){
//                consumer.await();
//            }
//            int rs=q.poll();
//            producer.signal();
//            return rs;
//        }
//        finally {
//            lock.unlock();
//        }
//
//    }
//
//    public int size() {
//        lock.lock();
//        int size=q.size();
//        lock.unlock();
//        return size;
//    }

    //https://leetcode.com/problems/design-bounded-blocking-queue/discuss/402284/Java-2-semaphores-and-thread-safe-queue
    //他这个用2个semaphone就行了，用三个的好想不行
    Semaphore producer;
    Semaphore consumer;
    Queue<Integer> q;
    int cap;
    public DesignBoundedBlockingQueue(int capacity) {
        q=new LinkedList<>();
        cap=capacity;
        producer =new Semaphore(capacity);
        consumer=new Semaphore(0);
    }

    public void enqueue(int element) throws InterruptedException {
        producer.acquire();
        q.offer(element);
        consumer.release();

    }

    public int dequeue() throws InterruptedException {
        consumer.acquire();
        int rs=q.poll();
        producer.release();
        return rs;

    }

    public int size() {

        return q.size();
    }
}