import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yufengzhu on 9/19/18.
 */
public class MinimumCosttoHireKWorkers {
    //看答案再想的还是不好想,很巧妙，也很难记住。有点像walmart合同工面试的那种基于2种元素排序的那种题
    //https://leetcode.com/problems/minimum-cost-to-hire-k-workers/discuss/141768/Detailed-explanation-O(NlogN)
    //https://leetcode.com/problems/minimum-cost-to-hire-k-workers/solution/
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        PriorityQueue<Double> pq=new PriorityQueue<>(Collections.reverseOrder());//最大堆，存的最大的元素，这里我第二次看把它改了和答案不同，其实用最大堆就好了，不用像答案那用用最小堆然后pq。offer那里用负数

        pair[] pairs=new pair[quality.length];
        for(int i=0;i<quality.length;i++){
            pairs[i]=new pair(quality[i],(double)wage[i]/(double)quality[i]);
        }
        Arrays.sort(pairs, new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {

                if(o1.ratio-o2.ratio>0){
                    return 1;
                }else if(o1.ratio-o2.ratio<0){
                    return -1;
                }else{
                    return 0;
                }
            }
        });

        double rs=Double.MAX_VALUE;
        double sum=0.0;
        for(int i=0;i<pairs.length;i++){
            pq.offer(pairs[i].quality);
            sum+=pairs[i].quality;
            if(pq.size()>K){
                sum-=pq.poll();
            }
            if(pq.size()==K){
                rs=Math.min(rs,pairs[i].ratio*sum);
            }
        }

        return rs;
    }
    class pair{
        double quality;
        double ratio;
        public pair(double quality,double ratio){
            this.quality=quality;
            this.ratio=ratio;
        }
    }
    public double mincostToHireWorkers2(int[] quality, int[] wage, int K) {

    }
}
