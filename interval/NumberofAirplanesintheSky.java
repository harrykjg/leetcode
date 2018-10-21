package interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yufengzhu on 4/18/18.
 */
public class NumberofAirplanesintheSky {
    //https://www.lintcode.com/problem/number-of-airplanes-in-the-sky/description  lintcode的
    //4/18/2018九章第二轮，还是不会
    public int countOfAirplanes(List<Interval> airplanes) {
        int rs=0;
        if(airplanes.size()==0){
            return 0;
        }
        List<point> ls=new ArrayList<>();
        for(Interval v:airplanes){
            ls.add(new point(v.start,1));
            ls.add(new point(v.end,0));
        }
        Collections.sort(ls, new Comparator<point>() {
            @Override
            public int compare(point o1, point o2) {
                if(o1.time==o2.time){
                    return o1.flag-o2.flag;
                }
                return o1.time-o2.time;
            }
        });

        int cur=0;
        for(int i=0;i<ls.size();i++){
            if(ls.get(i).flag==1){
                cur++;
            }else{
                cur--;
            }
            rs=Math.max(cur,rs);
        }
        return rs;

    }
    class Interval {
        int start, end;
        Interval( int start, int end){
        this.start = start;
        this.end = end;
        }
    }
    class point{
        int time;
        int flag;
        public point(int time,int flag){
            this.time=time;
            this.flag=flag;
        }
    }
    //9/25/2018还是不熟，注意区分用priorityqueue和不用的写法
    public int countOfAirplanes2(List<Interval> airplanes) {
        ArrayList<point> al=new ArrayList<>();
        for(Interval it:airplanes){
            al.add(new point(it.start,1));
            al.add(new point(it.end,0));
        }
        al.sort(new Comparator<point>() {
            @Override
            public int compare(point o1, point o2) {
                if(o1.time==o2.time){
                    return o1.flag-o2.flag;
                }
                return o1.time-o2.time;
            }
        });
        int count=0;
        int rs=Integer.MIN_VALUE;
        for(int i=0;i<al.size();i++){
            if(al.get(i).flag==0&&count>0){
                count--;
            }else{
                count++;
            }
            rs=Math.max(rs,count);
        }

        return rs;
    }
}
