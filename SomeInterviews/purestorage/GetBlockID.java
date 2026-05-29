package SomeInterviews.purestorage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class GetBlockID {
    static void main() {
        GetBlockID gb=new GetBlockID();
    }


    /*
     given get_block_id, implement get_one_id
提供int[] get_block_id(int count): 返回长度为count的id数组。
实现int get_one_id()。
优化方向：提升performance。
版本1：每次现调用get_block_id(1)获取，热身版本。
版本2：批量获取，预存着。
每次批量获取多少合适呢？这样有什么问题？如何优化？
版本3：开一个线程专门获取id，保证get_one_id可以源源不断的平滑获取id，生产者消费者问题变形。


新题，已知一个叫get_ids()的API能够耗时1s并返回100个各不相同的id（第二次call返回的和第一次的也不会有任何重复），有个待实现的函数叫get_one_id()，每秒最多被call 100次，每次call要能返回一个新的id。题目就是利用get_ids()实现get_one_id()，follow up是保证每次call get_one_id()不能等待超过1s

     */
    Queue<Integer> q=new LinkedList<>();

    public GetBlockID(){

        Thread fill=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    enqueue();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        fill.start();
    }

    public int getOneId(){
        if(q.isEmpty()){
            int[] a=getBlock(100);
            for (int i:a){
                q.offer(i);
            }
        }
        return q.poll();
    }
    public int[] getBlock(int count){
        return new int[]{};
    }

    //多线程版,参考gpt
    Queue<Integer> q2=new LinkedList<>();
    ReentrantLock rl=new ReentrantLock();
    Condition producer= rl.newCondition();
    Condition consumer=rl.newCondition();
    public int getOneId2() throws InterruptedException{
        rl.lock();
        int rs=0;
        try{
            while (q.isEmpty()){
                consumer.await();
            }
            rs=q.poll();
            if(q.size()<100){
                producer.signal();//原来想着enqueue线程会一直等着啊所以不用signal，其实不对，那里如果await了，则需要有人唤醒，否则一直睡着
            }
        }finally {
            rl.unlock();
        }
        return rs;
    }
    public void enqueue() throws InterruptedException{
        int[] a=getBlock(100);//如果放在while里面则可能每次call 100但是又拿不到锁所以不好吧
        while (true){
            rl.lock();//lock是放try外面还是里面？
            try {
                while (q.size()>=100){
                    producer.await();
                }
                for (int i:a){//那这个offer肯定只能在hold lock的情况下搞了
                    q.offer(i);
                }
                consumer.signal();//通知consumer
            }finally {
                rl.unlock();
            }
        }
    }


}
