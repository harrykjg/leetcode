package SomeInterviews.Confluent;

import java.util.*;

public class TimeBasedKeyValueStore981 {
    //比lc原题981多了getaverage，那就是value要变成int，原题是string没法搞average（start，end）
    //做法是原有的基础上把value做成一个entry class，里面还额外包含了prfixsum，set的时候就要设置好，这样找range的时候就用prefixsum找就快
    //否就就用treemap的submap方法直接加起算平均值
    class TimeMap {
        static class Entry {
            int value;
            long prefixSum;
            Entry(int value, long prefixSum) {
                this.value = value;
                this.prefixSum = prefixSum;
            }
        }
        // key -> timestamp -> Entry
        Map<String, TreeMap<Integer, Entry>> map = new HashMap<>();
        public void set(String key, int value, int timestamp) {
            map.putIfAbsent(key, new TreeMap<>());
            TreeMap<Integer, Entry> tm = map.get(key);
            long prefix = value;
            // 找当前 timestamp 前面的最后一个 entry
            Map.Entry<Integer, Entry> prev = tm.lowerEntry(timestamp);
            if (prev != null) {
                prefix += prev.getValue().prefixSum;
            }
            tm.put(timestamp, new Entry(value, prefix));
        }
        public Integer get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return null;
            }
            Map.Entry<Integer, Entry> e = map.get(key).floorEntry(timestamp);
            if (e == null) {
                return null;
            }
            return e.getValue().value;
        }

        public double getAverage(String key, int start, int end) {
            if (!map.containsKey(key)) {
                return 0.0;
            }
            TreeMap<Integer, Entry> tm = map.get(key);
            // 第一个 >= start
            Map.Entry<Integer, Entry> left = tm.ceilingEntry(start);
            // 最后一个 <= end
            Map.Entry<Integer, Entry> right = tm.floorEntry(end);

            if (left == null || right == null || left.getKey() > right.getKey()) {
                return 0.0;
            }
            long rightPrefix = right.getValue().prefixSum;
            // 找 left 前一个 entry
            Map.Entry<Integer, Entry> beforeLeft = tm.lowerEntry(left.getKey());
            long leftPrefix = beforeLeft == null ? 0 : beforeLeft.getValue().prefixSum;
            long sum = rightPrefix - leftPrefix;
            // 这里还需要知道区间里有几个元素
            int count = tm.subMap(left.getKey(), true,right.getKey(), true).size();
            return (double) sum / count;
        }
    }
}
