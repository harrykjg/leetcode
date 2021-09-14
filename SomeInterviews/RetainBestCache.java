package SomeInterviews;

import java.util.*;

public class RetainBestCache<K, T extends RetainBestCache.Ranka‍‌‌‌‍‍‍‌‌‍‌‍‍‍‌‍‌‍‌ble> {
    //8/8/2021
    //linkedin 面经
    //https://github.com/shileiwill/destination/blob/master/Round1/src/company/linkedin/RetainBestCache.java
    //https://www.1point3acres.com/bbs/thread-484962-2-1.html
    //其实也不难。他就是要实现一个到达cap之后如何删除，至于get的时候是否要改变rank就不管。但是新加进的东西是有固定rank的，要记录，因此要个treemap

    int entriesToRetain;
    HashMap<K, T> map = new HashMap<K, T>();
    DataSource<K, T> ds;
    TreeMap<Long, Set<K>> rankMap;

    int cap;

    /* Constructor with a data source (assumed to be slow) and a cache size */
    public RetainBestCache(DataSource<K, T> ds, int entriesToRetain) {
        cap=entriesToRetain;
        this.ds=ds;
    }

    /* Gets some data. If possible, retrieves it from cache to be fast. If the data is not cached,
     * retrieves it from the data source. If the cache is full, attempt to cache the returned data,
     * evicting the T with lowest rank among the ones that it has available
     * If there is a tie, the cache may choose any T with lowest rank to evict.
     */
    public T get(K key) {
        if (!map.containsKey(key)){
            T value=ds.get(key);
            add(key,value);
        }
        return map.get(key);
    }
    void add(K key,T value){
        if (map.size()==cap){
            removeLast();
            map.put(key,value);
        }else {
            map.put(key,value);
            long rank=value.getRank();
            if (!rankMap.containsKey(rank)){
                rankMap.put(rank,new HashSet<>());
            }
            rankMap.get(rank).add(key);
        }
    }
    void removeLast(){
        long rank=rankMap.firstKey();
        Set<K> set=rankMap.get(rank);
        Iterator<K> it=set.iterator();
        K key=it.next();
        set.remove(key);
        if (set.size()==0){
            rankMap.remove(rank);
        }
        map.remove(key);
    }

    /*
     * For reference, here are the Rankable and DataSource interfaces.
     * You do not need to implement them, and should not make assumptions
     * about their implementations.
     */
    interface Ranka‍‌‌‌‍‍‍‌‌‍‌‍‍‍‌‍‌‍‌ble {
        /**
         * Returns the Rank of this object, using some algorithm and potentially
         * the internal state of the Rankable.
         */
        long getRank();
    }

    interface DataSource<K, T extends Ranka‍‌‌‌‍‍‍‌‌‍‌‍‍‍‌‍‌‍‌ble> {
        T get(K key);
    }
}