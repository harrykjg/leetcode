package DataStruct.Stack;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by yufengzhu on 10/17/18.
 */
public class MaximumFrequencyStack {

    //想了挺久的以为不会，结果想出来之后一次过，但是复杂度是lgn。答案居然是O1的
    //https://leetcode.com/problems/maximum-frequency-stack/solution/
    PriorityQueue<info> pq;//思路是用一个pq，里面撞着一个info类型，这个info类包含这个数值的value，frequency和序列号，pq排序就按frequency，若prequency相同则看序号大小
    HashMap<Integer,Integer> map;
    int seq=0;
    public MaximumFrequencyStack() {
        pq=new PriorityQueue<>(new Comparator<info>() {
            @Override
            public int compare(info o1, info o2) {
                if(o1.freq==o2.freq){
                    return o2.id-o1.id;
                }
                return o2.freq-o1.freq;
            }
        });

        map=new HashMap<>();
    }

    public void push(int x) {//每次push其实就是push一个entry进pq里，序号递增，这样就保证了pq poll出来的东西是frequency最大的并且序号是最大的
        if(!map.containsKey(x)){
            map.put(x,1);
        } else{
            map.put(x,map.get(x)+1);
        }
        info inf=new info(++seq,map.get(x),x);
        pq.offer(inf);

    }

    public int pop() {
        info inf=pq.poll();
        map.put(inf.val,inf.freq-1);
        return inf.val;
    }
    class info{
        int id;
        int freq;
        int val;
        public info(int id,int freq,int val){
            this.id =id;
            this.freq=freq;
            this.val=val;
        }
    }
}
