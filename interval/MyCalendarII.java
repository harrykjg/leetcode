package interval;

import java.util.TreeMap;

public class MyCalendarII {
//6/24/2021不太会
    //9/8/2021还是不会
    //其实应该是像线扫描，把start和end作为线排序，这里会想按着以前pq的做法，是要先有排序好的数组，再一个个丢到pq里，pq再不断poll和offer算大小，但是这里没法
    //先有排序数组，pq也没法丢弃元素（因为book进来的interval不是按顺序的）。那么这里巧妙的用了treemap，自动帮排序，并且treemap的value用+1和-1去表示start和end
    //因此可以每次call book就从头遍历treemap，可以记录count
    //暴力法就是用2个list<int[]>，一个装overlap的的区间，一个装不overlap的区间，新来一个区间的时候就先暴力遍历overlap的list，看是否有重合，有就false，否则
    //暴力遍历不overlap的list，插入之，如果有overlap就加入overlap的list
    //https://www.cnblogs.com/grandyang/p/7968035.html
    TreeMap<Integer,Integer>map=new TreeMap<>();
    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        map.put(start,map.getOrDefault(start,0)+1);
        map.put(end,map.getOrDefault(end,0)-1);
        int count=0;
        for (int v:map.values()){
            count+=v;
            if (count>2){
                map.put(start,map.get(start)-1);
                map.put(end,map.get(end)+1);
                return false;
            }
        }
        return true;
    }


}
