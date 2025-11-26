package 灵神.图论.bfs;

import 灵神.单调栈.NextGreaterNodeInLinkedList1019;

import java.util.*;

public class ShortestDistanceAfterRoadAdditionQueriesI3243 {
    public static void main(String[] args) {
        int[][] q={{2,4},{0,2},{0,4}};
        System.out.println(shortestDistanceAfterQueries(5,q));
    }
    public static int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        Map<Integer, List<Integer>> map=new HashMap<>();
        for(int i=0;i<n-1;i++){
            map.put(i,new ArrayList<>());
            map.get(i).add(i+1);
        }
        int[] rs=new int[queries.length];
        for(int i=0;i<queries.length;i++){
            int a=queries[i][0];
            int b=queries[i][1];
            map.get(a).add(b);//题目说了没有重复的query
            rs[i]=bfs(n,map,new boolean[n]);
        }
        return rs;
    }
    static int bfs(int n,Map<Integer,List<Integer>> map,boolean[] memo){
        Queue<Integer> q=new LinkedList<>();
        q.offer(0);

        int dist=0;
        while (!q.isEmpty()){
            int size=q.size();
            for(int j=0;j<size;j++){
                int cur=q.poll();
                memo[cur]=true;
                if(cur==n-1){
                    return dist;
                }
                List<Integer> nei=map.get(cur);
                for(int ne:nei){
                    if(!memo[ne]){
                        q.offer(ne);
                    }
                }
            }
            dist++;
        }
        return 0;
    }
}
