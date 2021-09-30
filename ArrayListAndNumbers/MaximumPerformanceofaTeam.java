package ArrayListAndNumbers;

import java.util.*;

public class MaximumPerformanceofaTeam {
        //9/13/2021 用pq装efficency，然后poll到第1个efficiency，找所有effi大于等于他的speed，而且数量不能超过k，这样就比暴力dfs少举例子了。
    //由于数量不能超过k，所以speed也要sort，取最大的k个和poll出来的effi计算.这样还是有点暴力，会超时但是好理解
    //https://leetcode.com/problems/maximum-performance-of-a-team/discuss/539680/Java-Detailed-Explanation-PriorityQueue-O(NlogN)
    //他们的思路更屌，按effi从大到小把engineer排序，然后建一个pq，意义是按speed排序，然后把engineer遍历，则当前engineer就肯定是决定着group的最小effi，然后
    //把当前engineer的speed加入pq，同时维护一个totalspeed，把当前spped加到totalspeed里，可计算结果，然后下一个engineer来了，则effi变成是他的effi，同时把
    //他的speed加上，然后再下一个时，发现pq==k，说明已经满员，则要把speed最慢那个engineer踢掉，因为effi是按下一个engineer的，所以踢掉的engineer无所谓。
    //https://leetcode.com/problems/maximum-performance-of-a-team/discuss/539680/Java-Detailed-Explanation-PriorityQueue-O(NlogN)
        int mod=(int)Math.pow(10,9)+7;
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        PriorityQueue<Pair> pq=new PriorityQueue<>((p1,p2)->p2.efficiency-p1.efficiency);
        List<Pair> ls=new ArrayList<>();
        for (int i=0;i<n;i++){
            ls.add(new Pair(speed[i],efficiency[i]));
            pq.offer(new Pair(speed[i],efficiency[i]));
        }
        long rs=0;
        Collections.sort(ls, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.speed==o2.speed){
                    return o2.efficiency-o1.efficiency;
                }
                return o2.speed-o1.speed;
            }
        });
        while (!pq.isEmpty()){
            long eff=(long)(pq.poll().efficiency);//真恶心，这里不把effi转成long的话，后面long temp=sum*eff就算是long类型也会不对
            long sum=0;
            int count=0;
            for (int i=0;i<ls.size();i++){
                if (ls.get(i).efficiency>=eff){
                    sum+=ls.get(i).speed;
                    count++;
                    if (count==k){
                        break;
                    }
                }
            }
            long temp=sum*eff;
            rs=Math.max(rs,temp);
        }
        return (int)(rs%mod);
    }
    class Pair{
        int speed;
        int efficiency;
        public Pair(int speed,int efficiency){
            this.speed=speed;
            this.efficiency=efficiency;
        }
    }
}
