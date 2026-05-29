package linkedin;

import java.util.*;

public class CourseSchedule207 {
    static void main() {

    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree=new int[numCourses];
        Queue<Integer> q=new LinkedList<>();
        Map<Integer, Set<Integer>> map=new HashMap<>();
        for (int i=0;i<prerequisites.length;i++){
            indegree[prerequisites[i][0]]++;
            map.putIfAbsent(prerequisites[i][1],new HashSet<>());
            map.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for (int i=0;i<numCourses;i++){
            if(indegree[i]==0){
                q.offer(i);
            }
        }
        int count=0;
        while (!q.isEmpty()){
            int cur=q.poll();
            count++;
            Set<Integer> neighbour=map.get(cur);
            if(neighbour!=null){
                for (Integer nei:neighbour){
                    indegree[nei]--;
                    if(indegree[nei]==0){
                        q.offer(nei);
                    }
                }
            }

        }
        return count==numCourses;

    }
}
