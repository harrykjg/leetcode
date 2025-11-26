package 灵神.图论.dijistra;

import java.util.*;

public class NetworkDelayTime743 {
    public static void main(String[] args) {
        System.out.println(networkDelayTime(new int[][]{{1,2,1},{2,3,2},{1,3,4}},3,1));
    }

    //看回以前的解释，和灵神的写法差不多
    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> map=new HashMap<>();
        for (int i=0;i<times.length;i++){
            map.putIfAbsent(times[i][0],new ArrayList<>());
            map.get(times[i][0]).add(new int[]{times[i][1],times[i][2]});
        }
        int[] dist=new int[n];
        Arrays.fill(dist,Integer.MAX_VALUE);//dist相当于memo了
        dist[k-1]=0;
        int rs=-1;
        int found=0;
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        pq.offer(new int[]{k,0});
        while (!pq.isEmpty()){
            int[] cur=pq.poll();
            int curPoint=cur[0];
            int curDist=cur[1];

            if(dist[curPoint-1]<curDist){
                continue;
            }
            found++;
            List<int[]> neighbour=map.get(curPoint);
            if(neighbour!=null){
                for(int[] next:neighbour){
                    int nextPoint=next[0];
                    int nextDist=next[1];
                    if(dist[nextPoint-1]>curDist+nextDist){//不能用math。max比较，因为只有找到小于的才能加进pq里。否则死循环
                        dist[nextPoint-1]=curDist+nextDist;//开始想着这里与结果rs进行比较，其实不行，因为这里还在试的过程中，可能往后这个点有找到更短的距离，所以要运行完再找
                        pq.offer(new int[]{nextPoint,dist[nextPoint-1]});
                    }

                }
            }
        }
        if (found<n){
            return -1;
        }
        for(int i:dist){
            rs=Math.max(rs,i);
        }

        return rs;
    }
}
