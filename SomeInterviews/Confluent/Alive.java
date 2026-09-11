package SomeInterviews.Confluent;

import java.util.*;

public class Alive {
    /*
    sensor health。给 (sensorID, timestamp)，实现 wasAlive()。连续 3 个 slot 没 ping 就算 down。用 hashmap + binary search。

     */
    private static final int SLOT_SIZE = 10;

    //记录某个sensorid出现ping的时间点吧。
    private Map<String, List<Integer>> map = new HashMap<>();
    public void recordPing(String sensorId, int timestamp) {
        map.putIfAbsent(sensorId, new ArrayList<>());
        map.get(sensorId).add(timestamp);
    }
    // 如果所有 recordPing 完以后再 query，可以先统一 sort
    public void sortAll() {
        for (List<Integer> times : map.values()) {
            Collections.sort(times);
        }
    }
    //这里的写法是指找timestamp之前的slot_size时间段，但是有的面经貌似是找之后的。但是做法应该一样
    public boolean wasAlive(String sensorId, int timestamp) {
        List<Integer> times = map.get(sensorId);
        if (times == null || times.isEmpty()) {
            return false;
        }
        // 找近timestamp的 ping（<=timestamp)，注意是upperbound，然后减1就是了
        int idx = upperBound(times, timestamp) - 1;
        if (idx < 0) {
            // 查询时间之前还从来没 ping
            return false;
        }
        /*
         * 往回找所有相邻 ping 之间，有没有连续 3 个空 slot
         */
        for (int i = 1; i <= idx; i++) {
            //看当前i和i-1个ping的时间点，除以slot就得到slot的编号。那么就是假设time是0，1，2，3，4.。。这样每个slot长度是10的这样的时间线，
            //即【1-10】，【11-20】，【21-30】。。。这样的，这里slot就是0，1，2，3.。这样的编号，因此直接curSlot - prevSlot - 1;可以得
            //中间空的slot数量
            int prevSlot = times.get(i - 1) / SLOT_SIZE;
            int curSlot = times.get(i) / SLOT_SIZE;
            // 中间空 slot 数量
            int emptySlots = curSlot - prevSlot - 1;

            if (emptySlots >= 3) {
                return false;
            }
        }

         //这里容易漏 最后还要检查： 最后一个 ping 到 query timestamp 之间有没有连续 3 个空 slot。
        //前面是查了严格小于timestamp的的两两slot之间，最接近timestamp的这个slot和timestamp这个slot没查

        int lastPingSlot = times.get(idx) / SLOT_SIZE;//最接近timestamp的这个slot
        int querySlot = timestamp / SLOT_SIZE;
        if (querySlot - lastPingSlot >= 3) {
            return false;
        }
        return true;
    }

    // 返回第一个 > target 的 index
    private int upperBound(List<Integer> arr, int target) {
        int l = 0;
        int r = arr.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid) <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
    //找timestamp之后的版本
    // key -> sorted timestamps
    private Map<Integer, List<Integer>> map2 = new HashMap<>();

    public void add(int key, int timestamp) {
        map2.putIfAbsent(key, new ArrayList<>());
        map2.get(key).add(timestamp);
    }

    public void sortAll() {
        for (List<Integer> times : map2.values()) {
            Collections.sort(times);
        }
    }

    public boolean wasAlive(int key, int start) {
        List<Integer> times = map2.get(key);

        if (times == null || times.isEmpty()) {
            return false;
        }

        boolean[] seen = new boolean[5];

        // 找第一个 >= start 的 timestamp
        int idx = lowerBound(times, start);

        int end = start + 499;

        // 只扫描 [start, start+499]
        while (idx < times.size() && times.get(idx) <= end) {
            int timestamp = times.get(idx);

            int slot = (timestamp - start) / SLOT_SIZE;
            seen[slot] = true;

            idx++;
        }

        // 检查是否存在连续3个slot都有记录
        int streak = 0;
        for (boolean hasPing : seen) {
            if (hasPing) {
                streak++;

                if (streak >= 3) {
                    return true;
                }
            } else {
                streak = 0;
            }
        }

        return false;
    }

    private int lowerBound(List<Integer> arr, int target) {
        int l = 0;
        int r = arr.size();

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr.get(mid) < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }


    /*
        传感器健康检查（实现 wasAlive()）
系统会持续收到传感器的心跳 ping 记录，每条记录为：

sensorId（字符串或整数）
timestamp（整数，表示时间）
定义时间被划分为固定长度的 slot（例如每 1 分钟一个 slot；slot 长度在题目中作为常量给定）。

规则：若某个传感器连续 3 个 slot 没有收到 ping，则认为该传感器从那时起处于 down 状态。

请设计并实现一个数据结构，支持：

recordPing(sensorId, timestamp)：记录该传感器在该时间点的 ping。
wasAlive(sensorId, timestamp) -> bool：查询在给定 timestamp 时刻，该传感器是否仍被认为是 alive。
说明
wasAlive 的判定：在 timestamp 所在 slot 及其之前的历史中，是否存在连续 3 个 slot 都没有 ping 的“空档”；若在查询时刻之前已经出现过这样的空档，则返回 false。
允许同一 slot 内出现多次 ping（等价于该 slot 有 ping）。
约束/数据规模
传感器数量：可达 10^6
记录总量：可达 10^7
需要支持高频查询
示例
假设 slot 长度为 10（时间单位），则 slotIndex = floor(timestamp / 10)。

对同一 sensorId：

pings at timestamps: 5(slot0), 12(slot1), 55(slot5)
查询 wasAlive(sensorId, 40)：slot4，slot2/3/4 连续 3 个 slot 无 ping => 认为 down，返回 false。
查询 wasAlive(sensorId, 15)：slot1 尚未出现连续 3 个空 slot，返回 true。
这个解读不知道是不是一亩三分地用ai产生的，不知道准不准
     */
}
