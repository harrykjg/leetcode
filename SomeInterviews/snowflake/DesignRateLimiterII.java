package SomeInterviews.snowflake;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DesignRateLimiterII {
    //我还以为是多个rule共用一个queue，结果是每个rule各自有q
    List<Rule> rules=new ArrayList<>();
    int id;
    Lock lock=new ReentrantLock();
    long timestamp;
    public MultiRuleRateLimiter() {
        // TODO: Implement MultiRuleRateLimiter constructor logic.
    }

    public int registerRule(long ttl, int limit) {
        // TODO: Implement registerRule logic.
        lock.lock();
        try {
            Rule r = new Rule(ttl, limit);
            rules.add(r);
            int rs = id;
            id++;
            return rs;
        }finally {
            lock.unlock();
        }
    }
//说每个request是格100ms来的
    public boolean allowRequest() {
        // TODO: Implement allowRequest logic.
        lock.lock();
        try {
            long currentTime = System.currentTimeMillis();
            if (rules.isEmpty()) {
                return true;
            }
            boolean ok = true;
            for (int i = 0; i < rules.size(); i++) {
                if (!rules.get(i).allow(currentTime)) {
                    ok = false;
                    return false;
                }
            }
            if (ok) {//所有都ok才加进q
                for (Rule r : rules) {
                    r.putin(currentTime);
                }
            }
            return true;
        }finally {
            lock.unlock();
        }
    }
}
class Rule{
    long ttl;
    int limit;
    Queue<Long> q;
    public Rule(long ttl,int limit){
        this.ttl=ttl;
        this.limit=limit;
        q=new LinkedList<>();
    }
    public boolean allow(long time){
        while (!q.isEmpty()&&time-q.peek()>=ttl){
            q.poll();
        }
        if(q.size()>=limit){
            return false;
        }

        return true;
    }
    public void putin(long time){
        q.offer(time);
    }
}
