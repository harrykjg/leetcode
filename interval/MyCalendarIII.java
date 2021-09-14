package interval;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendarIII {
    //9/10/2021 和my calendar ii用的方法一样
    Map<Integer,Integer> map=new TreeMap<>();
    public void MyCalendarThree() {

    }

    public int book(int start, int end) {
        map.put(start,map.getOrDefault(start,0)+1);
        map.put(end,map.getOrDefault(start,0)-1);
        int count=0;
        int rs=0;
        for (int i:map.values()){
            count+=i;
            rs=Math.max(rs,count);
        }
        return rs;
    }
}
