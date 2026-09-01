package SomeInterviews.verkarda;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

public class DesignAmbientLightSensorSystem {
    //不需要treemap其中key=time，value是double值。然后其实也不需要linkedhashmap，由于timestamp是自增的，因此直接普通hashmap加维护一个
    //lasttimestamp和latestLux就行了，这里还是用的linkedhashmap
    class ALSWrapper {
        LinkedHashMap<Integer,Double> map=new LinkedHashMap<>();
        public ALSWrapper() {
            // TODO: Initialize ALSWrapper
        }
        public void record(int status, double lux, int timestamp) {
            // TODO: Implement record logic
            if(status==1){
                map.put(timestamp,lux);
            }
        }
        public double getLatestLux() {
            // TODO: Implement getLatestLux logic
            if(map.isEmpty()){
                return -1.0;
            }
            return map.lastEntry().getValue();
        }
        public double getLuxAt(int timestamp) {
            // TODO: Implement getLuxAt logic
            if(map.isEmpty()){
                return -2.0;
            }
            Integer latest=map.lastEntry().getKey();
            if(latest<timestamp){
                return -2.0;
            }
            if(!map.containsKey(timestamp)){//既然必须要用当前timestamp的数据，那也不需要floorkey什么的
                return -1.0;
            }
            if(timestamp+600<latest){
                return -1.0;
            }
            return map.get(timestamp);

        }
    }

    //线程安全版本，gpt的。用了ConcurrentHashMap保证map线程安全。还用了AtomicReference，是并发包的一个类，用来原子的改变某个object，
    // 之前说了可以用一个latest timesstamp和lux 来标记最后一个值，那volatile不行吗？答案是不行，因为这两个值是两个变量，
    // 如果只有一个变量是可以的。因此要建一个Reading类把他们放在一个obj里。
    class ALSWrapper {
        static class Reading {
            final int timestamp;
            final double lux;

            Reading(int timestamp, double lux) {
                this.timestamp = timestamp;
                this.lux = lux;
            }
        }
        // timestamp -> lux
        // ConcurrentHashMap 支持并发读写
        private final ConcurrentHashMap<Integer, Double> map =
                new ConcurrentHashMap<>();

        // 最新的一条 valid reading
        // 把 timestamp 和 lux 放一起，保证一起更新
        private final AtomicReference<Reading> latest =
                new AtomicReference<>(null);

        public void record(int status, double lux, int timestamp) {
            if (status == 0) {
                return;
            }
            // 先把当前 reading 存进去
            map.put(timestamp, lux);
            Reading cur = new Reading(timestamp, lux);
            /*
             * 如果题目严格保证只有一个 polling thread 调用 record，
             * 直接 latest.set(cur) 就够了。
             * 如果可能有多个 sensor / writer 同时 record，
             * 用 CAS 保证 timestamp 最大的 reading 成为 latest。
             */
            while (true) {//while true是代表CAS失败后可以读新的值再设
                Reading old = latest.get();//latest是memner vaiable，但是old可以是空
                if (old != null && old.timestamp >= timestamp) {
                    return;
                }
                if (latest.compareAndSet(old, cur)) {//CAS 就是compare and set，就是old值没改变就设成新值，否则失败
                    return;
                }
            }
        }
        public double getLatestLux() {
            Reading r = latest.get();
            if (r == null) {
                return -1.0;
            }
            return r.lux;
        }
        public double getLuxAt(int timestamp) {
            Reading r = latest.get();
            // 还没有任何 valid reading
            if (r == null) {
                return -2.0;
            }
            int latestTime = r.timestamp;
            // 查询未来时间
            if (timestamp > latestTime) {
                return -2.0;
            }
            // 超出最近 600 秒 window
            if (timestamp + 600 < latestTime) {
                return -1.0;
            }
            // 必须正好存在这个 timestamp
            Double lux = map.get(timestamp);
            if (lux == null) {
                return -1.0;
            }
            return lux;
        }
    }
}
