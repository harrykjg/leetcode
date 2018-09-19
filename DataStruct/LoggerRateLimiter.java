package DataStruct;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 8/19/18.
 */
public class LoggerRateLimiter {
    HashMap<String,Integer> map=new HashMap<>();
    public LoggerRateLimiter() {

    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        if(!map.containsKey(message)){
            map.put(message,1);
            return true;
        }

        if(timestamp-map.get(message)>10){
            map.put(message,timestamp);
            return true;
        }
        return false;
    }
    //9/12/2018,之前写的可以accept但是map的size会无限增加
    //https://leetcode.com/problems/logger-rate-limiter/discuss/83284/A-solution-that-only-keeps-part-of-the-messages 不知道他的set是拿来干嘛的
    //又结合uber的面经，http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=437519
    /*
    while(!queue.isEmpty() && timestamp-queue.peek()>1){
        queue.poll();-google 1point3acres
     }
    if(queue.size()<qps){
        queue.add(timestamp);
        return true;
    }else{
        return false;
     }
     */
    // 这个是结合他们的想法自己写的,和题目的意思不一样，这个是限制qps，而lc的是限制前后两个request必须相隔10秒
    class LoggerRateLimiter2{

        HashMap<String,Queue<Integer>> map=new HashMap<>();
        public LoggerRateLimiter2() {

        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            if(!map.containsKey(message)){
                Queue<Integer> q=new LinkedList<>();
                q.offer(timestamp);
                map.put(message,q);
                return true;
            }
            Queue<Integer> q=map.get(message);
            while (!q.isEmpty()&&timestamp-q.peek()>=1){//这里是1秒的意思，就是一秒之前的东西我都不需要了，因为是限制qps
                q.poll();
            }
            if(q.size()>=5){//假设qps是5
                return false;
            }
            q.offer(timestamp);
            return true;
        }
    }
}
