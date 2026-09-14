package SomeInterviews.Confluent;
import java.util.concurrent.CountDownLatch;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class DesignDelayedTasksScheduler {

    public static void main(String[] args) {
        int numberOfTasks = 5;
        DesignDelayedTasksScheduler taskScheduler = new DesignDelayedTasksScheduler(numberOfTasks);
        taskScheduler.start();
        try {
            for (int i = 1; i <= numberOfTasks; i++) {
                int delay = i * 1000;
                String taskName = "Task-" + i;
                Thread t=new Thread(()->{
                    System.out.println("Scheduling " + taskName + " to run in " + delay + " ms");
                });
                taskScheduler.schedule(t, delay);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        // Wait for all tasks to complete
        try {
            taskScheduler.latch.await(); // Wait until the latch counts down to zero
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        // After all tasks have finished, shutdown the taskScheduler
        taskScheduler.shutdown();
    }

    CountDownLatch latch;
    private final PriorityQueue<ScheduledTask> queue;
    private final Lock lock = new ReentrantLock();
    private final Condition con = lock.newCondition();
    private Thread schedulerThread;
    private boolean shutdown = false;
    public DesignDelayedTasksScheduler(int numberOfTasks) {
        latch = new CountDownLatch(numberOfTasks);//他这个numberoftask就是为了给latch计数的，这n个task运行完之后主线程就释放了，
        // 不是说这个queue的大小是这个，这有点奇怪，
        queue = new PriorityQueue<>(Comparator.comparingLong(a -> a.executeAt));//还有这种写法

    }
    public void start() {
        schedulerThread = new Thread(() -> {
            try {
                while (true) {
                    ScheduledTask task;
                    lock.lock();
                    try {
                        // 没有task就等
                        while (queue.isEmpty() && !shutdown) {
                            con.await();
                        }
                        // shutdown且没有剩余task
                        if (shutdown && queue.isEmpty()) {
                            break;
                        }
                        ScheduledTask next = queue.peek();
                        long now = System.currentTimeMillis();
                        long waitTime = next.executeAt - now;
                        if (waitTime > 0) {
                             // 最早的task还没到时间。等待期间如果有一个更早的新task进来，
                             //schedule()会signal，scheduler会提前醒来重新判断。
                            con.await(waitTime, TimeUnit.MILLISECONDS);
                            continue;
                        }
                        // 到时间了
                        task = queue.poll();
                    } finally {
                        lock.unlock();
                    }
                    // 不要拿着lock执行用户task
                    try {
                        task.run();
                    } finally {
                        latch.countDown();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Scheduler thread exiting");
        });
        schedulerThread.start();
    }

    public void schedule(Runnable runnable, int delayTime) throws InterruptedException {
        long executeAt = System.currentTimeMillis() + delayTime;
        ScheduledTask task = new ScheduledTask(runnable, executeAt);
        lock.lock();
        try {
            queue.offer(task);
              //新task可能比当前queue head更早，
             // 所以要唤醒scheduler重新检查。
            con.signalAll();
        } finally {
            lock.unlock();
        }
    }
    public void shutdown() {
        lock.lock();
        try {
            shutdown = true;
            con.signalAll();
        } finally {
            lock.unlock();
        }
        try {
            schedulerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private static class ScheduledTask implements Runnable {
        Runnable runnable;
        long executeAt;
        ScheduledTask(Runnable runnable, long executeAt) {
            this.runnable = runnable;
            this.executeAt = executeAt;
        }

        @Override
        public void run() {
            this.runnable.run();
        }
    }
}