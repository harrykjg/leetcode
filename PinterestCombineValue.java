import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 9/27/18.
 */
public class PinterestCombineValue {
    public static void main(String[] args){
        PinterestCombineValue pc=new PinterestCombineValue();
        List<String> ls=new ArrayList<>();
        ls.add("ab,0,1");
        ls.add("bc,0,2");
        ls.add("ab,30,1");
        ls.add("bc,30,2");
        ls.add("ab,60,1");
        ls.add("bc,60,2");
        ls.add("ab,90,1");
        ls.add("bc,90,2");
        pc.function(ls,60);
    }

    //pinterest面经
    /*
    input是数组，数组里的每个对象包含name, timestamp(long value)和value
    要求跟将相同name的并且落在同一个时间区间内的value 求和。
    values = [
      ("ab", 0, 1.0),
      ("bc", 0, 2.0),
      ("ab", 30, 1.0),
      ("bc", 30, 2.0),
      ("ab", 60, 1.0),
      ("bc", 60, 2.0),
      ("ab", 90, 1.0),
      ("bc", 90, 2.0),

    给定一个区间是60，那时间区间就是[0, 60), [60, 120), [120, 180)诸如此类
    要求结果是
    [
    (“ab”, 0, 2.0),
    (“bc”, 0, 4.0),
    (“ab”, 60, 2.0),
    (“bc”, 60, 4.0)

     */
    public List<String> function(List<String> logs,int time){
        List<String> rs=new ArrayList<>();
        HashMap<String,HashMap<Long,Integer>> map=new HashMap<>();
        for(String l:logs){
            String[] ll=l.split(",");
            String name=ll[0];
            Long ts=Long.parseLong(ll[1]);
            if(!map.containsKey(name)){
                HashMap<Long,Integer> m=new HashMap<>();
                map.put(name,m);
            }
            if(!map.get(name).containsKey(ts/time)){
                map.get(name).put(ts/time,Integer.parseInt(ll[2]));
            }else{
                map.get(name).put(ts/time,map.get(name).get(ts/time)+Integer.parseInt(ll[2]));
            }
        }
        for(String s:map.keySet()){
            for(Long l:map.get(s).keySet()){
                String temp=s;
                temp+=","+l+","+map.get(s).get(l);
                rs.add(temp);
            }
        }

        return rs;
    }

}

