package SomeInterviews.roblox;

import java.util.LinkedList;
import java.util.Queue;

public class DesignRequestRatelimiter {

    Queue<Integer> q=new LinkedList<>();
    public boolean[] rateLimit(int[] timestamps, int maxRequests, int windowSize) {

        boolean[] rs=new boolean[timestamps.length];
        for (int i=0;i<timestamps.length;i++){
            while (!q.isEmpty()&&q.peek()<timestamps[i]-windowSize+1){//这里漏了+1就错了
                q.poll();
            }
            if(q.size()>=maxRequests){
                rs[i]=false;
            }else{
                q.offer(timestamps[i]);
                rs[i]=true;
            }
        }
        return rs;

    }
    //如果允许同个timestamp有多个request
}
