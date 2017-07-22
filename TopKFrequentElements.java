import java.util.*;

/**
 * Created by 502575560 on 4/23/17.
 */
public class TopKFrequentElements {

    //自己想的,不是太顺
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> rs=new ArrayList<>();
        if(nums.length==0){
            return rs;
        }
        HashMap<Integer,struct2> map=new HashMap<>();
        PriorityQueue<struct2> pq=new PriorityQueue(k, new Comparator<struct2>() {//这里注意comparator用法这里写上范型,默认没写的那么o1和o2就只能是Object类型了
            @Override
            public int compare(struct2 o1, struct2 o2) {
                return o2.frequency-o1.frequency;//o1-o2的话就是升序了,要的是降序
            }
        });
        for(int n:nums){
           if(!map.containsKey(n)){
               struct2 s=new struct2(n,1);
               map.put(n,s);
           }else{
               map.get(n).frequency++;
           }
        }

        Iterator it=map.keySet().iterator();
        while(it.hasNext()){
            pq.offer(map.get(it.next()));
        }
        for(int i=0;i<k;i++){

            rs.add(pq.poll().k);
        }
        return rs;
    }
}
class struct2 {
    int k;
    public int frequency;
    public struct2(int a,int b){
        k=a;
        frequency=b;
    }
}