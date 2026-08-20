package SomeInterviews.verkarda;

import java.util.*;

public class TreeDiameter1245 {
    //像1522
    //自己想的就是找edge到root的所有path，选最长的两条，那么怎么找root呢?结果就是先随便一个点找到远的点A，再从A找最远的点B，则A到B就是最远的点
    //神奇
    public int treeDiameter(int[][] edges) {

        Map<Integer, Set<Integer>> map=new HashMap<>();
        for (int[] e:edges){
            int a=e[0];
            int b=e[1];
            map.putIfAbsent(a,new HashSet<>());
            map.get(a).add(b);
            map.putIfAbsent(b,new HashSet<>());
            map.get(b).add(a);
        }
        int[] first=bfs(edges[0][0],map);
        int[] sec=bfs(first[1],map);
        return sec[0];//这里sec就是A到最远的B的距离
    }
    //找最远的那个点和距离，不是找距离
    int[] bfs(int b,Map<Integer,Set<Integer>> map){
        Queue<Integer> q=new LinkedList<>();
        q.offer(b);
        Set<Integer> memo=new HashSet<>();
        memo.add(b);
        int dist=-1;
        int who=-1;
        while (!q.isEmpty()){
            int size=q.size();
            dist++;//注意如果这个dist写到for loop之后，那么在最后一层结束之后还会+1，就会多了一个1，但这样写dist需要初始成-1，否则如果b没有
            //邻居的话，dist也是1那就错了
            for (int i=0;i<size;i++){
                int cur=q.poll();
                who=cur;//反正最后的肯定是最远的

                Set<Integer> set=map.get(cur);
                if(!set.isEmpty()){
                    for (int nei:set){
                        if(!memo.contains(nei)){
                            q.offer(nei);
                            memo.add(nei);
                        }
                    }
                }
            }
        }
        return new int[]{dist,who};
    }
}
