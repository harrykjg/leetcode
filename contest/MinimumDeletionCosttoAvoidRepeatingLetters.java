package contest;

import java.util.PriorityQueue;

//自己想的用pq，其实没必要，看他的
public class MinimumDeletionCosttoAvoidRepeatingLetters {
    public int minCost(String s, int[] cost) {
        char[] ch=s.toCharArray();
        int i=0;
        int rs=0;
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        while (i<ch.length){
            pq.clear();
            pq.offer(cost[i]);
            while (i+1<ch.length&&ch[i]==ch[i+1]){
                pq.offer(cost[i+1]);
                i++;
            }
            while (pq.size()>1){
                rs+=pq.poll();
            }
            i++;
        }

        return rs;
    }
}
