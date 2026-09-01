package SomeInterviews.verkarda;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CameraLogRelayServer {
    //注意题目说的是non blocking，不是blocking,严格来说这题不能用trylock，要用ConcurrentLinkedQueue，那就直接搞定，不用reentrylock。那么就
    //很简单了，就是基本操作，没有lock和unlock
    class CameraLogServer {
        Queue<String> logqueue;
        Queue<String> cmd;
        ReentrantLock lock=new ReentrantLock();
//无生产者-消费者等待逻辑无需等待所以不用condition来实现wait notify
        public CameraLogServer() {
            // TODO: Initialize CameraLogServer
            logqueue =new LinkedList<>();
            cmd=new LinkedList<>();
        }
        //这个time只是用来print的吧，实际上q里有啥就poll啥，和time没关系
        public String getLogs(int time) {
            // TODO: Implement getLogs logic
            String rs = "";
            lock.tryLock();//如果我们将 lock.lock() 放在 try 块内部，一旦它抛出异常，程序会跳转到 catch 或 finally 块。
            //如果在 finally 块中无条件调用 lock.unlock()，而锁从未成功获取，这将导致一个巨大的错误：试图解锁一个未持有的锁，这会抛出IllegalMonitorStateException，会崩
            try {
                String command = "GET_LOGS " + time;
                cmd.offer(command);
                if (!logqueue.isEmpty()) {
                    rs = logqueue.poll();
                }
            }finally {
                lock.unlock();
            }
            return rs;
        }

        public void sendLog(String log) {
            // TODO: Implement sendLog logic
            lock.tryLock();
            try{
                logqueue.offer(log);
            }finally {
                lock.unlock();
            }

        }

        public String pollForCommand() {
            // TODO: Implement pollForCommand logic
            lock.tryLock();
            try{
                if(!cmd.isEmpty()){
                    return cmd.poll();
                }
            }finally {
                lock.unlock();;
            }

            return "";
        }
    }
}
