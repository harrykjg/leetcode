import java.util.*;

/**
 * Created by yufengzhu on 7/6/18.
 */
public class LFUCache {//关键是怎么记录谁是最不recently被访问的,看别人的代码吧
    //https://leetcode.com/problems/lfu-cache/discuss/94521/JAVA-O(1)-very-easy-solution-using-3-HashMaps-and-LinkedHashSet 关键和巧妙的地方是用的另一个map去记录
    //最不recently被访问的key

    HashMap<Integer,Integer> map;
    HashMap<Integer,Integer> frequency;
    HashMap<Integer,LinkedHashSet<Integer>> minFrequency;
    int cap;
    int minFreq=0;
    public LFUCache(int capacity) {
        map=new HashMap<>();
        minFrequency=new HashMap<>();
        frequency=new HashMap<>();
        map=new HashMap<>();
        cap=capacity;

    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        int tempFreq=frequency.get(key);
        frequency.put(key,tempFreq+1);

        minFrequency.get(tempFreq).remove(key);//minFrequency也要删除这个freuqncy
        //注意容易漏，而且不好理解，如果这个tempFreq是等于minFreq，并且已经不存在这个minFreq的元素的话，minFreq++，为啥加一就行了？怎么知道倒数第二个frequency是多少？是因为
        if(tempFreq==minFreq && minFrequency.get(tempFreq).size()==0){//这个frequency肯定是1个一个增长的，既然你当前访问的这个数是最不常被访问的数，你现在get了他一下，那么倒数第二小的frequency当然是++了
            minFreq++;
        }
        if(!minFrequency.containsKey(tempFreq+1)){
            LinkedHashSet<Integer> lset=new LinkedHashSet<>();
            lset.add(key);
            minFrequency.put(tempFreq+1,lset);
        }else{
            minFrequency.get(tempFreq+1).add(key);
        }
        return map.get(key);
    }

    public void put(int key, int value) {//比如先put了1，1如果再put1，1则还是算是1个frequency了，如果先put了1，1再put1，2，则key为1的frequency是重新算的也是1。单单put操作也算访问了1次
        if(cap<=0) {
            return;
        }
        if(map.containsKey(key)){
            map.put(key,value);
            get(key);
            return;
        }
        if(map.size()>=cap){
            int removeKey=minFrequency.get(minFreq).iterator().next();
            minFrequency.get(minFreq).remove(removeKey);
            frequency.remove(removeKey);
            map.remove(removeKey);

        }
        map.put(key,value);
        frequency.put(key,1);
        minFreq=1;//他是新来的那么他肯定frequency是1，肯定是最小的
        if(!minFrequency.containsKey(minFreq)){
            LinkedHashSet<Integer> lset=new LinkedHashSet<>();
            lset.add(key);
            minFrequency.put(1,lset);
        }else{
            minFrequency.get(minFreq).add(key);
        }

    }

}
