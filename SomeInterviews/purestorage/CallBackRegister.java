package SomeInterviews.purestorage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

public class CallBackRegister {
//https://leetcode.com/discuss/post/1030039/pure-storage-technical-round-member-of-t-tfz6/
    Queue<Runnable> q;
    boolean executing;
    ReentrantLock re;
    public CallBackRegister() {
        q=new LinkedList<>();
        re=new ReentrantLock();
    }
    void reg_cb(Runnable f){
//        re.lockInterruptibly();//等到时如果被interrupt会抛异常
//        re.tryLock();//没拿到锁立即返回false
        re.lock();//为啥这里不放try里呢？因为一旦进入try则肯定要执行finally，那里unlock的话可能出现情况就是lock的时候出问题了（runtimeexception）而锁
        //也没获取到，则到了finally又unlock，自然也会报错
        boolean flag=false;
        try{
            if(executing){
                q.offer(f);
                flag=true;
            }
        }catch (Exception ex){
            //直接catch不写也行，catch不是必须得
        }finally {
            re.unlock();
        }
        //注意用的是local的flag而不是全局的executing，因为那样的话又得得到lock才行
        if(!flag){//gpt说特意把run放在lock外面，因为原则上这个lock只保护executing的状态，不包含callback方法，否则可能占用锁的时间过长，
            f.run();  //而且callback可能又去获得别的锁或者这个锁，那有可能成为死锁。这里这样写是符合题目要求的，就是只是保证executing的时候不执行，之后再执行
        }
    }
    void start(){
        re.lock();
        try {
            executing = true;
        }
        finally {
            re.unlock();
        }
    }
    void complete(){
        re.lock();
        Queue<Runnable> nq=new LinkedList<>();
        try {
            executing=false;
            //注意这里拿另一个新queue指向这个共享q，再把共享q设成新的。否则在后面你poll共享q的时候是没lock的就又有concurrent问题
            nq=q;
            q=new LinkedList<>();

        }finally {
            re.unlock();
        }
        while (!nq.isEmpty()){//放try 外边，和上面的原因一样
            Runnable f=nq.poll();
            f.run();
        }
    }

}
