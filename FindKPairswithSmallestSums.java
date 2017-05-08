import java.util.*;

/**
 * Created by 502575560 on 5/5/17.
 */
public class FindKPairswithSmallestSums {
    public static void main(String[] args){
        int[] n1={1,7,11};
        int[] n2={2,4,6};
        kSmallestPairs(n1,n2,3);

    }
    //看了heap的提示之后自己想的改了2次accept
    public static List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> rs=new ArrayList<>();
        if(nums1.length==0||nums2.length==0){
            return rs;
        }
        PriorityQueue<int[]> q=new PriorityQueue<>(k, new Comparator<int[]>() {//要最大堆装的是最小的k个元素
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0]+o2[1]-o1[0]-o1[1];
            }
        });
        for(int i=0;i<nums1.length;i++){
            for (int j=0;j<nums2.length;j++){
                int sum=nums1[i]+nums2[j];
                int[] n=new int[]{nums1[i],nums2[j]};
                if(q.size()<k){
                    q.offer(n);
                    continue;
                }
                if(sum<q.peek()[0]+q.peek()[1]){
                    q.poll();
                    q.offer(n);
                }else{
                    if(q.size()>=k){//因为两个数组都是还需好的ascending的,所以当q满了并且sum比q.peek大那么后面的数也肯定大也就肯定不用加进来了,leetcode不加的话会慢
                        break;
                    }

                }
            }
        }
        while (q.size()!=0){
            rs.add(0,q.poll());
        }
        return rs;


    }
}
