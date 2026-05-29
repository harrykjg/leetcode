package SomeInterviews.snowflake;

import java.util.*;

public class ParallelCourses1136 {

    //5/24/2026就是拓扑排序，和bfs那样记录dist
    public int minimumSemesters(int n, int[][] relations) {
        int[] degree=new int[n+1];
        Map<Integer, Set<Integer>> map=new HashMap<>();
        for (int i=0;i<relations.length;i++){
            int pre=relations[i][0];
            int post=relations[i][1];
            degree[post]++;
            map.putIfAbsent(pre,new HashSet<>());
            map.get(pre).add(post);
        }
        Queue<Integer> q=new LinkedList<>();
        for (int i=1;i<degree.length;i++){
            if(degree[i]==0){
                q.offer(i);
            }
        }
        int rs=0;
        int count=0;
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                int cur=q.poll();
                count++;
                Set<Integer> neighbour=map.get(cur);
                if(neighbour!=null){
                    for(int nei:neighbour){
                        degree[nei]--;
                        if(degree[nei]==0){
                            q.offer(nei);
                        }
                    }
                }
            }
            rs++;
        }
        if(count!=n){
            return -1;
        }
        return rs;
    }

}
