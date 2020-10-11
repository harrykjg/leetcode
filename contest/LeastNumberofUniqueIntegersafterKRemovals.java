package contest;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LeastNumberofUniqueIntegersafterKRemovals {
    public static void main(String[] args){
        int[] a=new int[]{2,1,1,3,3,3};
        LeastNumberofUniqueIntegersafterKRemovals ln=new LeastNumberofUniqueIntegersafterKRemovals();
        ln.findLeastNumOfUniqueInts(a,3);
    }
    public int findLeastNumOfUniqueInts(int[] arr, int k) {//写的有点不顺，就是map记录每个元素和出现的次数，然后用pq用某个元素出现的次数按升序排列
        Map<Integer,Integer> map=new HashMap();//然后从pq的第一个entry开始，看k是否能删完这个元素出现的次数，能删完就把它从map里remove，直到用尽k，返回map的size就行了
        PriorityQueue<Map.Entry<Integer,Integer>> pq=new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });
        for(int i=0;i<arr.length;i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i],1);
            }else{
                map.put(arr[i],map.get(arr[i])+1);
            }
        }
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            pq.offer(entry);
        }
        int count=0;
        while (count<k){
            Map.Entry<Integer,Integer> cur=pq.poll();
            if(count+cur.getValue()<=k){
                map.remove(cur.getKey());
            }
            count+=cur.getValue();
        }
        return map.keySet().size();

    }
}
