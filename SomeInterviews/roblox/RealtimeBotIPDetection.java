package SomeInterviews.roblox;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RealtimeBotIPDetection {
    private int threshold;
    private int windowSize;
    Map<String, Queue<Integer>> map;
    //感觉和rate limiter一样吧，就是多了个map
    public RealtimeBotIPDetection(int threshold,int windowSize){
        map=new HashMap<>();
        this.threshold=threshold;
        this.windowSize=windowSize;
    }
    public boolean isBot(int timestamp, String ip) {
        if(!map.containsKey(ip)){
            map.put(ip,new LinkedList<>());
        }
        Queue<Integer> q=map.get(ip);
        while (!q.isEmpty()&&q.peek()<timestamp-windowSize+1){
            q.poll();
        }
        if(q.size()+1>threshold){
            return false;
        }
        q.offer(timestamp);
        return true;
    }
}
