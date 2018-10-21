import java.util.*;

/**
 * Created by yufengzhu on 9/26/18.
 */
public class PinterestUserBehavior {
    public static void main(String[]args){
        PinterestUserBehavior pu=new PinterestUserBehavior();
        Log log1=new Log(1,1,"p");
        Log log2=new Log(3,1,"s");
        Log log3=new Log(4,2,"s");
        Log log4=new Log(5,1,"s");
        Log log5=new Log(6,3,"p");
        Log log6=new Log(7,2,"b");
        Log log7=new Log(8,1,"p");
        Log log8=new Log(9,2,"b");
        Log log9=new Log(10,3,"b");
        Log log10=new Log(11,3,"p");
        Log log11=new Log(12,3,"b");
        Log log12=new Log(13,1,"s");
        Log log13=new Log(14,1,"s");
        List<Log> ls=new ArrayList<>();
        ls.add(log1);
        ls.add(log2);
        ls.add(log3);
        ls.add(log4);
        ls.add(log5);
        ls.add(log6);
        ls.add(log7);
        ls.add(log8);
        ls.add(log9);
        ls.add(log10);
        ls.add(log11);
        ls.add(log12);
        ls.add(log13);
        pu.find(ls);
    }
    //pinterest面经
    /*
    给出一个日志文件，文件里有时间戳，用户ID和用户的操作（比如 PIN， Search，Board），然后找出频率最高的三个相连的操作序列（相当于是找用户行为）。
没有太复杂的逻辑，用HashMap既可实现。讨论各种边界问题并且跑了测试用例之后，讨论到如果日志文件特别大该怎样处理。思路大概就是map reduce的意思，最后有讨论到会有用户的操
作被截断，我给出的结果是merge 结果集，但是理论上如果数据量够大，merge结果集也会出现内存溢出的问题，后来想明白，其实只需要取两个相邻结果集里把前一个的尾merge到后一个的头上，后一个
的头merge到前一个的尾，只需要进行几个merge操作就好了。
     */

    public List<String> find(List<Log> logs){
        List<String> rs=new ArrayList<>();
        HashMap<Integer,List<String>> map=new HashMap<>();
        for(int i=0;i<logs.size();i++){
            if(!map.containsKey(logs.get(i).id)){
                ArrayList<String> al=new ArrayList<>();
                map.put(logs.get(i).id,al);
            }
            map.get(logs.get(i).id).add(logs.get(i).behav);
        }
        HashMap<String,Integer> behaviours=new HashMap<>();
        PriorityQueue<pair> pq=new PriorityQueue<>(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return o1.count-o2.count;
            }
        });
        for(List<String> al:map.values()){
            for(int i=0;i<al.size();i++){
                String beh="";
                if(i+2<al.size()){
                    beh+=al.get(i)+al.get(i+1)+al.get(i+2);
                }else{
                    continue;
                }
                if(!behaviours.containsKey(beh)){
                    behaviours.put(beh,1);
                }else{
                    behaviours.put(beh,behaviours.get(beh)+1);
                }
                if(pq.size()<3){
                    pq.offer(new pair(beh,behaviours.get(beh)));
                }else{
                    if(pq.peek().count<behaviours.get(beh)){
                        pq.poll();
                        pq.offer(new pair(beh,behaviours.get(beh)));
                    }
                }
            }
        }
        while (!pq.isEmpty()){
            rs.add(pq.poll().beh);
        }
        return rs;
    }
}
class pair{
    String beh;
    int count;
    public pair(String beh,int count){
        this.beh=beh;
        this.count=count;
    }
}
class Log{
    int time;
    int id;
    String behav;
    public Log(int time,int id,String s){
        this.time=time;
        this.id=id;
        this.behav=s;
    }
}