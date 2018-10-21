import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yufengzhu on 10/2/18.
 */
public class producerComsumerProblem {

    //https://blog.csdn.net/u010983881/article/details/78554671

    public static void main(String[] args){
        /*
        //这个是用blocking queue的
        BlockingQueue<Integer> bq=new ArrayBlockingQueue<Integer>(10);

        Thread t1=new Thread(new comsumer(bq));
        Thread p1=new Thread(new producer(bq,10));
        Thread t2=new Thread(new comsumer(bq));
        Thread p2=new Thread(new producer(bq,10));
        Thread t3=new Thread(new comsumer(bq));
        Thread p3=new Thread(new producer(bq,10));
        Thread t4=new Thread(new comsumer(bq));
        Thread p4=new Thread(new producer(bq,10));
        Thread t5=new Thread(new comsumer(bq));
        Thread p5=new Thread(new producer(bq,10));

        t1.start();
        p1.start();
        t2.start();
        p2.start();
        t3.start();
        p3.start();
        t4.start();
        p4.start();
        t5.start();
        p5.start();
        */

        //这个是用synchnize wait和notifyall的
        Queue<Integer> q=new LinkedList<>();
        Thread t1=new Thread(new comsumer2(q));
        Thread p1=new Thread(new producer2(q,10));
        Thread t2=new Thread(new comsumer2(q));
        Thread p2=new Thread(new producer2(q,10));
        Thread t3=new Thread(new comsumer2(q));
        Thread p3=new Thread(new producer2(q,10));
        Thread t4=new Thread(new comsumer2(q));
        Thread p4=new Thread(new producer2(q,10));
        Thread t5=new Thread(new comsumer2(q));
        Thread p5=new Thread(new producer2(q,10));

        t1.start();
        p1.start();
        t2.start();
        p2.start();
        t3.start();
        p3.start();
        t4.start();
        p4.start();
        t5.start();
        p5.start();
    }

}
class comsumer implements Runnable{
    BlockingQueue<Integer> bq;
    public comsumer(BlockingQueue<Integer> bq){
        this.bq=bq;
    }
    public void consume(){
//        while (true){
            try {
                int cur=bq.take();
                System.out.println("cccccc");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        }
    }

    @Override
    public void run() {
        consume();
    }
}

class producer implements Runnable{
    int cap;
    BlockingQueue<Integer> bq;
    public producer(BlockingQueue<Integer> bq,int cap){
        this.cap=cap;
        this.bq=bq;
    }
    public void produce(){

//        while (true) {
            try {
                bq.put(1);
                System.out.println("pppppp");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        }

    }

    @Override
    public void run() {
        produce();
    }
}

class comsumer2 implements Runnable{
    Queue<Integer> bq;
    public comsumer2(Queue<Integer> bq){
        this.bq=bq;
    }
    public void consume(){
        while (true){
            synchronized (bq){
                while (bq.isEmpty()){
                    try {
                        bq.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int cur=bq.poll();
                System.out.println("ccccc");
                bq.notifyAll();
            }
        }
    }

    @Override
    public void run() {
        consume();
    }
}

class producer2 implements Runnable{
    int cap;
    Queue<Integer> q;
    public producer2(Queue<Integer> q,int cap){
        this.cap=cap;
        this.q=q;
    }
    public void produce(){

        while (true) {
            synchronized (q){
                while (q.size()>=cap){
                    try {
                        q.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                q.offer(1);
                System.out.println("ppppp");
                q.notifyAll();
            }
        }

    }

    @Override
    public void run() {
        produce();
    }
}