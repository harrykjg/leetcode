package 灵神.图论.bfs;

import java.util.*;

public class BusRoutes815 {
    public static void main(String[] args) {

    }
    //自己写还是TLE，还是建图那里用了三重循环，看回以前的解释.以下是对的code
    public int numBusesToDestination(int[][] routes, int source, int target) {
        int rs=0;
        if(source==target){
            return 0;
        }
        Map<Integer, Set<Integer>> map=new HashMap<>();//他的意义是key为站点，value为所有能到这个站点的bus（而这些bus又能去别的站点）
        for (int i=0;i<routes.length;i++){
            for(int j=0;j<routes[i].length;j++){
                map.putIfAbsent(routes[i][j],new HashSet<>());
                map.get(routes[i][j]).add(i);
            }
        }
        if(!map.containsKey(source)||!map.containsKey(target)){//不加这个也超时
            return -1;
        }
        Queue<Integer> q=new LinkedList<>();
        q.offer(source);
        Set<Integer> set=new HashSet<>();
        int dist=1;
        while (!q.isEmpty()){//BFS的意义就是对于某个站点，拿到所有经过此处的bus，继而拿到这些bus能去的站点。因此set里装的还是站点，不是bus
            int size=q.size();
            for (int i=0;i<size;i++){
                int cur=q.poll();
                set.add(cur);

                Set<Integer> buses=map.get(cur);
                for(int nei:buses){
                    for(int stop:routes[nei]){
                        if(stop==target){
                            return dist;
                        }
                        if(!set.contains(stop)){
                            q.offer(stop);
                            set.add(stop);//注意这里不加这个就会超时，尽管外面poll的时候也已经加了也不行
                        }
                    }

                }
            }
            dist++;
        }
        return -1;

    }
    //2/14/2026 常规想法还是建图得三重循环，看回以前的，思路是把站点对应的bus的编号用map记住，这样对于某一个站点，可以找到其对应的的bus，再去routes
    //数组找邻居即可，所以说省了三重循环建图。 完了还是bfs思路，无非是找邻居的时候要通过bus编号去找

}
