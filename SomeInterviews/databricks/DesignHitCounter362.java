package SomeInterviews.databricks;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DesignHitCounter362 {

    //3/22/2026 只能想到用Linkedhashmap，答案是用queue，这样可以把过期的删掉。我用linkedhashmap的话，也应该清理旧数据
    // gpt说了几种优化，由于是只要最近的300个时间点，因此旧的可以删掉，那么就可以用长度为300的数组。不好想
    Map<Integer,Integer> map;
    public HitCounter() {
        map=new LinkedHashMap<>();
    }

    public void hit(int timestamp) {
        map.put(timestamp,map.getOrDefault(timestamp,0)+1);
    }

    public int getHits(int timestamp) {
        int start=timestamp-300+1;//是包括timestamp自己的，因此要从start+1开始到start+300，举个例子
        int rs=0;
        Iterator<Integer> it=map.keySet().iterator();
        while (it.hasNext()){
            int key=it.next();
            if(key<start){
                it.remove();//注意如果用map。remove就会concurrentmodificationexception，应该用it。remove
                continue;
            }
            rs+=map.get(key);
        }
        return rs;
    }
    /*
    固定300的优化
    class HitCounter {
    private int[] times;
    private int[] hits;

    public HitCounter() {
        times = new int[300];
        hits = new int[300];
    }

    public void hit(int timestamp) {
        int idx = timestamp % 300; //原来是这样处理
        if (times[idx] != timestamp) {
            times[idx] = timestamp;//注意不是存timestamp%300，是存timestamp本身，mod数只是index
            hits[idx] = 1;
        } else {
            hits[idx]++;
        }
    }

    public int getHits(int timestamp) {
        int total = 0;
        for (int i = 0; i < 300; i++) {//比如335有了hit，现在get630，则630-335<300，因此可以get
            if (timestamp - times[i] < 300) {
                total += hits[i];
            }
        }
        return total;
    }
}
     */

    //或者变形体是时间区间不固定的
}
