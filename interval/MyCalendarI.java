package interval;

import java.util.*;

public class MyCalendarI {

    //https://leetcode.com/problems/my-calendar-i/discuss/109475/JavaC%2B%2B-Clean-Code-with-Explanation treemap的floor和celling注意
    //6/24/2021,还以为和以前的meeting room啥的都一样，结果不是，这里没法先对进来的meeting排序所以用pq以前的检测再poll出来的方法不行，而且订不到的meeting
    //不会加进来
    //如果这题不让用treemap的话，就得用维护一个sorted list，每进来一个meeting就二分搜索到他该被insert的位置，然后检测前后meeting又没重复
    TreeMap<Integer,Integer> map;
    public MyCalendarI() {
        map=new TreeMap<>();
    }

    public boolean book(int start, int end) {//用start作为key，因为treemap就是按key来默认升序排序的

        if (map.ceilingEntry(start)!=null){
            if (map.ceilingEntry(start).getKey()<end){
                return false;
            }
        }
        if (map.floorEntry(start)!=null){//开始想的这样不行，因为floor返回的interval他的end不一定是最接近当前start的啊，可能有一个大的interval，他的
            if (map.floorEntry(start).getValue()>start){//start很小，但是end很接近当前start，而floor返回的interval的end其实距离当前start还有距离。
                return false;                         //其实是不存在的，因为这样的话就说明前面overlap了，而overlap的不会被插入
            }
        }
        map.put(start,end);
        return true;
    }

    //9/7/2021 没法用numberofairplanesinthesky那种先扫描，因为没有输入数组给你排序,也用不了那题的第一个方法

}
