import java.util.*;

/**
 * Created by yufengzhu on 10/14/18.
 */
public class NetworkDelayTime {

    public static void main(String[] args){
        NetworkDelayTime nd=new NetworkDelayTime();
        int[][] times=new int[8][3];
        times[1][0]=1;
        times[1][1]=2;
        times[1][2]=3;
        times[2][0]=1;
        times[2][1]=3;
        times[2][2]=5;
        times[3][0]=1;
        times[3][1]=4;
        times[3][2]=20;
        times[4][0]=2;
        times[4][1]=3;
        times[4][2]=1;
        times[5][0]=2;
        times[5][1]=4;
        times[5][2]=6;
        times[6][0]=3;
        times[6][1]=4;
        times[6][2]=1;
        times[7][0]=4;
        times[7][1]=5;
        times[7][2]=2;
//        times[8][0]=6;
//        times[8][1]=7;
//        times[8][2]=1;
       System.out.print( nd.networkDelayTime(times,5,1));

    }

    //写的不好,题意也理解错了，求的是从k点要到最远的距离的时间，因为题目意思是从k点出发用多久时间能到达所有点，是concurrent的
    //dijistra还是写不出来。
    //https://leetcode.com/problems/network-delay-time/discuss/109968/Simple-JAVA-Djikstra's-(PriorityQueue-optimized)-Solution-with-explanation
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, HashMap<Integer,Integer>> map = new HashMap();
        for (int[] edge : times) {
            if (!map.containsKey(edge[0]))
                map.put(edge[0], new HashMap<>());
            map.get(edge[0]).put(edge[1], edge[2]);
        }
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        HashMap<Integer,Integer> dist=new HashMap<>();
        dist.put(K,0);//K到K点的距离为0
        int rs=Integer.MIN_VALUE;
        pq.offer(new int[]{K,0});//pq里面的数组的第一位是target节点，第二位是从k到这个target的长度
        while (!pq.isEmpty()){//每一个while循环其实涉及到3条边，比如说初始点是A,中间点是B,B的一个邻居是C,那么每个while循环是看A到B的距离加B到C的距离，是否小于A到C的距离。而A到B的距离是存在dist里了
            int[] c=pq.poll();
            int cur=c[0];
            int len=c[1];
            if(dist.containsKey(cur)&&dist.get(cur)<len){//写成<=也不行，这一步也很关键，这个cur其实是一个中间点，cur的邻居才是这一层循环想计算的距离。当dist里面有这个cur了并且K点到cur的距离小于K点到某个点的
                continue;  //距离的话，那么就说明这个cur的len已经outdated了，因为pq里面的数组本来是当这个数组被放进pq的时候是最小的，然而现在poll出来时发现他已经不是最小了，说明他已经过时了。
            }
//            dist.put(cur,len);
            if(map.get(cur)!=null){
                for(int nei:map.get(cur).keySet()){
                    int len2=len+map.get(cur).get(nei);
                    if(!dist.containsKey(nei)||dist.get(nei)>len2){
                        dist.put(nei,len2);
                        pq.offer(new int[]{nei,len2});
                    }

                }
            }
        }
        if(dist.size()<N){
            return -1;
        }
        for(int i:dist.values()){
            rs=Math.max(i,rs);
        }

        return rs;
    }

}
