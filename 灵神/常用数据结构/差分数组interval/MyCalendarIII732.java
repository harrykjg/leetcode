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
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            max+= entry.getValue();
            rs=Math.max(rs,max);
        }
        return rs;
    }

    //3/15/2026 还是想的不是太明白，以为用set就行做线扫描，其实不行，因为如果有两个相同起点的set只按着是1 算所以错,
    //但这样还是超时，可能像第一次那样不用pair就行吧
    TreeMap<Pair,Integer> map2;
    public MyCalendarIII7323() {
        map2=new TreeMap<>((a,b)->{
            if(a.time==b.time){
                return b.end-a.end;
            }
            return a.time-b.time;
        });
    }
    public int book2(int startTime, int endTime) {
        int rs=0;
        Pair start=new Pair(startTime,0);
        Pair end=new Pair(endTime,1);
        map2.put(start,map2.getOrDefault(start,0)+1);
        map2.put(end,map2.getOrDefault(end,0)+1);
        int count=0;
        for (Pair p:map2.keySet()){
            System.out.println(p.time);
            if(p.end==0){
                count+=map2.get(p);
            }else{
                count-=map2.get(p);
            }
            rs=Math.max(rs,count);
        }
        return rs;

    }

}
class Pair{
    int time;
    int end;
    public Pair(int time,int end){
        this.time=time;
        this.end=end;
    }
}
