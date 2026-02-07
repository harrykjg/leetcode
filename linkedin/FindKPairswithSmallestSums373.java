package linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairswithSmallestSums373 {
    static void main() {
        int[] a={1,7,11};
        int[] b={2,4,6};
        System.out.println(kSmallestPairs(a,b,3));
    }
    //只能想到是要固定一个index，取另一个，但是还是没想出来。看灵神那边的解释
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> rs=new ArrayList<>();
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[2]-b[2]);
        for (int i=0;i<nums1.length;i++){
            int[] a={i,0,nums1[i]+nums2[0]};
            pq.offer(a);
        }
        while (rs.size()<k&&!pq.isEmpty()){
            int[] cur=pq.poll();
            List<Integer> al=new ArrayList<>();
            al.add(nums1[cur[0]]);
            al.add(nums2[cur[1]]);
            rs.add(al);
            if(cur[1]+1<nums2.length){
                pq.offer(new int[]{cur[0],cur[1]+1,nums1[cur[0]]+nums2[cur[1]+1]});

            }
        }
        return rs;

    }

}
