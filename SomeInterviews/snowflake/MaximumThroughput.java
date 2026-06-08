package SomeInterviews.snowflake;

import java.util.List;

public class MaximumThroughput {
    /*
    A company wants to increase the throughput of a message-processing pipeline while staying within a fixed scaling budget.

You are given two integer arrays throughput and scalingCost, both of length n. Service i starts with throughput throughput[i], and one upgrade for that service costs scalingCost[i].

The services form a pipeline, so each message must pass through every service in order. As a result, the throughput of the whole pipeline is the minimum throughput among all services after scaling.

Each service may be upgraded any number of times. If service i is upgraded x times, its throughput becomes throughput[i] * (1 + x), and the total cost for that service becomes x * scalingCost[i].

Return the maximum possible pipeline throughput that can be achieved without spending more than budget.

Constraints:

throughput.length = scalingCost.length = n ≥ 1
budget ≥ 0
Example 1:

Input: throughput = [4, 2, 7], scalingCost = [3, 5, 6], budget = 32
Output: 10
Explanation: In this example, you can upgrade service 0 two times (4 × 3 = 12; cost 6), service 1 four times (2 × 5 = 10; cost 20), and service 2 one time (7 × 2 = 14; cost 6), for a total cost of 32, which exactly uses the entire budget. After these upgrades, the throughputs are [12, 10, 14]. The pipeline throughput is determined by the lowest among these values, which is 10. No higher minimum throughput is possible without exceeding the budget.

Example 2:

Input: throughput = [5], scalingCost = [4], budget = 8
Output: 15

Example 3:

Input: throughput = [3, 6, 9], scalingCost = [2, 100, 100], budget = 4
Output: 6
     */

    public int maximumThroughput(List<Integer> throughput, List<Integer> scalingCost, int budget) {
        long maxPossible=Long.MAX_VALUE;//找单一个throughput，把所有budge都用在他身上的最大值。开始想的是找最cost effective的但是可能价格很高导致一个都用不上吧
        for (int i=0;i<throughput.size();i++){
            maxPossible=Math.min((long)throughput.get(i)*(1+budget/scalingCost.get(i)),maxPossible);//注意取min就行了，而且容易漏1！
        }
        long b=0;
        long e=maxPossible;
        while (b+1<e){//用模版可以，否则也不好搞
            long m=b+(e-b)/2;
            if(ok(throughput,scalingCost,budget,m)){
                b=m;
            }else{
                e=m;
            }
        }
        if(ok(throughput,scalingCost,budget,e)){//先试e，反过来就不对
            return (int)e;
        }else if(ok(throughput,scalingCost,budget,b)){
            return (int)b;
        }
        return -1;
    }
    //开始没想明白，想着如果把budge分给某个人，那么就会影响剩余的budget的分配，其实不用考虑，只需计算出每个人达到target的最小cost，这些总
    //cost加起来小于等于budget就是ok的，超了就不ok
    boolean ok(List<Integer> throughput, List<Integer> scalingCost, int budget,long target){
        long total=0;
        for (int i=0;i<throughput.size();i++){
            if(throughput.get(i)>=target){
                continue;
            }
            long need=target/throughput.get(i);
            if(need*throughput.get(i)<target){
                need++;
            }
            total+=(need-1)*scalingCost.get(i);
        }
        return total<=budget;
    }
}
