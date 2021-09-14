import java.util.Map;
import java.util.TreeMap;

public class TimeBasedKeyValueStore {
    //8/9/2021 就是map里装treemap，treemap用timestamp做key，就可以按时间取得value了
    Map<String, TreeMap<Integer,String>> map=new TreeMap<>();
    public TimeMap() {

    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            TreeMap<Integer,String> tm=new TreeMap<>();
            map.put(key,tm);
        }
        map.get(key).put(timestamp,value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)||map.get(key).floorEntry(timestamp)==null){
            return "";
        }
        return map.get(key).floorEntry(timestamp).getValue();
    }
}
