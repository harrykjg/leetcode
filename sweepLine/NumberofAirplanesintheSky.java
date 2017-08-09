package sweepLine;

import sun.jvm.hotspot.utilities.Interval;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 502575560 on 7/29/17.
 */
//leetcode 没有
public class NumberofAirplanesintheSky {
    //http://blog.csdn.net/feliciafay/article/details/49061801
    //http://www.cnblogs.com/beiyeqingteng/p/5690066.html  //排序的算法神奇,不好想到
    //http://www.jiuzhang.com/solutions/number-of-airplanes-in-the-sky/ //排序的
    public int countOfAirplanes(List<Interval> airplanes) {
        HashMap <Integer,Integer> map=new HashMap<>();
        int rs=0;
        for(Interval it:airplanes){
            int i=it.start;
            for(;i<it.end;i++){
                if(!map.containsKey(i)){
                    map.put(i,1);
                    rs=Math.max(1,rs);
                }else {
                    rs=Math.max(rs,map.get(i)+1);
                    map.put(i,map.get(i)+1);
                }
            }
        }
        return rs;
    }
    class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
