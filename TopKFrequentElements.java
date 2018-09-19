import java.util.*;

/**
 * Created by 502575560 on 4/23/17.
 */
public class TopKFrequentElements {
    public static void main(String[] args){
        TopKFrequentElements tf=new TopKFrequentElements();
        int[] nums={1,2};
        tf.topKFrequent2(nums,2);
    }

    //自己想的,不是太顺
    //https://leetcode.com/problems/top-k-frequent-elements/discuss/81635/3-Java-Solution-using-Array-MaxHeap-TreeMap
    //https://leetcode.com/problems/top-k-frequent-elements/discuss/81602/Java-O(n)-Solution-Bucket-Sort  这个是bucket sort复杂度更低
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

    //9/3/2018想的是用quickselect，那么要选top k个最frequent的elememnt的话就是要进行k次quickselect。但是最好的算法还是桶排序
    public List<Integer> topKFrequent2(int[] nums, int k) {
        List<Integer> rs=new ArrayList<>();
        HashMap<Integer,struct2> map=new HashMap<>();
        List<struct2> ls=new ArrayList<>();

        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                struct2 st=new struct2(nums[i],1);
                map.put(nums[i],st);
            }else{
                map.get(nums[i]).frequency++;
            }
        }

        ls.addAll(map.values());
        for(int i=0;i<k;i++){
            quickselect(rs,i,0,ls.size()-1,ls);
        }
        return rs;
    }
    void quickselect(List<Integer> rs,int k,int b,int e,List<struct2> ls){
        struct2 key=ls.get(b);

        int l=b;
        int r=e;
        while (l<r){
            while (r>l&&ls.get(r).frequency<=key.frequency){//开始这里少写等号就错了，比较恶心，貌似一般快速排序都是这里有等号，下面的while没有等号
                r--;
            }
            if(l<r){
                ls.set(l,ls.get(r));
                l++;
            }
            while (l<r&&ls.get(l).frequency>key.frequency){
                l++;
            }
            if(l<r){
                ls.set(r,ls.get(l));
                r--;
            }
        }
        ls.set(l,key);
        if(l==k){
            rs.add(ls.get(l).k);
            return;
        }
        if(l<k){
            quickselect(rs,k,l+1,e,ls);
        }else {
            quickselect(rs,k,b,l-1,ls);
        }

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