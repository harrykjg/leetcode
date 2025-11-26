package 灵神.图论.拓扑排序;

import java.util.*;

public class CourseScheduleII210 {
    public static void main(String[] args) {
    int[][] pre=new int[][]{{1,0},{0,1}};
    int[] rs=findOrder(2,pre);
    for (int i:rs){
        System.out.println(i);
    }
    }
//还是得想清楚谁是出度谁是入度，比如【0,1】画图应该是1->0代表从1可以指向0，既先走1再走0.这样的话0的入度就为1.
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map=new HashMap<>();//某先修课为key，value为要先修他的课
        Map<Integer,Integer> indegree=new HashMap<>();//入度
        for(int i=0;i<prerequisites.length;i++){
            map.putIfAbsent(prerequisites[i][1],new HashSet<>());
            map.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree.put(prerequisites[i][0],indegree.getOrDefault(prerequisites[i][0],0)+1);
        }
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(!indegree.containsKey(i)){
                q.offer(i);
            }
        }

        int[] rs=new int[numCourses];
        int index=0;
        while (!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                int cur=q.poll();
                rs[index++]=cur;
                Set<Integer> neighbour=map.get(cur);
                if(neighbour==null){
                    continue;
                }
                for(int nei:neighbour){
                    indegree.put(nei,indegree.get(nei)-1);
                    if(indegree.get(nei)==0){
                        q.offer(nei);
                    }
                }
            }

        }
        if(index<numCourses){//这个容易漏，说明没有找到答案
            return new int[0];
        }

        return rs;
    }
}
