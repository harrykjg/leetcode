package 灵神.常用数据结构.差分数组interval;

import java.util.*;

public class MyCalendarIII732 {
    static void main() {

    }
    Map<Integer,Integer> map;
    public MyCalendarIII732() {
        map=new TreeMap<>();
    }

    public int book(int startTime, int endTime) {
        map.put(startTime,map.getOrDefault(startTime,0)+1);//实际上就是线扫描吧，不用创建pair class，
        // 直接把end的value减一
        map.put(endTime,map.getOrDefault(endTime,0)-1);
        int max=0;
        int rs=0;
        for(int key:map.keySet()){
            max+=map.get(key);
            rs=Math.max(rs,max);
        }
        return rs;
    }

}
