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
//8/12/2021 不会了
    //https://www.cnblogs.com/reboot329/p/5875873.html  klogk的方法不好理解。举个例子nums1=[1,33,44,55]nums2=[2,4,6,6]，k=3，现在他
    //先把nums2的第一个和前k个nums1的数放进pq里，pq的元素记录了nums2的元素下标。然后开始从pq里拿k个元素，拿出来第一个肯定是最小的放进结果集，
    //然后再把这poll出来pair的nums1的那个数去和nums2对应的数的后一个数组成pair加进q里，这样下一次q poll出来也能保证是最小的。真不好想。
    public static List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<int[]> rs=new ArrayList<>();

        return rs;
    }
}
