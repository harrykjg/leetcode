package ArrayListAndNumbers;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxValueofEquation {
    //9/3/2021 不会
    //https://leetcode.com/problems/max-value-of-equation/discuss/709231/JavaPython-Priority-Queue-and-Deque-Solution-O(N) 下面评论说
    //lessons learned: when using a heap to maintain an ordered subsequence/subarray, consider using a monotonic stack.
    //由题目说的要求yi + yj + |xi - xj|的最大值，并且xj大于xi，可得yi+yj+xj-xi，即yi-xi+yj+xj。然后由于输入是按x排序的，那么我们就可以对于某个point，
    //把他当作是yj+xj，看他们前面的所有的yi-xi，i属于0到j-1，选最大的并且符合xj-xi小于等于k的就行。那么就用pq边走边存一个pair class，pair里的key是
    //yi-xi，value是xi，按yi-xi排序，这样后面那个就可以只和最大的yi-xi比较，并且可以判断是否符合条件
    public int findMaxValueOfEquation(int[][] points, int k) {
        PriorityQueue<Pair> pq=new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.key==o2.key){
                    return o2.value-o1.value;
                }
                return o2.key-o1.key;
            }
        });
        pq.offer(new Pair(points[0][1]-points[0][0],points[0][0]));
        int rs=0;
        for (int i=1;i<points.length;i++){
            while (!pq.isEmpty()){
                if (points[i][0]-pq.peek().value<=k){
                    int temp=points[i][0]+points[i][1]+pq.peek().key;
                    rs=Math.max(temp,rs);
                    break;
                }else {
                    pq.poll();
                }
            }
            pq.offer(new Pair(points[i][1]-points[i][0],points[i][0]));
        }
        return rs;
    }
    class Pair{
        int key;
        int value;
        public Pair(int key,int value){
            this.key=key;
            this.value=value;
        }
    }
}
