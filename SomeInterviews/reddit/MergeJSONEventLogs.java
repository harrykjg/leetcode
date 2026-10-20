package SomeInterviews.reddit;

import java.util.*;

public class MergeJSONEventLogs {

    class HeapEntry {
        int time;
        String serverId;
        Map<String, Object> event;

        HeapEntry(int time, String serverId, Map<String, Object> event) {
            this.time = time;
            this.serverId = serverId;
            this.event = event;
        }
    }
    //开始想的是先拿出第一个server的logs，然后别的都insert进来,但是貌似题目就提供了heapentry这个class那就引导你用pq？
    //用pq也挺巧妙，是把每一个server的第一个事件放进pq里，pq poll出来就是按时间顺序的，然后再把这个server的下一个log放进pq，
    //那么你怎么得到下一个log呢？就是要用一个map记录哪个server走到哪个index了，然后logs.get(serverid).get(next)就行了
    public List<String> mergeLogs(Map<String, List<Map<String, Object>>> logs) {
        // TODO: Implement mergeLogs logic.
        List<String> rs=new ArrayList<>();
        PriorityQueue<HeapEntry> pq=new PriorityQueue<>((a,b)->Integer.compare(a.time,b.time));
        Map<String,Integer> index=new HashMap<>();
        for(String server:logs.keySet()){
            index.put(server,0);
            List<Map<String,Object>> ServerFistLog=logs.get(server);
            if(ServerFistLog.isEmpty()){
                continue;//这个容易漏，就是没有log，否则后面.get(0)会报错
            }
            HeapEntry ent=new HeapEntry((int)ServerFistLog.get(0).get("time"),server,ServerFistLog.get(0));
            pq.offer(ent);
        }
        while (!pq.isEmpty()){
            HeapEntry h=pq.poll();
            String log=formatEvent(h.time,h.serverId,h.event);
            rs.add(log);
            int next=index.get(h.serverId)+1;
            if(next<logs.get(h.serverId).size()){
                Map<String, Object> event=logs.get(h.serverId).get(next);
                int time=(int)event.get("time");
                HeapEntry ent=new HeapEntry(time,h.serverId,event);
                pq.offer(ent);
                index.put(h.serverId,next);
            }
        }
        return rs;
    }
    private String formatEvent(int time, String serverId, Map<String, Object> event) {
        String type = (String) event.get("type");
        String target = (String) event.get("target");

        if (target != null) {
            return time + ": " + serverId + " " + type + " " + target;
        } else {
            return time + ": " + serverId + " " + type;
        }
    }

}
