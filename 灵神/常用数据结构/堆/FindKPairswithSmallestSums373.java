package 灵神.常用数据结构.堆;

import java.util.List;

public class FindKPairswithSmallestSums373 {
    static void main() {


    }

    //12/6/2025
    //见解释，就是说最小的肯定是nums1的第一个和nums2的第一个，那么第二个呢？就肯定是nums1的第二个和nums2的第一个，或者nums1的第一个和nums2的第二个，
    //那么第k小呢？就是但这会导致一个问题：例如当 (1,0) 出堆时，会把 (1,1) 入堆；当 (0,1) 出堆时，也会把 (1,1) 入堆，这样堆中会有重复元素。
    // 为了避免有重复元素，还需要额外用一个哈希表记录在堆中的下标对。只有当下标对不在堆中时，才能入堆。能否不用哈希表呢？解决办法就是把nums1全部
    //都扔进pq里，那么poll出来的时候只需要加入nums2的index+1了。

    //https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/solutions/2286318/jiang-qing-chu-wei-shi-yao-yi-kai-shi-ya-i0dj/
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

    }
}
