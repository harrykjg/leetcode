import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 9/23/18.
 */
public class DesignHitCounter {
    //这样用q有个问题就是如果没有人gethits的话q会越来越大，可以用priprityqueue解决，就是给pq设个size，用最小q，装的就是timestamp最大的即最近5分钟的值
    Queue<Integer> q=new LinkedList<>();
    public DesignHitCounter() {

    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        q.offer(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!q.isEmpty()&&q.peek()+300<=timestamp){//这个开始少写了个=号，要想清楚
            q.poll();
        }
        return q.size();
    }
}
