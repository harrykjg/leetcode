import java.util.*;

/**
 * Created by yufengzhu on 8/8/18.
 */
public class TopKFrequentWords {
    //自己想的就是map加priority queue,这里用了新的写法，就是priority queue里放的是entry类型，否则就要新建一个class用来存count和string了
    //https://leetcode.com/problems/top-k-frequent-words/discuss/108346/My-simple-Java-solution-using-HashMap-and-PriorityQueue-O(nlogk)-time-and-O(n)-space  和我写的几乎一样
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String,Integer> map=new HashMap<>();
        for(String s:words){
            map.put(s,map.getOrDefault(s,0)+1);
        }
        PriorityQueue<Map.Entry<String,Integer>> pq=new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue()==o2.getValue()){

                    return o1.getKey().compareTo(o2.getKey());//注意string是这样比较大小的，相减是不行的
                }
                return o2.getValue()-o1.getValue();
            }
        });
        for(Map.Entry<String,Integer> en:map.entrySet()){
            pq.offer(en);
        }
        int i=0;
        List<String> rs=new ArrayList<>();
        while (i<k){
            rs.add(pq.poll().getKey());
            i++;
        }
        return rs;
    }
}
