package interval;

import java.util.*;

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
                    return o1.flag-o2.flag;//这里是要吧end的放在前头，所以说o1.flag是start的话，要后在后面，所以o1.flag-o2.flag>0的话o1才大
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

    //05/21/2020,用pq做错了一些，pq只care谁先end，如果按谁start排序就错了，而array是要看谁先start才行
    public int countOfAirplanes3(List<Interval> airplanes) {
        if(airplanes.size()==0){
            return 0;
        }
        PriorityQueue<Interval> pq=new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {//
                return o1.end-o2.end;
            }
        });
        Collections.sort(airplanes, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if(o1.start==o2.start){
                    return o1.end-o2.end;
                }
                return o1.end-o2.end;
            }
        });
        int rs=0;
        for(int i=0;i<airplanes.size();i++){
            while (!pq.isEmpty()&&pq.peek().end<=airplanes.get(i).start){
                pq.poll();
            }
            pq.offer(airplanes.get(i));
            rs=Math.max(rs,pq.size());
        }
        return rs;
    }

}
